package com.kirtar.web.lab4.services;



import com.kirtar.web.lab4.models.Hit;
import com.kirtar.web.lab4.models.Person;
import com.kirtar.web.lab4.repositories.HitRepository;
import com.kirtar.web.lab4.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import java.sql.Timestamp;
import java.util.List;

@Service
public class HitService {

    @Autowired
    private HitRepository hitRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    public List<Hit> getHitsForPerson(UserDetails userDetails) {
        Person person = peopleRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User by username not found"));

        return hitRepository.findByPerson(person);
    }


    public Hit saveHit(UserDetails userDetails, Double x, Double y, Double r, boolean result,long executionTime) {
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Person person = peopleRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User by username not found"));

        Hit hit = new Hit();
        hit.setPerson(person);
        hit.setX(x);
        hit.setY(y);
        hit.setR(r);
        hit.setResult(result);
        hit.setExecutionTime(executionTime);
        hit.setTime(new Timestamp(System.currentTimeMillis()));

        return hitRepository.save(hit);
    }

    public void clearHits(UserDetails userDetails) {
        hitRepository.deleteAll(getHitsForPerson(userDetails));
    }


}