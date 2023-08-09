package com.formacion.block7crud.Controladores;

import com.formacion.block7crud.Controladores.dto.PersonInputDto;
import com.formacion.block7crud.Controladores.dto.PersonOutputDto;
import com.formacion.block7crud.Entity.Person;
import com.formacion.block7crud.Exceptions.BadRequestException;
import com.formacion.block7crud.Servicios.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("persona")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PersonOutputDto addPerson(@RequestBody PersonInputDto person) {
        PersonOutputDto newPerson = personService.addPerson(person);
        return newPerson;
    }

    @PutMapping("{id}")
    public ResponseEntity<PersonOutputDto> updatePerson(@PathVariable int id, @RequestBody PersonInputDto person) {

        PersonOutputDto updatedPerson = personService.updatePerson(id, person);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {
        Person existingPerson = personService.getPersonById(id);
        if (existingPerson != null) {
            personService.deletePerson(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        Person person = personService.getPersonById(id);
        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nombre/{name}")
    public ResponseEntity<List<Person>> getPersonByName(@PathVariable String name) {
        List<Person> persons = personService.getPersonByName(name);
        if (!persons.isEmpty()) {
            return new ResponseEntity<>(persons, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }


}
