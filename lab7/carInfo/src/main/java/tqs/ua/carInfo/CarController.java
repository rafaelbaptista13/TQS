package tqs.ua.carInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@RequestBody Car car){
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carManagerService.save(car);

        return new ResponseEntity<>(saved, status);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars(){
        return carManagerService.getAllCars();
    }

    @GetMapping("/car")
    public ResponseEntity<Car> getCarById(@RequestParam("carId") Long carId){   
        Car car = carManagerService.getCarDetails(carId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(car, status);
    }

}
