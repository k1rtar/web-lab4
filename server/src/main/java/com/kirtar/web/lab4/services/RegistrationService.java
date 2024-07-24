package com.kirtar.web.lab4.services;

import com.kirtar.web.lab4.models.Person;
import com.kirtar.web.lab4.repositories.PeopleRepository;
import com.kirtar.web.lab4.services.exceptions.RegistrationException;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class RegistrationService {

        private final PeopleRepository peopleRepository;
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
            this.peopleRepository = peopleRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @Transactional
        public void register(Person person) throws RegistrationException {
            if (peopleRepository.findByUsername(person.getUsername()).isPresent()) {
                throw new RegistrationException("User already exists");
            }
            System.out.println(person.getUsername());
            System.out.println(person.getPassword());
            person.setPassword(passwordEncoder.encode(person.getPassword()));
            peopleRepository.save(person);
        }
}
