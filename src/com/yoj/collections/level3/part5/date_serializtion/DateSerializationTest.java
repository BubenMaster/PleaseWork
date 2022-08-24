package com.yoj.collections.level3.part5.date_serializtion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DateSerializationTest {
    public static void main(String[] args) throws JsonProcessingException {
        Event event = new Event("event#1");

        String result = new ObjectMapper().writeValueAsString(event);

        System.out.println(result);
    }
}
