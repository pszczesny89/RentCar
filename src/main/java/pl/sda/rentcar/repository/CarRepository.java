package pl.sda.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.rentcar.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

}
