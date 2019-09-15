package pl.sda.rentcar.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.sda.rentcar.dtos.HireDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HireEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long carId;
    private Long driverId;
    private LocalDate hireDate;
    private LocalDate returnDate;
    private int mileage;

    public HireDTO toDto() {
        return HireDTO.builder()
                .id(id)
                .carId(carId)
                .driverId(driverId)
                .hireDate(hireDate)
                .returnDate(returnDate)
                .mileage(mileage)
                .build();
    }
}
