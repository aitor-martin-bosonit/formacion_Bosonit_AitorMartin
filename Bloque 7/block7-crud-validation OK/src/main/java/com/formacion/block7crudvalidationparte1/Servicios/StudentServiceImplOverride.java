package com.formacion.block7crudvalidationparte1.Servicios;

import com.formacion.block7crudvalidationparte1.Entity.Student;
import com.formacion.block7crudvalidationparte1.Excepciones.EntityNotFoundException;
import com.formacion.block7crudvalidationparte1.dto.StudentFullOutputDto;
import com.formacion.block7crudvalidationparte1.dto.StudentInputDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface StudentServiceImplOverride {
    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Integer id);

    @Transactional
    void deleteStudent(Integer idStudent) throws EntityNotFoundException;

    StudentFullOutputDto getStudentFullDetails(Integer id);

    StudentInputDto agregarEstudiante(StudentInputDto studentInputDto);

    StudentInputDto getStudentDtoById(Integer id);

    List<StudentInputDto> getStudentsDtoByName(String name);

    List<StudentFullOutputDto> getStudentFullDetailsByName(String name);

    List<StudentFullOutputDto> getStudentFullDetails();
}
