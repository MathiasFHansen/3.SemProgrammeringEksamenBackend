package dtos;

import entities.Car;

import java.util.ArrayList;
import java.util.List;

public class CarsDTO {
    List<CarDTO> all = new ArrayList<>();

    public CarsDTO(List<Car> carEntities) {
        carEntities.forEach((c) -> {
            all.add(new CarDTO(c));
        });
    }

    public List<CarDTO> getAll(){
        return all;
    }
}
