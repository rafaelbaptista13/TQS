package tqs.ua.carInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.testcontainers.junit.jupiter.Testcontainers;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@Testcontainers
@WebMvcTest(CarController.class)
class CarController_WithMockServiceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService service;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    public void whenPostCar_thenCreateCar() throws Exception {
        Car audi = new Car("Audi", "A5");

        given(service.save(Mockito.any())).willReturn(audi);
        //when(service.save(Mockito.any())).thenReturn(audi);

        mvc.perform(post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(audi)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is("A5")))
                .andExpect(jsonPath("$.maker", is("Audi")));
        verify(service, VerificationModeFactory.times(1)).save(Mockito.any());
        reset(service);
    }

    @Test
    public void givenCar_whenGetCar_thenReturnJson() throws Exception{
        Car audi = new Car("Audi", "A5");

        given(service.getCarDetails(Long.valueOf("1"))).willReturn(audi);
        //when(service.getCarDetails(Long.valueOf("1"))).thenReturn(audi);

        mvc.perform(get("/api/car").contentType(MediaType.APPLICATION_JSON).param("carId","1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model", is("A5")))
                .andExpect(jsonPath("$.maker", is("Audi")));
        verify(service, VerificationModeFactory.times(1)).getCarDetails(Long.valueOf("1"));
        reset(service);
    }
}