package com.crm.clients;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientsRepository clientRepository;

    @Autowired
    public ClientService(ClientsRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Get all clients
    public List<ClientProjection> getAllClients() {
        return clientRepository.findAllProjectedBy( );
    }

    // Get client by ID
    public Optional<Client> getClientById(UUID id) {
        return clientRepository.findById(id);
    }

    // Create a new client
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // Update an existing client
    public Optional<Client> updateClient(UUID id, Client clientDetails) {
        return clientRepository.findById(id).map(client -> {
            client.setNom(clientDetails.getNom());
            client.setPrenom(clientDetails.getPrenom());
            client.setEmail(clientDetails.getEmail());
            client.setTelephone(clientDetails.getTelephone());
            // Update other fields as needed...
            return clientRepository.save(client);
        });
    }

    // Delete a client
    public boolean deleteClient(UUID id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
