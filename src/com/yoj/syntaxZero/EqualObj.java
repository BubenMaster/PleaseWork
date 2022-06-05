package com.yoj.syntaxZero;

public class EqualObj {
    private String model;
    private String color;
    private int price;

    public EqualObj(String model, String color, int price) {
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof EqualObj)) return false;

        EqualObj equalObj = (EqualObj) obj;
        //Сревнение цен
        if (!(this.price == equalObj.price)) return false;

        //сравнение цветов и кузовов
        return (stringMatch(this.color, equalObj.color) && stringMatch(this.model, equalObj.model));



    }

    public boolean stringMatch(String s1, String s2) {
        boolean match;
        if (s1 == null) {
            match = (s2 == null);
        }
        else {
            if (s2 == null) return false;
            match = (s1.equals(s2));
        }
        return match;
    }

    //напишите тут ваш код

    public static void main(String[] args) {
        EqualObj equalObj1 = new EqualObj("X", "Blacky", 999);
        EqualObj equalObj2 = new EqualObj("X", "Blacky", 999);

        System.out.println(equalObj1.equals(equalObj2));
    }

}