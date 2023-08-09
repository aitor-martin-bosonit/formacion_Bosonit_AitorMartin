package com.example.jpacascade.Controllers.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaOutputDto {

    private int id;
    private double importeFra = 0;
    private ClienteOutputDto clienteOutputDto;
    private List<LineaOutputDto> lineaOutputDtoList;

}
