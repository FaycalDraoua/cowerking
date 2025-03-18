package com.crm.abonnement;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abonnements")
public class AbonnementController {
    AbonnementService abonnementService;
    public AbonnementController(AbonnementService abonnementService) {
        this.abonnementService = abonnementService;
    }

    @GetMapping
    public List<abonnement> getAbonnements() {
        return abonnementService.getallabonnement();
    }

    @PostMapping()
    public abonnement createnewAbonnement(@RequestBody abonnement newAbonnement) {
        return abonnementService.createAbonnement(newAbonnement);
    }
}

