package com.yoj.core.level7.crud;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Person {
    private String name;
    private Sex sex;
    private Date birthDate;

    private Person(String name, Sex sex, Date birthDate) {
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public static Person createMale(String name, Date birthDate) {
        return new Person(name, Sex.MALE, birthDate);
    }

    public static Person createFemale(String name, Date birthDate) {
        return new Person(name, Sex.FEMALE, birthDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String retName, retSex, retBirthDate;
        if (name == null) retName = "no data";
            else retName = name;

        if (sex == null) retSex = "_";
        else retSex = sexConvert(sex);

        if (birthDate == null) retBirthDate = "_";
        else retBirthDate = format.format(birthDate);

        return retName +
                " " + retSex +
                " " + retBirthDate;
    }

    private static String sexConvert (Sex sex) {
        switch (sex) {
            case FEMALE : return "ж";
            case MALE: return "м";
            default: return "";
        }
    }
}
