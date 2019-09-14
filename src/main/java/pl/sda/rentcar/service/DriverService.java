package pl.sda.rentcar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.rentcar.dtos.DriverDTO;
import pl.sda.rentcar.entity.Driver;
import pl.sda.rentcar.repository.DriverRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    @Transactional
    public Driver save(DriverDTO dto) {
        Driver entity = toEntity(dto);

        entity = driverRepository.save(entity);

        return toDTO(entity);
    }

    private Driver toDTO(Driver entity) {
        return Driver.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }

    private Driver toEntity(DriverDTO dto) {
        return Driver.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    @Transactional
    public List<DriverDTO> getAll() {
        return driverRepository.findAll()
                .stream()
                .map(e -> {
                    return new DriverDTO(e.getId(), e.getName(), e.getSurname(), e.getEmail(), e.getPassword());
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public Driver getOne(Long id) {
        return driverRepository.findById(id)
                .map(e -> toDTO(e))
                .orElse(null);
    }

    @Transactional
    public void remove(Long id) {
        driverRepository.deleteById(id);
    }

}
