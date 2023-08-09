package com.example.jpacascade.Services;

import com.example.jpacascade.Controllers.Dto.ClienteInputDto;
import com.example.jpacascade.Controllers.Dto.ClienteOutputDto;
import com.example.jpacascade.Entidades.Cliente;
import com.example.jpacascade.Repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteOutputDto createCliente(ClienteInputDto clienteInputDto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteInputDto.getNombre());

        Cliente clienteGuardado = clienteRepository.save(cliente);

        ClienteOutputDto clienteOutputDto = new ClienteOutputDto();
        clienteOutputDto.setId(clienteGuardado.getCodigoCliente());
        clienteOutputDto.setNombre(clienteGuardado.getNombre());

        return clienteOutputDto;
    }
    public List<ClienteOutputDto> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteOutputDto> clienteOutputDtos = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteOutputDto clienteOutputDto = new ClienteOutputDto();
            clienteOutputDto.setId(cliente.getCodigoCliente());
            clienteOutputDto.setNombre(cliente.getNombre());
            clienteOutputDtos.add(clienteOutputDto);
        }

        return clienteOutputDtos;
    }

}
