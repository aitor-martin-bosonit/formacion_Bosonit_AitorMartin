package com.formacion.tripfrontend.domain;

import com.formacion.tripfrontend.controller.dtos.input.ClienteInput;
import com.formacion.tripfrontend.controller.dtos.output.ClienteOutput;
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
public class Cliente {
    @Id
    @GeneratedValue
    private int idCliente;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String email;
    private String telefono;

    @ManyToMany
    private List<Viaje> listaViajes;

    public Cliente(ClienteInput clienteInput){
        BeanUtils.copyProperties(clienteInput, this);
    }

    public ClienteOutput clienteToClienteOutput(){
        List<Integer> viajeIds = listaViajes.stream().map(Viaje::getIdViaje).toList();
        return new ClienteOutput(idCliente, nombre, apellido, edad, email, telefono, viajeIds);
    }
}
