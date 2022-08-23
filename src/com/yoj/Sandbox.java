package com.yoj;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoj.users.Cars;
import com.yoj.users.User;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Sandbox {

    public static void main(String[] args) {

        User user = new User();

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(writer,user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(writer);

        StringReader reader = new StringReader(writer.toString());

        User user2;
        try {
            user2 = mapper.readValue(reader, user.getClass());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(user2);
    }
}
