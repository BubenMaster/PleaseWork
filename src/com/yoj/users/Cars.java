package com.yoj.users;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Cars {
    private String model;
    private boolean isElectric;

    public Cars() {
    }

    public Cars(String model) {
        this.model = model;
    }

    public Cars(String model, boolean isElectric) {
        this.model = model;
        this.isElectric = isElectric;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    @Override
    public String toString() {
        return "Car " + model + ", electric - " + isElectric;
    }
}
