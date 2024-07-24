package com.kirtar.web.lab4.controllers;


//import com.kirtar.web.lab4.dto.AuthenticationDTO;
import com.kirtar.web.lab4.dto.PersonDTO;
import com.kirtar.web.lab4.models.Person;
import com.kirtar.web.lab4.security.JWTUtil;
import com.kirtar.web.lab4.services.RegistrationService;
import com.kirtar.web.lab4.services.exceptions.RegistrationException;
import com.kirtar.web.lab4.utils.PersonValidator;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                                   BindingResult bindingResult) throws RegistrationException {
        Person person = convertToPerson(personDTO);

        personValidator.validate(person, bindingResult);

//        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getAllErrors());
//            throw new ValidationException("Ошибка!");
//        }

        registrationService.register(person);

        String token = jwtUtil.generateToken(person.getUsername());
        Map<String,String> response = new HashMap<>();
        response.put("jwt",token);
        response.put("username",personDTO.getUsername());
        return response;
    }

    @PostMapping("status")
    public ResponseEntity<?> status() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody PersonDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        Map<String,String> response = new HashMap<>();
        response.put("jwt",token);
        response.put("username",authenticationDTO.getUsername());
        return response;
    }

    public Person convertToPerson(PersonDTO personDTO) {
        return this.modelMapper.map(personDTO, Person.class);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleBadCredentialsException(BadCredentialsException exp) {
        return "Такого пользователя не существует. Проверьте правильность имени и пароля";
    }

    @ExceptionHandler(RegistrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleRegistrationException(RegistrationException exp) {
        return "Что-то пошло не так... Учётная запись не была создана";
    }
}


