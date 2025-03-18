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
    public List<Client> getAllClients() {
        return clientRepository.findAll();
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
            if (clientDetails.getNom() != null && !clientDetails.getNom().isEmpty()) {
                client.setNom(clientDetails.getNom());
            }
            if (clientDetails.getPrenom() != null && !clientDetails.getPrenom().isEmpty()) {
                client.setPrenom(clientDetails.getPrenom());
            }
            if (clientDetails.getEmail() != null && !clientDetails.getEmail().isEmpty()) {
                client.setEmail(clientDetails.getEmail());
            }
            if (clientDetails.getTelephone() != null && !clientDetails.getTelephone().isEmpty()) {
                client.setTelephone(clientDetails.getTelephone());
            }
            if (clientDetails.getDateNaissance() != null) {
                client.setDateNaissance(clientDetails.getDateNaissance());
            }
            if (clientDetails.getAdressePostale() != null && !clientDetails.getAdressePostale().isEmpty()) {
                client.setAdressePostale(clientDetails.getAdressePostale());
            }
            if (clientDetails.getNomEntreprise() != null && !clientDetails.getNomEntreprise().isEmpty()) {
                client.setNomEntreprise(clientDetails.getNomEntreprise());
            }
            if (clientDetails.getStatutProfessionnel() != null && !clientDetails.getStatutProfessionnel().isEmpty()) {
                client.setStatutProfessionnel(clientDetails.getStatutProfessionnel());
            }
            if (clientDetails.getSecteurActivite() != null && !clientDetails.getSecteurActivite().isEmpty()) {
                client.setSecteurActivite(clientDetails.getSecteurActivite());
            }
            if (clientDetails.getIdentifiant() != null && !clientDetails.getIdentifiant().isEmpty()) {
                client.setIdentifiant(clientDetails.getIdentifiant());
            }
            if (clientDetails.getMotDePasse() != null && !clientDetails.getMotDePasse().isEmpty()) {
                client.setMotDePasse(clientDetails.getMotDePasse());
            }
            client.setConsentementCgv(clientDetails.isConsentementCgv());
            client.setAutorisationNewsletter(clientDetails.isAutorisationNewsletter());
            client.setAutorisationImages(clientDetails.isAutorisationImages());
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
