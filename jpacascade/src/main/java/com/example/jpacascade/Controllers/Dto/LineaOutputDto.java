package com.example.jpacascade.Controllers.Dto;


import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineaOutputDto {

    private int id;
    private String producto;
    private double cantidad;
    private double importe;

}
