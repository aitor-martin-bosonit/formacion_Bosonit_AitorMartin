package com.formacion.block7crudvalidationparte1.Controllers;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.Servicios.PersonService;
import com.formacion.block7crudvalidationparte1.dto.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public void createPerson(@RequestBody PersonDTO personDTO) throws Exception {
        Person person = convertToEntity(personDTO);
        person.validate(); // Validate the entity
        personService.save(person);
    }

    @PutMapping("/{id}")
    public void updatePerson(@PathVariable int id, @RequestBody PersonDTO personDTO) throws Exception {
        Person person = convertToEntity(personDTO);
        person.validate(); // Validate the entity
        personService.update(id, person);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) {
        personService.delete(id);
    }

    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable int id) {
        Person person = personService.findById(id);
        return convertToDTO(person);
    }

    @GetMapping("/usuario")
    public PersonDTO getPersonByUsuario(@RequestParam String usuario) {
        Person person = personService.findByUsuario(usuario);
        return convertToDTO(person);
    }

    @GetMapping
    public List<PersonDTO> getAllPersons() {
        List<Person> persons = personService.findAll();
        return persons.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Metodos para convertir entre Entidades y DTOs
    private Person convertToEntity(PersonDTO dto) throws Exception {

        return PersonConverter.convertToEntity(dto);

    }

    private PersonDTO convertToDTO(Person entity) {

        return PersonConverter.convertToDTO(entity);

    }

}
