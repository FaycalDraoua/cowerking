package com.crm.clientabonnement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface clientabonnementRepository extends JpaRepository<ClientAbonnements, UUID> {


}
