package com.entities.owners;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class OwnerDto {
    private String name;
    private Date birthdate;
    private String password;
    private String role;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = birthdate != null ? dateFormat.format(birthdate) : null;

        return "{\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"birthdate\": \"" + formattedDate + "\",\n" +
                "  \"password\": \"" + password + "\",\n" +
                "  \"role\": \"" + role + "\"\n" +
                "}";

    }
}