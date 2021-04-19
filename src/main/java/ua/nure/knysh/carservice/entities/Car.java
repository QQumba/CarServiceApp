package ua.nure.knysh.carservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private Long personId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "car_factory",
            joinColumns = @JoinColumn(name = "car_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "factory_id",
                    referencedColumnName = "id"))
    private List<Factory> factories;

    public Long getPersonId() {
        if(person != null){
            return this.person.getId();
        }
        return null;
    }

    @PreRemove
    private void preRemove(){
        factories.forEach(factory -> factory.getCars().remove(this));
    }
}
