package com.crm;

import com.crm.clients.Client;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "reservations")
@Data
public class reservation {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateReservation;

    @Column(nullable = false)
    private String heureDebut;

    @Column(nullable = false)
    private String heureFin;

    @Column(nullable = false)
    private String statut; // e.g., "confirmée", "annulée", "terminée"
}

