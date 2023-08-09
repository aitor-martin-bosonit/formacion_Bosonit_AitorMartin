package com.example.jpacascade2.Controllers.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacturaOutputDto {

    private int id;
    private double importeFra = 0;
    private ClienteOutputDto clienteOutputDto;
    private List<LineaOutputDto> lineaOutputDtoList;


}
