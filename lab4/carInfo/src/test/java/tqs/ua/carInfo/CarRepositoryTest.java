package tqs.ua.carInfo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    
    @Test
    public void whenFindByCarId_thenReturnCar(){
        Car audi = new Car("Audi","A5");
        entityManager.persistAndFlush(audi);

        Car found = carRepository.findByCarId(Long.valueOf("1"));
        assertThat(found.getModel()).isEqualTo(audi.getModel());
    }

    @Test
    public void whenInvalidCarId_thenReturnNull(){
        Car nonExistant = carRepository.findByCarId(Long.valueOf("-1"));
        assertThat(nonExistant).isNull();
    }

}