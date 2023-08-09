package com.formacion.block7crudvalidationparte1.Servicios;

import com.formacion.block7crudvalidationparte1.Entity.Profesor;
import com.formacion.block7crudvalidationparte1.dto.ProfesorFullOutputDto;
import com.formacion.block7crudvalidationparte1.dto.ProfesorInputDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProfesorService {

    Profesor saveProfesor(Profesor profesor);

    List<Profesor> getAllProfesores();

    Profesor getProfesorById(Integer id);

    void deleteProfesor(Integer id);

    Profesor updateProfesor(Integer id, Profesor profesor);



    ProfesorInputDto getProfesorDtoById(Integer id);

    List<ProfesorInputDto> getProfesoresDtoByName(String name);

    ProfesorFullOutputDto getProfesorFullDetailsById(Integer id);

    List<ProfesorFullOutputDto> getProfesorFullDetailsByName(String name);

    ProfesorInputDto createProfesor(ProfesorInputDto profesorInputDto);
    ProfesorInputDto updateProfesor(Integer id, ProfesorInputDto profesorInputDto);

}
