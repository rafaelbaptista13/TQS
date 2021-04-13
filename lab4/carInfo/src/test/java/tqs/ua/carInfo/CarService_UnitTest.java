package tqs.ua.carInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarService_UnitTest {
    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    
    @BeforeEach
    public void setUp(){
        Car audi = new Car("Audi", "A5");
        Car bmw = new Car("BMW", "420d");
        Car mercedes = new Car("Mercedes", "C220");


        Mockito.when(carRepository.findByCarId(Long.valueOf("1"))).thenReturn(audi);
        Mockito.when(carRepository.findByCarId(Long.valueOf("2"))).thenReturn(bmw);
        Mockito.when(carRepository.findByCarId(Long.valueOf("3"))).thenReturn(mercedes);
        Mockito.when(carRepository.findByCarId(Long.valueOf("-1"))).thenReturn(null);
    }

    
    @Test
    public void whenValidId_thenCarShouldBeFound() {
        Car found = carService.getCarDetails(Long.valueOf("1"));
        assertThat(found.getModel()).isEqualTo("A5");
        found = carService.getCarDetails(Long.valueOf("2"));
        assertThat(found.getModel()).isEqualTo("420d");
        found = carService.getCarDetails(Long.valueOf("3"));
        assertThat(found.getModel()).isEqualTo("C220");
    }

    @Test
    public void whenInvalidId_thenCarShouldNotBeFound() {
        Car found = carService.getCarDetails(Long.valueOf("-1"));
        assertThat(found).isNull();

        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findByCarId(Long.valueOf("-1"));
        Mockito.reset(carRepository);
    }
    
}