package com.formacion.block7crudvalidationparte1.Mapper;

import com.formacion.block7crudvalidationparte1.Entity.Asignatura;
import com.formacion.block7crudvalidationparte1.dto.AsignaturaInputDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AsignaturaMapper {

    public Asignatura toEntity(AsignaturaInputDto asignaturaDTO) {
        Asignatura asignatura = new Asignatura();
        asignatura.setComents(asignaturaDTO.getComents());
        asignatura.setInitialDate(asignaturaDTO.getInitialDate());
        asignatura.setFinishDate(asignaturaDTO.getFinishDate());

        // Quitamos la vinculación al estudiante
        // asignaturaEntity.setStudent(studentEntity);

        return asignatura;
    }

    public AsignaturaInputDto toDTO(Asignatura asignatura) {
        AsignaturaInputDto asignaturaInputDto = new AsignaturaInputDto();
        asignaturaInputDto.setIdAsignatura(asignatura.getIdAsignatura());
        asignaturaInputDto.setComents(asignatura.getComents());
        asignaturaInputDto.setInitialDate(asignatura.getInitialDate());
        asignaturaInputDto.setFinishDate(asignatura.getFinishDate());

        return asignaturaInputDto;
    }


    // Método para convertir una lista de entidades a una lista de DTOs
    public List<AsignaturaInputDto> toDTOList(List<Asignatura> asignatura) {
        return asignatura.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
