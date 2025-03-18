package com.crm.clientabonnement;

import com.crm.abonnement.AbonnementRepository;
import com.crm.abonnement.abonnement;
import com.crm.clients.Client;
import com.crm.clients.ClientsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class clientabonnementService {
    private final clientabonnementRepository clientabonnementRepository;
    private final AbonnementRepository abonnementRepository;
    private final ClientsRepository clientRepository;

    public clientabonnementService(clientabonnementRepository clientabonnementRepository, AbonnementRepository abonnementRepository, ClientsRepository clientRepository) {
        this.clientabonnementRepository = clientabonnementRepository;
        this.abonnementRepository = abonnementRepository;
        this.clientRepository = clientRepository;
    }

    public ClientAbonnements assignAbonnementToClient(UUID clientId, UUID abonnementId, String optionsSupplementaires) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new IllegalArgumentException("Client not found"));
        abonnement abonnement = abonnementRepository.findById(abonnementId).orElseThrow(() -> new IllegalArgumentException("Abonnement not found"));

        LocalDate dateDebut = LocalDate.now();
        LocalDate dateFin;

        switch (abonnement.getDuree().toLowerCase()) {
            case "mensuel":
                dateFin = dateDebut.plusMonths(1);
                break;
            case "annuel":
                dateFin = dateDebut.plusYears(1);
                break;
            default:
                throw new IllegalArgumentException("Unsupported abonnement duree: " + abonnement.getDuree());
        }

        ClientAbonnements clientAbonnements = new ClientAbonnements();
        clientAbonnements.setClient(client);
        clientAbonnements.setAbonnement(abonnement);
        clientAbonnements.setDateDebut(java.sql.Date.valueOf(dateDebut));
        clientAbonnements.setDateFin(java.sql.Date.valueOf(dateFin));
        clientAbonnements.setStatut(true);
        clientAbonnements.setOptionsSupplementaires(optionsSupplementaires);

        return clientabonnementRepository.save(clientAbonnements);

    }

    @Scheduled(cron = " 0 1 0 * * ?") // Runs every day at midnight
    public void updateExpiredAbonnements() {
        List<ClientAbonnements> abonnements = clientabonnementRepository.findAll();
        LocalDate today = LocalDate.now();

        for (ClientAbonnements abonnement : abonnements) {
            LocalDate dateFin = abonnement.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (dateFin.isBefore(today) && abonnement.isStatut()) {
                abonnement.setStatut(false);
                clientabonnementRepository.save(abonnement);
            }
        }
    }
        }


