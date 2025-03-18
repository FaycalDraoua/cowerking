package com.crm.clientabonnement;



import com.crm.clients.Client;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

    @Entity
    @Table(name = "client_abonnements")
    @Data
    public class ClientAbonnements {
        @Id
        @GeneratedValue
        private UUID id;

        @ManyToOne
        @JsonBackReference
        @JoinColumn(name = "client_id", nullable = false)
        private Client client;

        @ManyToOne
        @JoinColumn(name = "abonnement_id", nullable = false)
        private com.crm.abonnement.abonnement abonnement;

        @Column(nullable = false)
        @Temporal(TemporalType.DATE)
        private Date dateDebut;

        @Column(nullable = false)
        @Temporal(TemporalType.DATE)
        private Date dateFin;

        @Column(nullable = false)
        private boolean statut; // true = actif, false = expiré

        @Column(columnDefinition = "TEXT")
        private String optionsSupplementaires;
    }


