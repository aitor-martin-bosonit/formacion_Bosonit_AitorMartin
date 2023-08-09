package com.formacion.block7crudvalidationparte1.Servicios;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person findById(int id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person findByUsuario(String usuario) {
        return personRepository.findByUsuario(usuario);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public void update(int id, Person person) throws Exception {
        Person existingPerson = personRepository.findById(id).orElse(null);
        if (existingPerson != null) {

            existingPerson.setUsuario(person.getUsuario());
            existingPerson.setPassword(person.getPassword());
            existingPerson.setName(person.getName());
            existingPerson.setSurname(person.getSurname());
            existingPerson.setCompanyEmail(person.getCompanyEmail());
            existingPerson.setPersonalEmail(person.getPersonalEmail());
            existingPerson.setCity(person.getCity());
            existingPerson.setActive(person.isActive());
            existingPerson.setCreatedDate(person.getCreatedDate());
            existingPerson.setImageUrl(person.getImageUrl());
            existingPerson.setTerminationDate(person.getTerminationDate());

            personRepository.save(existingPerson);
        }
    }

    @Override
    public void delete(int id) {
        personRepository.deleteById(id);
    }
}
