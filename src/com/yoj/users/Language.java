package com.yoj.users;

public class Language {
    private String name;
    private Double ranking;

    public Language(String name, Double ranking) {
        this.name = name;
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public Double getRanking() {
        return ranking;
    }

    @Override
    public String toString() {
        return "Язык программирования - " + name + ", рейтинг  - " + ranking + "% опрошенных.";
    }
}
