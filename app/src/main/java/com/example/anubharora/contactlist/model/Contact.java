package com.example.anubharora.contactlist.model;

/**
 * Created by anubharora on 5/2/17.
 */

public class Contact {
    public String name, number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
