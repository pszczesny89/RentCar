package pl.sda.rentcar.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DriverDTO {

private Long id;
private String name;
private String surname;
private String email;
private String password;
}
