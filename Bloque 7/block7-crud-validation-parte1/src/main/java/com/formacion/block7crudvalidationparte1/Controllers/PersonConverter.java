package com.formacion.block7crudvalidationparte1.Controllers;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.dto.PersonDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonConverter {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Person convertToEntity(PersonDTO dto) throws Exception {
        Person person = new Person();
        person.setIdPerson(dto.getIdPerson());
        person.setUsuario(dto.getUsuario());
        person.setPassword(dto.getPassword());
        person.setName(dto.getName());
        person.setSurname(dto.getSurname());
        person.setCompanyEmail(dto.getCompanyEmail());
        person.setPersonalEmail(dto.getPersonalEmail());
        person.setCity(dto.getCity());
        person.setActive(dto.isActive());
        person.setCreatedDate(DATE_FORMAT.parse(String.valueOf(dto.getCreatedDate())));
        person.setImageUrl(dto.getImageUrl());
        person.setTerminationDate(DATE_FORMAT.parse(String.valueOf(dto.getTerminationDate())));
        return person;
    }

    public static PersonDTO convertToDTO(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setIdPerson(person.getIdPerson());
        dto.setUsuario(person.getUsuario());
        dto.setPassword(person.getPassword());
        dto.setName(person.getName());
        dto.setSurname(person.getSurname());
        dto.setCompanyEmail(person.getCompanyEmail());
        dto.setPersonalEmail(person.getPersonalEmail());
        dto.setCity(person.getCity());
        dto.setActive(person.isActive());
        //dto.setCreatedDate(DATE_FORMAT.format(person.getCreatedDate()));
        dto.setImageUrl(person.getImageUrl());
        if (person.getTerminationDate() != null) {
            //dto.setTerminationDate(DATE_FORMAT.format(person.getTerminationDate()));
        }
        return dto;
    }

}
