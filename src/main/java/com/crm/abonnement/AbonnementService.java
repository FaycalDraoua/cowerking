package com.crm.abonnement;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbonnementService {
    private final AbonnementRepository abonnementRepository;

    public AbonnementService(AbonnementRepository abonnementRepository) {
        this.abonnementRepository = abonnementRepository;

    }
    public List<abonnement> getallabonnement (){
        return abonnementRepository.findAll();
    }
    public abonnement createAbonnement(abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }



}
