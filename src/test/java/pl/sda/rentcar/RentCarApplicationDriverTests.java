package pl.sda.rentcar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sda.rentcar.controller.DriverController;
import pl.sda.rentcar.dtos.DriverDTO;
import pl.sda.rentcar.entity.Driver;
import pl.sda.rentcar.repository.DriverRepository;
import pl.sda.rentcar.service.DriverService;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RentCarApplicationDriverTests {

    private MockMvc mockMvc;
    @Mock
    private DriverService driverService;

    @Mock
    private DriverRepository driverRepository;

    @Captor
    private ArgumentCaptor<DriverDTO> driverCaptor;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new DriverController(driverService, driverRepository))
                .build();
    }

    @Test
    public void getAllDriversTest() throws Exception {
        when(driverService.getAll()).thenReturn(Arrays.asList(new DriverDTO(1L,"Robert", "Lewandowski", "robertlewandowski@gmail.com", "123456"),
                new DriverDTO(2L, "Hubert", "Wagner", "hubertwagne@wp.pl", "1qaz")));

        mockMvc.perform(get("/driver"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[0].name").value("Robert"))
                .andExpect(jsonPath("$.[1].name").value("Hubert"))
                .andExpect(jsonPath("$.[0].surname").value("Lewandowski"))
                .andExpect(jsonPath("$.[1].surname").value("Wagner"))
                .andExpect(jsonPath("$.[0].email").value("robertlewandowski@gmail.com"))
                .andExpect(jsonPath("$.[1].email").value("hubertwagne@wp.pl"))
                .andExpect(jsonPath("$.[0].password").value("123456"))
                .andExpect(jsonPath("$.[1].password").value("1qaz"));
    }

    @Test
    public void getOneDriverTest() throws Exception {
        when(driverService.getOne(5L)).thenReturn(new Driver(5L, "Kazimierz", "Wielki", "kazimierzwielki@onet.pl", "123000L"));

        mockMvc.perform(get("/driver/5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5L))
                .andExpect(jsonPath("$.name").value("Kazimierz"))
                .andExpect(jsonPath("$.surname").value("Wielki"))
                .andExpect(jsonPath("$.email").value("kazimierzwielki@onet.pl"))
                .andExpect(jsonPath("$.password").value("123000L"));

    }

    @Test
    public void addDriverToService() throws Exception {
        mockMvc.perform(post("/driver")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"name\": \"Adam\", \"surname\": \"Malysz\", \"email\": \"adammalysz@gmail.com\", \"password\": \"qwerty\"}"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(driverService).save(driverCaptor.capture());
        assertEquals("Adam", driverCaptor.getValue().getName());
        assertEquals("Malysz", driverCaptor.getValue().getSurname());
        assertEquals("adammalysz@gmail.com", driverCaptor.getValue().getEmail());
        assertEquals("qwerty", driverCaptor.getValue().getPassword());

    }

    @Test
    public void RemoveDriverTest() throws Exception {
        mockMvc.perform(delete("/driver/13"));
        verify(driverService).remove(13L);
    }
}

