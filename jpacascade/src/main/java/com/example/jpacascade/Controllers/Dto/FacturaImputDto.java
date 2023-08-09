package com.example.jpacascade.Controllers.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaImputDto {

    private int codigoCliente;
    private double importeFra;
    private List<LineaInputDto> lineaInputDtoList;


}
