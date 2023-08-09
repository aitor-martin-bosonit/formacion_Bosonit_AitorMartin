package com.example.jpacascade2.Controllers.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LineaOutputDto {

    private int id;
    private String producto;
    private double cantidad;
    private double importe;

}
