package pl.sda.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.rentcar.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
