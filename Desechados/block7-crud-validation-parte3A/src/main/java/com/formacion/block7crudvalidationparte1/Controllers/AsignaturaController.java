package com.formacion.block7crudvalidationparte1.Controllers;

import com.formacion.block7crudvalidationparte1.Entity.Asignatura;
import com.formacion.block7crudvalidationparte1.Entity.Student;
import com.formacion.block7crudvalidationparte1.Mapper.AsignaturaMapper;
import com.formacion.block7crudvalidationparte1.Servicios.AsignaturaService;
import com.formacion.block7crudvalidationparte1.dto.AsignaturaInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @Autowired
    private AsignaturaMapper asignaturaMapper;

    @GetMapping("/all")
    public ResponseEntity<List<AsignaturaInputDto>> getAllAsignaturas() {
        List<AsignaturaInputDto> asignaturas = asignaturaService.getAllAsignaturas();
        return new ResponseEntity<>(asignaturas, HttpStatus.OK);
    }

    @GetMapping("/student/{idStudent}")
    public ResponseEntity<List<AsignaturaInputDto>> getAsignaturasByStudentId(@PathVariable Integer idStudent) {
        List<Asignatura> asignaturas = asignaturaService.getAsignaturasByStudentId(idStudent);
        return ResponseEntity.ok(asignaturaMapper.toDTOList(asignaturas));
    }

    @GetMapping("/{idAsignatura}/students")
    public ResponseEntity<List<Student>> getStudentsByAsignaturaId(@PathVariable Integer idAsignatura) {
        List<Student> students = asignaturaService.getStudentByAsignaturaId(idAsignatura);
        return ResponseEntity.ok(students);
    }


    @PostMapping
    public ResponseEntity<AsignaturaInputDto> createAsignatura(@RequestBody AsignaturaInputDto asignaturaInputDto) {
        AsignaturaInputDto asignaturaDTO = asignaturaService.createAsignatura(asignaturaInputDto);
        return ResponseEntity.ok(asignaturaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsignatura(@PathVariable Integer id) {
        return asignaturaService.deleteAsignatura(id);
    }

}
