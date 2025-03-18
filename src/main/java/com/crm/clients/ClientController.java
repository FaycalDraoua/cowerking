package com.crm.clients;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Get all clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Get client by ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Create a new client
    @PostMapping
    ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            Client created = clientService.createClient(client);
            return ResponseEntity.status(HttpStatus.CREATED).body("client created");
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException constraintViolationException) {
                if (constraintViolationException.getConstraintName().contains("email")) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
                } else if (constraintViolationException.getConstraintName().contains("telephone")) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("phone number already exists");

                }
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    // Update a client
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable UUID id, @RequestBody Client client) {

        try {
            Optional<Client> updated = clientService.updateClient(id, client);
            return updated.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException constraintViolationException) {
                if (constraintViolationException.getConstraintName().contains("email")) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
                } else if (constraintViolationException.getConstraintName().contains("telephone")) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("phone number already exists");

                }
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    // Delete a client
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        boolean deleted = clientService.deleteClient(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
