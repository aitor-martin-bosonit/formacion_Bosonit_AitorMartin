package com.formacion.block7crudvalidationparte1.Mapper;

import com.formacion.block7crudvalidationparte1.Entity.Person;
import com.formacion.block7crudvalidationparte1.Entity.Profesor;
import com.formacion.block7crudvalidationparte1.dto.ProfesorFullOutputDto;
import com.formacion.block7crudvalidationparte1.dto.ProfesorInputDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfesorMapper {


        public Profesor toEntity(ProfesorInputDto profesorInputDto) {
            Profesor profesor = new Profesor();
            profesor.setIdProfesor(profesorInputDto.getIdProfesor());

            // Establecer PersonaEntity con solo el ID establecido
            Person person = new Person();
            person.setIdPerson(profesorInputDto.getIdPerson());
            profesor.setPerson(person);

            profesor.setComments(profesorInputDto.getComments());
            profesor.setBranch(profesorInputDto.getBranch());
            return profesor;
        }

        public ProfesorInputDto toDTO(Profesor profesor) {
            ProfesorInputDto profesorInputDto = new ProfesorInputDto();
            profesorInputDto.setIdProfesor(profesor.getIdProfesor());

            // Establecer el idPersona de la entidad PersonaEntity asociada al profesor
            if (profesor.getPerson() != null) {
                profesorInputDto.setIdPerson(profesor.getPerson().getIdPerson());
            }

            profesorInputDto.setComments(profesor.getComments());
            profesorInputDto.setBranch(profesor.getBranch());
            return profesorInputDto;
        }

        public List<ProfesorInputDto> toDTOList(List<Profesor> profesors) {
            List<ProfesorInputDto> profesorInputDtos = new ArrayList<>();
            for (Profesor profesor : profesors) {
                profesorInputDtos.add(toDTO(profesor));
            }
            return profesorInputDtos;
        }


        public ProfesorFullOutputDto toFullDTO(Profesor profesor) {
            ProfesorFullOutputDto profesorFullDto = new ProfesorFullOutputDto();
            profesorFullDto.setIdProfesor(profesor.getIdProfesor());

            // Establecer el idPersona y otros detalles de la entidad PersonaEntity asociada al profesor
            if (profesor.getPerson() != null) {
                profesorFullDto.setIdPerson(profesor.getPerson().getIdPerson());
                profesorFullDto.setUsuario(profesor.getPerson().getUsuario());
                profesorFullDto.setName(profesor.getPerson().getName());
                profesorFullDto.setSurname(profesor.getPerson().getSurname());
                profesorFullDto.setCompanyEmail(profesor.getPerson().getCompanyEmail());
                profesorFullDto.setPersonalEmail(profesor.getPerson().getPersonalEmail());
                profesorFullDto.setCity(profesor.getPerson().getCity());
                profesorFullDto.setActive(profesor.getPerson().isActive());
                profesorFullDto.setCreatedDate(profesor.getPerson().getCreatedDate());
                profesorFullDto.setImageUrl(profesor.getPerson().getImageUrl());
                profesorFullDto.setTerminationDate(profesor.getPerson().getTerminationDate());
            }

            profesorFullDto.setComments(profesor.getComments());
            profesorFullDto.setBranch(profesor.getBranch());
            return profesorFullDto;
        }

        public List<ProfesorFullOutputDto> toFullDTOList(List<Profesor> profesors) {
            List<ProfesorFullOutputDto> profesorFullDTOs = new ArrayList<>();
            for (Profesor profesor : profesors) {
                profesorFullDTOs.add(toFullDTO(profesor));
            }
            return profesorFullDTOs;
        }

}
