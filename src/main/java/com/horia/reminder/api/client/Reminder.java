package com.horia.reminder.api.client;

import java.util.Date;

public class Reminder {

    private String name;

    private String description;

    private String date;

    private String phoneNumber;

    public Reminder() {}

    public Reminder(String name, String description, String date, String phoneNumber) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
