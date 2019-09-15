package pl.sda.rentcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.rentcar.dtos.DriverDTO;
import pl.sda.rentcar.entity.Driver;
import pl.sda.rentcar.repository.DriverRepository;

@Service
@RequiredArgsConstructor
public class DriverFinder {
    private final DriverRepository driverRepository;

    public DriverDTO findById(Long id) {
        return driverRepository.findById(id)
                .map(Driver::toDTO)
                .orElseThrow(() -> new IllegalStateException("Nie istnieje taki kierowca"));
    }
}
