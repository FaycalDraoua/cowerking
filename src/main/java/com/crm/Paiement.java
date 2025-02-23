package com.crm;

import com.crm.clients.Client;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "paiements")
@Data
public class Paiement {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "abonnement_id", nullable = false)
    private abonnement abonnement;

    @Column(nullable = false)
    private double montant;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePaiement;

    @Column(nullable = false)
    private String modePaiement; // e.g., "carte bancaire", "virement", "espèces"

    @Column(columnDefinition = "TEXT")
    private String factureDetails;
}
