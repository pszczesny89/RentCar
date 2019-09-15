package pl.sda.rentcar.entity;

import lombok.*;
import pl.sda.rentcar.dtos.CarDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String registration;
    private Long mileage;
    private Integer price;
    private boolean isAvailable;

    public CarDTO toDto() {
        return CarDTO.builder()
                .id(id)
                .brand(brand)
                .model(model)
                .registration(registration)
                .mileage(mileage)
                .price(price)
                .isAvailable(isAvailable)
                .build();
    }
}
