package pl.sda.rentcar.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.rentcar.dtos.DriverDTO;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;

    public DriverDTO toDTO() {
        return DriverDTO.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .build();
    }
}
