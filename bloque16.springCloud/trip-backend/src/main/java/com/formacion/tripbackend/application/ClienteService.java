package com.formacion.tripbackend.application;

import com.formacion.tripbackend.controller.dtos.input.ClienteInput;
import com.formacion.tripbackend.controller.dtos.output.ClienteOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {

    ClienteOutput addCliente(ClienteInput clienteInput);
    void deleteCliente(int id);
    ClienteOutput updateCliente(Integer id, ClienteInput clienteInput);
    List<ClienteOutput> getAllCliente(int pageNumber, int pageSize);
    ClienteOutput getCliente(int id);
    int countPasajeros(int idViaje);

}
