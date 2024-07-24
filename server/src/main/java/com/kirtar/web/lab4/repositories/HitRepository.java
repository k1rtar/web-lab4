package com.kirtar.web.lab4.repositories;

import com.kirtar.web.lab4.models.Hit;
import com.kirtar.web.lab4.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HitRepository extends JpaRepository<Hit, Long> {

    List<Hit> findByPerson(Person person);

}
