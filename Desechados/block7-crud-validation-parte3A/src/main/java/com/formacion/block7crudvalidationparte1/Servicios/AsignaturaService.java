package com.formacion.block7crudvalidationparte1.Servicios;

import com.formacion.block7crudvalidationparte1.Entity.Asignatura;
import com.formacion.block7crudvalidationparte1.Entity.Student;
import com.formacion.block7crudvalidationparte1.dto.AsignaturaInputDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AsignaturaService {

    List<AsignaturaInputDto> getAllAsignaturas();

    List<Asignatura> getAsignaturasByStudentId(Integer idStudent);

    AsignaturaInputDto createAsignatura(AsignaturaInputDto asignaturaInputDto);

    ResponseEntity<?> deleteAsignatura(Integer idAsignatura);

    List<Student> getStudentByAsignaturaId(Integer idAsignatura);

}
