package com.yoj.collections.level3.part5.date_serializtion;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Event {
    public String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    public Date eventDate;

    public Event(String name) {
        this.name = name;
        eventDate = new Date();
    }
}
