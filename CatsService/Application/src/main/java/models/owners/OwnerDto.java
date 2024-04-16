package models.owners;

import lombok.Data;

import java.util.Date;

@Data
public class OwnerDto {
    private Long id;
    private String name;
    private Date birthDate;
    private String password;
    private String role;
}
