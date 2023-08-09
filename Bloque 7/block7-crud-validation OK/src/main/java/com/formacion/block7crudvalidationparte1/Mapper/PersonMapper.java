package com.formacion.block7crudvalidationparte1.Mapper;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.dto.PersonInputDto;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PersonMapper {

    public Person toEntity(PersonInputDto personInputDto) {
        Person person = new Person();
        person.setUsuario(personInputDto.getUsuario());
        person.setPassword(personInputDto.getPassword());
        person.setName(personInputDto.getName());
        person.setSurname(personInputDto.getSurname());
        person.setCompanyEmail(personInputDto.getCompanyEmail());
        person.setPersonalEmail(personInputDto.getPersonalEmail());
        person.setCity(personInputDto.getCity());
        person.setActive(personInputDto.isActive());
        person.setCreatedDate(personInputDto.getCreatedDate().toInstant());
        person.setImageUrl(personInputDto.getImageUrl());
        person.setTerminationDate(personInputDto.getTerminationDate().toInstant());
        return person;
    }

    public PersonInputDto toDTO(Person person) {
        PersonInputDto personInputDto = new PersonInputDto();
        personInputDto.setIdPerson(person.getIdPerson());
        personInputDto.setUsuario(person.getUsuario());
        personInputDto.setPassword(person.getPassword());
        personInputDto.setName(person.getName());
        personInputDto.setSurname(person.getSurname());
        personInputDto.setCompanyEmail(person.getCompanyEmail());
        personInputDto.setPersonalEmail(person.getPersonalEmail());
        personInputDto.setCity(person.getCity());
        personInputDto.setActive(person.isActive());
        personInputDto.setCreatedDate(Date.from(person.getCreatedDate()));
        personInputDto.setImageUrl(person.getImageUrl());
        personInputDto.setTerminationDate(Date.from(person.getTerminationDate()));
        return personInputDto;
    }

}
