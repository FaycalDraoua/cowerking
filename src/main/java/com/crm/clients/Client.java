package com.crm.clients;

import com.crm.clientabonnement.ClientAbonnements;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
        @Id
        @GeneratedValue
        private UUID id;

        @Column(nullable = false)
        private String nom;

        @Column(nullable = false)
        private String prenom;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false)
        private String telephone;

        @Column()
        private LocalDate dateNaissance;  // Alternatively, use LocalDate

        @Column
        private String adressePostale;

        @Column
        private String nomEntreprise;

        @Column
        private String statutProfessionnel;

        @Column
        private String secteurActivite;

        @Column(unique = true)
        private String identifiant;

        @Column
        private String motDePasse; // Will be null if the client is added manually

        @Column
        private boolean consentementCgv;

        @Column
        private boolean autorisationNewsletter;

        @Column
        private boolean autorisationImages;

        @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference
        private List<ClientAbonnements> clientAbonnements;
}
