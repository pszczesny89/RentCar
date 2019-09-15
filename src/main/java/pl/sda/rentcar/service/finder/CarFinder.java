package pl.sda.rentcar.service.finder;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.rentcar.dtos.CarDTO;
import pl.sda.rentcar.entity.CarEntity;
import pl.sda.rentcar.repository.CarRepository;

@Service
@RequiredArgsConstructor
public class CarFinder {
    private final CarRepository repository;

    public CarDTO findById(Long id) {
        return repository.findById(id)
                .map(CarEntity::toDto)
                .orElseThrow(() -> new IllegalStateException("We don't have that car!"));
    }
}
