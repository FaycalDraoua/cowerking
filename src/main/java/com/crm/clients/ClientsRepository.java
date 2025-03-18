package com.crm.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientsRepository extends JpaRepository<Client, UUID> {

    @Query("SELECT c.id as id ,c.nom AS nom, c.prenom AS prenom, c.email AS email, c.telephone AS telephone, ca.statut AS statut " +
            "FROM ClientAbonnements ca " +
            "JOIN ca.client c " +
            "JOIN ca.abonnement a")
    List<ClientProjection> findAllProjectedBy();

    List<Client> nom(String nom);

    @Query("SELECT c.nom FROM Client c WHERE c.id = ?1")
    Optional<String> findnamebyid(UUID id);

}