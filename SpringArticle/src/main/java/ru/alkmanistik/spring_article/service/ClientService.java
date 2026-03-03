package ru.alkmanistik.spring_article.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alkmanistik.spring_article.dto.ClientDto;
import ru.alkmanistik.spring_article.model.Client;
import ru.alkmanistik.spring_article.repository.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDto.ClientResponse> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(ClientDto.ClientResponse::new)
                .collect(Collectors.toList());
    }

    public ClientDto.ClientResponse getClientById(Integer id) {
        return clientRepository.findById(id)
                .map(ClientDto.ClientResponse::new)
                .orElse(null);
    }

    public List<ClientDto.ClientResponse> searchClientsByName(String name) {
        return clientRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(ClientDto.ClientResponse::new)
                .collect(Collectors.toList());
    }

    public ClientDto.ClientResponse createClient(ClientDto.CreateClientRequest request) {
        Client client = new Client(request.getName());
        Client saved = clientRepository.save(client);
        return new ClientDto.ClientResponse(saved);
    }

    public ClientDto.ClientResponse updateClient(ClientDto.UpdateClientRequest request) {
        Client existingClient = clientRepository.findById(request.getId()).orElse(null);
        if (existingClient == null) {
            return null;
        }

        existingClient.setName(request.getName());
        Client updated = clientRepository.save(existingClient);
        return new ClientDto.ClientResponse(updated);
    }

    public boolean deleteClient(Integer id) {
        if (!clientRepository.existsById(id)) {
            return false;
        }
        clientRepository.deleteById(id);
        return true;
    }
}