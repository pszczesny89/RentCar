package pl.sda.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.rentcar.entity.Drivers;

public interface DriverRepository extends JpaRepository<Drivers, Long> {
}
