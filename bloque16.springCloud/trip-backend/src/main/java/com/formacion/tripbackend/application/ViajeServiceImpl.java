package com.formacion.tripbackend.application;

import com.formacion.tripbackend.controller.dtos.input.ViajeInput;
import com.formacion.tripbackend.controller.dtos.output.ViajeOutput;
import com.formacion.tripbackend.domain.Cliente;
import com.formacion.tripbackend.domain.Viaje;
import com.formacion.tripbackend.repository.ClienteRepository;
import com.formacion.tripbackend.repository.ViajeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    ViajeRepository viajeRepository;

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> getClientesFromIds(List<Integer> clientesIds) {
        if (clientesIds == null) {
            return Collections.emptyList();
        }
        return clientesIds.stream()
                .map(clientesId -> clienteRepository.findById(clientesId)
                        .orElseThrow(() -> new EntityNotFoundException("Cliente con Id " + clientesId + "NOT FOUND")))
                .collect(Collectors.toList());
    }


    @Override
    public ViajeOutput addViaje(ViajeInput viajeInput) {
        if (viajeInput.getListaPasajeros().size() > 40) {
            throw new EntityNotFoundException("SOlo se pueden añadir 40 pasajeros");
        }

        List<Cliente> clienteList = getClientesFromIds(viajeInput.getListaPasajeros());
        Viaje viaje = new Viaje(viajeInput);
        viaje.setListaPasajeros(clienteList);
        clienteList.forEach(cliente -> {
            cliente.getListaViajes().add(viaje);
            clienteRepository.save(cliente);
        });

        return viajeRepository.save(viaje).viajeToViajeOutput();
    }


    @Override
    public void deleteViaje(int id) {
        Viaje viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaje con ID: " + id + "NOT FOUND"));
        viaje.getListaPasajeros().forEach(cliente -> {
            cliente.getListaViajes().remove(viaje);
            clienteRepository.save(cliente);
        });
        viajeRepository.deleteById(id);
    }


    @Override
    public ViajeOutput updateViaje(Integer id, ViajeInput viajeInput) {
        Viaje viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaje con ID: " + id + "NOT FOUND"));

        if(viajeInput.getDestino() != null){
            viaje.setDestino(viajeInput.getDestino());
        }
        if(viajeInput.getEstado() != null){
            viaje.setEstado(viajeInput.getEstado());
        }
        if(viajeInput.getHoraLlegada() != null){
            viaje.setHoraLlegada(viajeInput.getHoraLlegada());
        }
        if(viajeInput.getHoraSalida() != null){
            viaje.setHoraSalida(viajeInput.getHoraSalida());
        }
        if(viajeInput.getOrigen() != null){
            viaje.setOrigen(viajeInput.getOrigen());
        }

        if (viajeInput.getListaPasajeros() != null) {
            updateViajePasajeros(viaje, viajeInput.getListaPasajeros());
        }

        return viajeRepository.save(viaje).viajeToViajeOutput();
    }


    private void updateViajePasajeros(Viaje viaje, List<Integer> pasajerosIds) {
        List<Cliente> clienteList = getClientesFromIds(pasajerosIds);
        viaje.getListaPasajeros().forEach(cliente -> {
            cliente.getListaViajes().remove(viaje);
            clienteRepository.save(cliente);
        });
        viaje.setListaPasajeros(clienteList);
        clienteList.forEach(cliente -> {
            cliente.getListaViajes().add(viaje);
            clienteRepository.save(cliente);
        });
    }


    @Override
    public List<ViajeOutput> getAllViaje(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return viajeRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Viaje::viajeToViajeOutput)
                .toList();
    }


    @Override
    public ViajeOutput getViaje(int id) {
        Viaje viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaje con ID: " + id + "NOT FOUND"));
        return viaje.viajeToViajeOutput();
    }


    @Override
    public ViajeOutput addPasajero(Integer idViaje, Integer idPasajero) {
        Viaje viaje = viajeRepository.findById(idViaje)
                .orElseThrow(() -> new EntityNotFoundException("Viaje con ID " + idViaje + "NOT FOUND"));
        Cliente cliente = clienteRepository.findById(idPasajero)
                .orElseThrow(() -> new EntityNotFoundException("Cliente con ID: " + idPasajero + "NOT FOUND"));

        if (viaje.getListaPasajeros().contains(cliente)) {
            throw new EntityNotFoundException("Pasajero pendiente de viaje");
        } else {
            cliente.getListaViajes().add(viaje);
            clienteRepository.save(cliente);
            viaje.getListaPasajeros().add(cliente);
            return viajeRepository.save(viaje).viajeToViajeOutput();
        }
    }


    @Override
    public ViajeOutput modifyEstado(Integer idViaje, String estado) {
        Viaje viaje = viajeRepository.findById(idViaje)
                .orElseThrow(() -> new EntityNotFoundException("Viaje con ID " + idViaje + "NOT FOUND"));
        viaje.setEstado(estado);
        return viajeRepository.save(viaje).viajeToViajeOutput();
    }


    @Override
    public String verifyViaje(Integer idViaje) {
        Viaje viaje = viajeRepository.findById(idViaje)
                .orElseThrow(() -> new EntityNotFoundException("Viaje con ID " + idViaje + "NOT FOUND"));

        if (viaje.getEstado().equals("abierto") && viaje.getListaPasajeros().size() < 40) {
            return "Disponible";
        }
        return "No disponible";
    }

}
