package ua.nure.knysh.carservice.contract;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ua.nure.knysh.carservice.entities.Factory;
import ua.nure.knysh.carservice.entities.Person;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CarDto {
    private Long id;
    private String modelName;
    private Integer maxSpeed = 0;
    private Boolean hasAutomaticTransmission = false;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long personId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String personEmail;
}
