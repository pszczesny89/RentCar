package pl.sda.rentcar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sda.rentcar.controller.CarController;
import pl.sda.rentcar.dtos.CarDTO;
import pl.sda.rentcar.repository.CarRepository;
import pl.sda.rentcar.service.CarService;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RentcarApplicationCarTests {
    private MockMvc mockMvc;
    @Mock
    private CarService service;
    @Mock
    private CarRepository repository;
    @Captor
    private ArgumentCaptor<CarDTO> carCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new CarController(service, repository))
                .build();
    }

    @Test
    public void getAllCarsTest() throws Exception {
        when(service.getAll()).thenReturn(Arrays.asList(new CarDTO(1L, "Skoda", "Fabia", "CDE 3456", 123000L, true),
                new CarDTO(5L, "Mercedes", "S", "NJD 7658", 612300L, false)));

        mockMvc.perform(get("/cars"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[1].id").value(5))
                .andExpect(jsonPath("$.[0].brand").value("Skoda"))
                .andExpect(jsonPath("$.[1].brand").value("Mercedes"))
                .andExpect(jsonPath("$.[0].model").value("Fabia"))
                .andExpect(jsonPath("$.[1].model").value("S"))
                .andExpect(jsonPath("$.[0].registration").value("CDE 3456"))
                .andExpect(jsonPath("$.[1].registration").value("NJD 7658"));
    }

    @Test
    public void getOneCarTest() throws Exception {
        when(service.getOne(13l)).thenReturn(new CarDTO(13L, "Skoda", "Fabia", "CDE 3456", 123000L, true));

        mockMvc.perform(get("/cars/13"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(13L))
                .andExpect(jsonPath("$.brand").value("Skoda"))
                .andExpect(jsonPath("$.model").value("Fabia"))
                .andExpect(jsonPath("$.registration").value("CDE 3456"))
                .andExpect(jsonPath("$.mileage").value(123000L))
                .andExpect(jsonPath("$.available").value(true));
    }

    @Test
    public void addEmployeeToService() throws Exception {
        mockMvc.perform(put("/cars")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"brand\": \"Skoda\", \"model\": \"Fabia\", \"registration\": \"CDE 3456\", \"mileage\": 12000, \"available\": true}"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service).add(carCaptor.capture());
        assertEquals("Skoda", carCaptor.getValue().getBrand());
        assertEquals("Fabia", carCaptor.getValue().getModel());
        assertEquals("CDE 3456", carCaptor.getValue().getRegistration());
        assertEquals("CDE 3456", carCaptor.getValue().getRegistration());
        assertEquals(Long.valueOf(12000), carCaptor.getValue().getMileage());
        assertTrue(carCaptor.getValue().isAvailable());
    }

    @Test
    public void RemoveCarTest() throws Exception {
        mockMvc.perform(delete("/cars/13"));
        verify(service).removeCar(13L);
    }
}
