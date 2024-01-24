package com.kirtar.web.lab4.dto;


import com.kirtar.web.lab4.models.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private  String username;
    private String password;
    public PersonDTO(Person person){
        this.id = person.getId();
        this.username = person.getUsername();
        this.password = person.getPassword();

    }
}
