package com.yoj.core.leve10.part2.javarushRnW;

import java.io.*;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class User {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private boolean isMale;
    private Country country;

    public User(String firstName, String lastName, Date birthDate, boolean isMale, Country country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.isMale = isMale;
        this.country = country;
    }

    public User() {
        this.firstName = null;
        this.lastName = null;
        this.birthDate = null;
        this.isMale = false;
        this.country = null;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void save(OutputStream output) {
        Writer writer = new BufferedWriter( new OutputStreamWriter(output));
        try {
            writer.write(firstName + "\n");
            writer.write(lastName + "\n");
            writer.write(birthDate.getTime() + "\n");
            writer.write(isMale + "\n");
            writer.write(country + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(BufferedReader reader) {

        String countryString = null;
        try {
            firstName = reader.readLine();
            lastName = reader.readLine();
            birthDate = new Date(Long.parseLong(reader.readLine()));
            isMale = Boolean.parseBoolean(reader.readLine());
            countryString = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //parsing country
        for (Country elem: Country.values()) {
            if (elem.getDisplayName().equalsIgnoreCase(countryString)) country = elem;
        }
    }

    public static enum Country {
        UKRAINE("Ukraine"),
        RUSSIA("Russia"),
        OTHER("Other");

        private String name;

        private Country(String name) {
            this.name = name;
        }

        public String getDisplayName() {
            return this.name;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (isMale != user.isMale) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (birthDate != null ? !birthDate.equals(user.birthDate) : user.birthDate != null) return false;
        return country == user.country;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (isMale ? 1 : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
