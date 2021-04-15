package tqs.ua.carInfo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.*;
import java.io.IOException;
import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource( locations = "application-integrationtest.properties")
class CarRestControllerTemplateIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void resetDB(){carRepository.deleteAll();}
    
    @Test
    public void whenValidInput_thenCreateCar() throws IOException, Exception{
        Car audi = new Car("Audi","A5");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/cars", audi, Car.class);
        
        List<Car> found = carRepository.findAll();
        assertThat(found).extracting(Car::getModel).containsOnly("A5");
    }

    @Test
    public void givenCar_whenGetCar_thenStatusOK() throws Exception{
        Car audi = new Car("Audi","A5");
        carRepository.saveAndFlush(audi);
        Car bmw = new Car("BMW","420d");
        carRepository.saveAndFlush(bmw);

        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/cars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("A5", "420d");
    }
    
}