package com.example.kiemdien;

import java.util.Date;

public class Student {
    private String id;
    private String name;
    private String checkIn;

    public Student() {

    }

    public Student(String id, String name, String checkIn) {
        this.id = id;
        this.name = name;
        this.checkIn = checkIn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
