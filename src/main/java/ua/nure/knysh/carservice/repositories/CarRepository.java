package ua.nure.knysh.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.knysh.carservice.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
