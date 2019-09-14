package pl.sda.rentcar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"application.properties", "application-ext.properties"}, ignoreResourceNotFound = true)
public class RentcarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentcarApplication.class, args);
    }
}
