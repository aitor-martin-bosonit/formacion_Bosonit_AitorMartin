package com.formacion.block7crudvalidationparte1.Mapper;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.dto.PersonInputDto;
import org.springframework.stereotype.Component;

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
        person.setCreatedDate(personInputDto.getCreated_date());
        person.setImageUrl(personInputDto.getImagenUrl());
        person.setTerminationDate(personInputDto.getTermination_date());
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
        personInputDto.setCreated_date(person.getCreatedDate());
        personInputDto.setImagenUrl(person.getImageUrl());
        personInputDto.setTermination_date(person.getTerminationDate());
        return personInputDto;
    }

}
