package entities;

import dtos.RaceDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "race")
@Entity
public class Race implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String date;
    private String time;
    private String location;

    @ManyToMany(mappedBy = "raceList", cascade = CascadeType.ALL)
    private List<Car> carList = new ArrayList<>();

    public Race() {
    }

    public Race(RaceDTO raceDTO) {
        this.name = raceDTO.getName();
        this.date = raceDTO.getDate();
        this.time = raceDTO.getTime();
        this.location = raceDTO.getLocation();
    }

    public Race(String name, String date, String time, String location) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public void addCar(Car car){
        carList.add(car);
        car.getRaceList().add(this);
    }
}