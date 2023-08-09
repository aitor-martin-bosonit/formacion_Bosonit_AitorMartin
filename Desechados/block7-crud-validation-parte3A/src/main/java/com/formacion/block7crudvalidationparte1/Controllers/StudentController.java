package com.formacion.block7crudvalidationparte1.Controllers;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.Entity.Student;
import com.formacion.block7crudvalidationparte1.Excepciones.CustomError;
import com.formacion.block7crudvalidationparte1.Excepciones.EntityNotFoundException;
import com.formacion.block7crudvalidationparte1.Excepciones.UnprocessableEntityException;
import com.formacion.block7crudvalidationparte1.Servicios.PersonService;
import com.formacion.block7crudvalidationparte1.Servicios.StudentService;
import com.formacion.block7crudvalidationparte1.dto.PersonInputDto;
import com.formacion.block7crudvalidationparte1.dto.StudentInputDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RestController
@RequestMapping("/students")
public class StudentController {


    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllStudents(
            @RequestParam(required = false, defaultValue = "simple") String outputType) {
        return new ResponseEntity<>(
                outputType.equals("full")
                        ? studentService.getStudentFullDetails()
                        : studentService.getAllStudentsSimpleDetails(),
                HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<StudentInputDto> agregarEstudiante(@RequestBody StudentInputDto studentInputDto) {
        return new ResponseEntity<>(studentService.agregarEstudiante(studentInputDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Integer id,
                                                    @RequestParam(required = false, defaultValue = "simple") String outputType) {
        return new ResponseEntity<>(outputType.equals("full") ? studentService.getStudentFullDetails(id) : studentService.getStudentDTOById(id), HttpStatus.OK);
    }

    @GetMapping("/nombre/{name}")
    public ResponseEntity<List<?>> getStudentsByName(@PathVariable String name,
                                                        @RequestParam(required = false, defaultValue = "simple") String outputType) {
        return new ResponseEntity<>(outputType.equals("full") ? studentService.getStudentFullDetailsByName(name) : studentService.getStudentsDTOByName(name), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentInputDto> updateStudent(@PathVariable Integer id, @RequestBody StudentInputDto studentInputDto) {
        return new ResponseEntity<>(studentService.agregarEstudiante(studentInputDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Estudiante borrado", HttpStatus.OK);
    }

    @PutMapping("/{idStudent}/asignaturas")
    public ResponseEntity<String> assignAsignaturasToStudent(@PathVariable Integer idStudent, @RequestBody List<Integer> idsAsignaturas) {
        studentService.asignarAsignaturasStudent(idStudent, idsAsignaturas);
        return new ResponseEntity<>("Asignaturas asignadas con éxito al estudiante.", HttpStatus.OK);
    }

    @PutMapping("/{idStudent}/desasignar-asignaturas")
    public ResponseEntity<String> unassignAsignaturasFromStudent(@PathVariable Integer idStudent, @RequestBody List<Integer> idsAsignaturas) {
        studentService.desasignarAsignaturasStudent(idStudent, idsAsignaturas);
        return new ResponseEntity<>("Asignaturas desasignadas.", HttpStatus.OK);
    }



}
