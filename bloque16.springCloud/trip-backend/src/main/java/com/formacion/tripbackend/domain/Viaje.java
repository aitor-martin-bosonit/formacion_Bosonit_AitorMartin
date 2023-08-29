package com.formacion.tripbackend.domain;

import com.formacion.tripbackend.controller.dtos.input.ViajeInput;
import com.formacion.tripbackend.controller.dtos.output.ViajeOutput;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="viaje")
public class Viaje {

    @Id
    @GeneratedValue
    private int idViaje;
    private String origen;
    private String destino;
    private String horaSalida;
    private String horaLlegada;
    private String estado;

    @ManyToMany(mappedBy = "listaViajes")
    private List<Cliente> listaPasajeros;

    public Viaje(ViajeInput viajeInput){
        BeanUtils.copyProperties(viajeInput, this);
    }

    public ViajeOutput viajeToViajeOutput(){
        List<Integer> pasajeroIds = listaPasajeros.stream().map(Cliente::getIdCliente).toList();
        return new ViajeOutput(idViaje, origen, destino, horaSalida, horaLlegada, estado, pasajeroIds);
    }

}
