package com.crm.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientsRepository extends JpaRepository<Client, UUID> {

    @Query("SELECT c.nom AS nom, c.prenom AS prenom, c.email AS email, c.telephone AS telephone, a.type AS type " +
            "FROM ClientAbonnements ca " +
            "JOIN ca.client c " +
            "JOIN ca.abonnement a")

    List<ClientProjection> findAllProjectedBy();
}