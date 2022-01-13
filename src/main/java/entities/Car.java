package entities;

import dtos.CarDTO;
import dtos.RaceDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "car")
@Entity
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String brand;
    private String model;
    private int year;

    @ManyToMany
    private List<Race> raceList = new ArrayList<>();

    public Car() {
    }

    public Car(CarDTO carDTO) {
        this.name = carDTO.getName();
        this.brand = carDTO.getBrand();
        this.model = carDTO.getModel();
        this.year = carDTO.getYear();
    }

    public Car(String name, String brand, String model, int year) {
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Race> getRaceList() {
        return raceList;
    }

    public void setRaceList(List<Race> raceList) {
        this.raceList = raceList;
    }

    public void addRace(Race carRace) {
        raceList.add(carRace);
    }
}