package com.example.jpacascade.Controllers.Dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineaInputDto {

    private String producto;
    private double cantidad;
    private double importe;
    private int idFra;

}
