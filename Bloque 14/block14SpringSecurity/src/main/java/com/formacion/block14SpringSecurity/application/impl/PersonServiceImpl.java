package com.formacion.block14SpringSecurity.application.impl;

import com.formacion.block14SpringSecurity.application.PersonService;
import com.formacion.block14SpringSecurity.controllers.dto.input.PersonInputDto;
import com.formacion.block14SpringSecurity.controllers.dto.output.PersonOutputDto;
import com.formacion.block14SpringSecurity.domain.Person;
import com.formacion.block14SpringSecurity.exceptions.EntityNotFoundException;
import com.formacion.block14SpringSecurity.exceptions.UnprocessableEntityException;
import com.formacion.block14SpringSecurity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDto addPerson(PersonInputDto person) throws UnprocessableEntityException {
        if (person.getUsername() == null || person.getUsername().length() < 6 || person.getUsername().length() > 10) {
            throw new UnprocessableEntityException();
        }
        if (person.getPassword() == null || person.getName() == null || person.getCompany_email() == null || person.getPersonal_email() == null
                || person.getCity() == null || person.getActive() == null || person.getCreated_date() == null) {
            throw new UnprocessableEntityException();
        }
        return personRepository.save(new Person(person)).personToPersonOutputDto();
    }

    @Override
    public Object getPersonById(int id, String outputType) throws EntityNotFoundException {
        Person person = personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (outputType.equalsIgnoreCase("full") && person.getRole() != null) {
            return person.personToPersonOutputDto();
        }
        return person.personToPersonOutputDto();
    }

    @Override
    public Object getPersonByUsername(String username, String outputType) throws EntityNotFoundException {
        Person person = personRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
        if (outputType.equalsIgnoreCase("full") && person.getRole() != null) {
            return person.personToPersonOutputDto();
        }

        return person.personToPersonOutputDto();
    }

    @Override
    public Iterable<PersonOutputDto> getAllPersons(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Person::personToPersonOutputDto)
                .toList();
    }

    @Override
    public Iterable<?> getAllPersonsFull(String outputType) {
        List<Person> persons = personRepository.findAll();
        List<Object> objectsPersons = new ArrayList<>();

        for (Person person : persons) {
            if (outputType.equalsIgnoreCase("full")) {
                objectsPersons.add(person.personToPersonOutputDto());
            }
        }

        return objectsPersons;
    }

    @Override
    public void deletePerson(int id) throws EntityNotFoundException {
        if (personRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException();
        } else {
            personRepository.deleteById(id);
        }
    }

    @Override
    public PersonOutputDto updatePerson(PersonInputDto person, int id) throws EntityNotFoundException, UnprocessableEntityException {
        Person personUpdated = personRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (person.getSurname() != null) {
            personUpdated.setSurname(person.getSurname());
        }
        if (person.getImage_url() != null) {
            personUpdated.setImage_url(person.getImage_url());
        }
        if (person.getTermination_date() != null) {
            personUpdated.setTermination_date(person.getTermination_date());
        }
        if (person.getUsername() != null && person.getUsername().length() >= 6 && person.getUsername().length() <= 10) {
            personUpdated.setUsername(person.getUsername());
        } else {
            throw new UnprocessableEntityException();
        }
        if (person.getPassword() == null || person.getName() == null || person.getCompany_email() == null || person.getPersonal_email() == null
                || person.getCity() == null || person.getActive() == null || person.getCreated_date() == null) {
            throw new UnprocessableEntityException();
        } else {
            personUpdated.setPassword(person.getPassword());
            personUpdated.setName(person.getName());
            personUpdated.setCompany_email(person.getCompany_email());
            personUpdated.setPersonal_email(person.getPersonal_email());
            personUpdated.setCity(person.getCity());
            personUpdated.setActive(person.getActive());
            personUpdated.setCreated_date(person.getCreated_date());
        }

        return personRepository.save(personUpdated).personToPersonOutputDto();
    }
}