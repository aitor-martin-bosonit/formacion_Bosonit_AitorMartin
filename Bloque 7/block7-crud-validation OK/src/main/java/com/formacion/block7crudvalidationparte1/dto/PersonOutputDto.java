package com.formacion.block7crudvalidationparte1.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonOutputDto {

    private int idPerson;
    private String usuario;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private String createdDate;
    private String terminationDate;

}
