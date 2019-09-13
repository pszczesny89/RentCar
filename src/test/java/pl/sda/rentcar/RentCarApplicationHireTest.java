package pl.sda.rentcar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sda.rentcar.controller.web.HireController;
import pl.sda.rentcar.dtos.HireDTO;
import pl.sda.rentcar.repository.HireRepository;
import pl.sda.rentcar.service.HireService;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RentCarApplicationHireTest {
    private MockMvc mockMvc;
    @Mock
    private HireService service;
    @Mock
    private HireRepository repository;
    @Captor
    private ArgumentCaptor<HireDTO> hireCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new HireController(service, repository))
                .build();
    }

    @Test
    public void getAllCarsTest() throws Exception {
        when(service.getAll()).thenReturn(Arrays.asList(new HireDTO(1L, 3L, 2L, LocalDate.of(2019, 12, 8), LocalDate.of(2019, 12, 11), 155),
                new HireDTO(5L, 4L, 1L, LocalDate.of(2019, 11, 5), LocalDate.of(2019, 12, 14), 1055)));


        mockMvc.perform(get("/hire"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[1].id").value(5))
                .andExpect(jsonPath("$.[0].carId").value(3))
                .andExpect(jsonPath("$.[1].carId").value(4))
                .andExpect(jsonPath("$.[0].driverId").value(2))
                .andExpect(jsonPath("$.[1].driverId").value(1))
//                .andExpect(jsonPath("$.[0].hireDate").value("2019-12-08"))
//                .andExpect(jsonPath("$.[1].hireDate").value(LocalDate.of(2019, 11, 5)))
//                .andExpect(jsonPath("$.[0].returnDate").value(LocalDate.of(2019, 12, 11)))
//                .andExpect(jsonPath("$.[1].returnDate").value(LocalDate.of(2019, 12, 14)))
                .andExpect(jsonPath("$.[0].mileage").value(155))
                .andExpect(jsonPath("$.[1].mileage").value(1055));
    }
}
