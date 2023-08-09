package com.formacion.block7crudvalidationparte1.Controllers;

import com.formacion.block7crudvalidationparte1.Entity.Profesor;
import com.formacion.block7crudvalidationparte1.Excepciones.CustomError;
import com.formacion.block7crudvalidationparte1.Excepciones.EntityNotFoundException;
import com.formacion.block7crudvalidationparte1.Excepciones.UnprocessableEntityException;
import com.formacion.block7crudvalidationparte1.Mapper.ProfesorMapper;
import com.formacion.block7crudvalidationparte1.Servicios.PersonService;
import com.formacion.block7crudvalidationparte1.Servicios.ProfesorService;
import com.formacion.block7crudvalidationparte1.dto.ProfesorFullOutputDto;
import com.formacion.block7crudvalidationparte1.dto.ProfesorInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;
    private final ProfesorMapper profesorMapper;
    private final PersonService personService;

    @Autowired
    public ProfesorController(ProfesorService profesorService, ProfesorMapper profesorMapper, PersonService personService) {

        this.profesorService = profesorService;
        this.profesorMapper = profesorMapper;
        this.personService = personService;
    }

    @PostMapping()
    public ResponseEntity<?> agregarProfesor(@RequestBody ProfesorInputDto profesorInputDto) {
        try {
            ProfesorInputDto newProfesorInputDto = profesorService.createProfesor(profesorInputDto);
            return new ResponseEntity<>(newProfesorInputDto, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getProfesorById(@PathVariable Integer id,
                                                  @RequestParam(required = false, defaultValue = "simple") String outputType) {
        try {
            if ("full".equalsIgnoreCase(outputType)) {

                ProfesorFullOutputDto profesorFullDto = profesorService.getProfesorFullDetailsById(id);
                return ResponseEntity.ok(profesorFullDto);
            } else {

                ProfesorInputDto profesorInputDto = profesorService.getProfesorDtoById(id); // Necesitas implementar este método en el servicio
                return ResponseEntity.ok(profesorInputDto);
            }
        } catch (EntityNotFoundException e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProfesors(@RequestParam(required = false, defaultValue = "simple") String outputType) {
        List<Profesor> profesores = profesorService.getAllProfesores();

        if ("full".equalsIgnoreCase(outputType)) {
            List<ProfesorFullOutputDto> profesorFullDTOs = profesores.stream()
                    .map(profesor -> profesorService.getProfesorFullDetailsById(profesor.getIdProfesor()))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(profesorFullDTOs);
        } else {
            List<ProfesorInputDto> profesorInputDtos = profesores.stream()
                    .map(profesorMapper::toDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(profesorInputDtos);
        }
    }
    @GetMapping("/nombre/{name}")
    public ResponseEntity<?> getProfesorsByName(@PathVariable String name,
                                                 @RequestParam(required = false, defaultValue = "simple") String outputType) {
        try {
            List<ProfesorInputDto> profesorInputDtos = profesorService.getProfesoresDtoByName(name);

            if (!profesorInputDtos.isEmpty()) {
                if ("full".equalsIgnoreCase(outputType)) {
                    List<ProfesorFullOutputDto> profesorsFullDTOs = profesorInputDtos.stream()
                            .map(profesorDTO -> profesorService.getProfesorFullDetailsById(profesorDTO.getIdProfesor()))
                            .collect(Collectors.toList());
                    return ResponseEntity.ok(profesorsFullDTOs);
                } else {
                    return ResponseEntity.ok(profesorInputDtos);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfesor(@PathVariable Integer id, @RequestBody ProfesorInputDto profesorInputDto) {
        try {
            ProfesorInputDto updatedProfesorInputDto = profesorService.updateProfesor(id, profesorInputDto);
            return ResponseEntity.ok(updatedProfesorInputDto);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (UnprocessableEntityException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfesor(@PathVariable Integer id) {
        profesorService.deleteProfesor(id);
        return ResponseEntity.ok("Profesor eliminado.");
    }

}
