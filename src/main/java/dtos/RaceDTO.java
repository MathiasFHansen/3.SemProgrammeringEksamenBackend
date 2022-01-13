package dtos;

import entities.Race;

public class RaceDTO {

    private String name;
    private String date;
    private String time;
    private String location;

    public RaceDTO() {
    }


    public RaceDTO(Race race) {
        this.name = race.getName();
        this.date = race.getDate();
        this.time = race.getTime();
        this.location = race.getLocation();
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
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
