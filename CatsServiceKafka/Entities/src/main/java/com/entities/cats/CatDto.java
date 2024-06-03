package com.entities.cats;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class CatDto {
    private String name;
    private Date birthdate;
    private String breed;
    private String color;
    private Long ownerId;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = birthdate != null ? dateFormat.format(birthdate) : null;

        return "{\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"birthdate\": \"" + formattedDate + "\",\n" +
                "  \"breed\": \"" + breed + "\",\n" +
                "  \"color\": \"" + color + "\",\n" +
                "  \"ownerId\": \"" + ownerId + "\"\n" +
                "}";

    }
}

