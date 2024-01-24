package com.kirtar.web.lab4.controllers;


import java.util.List;

import com.kirtar.web.lab4.dto.HitDTO;
import com.kirtar.web.lab4.models.Hit;
import com.kirtar.web.lab4.services.HitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;


@RestController
@RequestMapping("/main")
public class HitController {

    @Autowired
    HitService hitService;

    @GetMapping("hits")
    public ResponseEntity<List<HitDTO>> hitsHistoryData(@AuthenticationPrincipal UserDetails userDetails) {

        return new ResponseEntity<>(hitService.getHitsForPerson(userDetails).stream().map(hit -> new HitDTO(hit)).toList(), HttpStatus.OK);

    }

    @PostMapping("hits")
    public ResponseEntity<?> createHit(@Valid @RequestBody HitDTO hitDTO, BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails) throws ValidationException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getFieldError().getDefaultMessage());
        }


        long currentTime = System.nanoTime();
        hitDTO.setResult(checkHit(hitDTO));
        hitDTO.setExecutionTime(System.nanoTime() - currentTime);
        Hit hit = hitService.saveHit(userDetails, hitDTO.getX(), hitDTO.getY(), hitDTO.getR(), hitDTO.getResult(),hitDTO.getExecutionTime());
        return new ResponseEntity<>(new HitDTO(hit), HttpStatus.CREATED);

    }

    @DeleteMapping("hits")
    public ResponseEntity<?> clearHits(@AuthenticationPrincipal UserDetails userDetails) {

        hitService.clearHits(userDetails);
        return ResponseEntity.ok().build();

    }

    public boolean checkHit(HitDTO hitDTO) {

        Double x = hitDTO.getX();
        Double y = hitDTO.getY();
        Double r = hitDTO.getR();
        boolean rectangle = (x<=0 && y<=0 && x>=-r && y>=-r/2);
        boolean triangle =  (x<=0 && y>=0 && x>=-r/2 && y<=r && y<=2*x+r);
        boolean quadrant = (x>=0 && x<=r && y>=-r && y<=0 && x*x+y*y<=r*r);
        if (rectangle || triangle || quadrant) {
            return true;
        } else {
            return false;
        }

    }
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleValidationException(ValidationException exp) {
        return exp.getMessage();
    }

}