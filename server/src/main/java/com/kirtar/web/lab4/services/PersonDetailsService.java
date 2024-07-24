package com.kirtar.web.lab4.services;


import com.kirtar.web.lab4.models.Person;
import com.kirtar.web.lab4.repositories.PeopleRepository;
import com.kirtar.web.lab4.services.exceptions.RegistrationException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(s);
        if (person.isEmpty()) throw new UsernameNotFoundException("User not found");
        return new User(person.get().getUsername(), person.get().getPassword(), new ArrayList<>());
    }

}
