package ua.nure.knysh.carservice.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "car")
public class Car {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String modelName;

    @Column(nullable = false)
    private Integer maxSpeed = 0;

    @Column(nullable = false)
    private Boolean hasAutomaticTransmission = false;
}
