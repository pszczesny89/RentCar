package pl.sda.rentcar.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HireDTO {
    private Long id;
    private Long carId;
    private Long driverId;
    private LocalDate hireDate;
    private LocalDate returnDate;
    private int mileage;
}
