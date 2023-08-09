package com.formacion.block7crudvalidationparte1.Controllers;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.Excepciones.CustomError;
import com.formacion.block7crudvalidationparte1.Excepciones.EntityNotFoundException;
import com.formacion.block7crudvalidationparte1.Excepciones.UnprocessableEntityException;
import com.formacion.block7crudvalidationparte1.Mapper.PersonMapper;
import com.formacion.block7crudvalidationparte1.Servicios.PersonService;
import com.formacion.block7crudvalidationparte1.Servicios.ProfesorService;
import com.formacion.block7crudvalidationparte1.Servicios.StudentService;
import com.formacion.block7crudvalidationparte1.dto.PersonInputDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {


    @Autowired
    private PersonService personService;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ProfesorService profesorService;


    @PostMapping()
    public ResponseEntity<?> agregarPersona(@RequestBody PersonInputDto personInputDto) {
        try {
            // Convertir el DTO a una entidad PersonaEntity usando el mapper
            Person newPerson = personMapper.toEntity(personInputDto);

            // Guardar la entidad en la base de datos
            Person savePerson = personService.agregarPersona(newPerson);

            // Convertir la entidad guardada a DTO y devolverla en la respuesta
            PersonInputDto savePersonDto = personMapper.toDTO(savePerson);

            return new ResponseEntity<>(savePersonDto, HttpStatus.CREATED);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Person> searchById(@PathVariable Integer id) {
        Person person = personService.searchById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<Person> searchByUser(@PathVariable String usuario) {
        Person person = personService.searchByUser(usuario);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        List<Person> person = personService.getAll();
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyPerson(@PathVariable Integer id, @RequestBody PersonInputDto personInputDto) {
        try {
            // Convertir el DTO a una entidad PersonaEntity usando el mapper
            Person person = personMapper.toEntity(personInputDto);

            // Modificar la entidad en la base de datos
            Person personModify = personService.modifyPerson(id, person);

            // Convertir la entidad modificada a DTO y devolverla en la respuesta
            PersonInputDto personModifyDto = personMapper.toDTO(personModify);

            return new ResponseEntity<>(personModifyDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Integer id) {
        try {
            try {
                // Intentar eliminar un estudiante con el id de la persona
                studentService.deleteStudent(id);
            } catch (EntityNotFoundException e) {
                // Ignorar si no se encuentra el estudiante
            }

            try {
                // Intentar eliminar un profesor con el id de la persona
                profesorService.deleteProfesor(id);
            } catch (EntityNotFoundException e) {
                // Ignorar si no se encuentra el profesor
            }

            // Proceder a eliminar la persona
            personService.deletePerson(id);
            return ResponseEntity.ok("Persona con id " + id + " eliminada.");
        } catch (EntityNotFoundException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } catch (UnprocessableEntityException e) {
            CustomError error = new CustomError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getExternalMessage());
            return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
