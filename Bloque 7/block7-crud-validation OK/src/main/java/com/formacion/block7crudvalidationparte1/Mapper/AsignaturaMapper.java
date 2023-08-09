package com.formacion.block7crudvalidationparte1.Mapper;

import com.formacion.block7crudvalidationparte1.Entity.Asignatura;
import com.formacion.block7crudvalidationparte1.dto.AsignaturaInputDto;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AsignaturaMapper {

    public Asignatura toEntity(AsignaturaInputDto asignaturaDTO) {
        Asignatura asignatura = new Asignatura();
        asignatura.setComents(asignaturaDTO.getComments());
        asignatura.setInitialDate(asignaturaDTO.getInitialDate().toInstant());
        asignatura.setFinishDate(asignaturaDTO.getFinishDate().toInstant());

        return asignatura;
    }

    public AsignaturaInputDto toDTO(Asignatura asignatura) {
        AsignaturaInputDto asignaturaInputDto = new AsignaturaInputDto();
        asignaturaInputDto.setIdAsignatura(asignatura.getIdAsignatura());
        asignaturaInputDto.setComments(asignatura.getComents());
        asignaturaInputDto.setInitialDate(Date.from(asignatura.getInitialDate()));
        asignaturaInputDto.setFinishDate(Date.from(asignatura.getFinishDate()));

        return asignaturaInputDto;
    }

    public List<AsignaturaInputDto> toDTOList(List<Asignatura> asignatura) {
        return asignatura.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
