package com.formacion.tripbackend.application;

import com.formacion.tripbackend.controller.dtos.input.ViajeInput;
import com.formacion.tripbackend.controller.dtos.output.ViajeOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ViajeService {

    ViajeOutput addViaje(ViajeInput viajeInput);
    void deleteViaje(int id);
    ViajeOutput updateViaje(Integer id, ViajeInput viajeInput);
    List<ViajeOutput> getAllViaje(int pageNumber, int pageSize);
    ViajeOutput getViaje(int id);
    ViajeOutput addPasajero(Integer idViaje, Integer idPasajero);
    ViajeOutput modifyEstado(Integer idViaje, String estado);
    String verifyViaje(Integer idViaje);

}
