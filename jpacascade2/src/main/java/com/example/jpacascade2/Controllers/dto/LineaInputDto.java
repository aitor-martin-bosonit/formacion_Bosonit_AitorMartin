package com.example.jpacascade2.Controllers.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LineaInputDto {

    private String producto;
    private double cantidad;
    private double importe;
    private int idFra;


}
