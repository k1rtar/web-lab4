package com.kirtar.web.lab4.dto;


import com.kirtar.web.lab4.models.Hit;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class HitDTO {

    @NotNull
    @Min(value = -3, message = "Значение X должно быть больше или равно -3.")
    @Max(value = 3, message = "Значение X должно быть меньше или равно 3.")
    private Double x;

    @NotNull
    @Min(value = -5, message = "Значение Y должно быть больше или равно -5.")
    @Max(value = 5, message = "Значение Y должно быть меньше или равно 5.")
    private Double y;

    @NotNull
    @Min(value = -3, message = "Значение R должно быть больше -3.")
    @Max(value = 3, message = "Значение R должно быть меньше или равно 3.")
    private Double r;
    private Boolean result;
    private Long executionTime;
    private Timestamp time;
    private PersonDTO user;


    public HitDTO(Hit hit) {
        this.x = hit.getX();
        this.y = hit.getY();
        this.r = hit.getR();
        this.result = hit.getResult();
        this.executionTime = hit.getExecutionTime();
        this.time = hit.getTime();
        this.user = new PersonDTO(hit.getPerson());
    }

}