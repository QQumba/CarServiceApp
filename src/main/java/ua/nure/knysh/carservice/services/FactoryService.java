package ua.nure.knysh.carservice.services;

import org.springframework.stereotype.Service;
import ua.nure.knysh.carservice.entities.Factory;

import java.util.List;

@Service
public interface FactoryService extends CRUDService<Factory>{
    List<Factory> getCarFactories(Long carId);
    void addCar(Long factoryId, Long carId);
}
