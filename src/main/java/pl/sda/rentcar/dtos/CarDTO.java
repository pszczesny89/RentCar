package pl.sda.rentcar.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDTO {
    private Long id;
    private String brand;
    private String model;
    private String registration;
    private Long mileage;
    private Integer price;
    private boolean isAvailable;
}


