package controller;

import application.PersonService;
import controller.dto.PersonInputDto;
import controller.dto.PersonOutputDto;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {


    @Autowired
    private PersonService servicioPersona;

    @GetMapping("/search")
    public ResponseEntity<List<Person>> searchPersons(
            @RequestParam(required = false) String user,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) LocalDateTime creationDate,
            @RequestParam(required = false, defaultValue = "user") String sortBy,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam int pageNumber) {

        List<Person> persons = servicioPersona.searchPersons(user, name, surname, creationDate, sortBy,pageSize, pageNumber);

        if (persons.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(persons);
    }

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person){
        URI location = URI.create("/persona");
        PersonOutputDto personOutputDto = servicioPersona.addPerson(person);
        return ResponseEntity.created(location).body(personOutputDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable int id,@RequestParam(defaultValue = "simple") String output) {
        PersonOutputDto personOutputDto = servicioPersona.getPerson(id);
        return ResponseEntity.ok().body(personOutputDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePersonById(@RequestParam int id){
        servicioPersona.deletePersonId(id);
        return ResponseEntity.ok().body("La persona con id: "+id+" ha sido eliminada");
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<PersonOutputDto>> getPersonsByName(@RequestParam(defaultValue = "simple") String output,
                                                                  @RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                                  @RequestParam(defaultValue = "4", required = false) int pageSize,
                                                                  @PathVariable String nombre) {
        List<PersonOutputDto> persons = servicioPersona.getPersonsName(pageNumber, pageSize, nombre, output);

        if (persons.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(persons);    }
    @GetMapping
    public ResponseEntity<List<PersonOutputDto>> getAllPersons(
            @RequestParam(defaultValue = "simple") String output,
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        List<PersonOutputDto> persons = servicioPersona.getAllPersons(pageNumber, pageSize, output);

        if (persons.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(persons);    }

    public ResponseEntity<PersonOutputDto> updatePerson(@PathVariable Integer id, @RequestBody PersonInputDto person) {
        PersonOutputDto updatedPerson = servicioPersona.updatePerson(id, person);

        if (updatedPerson == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedPerson);
    }
}
