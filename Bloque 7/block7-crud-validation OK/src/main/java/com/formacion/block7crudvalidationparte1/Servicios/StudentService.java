package com.formacion.block7crudvalidationparte1.Servicios;

import com.formacion.block7crudvalidationparte1.Entity.Student;
import com.formacion.block7crudvalidationparte1.dto.StudentFullOutputDto;
import com.formacion.block7crudvalidationparte1.dto.StudentInputDto;
import com.formacion.block7crudvalidationparte1.dto.StudentSimpleOutputDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface StudentService {

    Student saveStudent(Student student);

    List<Student> getAllStudents();

    List<StudentSimpleOutputDto> getAllStudentsSimpleDetails();

    Student getStudentById(Integer id);

    void deleteStudent(Integer id);

    List<StudentFullOutputDto> getStudentFullDetails();

    StudentFullOutputDto getStudentFullDetails(Integer id);

    StudentInputDto agregarEstudiante(StudentInputDto studentInputDto);

    StudentInputDto getStudentDTOById(Integer id);

    List<StudentInputDto> getStudentsDTOByName(String name);

    StudentInputDto getStudentDtoById(Integer id);

    List<StudentInputDto> getStudentsDtoByName(String name);

    List<StudentFullOutputDto> getStudentFullDetailsByName(String name);

    void asignarAsignaturasStudent(Integer idStudent, List<Integer> idsAsignaturas);

    void desasignarAsignaturasStudent(Integer idStudent, List<Integer> idsAsignaturas);

}
