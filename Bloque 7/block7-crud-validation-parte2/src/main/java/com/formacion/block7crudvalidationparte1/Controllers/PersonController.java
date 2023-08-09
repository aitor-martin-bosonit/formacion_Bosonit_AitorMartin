package com.formacion.block7crudvalidationparte1.Controllers;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.Excepciones.CustomError;
import com.formacion.block7crudvalidationparte1.Excepciones.EntityNotFoundException;
import com.formacion.block7crudvalidationparte1.Excepciones.UnprocessableEntityException;
import com.formacion.block7crudvalidationparte1.Servicios.PersonService;
import com.formacion.block7crudvalidationparte1.dto.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    /*@PostMapping
    public void createPerson(@RequestBody PersonDTO personDTO) throws Exception {
        Person person = convertToEntity(personDTO);
        person.validate(); // Validate the entity
        personService.save(person);
    }*/
    @PostMapping
    public ResponseEntity<Object> createPerson(@RequestBody PersonDTO personDTO) {
        try {
            Person person = convertToEntity(personDTO);
            person.validate();
            personService.save(person);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            CustomError customError = new CustomError();
            customError.setTimestamp(new Date());
            customError.setHttpCode(422);
            customError.setMensaje(ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(customError);
        }
    }


    /*@PutMapping("/{id}")
    public void updatePerson(@PathVariable int id, @RequestBody PersonDTO personDTO) throws Exception {
        Person person = convertToEntity(personDTO);
        person.validate(); // Validate the entity
        personService.update(id, person);
    }*/
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable int id, @RequestBody PersonDTO personDTO) {
        try {
            Person person = convertToEntity(personDTO);
            person.validate();
            personService.update(id, person);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex) {
            CustomError customError = new CustomError();
            customError.setTimestamp(new Date());
            customError.setHttpCode(404);
            customError.setMensaje(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError);
        } catch (UnprocessableEntityException ex) {
            CustomError customError = new CustomError();
            customError.setTimestamp(new Date());
            customError.setHttpCode(422);
            customError.setMensaje(ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(customError);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable int id) {
        personService.delete(id);
    }

   /* @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable int id) {

        Person person = personService.findById(id);
        return convertToDTO(person);
    }*/
   @GetMapping("/{id}")
    public ResponseEntity<Object> getPersonaById(@PathVariable int id) {
       try {
           Person person = personService.findById(id);
           if (person == null) {
               throw new EntityNotFoundException("Persona no encontrada con ID: " + id);
           }
           PersonDTO personDTO = convertToDTO(person);
           return ResponseEntity.ok(personDTO);
       } catch (EntityNotFoundException ex) {
           CustomError customError = new CustomError();
           customError.setTimestamp(new Date());
           customError.setHttpCode(404);
           customError.setMensaje(ex.getMessage());
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError);
       }
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
