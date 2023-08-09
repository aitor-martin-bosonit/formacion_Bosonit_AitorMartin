package com.formacion.block7crud.Servicios;

import com.formacion.block7crud.Controladores.dto.PersonInputDto;
import com.formacion.block7crud.Controladores.dto.PersonOutputDto;
import com.formacion.block7crud.Entity.Person;
import com.formacion.block7crud.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDto) {
        Person person= new Person(personInputDto);
        return new PersonOutputDto(personRepository.save(person));
    }

    @Override
    public PersonOutputDto updatePerson(Integer id, PersonInputDto personInputDto) {
        Person existingPerson = personRepository.findById(id).orElseThrow(() -> new RuntimeException("errrrrro!!!"));
        if (existingPerson.getName() != null) {
            existingPerson.setName(personInputDto.getName());
        }
        if (existingPerson.getAge() != null) {
            existingPerson.setAge(personInputDto.getAge());
        }
        if (existingPerson.getPopulation() != null) {
            existingPerson.setPopulation(personInputDto.getPopulation());
        }
        return new PersonOutputDto(personRepository.save(existingPerson));

    }


    @Override
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person getPersonById(Integer id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public List<Person> getPersonByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

}
