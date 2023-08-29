package com.formacion.tripfrontend.domain;

import com.formacion.tripfrontend.controller.dtos.input.ViajeInput;
import com.formacion.tripfrontend.controller.dtos.output.ViajeOutput;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
