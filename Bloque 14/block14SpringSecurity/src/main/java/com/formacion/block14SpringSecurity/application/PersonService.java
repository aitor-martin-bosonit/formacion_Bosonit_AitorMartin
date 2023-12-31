package com.formacion.block14SpringSecurity.application;

import com.formacion.block14SpringSecurity.controllers.dto.input.PersonInputDto;
import com.formacion.block14SpringSecurity.controllers.dto.output.PersonOutputDto;
import com.formacion.block14SpringSecurity.exceptions.EntityNotFoundException;
import com.formacion.block14SpringSecurity.exceptions.UnprocessableEntityException;



public interface PersonService {
    PersonOutputDto addPerson(PersonInputDto person) throws UnprocessableEntityException;
    Object getPersonById(int id, String outputType) throws EntityNotFoundException;
    Object getPersonByUsername(String username, String outputType);
    Iterable<PersonOutputDto> getAllPersons(int pageNumber, int pageSize);
    Iterable<?> getAllPersonsFull(String outputType);
    void deletePerson(int id) throws EntityNotFoundException;
    PersonOutputDto updatePerson(PersonInputDto person, int id) throws EntityNotFoundException, UnprocessableEntityException;
}


