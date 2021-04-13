package tqs.ua.carInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CarRepository  extends JpaRepository<Car, Long> {
    public Car findByCarId(Long id);
    public List<Car> findAll();
}
