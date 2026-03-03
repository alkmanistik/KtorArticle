package ru.alkmanistik.spring_article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alkmanistik.spring_article.dto.ClientDto;
import ru.alkmanistik.spring_article.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientDto.ClientResponse> createClient(@RequestBody ClientDto.CreateClientRequest request) {
        ClientDto.ClientResponse client = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientDto.ClientResponse>> getAllClients() {
        List<ClientDto.ClientResponse> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto.ClientResponse> getClientById(@PathVariable Integer id) {
        ClientDto.ClientResponse client = clientService.getClientById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<ClientDto.ClientResponse>> searchClients(@RequestBody ClientDto.SearchClientRequest request) {
        List<ClientDto.ClientResponse> clients = clientService.searchClientsByName(request.getName());
        return ResponseEntity.ok(clients);
    }

    @PutMapping
    public ResponseEntity<ClientDto.ClientResponse> updateClient(@RequestBody ClientDto.UpdateClientRequest request) {
        ClientDto.ClientResponse client = clientService.updateClient(request);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) {
        boolean deleted = clientService.deleteClient(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}