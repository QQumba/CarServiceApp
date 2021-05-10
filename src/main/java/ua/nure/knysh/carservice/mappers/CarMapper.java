package ua.nure.knysh.carservice.mappers;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;
import ua.nure.knysh.carservice.contract.CarDto;
import ua.nure.knysh.carservice.entities.Car;

import java.util.Optional;

@Service
public class CarMapper {
    public CarDto map (Car car){
        return new CarDto()
                .setId(car.getId())
                .setModelName(car.getModelName())
                .setMaxSpeed(car.getMaxSpeed())
                .setHasAutomaticTransmission(car.getHasAutomaticTransmission())
                .setPersonEmail(car.getPersonEmail())
                .setPersonId(car.getPersonId());
    }

    public Optional<CarDto> map(Optional<Car> car){
        if(car.isEmpty()){
            return Optional.empty();
        }
        var carDto = map(car.get());
        return Optional.of(carDto);
    }

    public Car map (CarDto carDto){
        Car car = new Car()
                .setId(carDto.getId())
                .setModelName(carDto.getModelName())
                .setMaxSpeed(carDto.getMaxSpeed())
                .setHasAutomaticTransmission(carDto.getHasAutomaticTransmission())
                .setPersonEmail(carDto.getPersonEmail())
                .setPersonId(carDto.getPersonId());

        System.out.println("mapped car personId: " + car.getPersonId());
        return car;
    }



    public Car mapToEntity(CarDto source, Car destination){
        throw new NotYetImplementedException();
    }
}
