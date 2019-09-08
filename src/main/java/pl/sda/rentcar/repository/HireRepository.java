package pl.sda.rentcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.rentcar.entity.HireEntity;

public interface HireRepository extends JpaRepository<HireEntity, Long> {
    HireEntity getById(Long id);
}
