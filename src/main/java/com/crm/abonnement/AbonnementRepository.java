package com.crm.abonnement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AbonnementRepository extends JpaRepository<abonnement, UUID> {
}
