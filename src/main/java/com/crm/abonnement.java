package com.crm;



import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

    @Entity
    @Table(name = "abonnements")
    @Data
    public class abonnement {
        @Id
        @GeneratedValue
        private UUID id;

        @Column(nullable = false)
        private String nom; // e.g., "Abonnement Mensuel"

        @Column(nullable = false)
        private String type; // e.g., coworking, salle_reunion, etc.

        @Column(nullable = false)
        private String duree; // e.g., "mensuel", "annuel"

        @Column(nullable = false)
        private double tarification;

        @Column
        private String description;
    }


