package com.formacion.tripbackend.application;

import com.formacion.tripbackend.controller.dtos.input.ClienteInput;
import com.formacion.tripbackend.controller.dtos.output.ClienteOutput;
import com.formacion.tripbackend.domain.Cliente;
import com.formacion.tripbackend.domain.Viaje;
import com.formacion.tripbackend.repository.ClienteRepository;
import com.formacion.tripbackend.repository.ViajeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

        @Autowired
        ClienteRepository clienteRepository;

        @Autowired
        ViajeRepository viajeRepository;

        public List<Viaje> getViajesFromIds(List<Integer> viajesIds) {
            if (viajesIds == null) {
                return List.of(); // Lista vacía
            }
            return viajesIds.stream()
                    .map(viajeId -> viajeRepository.findById(viajeId)
                            .orElseThrow(() -> new EntityNotFoundException("Viaje con ID " + viajeId + "NOT FOUND")))
                    .collect(Collectors.toList());
        }


        @Override
        public ClienteOutput addCliente(ClienteInput clienteInput) {
            List<Viaje> viajeList = getViajesFromIds(clienteInput.getListaViajes());
            Cliente cliente = new Cliente(clienteInput);
            cliente.setListaViajes(viajeList);
            ClienteOutput clienteOutput = clienteRepository.save(cliente).clienteToClienteOutput();
            viajeList.forEach(viaje -> {
                viaje.getListaPasajeros().add(cliente);
                viajeRepository.save(viaje);
            });
            return clienteOutput;
        }


        @Override
        public void deleteCliente(int id) {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Cliente con ID " + id + "NOT FOUND"));
            cliente.getListaViajes().forEach(viaje -> {
                viaje.getListaPasajeros().remove(cliente);
                viajeRepository.save(viaje);
            });
            clienteRepository.deleteById(id);
        }


        @Override
        public ClienteOutput updateCliente(Integer id, ClienteInput clienteInput) {
            Cliente cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Cliente con ID " + id + "NOT FOUND"));


            if (clienteInput.getListaViajes() != null) {
                updateClienteViajes(cliente, clienteInput.getListaViajes());
            }

            return clienteRepository.save(cliente).clienteToClienteOutput();
        }


        private void updateClienteViajes(Cliente cliente, List<Integer> viajesIds) {
            List<Viaje> viajeList = getViajesFromIds(viajesIds);
            cliente.getListaViajes().forEach(viaje -> {
                viaje.getListaPasajeros().remove(cliente);
                viajeRepository.save(viaje);
            });
            cliente.setListaViajes(viajeList);
            viajeList.forEach(viaje -> {
                viaje.getListaPasajeros().add(cliente);
                viajeRepository.save(viaje);
            });
        }


        @Override
        public List<ClienteOutput> getAllCliente(int pageNumber, int pageSize) {
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
            return clienteRepository.findAll(pageRequest).getContent()
                    .stream()
                    .map(Cliente::clienteToClienteOutput).toList();
        }


        @Override
        public ClienteOutput getCliente(int id) {
            Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente con ID " + id + "NOT FOUND"));
            return cliente.clienteToClienteOutput();
        }



        @Override
        public int countPasajeros(int idViaje) {
            Viaje viaje = viajeRepository.findById(idViaje).orElseThrow(() -> new EntityNotFoundException("Viaje con Id " + idViaje + "NOT FOUND"));
            return viaje.getListaPasajeros().size();
        }
}


