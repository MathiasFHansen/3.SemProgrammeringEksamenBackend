package dtos;

import entities.Car;

public class CarDTO {

    private String name;
    private String brand;
    private String model;
    private int year;

    public CarDTO() {
    }

    public CarDTO(Car car) {
        this.name = car.getName();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.year = car.getYear();
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
}
