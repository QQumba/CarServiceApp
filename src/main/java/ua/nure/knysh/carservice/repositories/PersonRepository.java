package ua.nure.knysh.carservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nure.knysh.carservice.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
