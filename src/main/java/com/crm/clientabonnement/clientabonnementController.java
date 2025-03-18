package com.crm.clientabonnement;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/clientabonnements")
public class clientabonnementController {
    private final clientabonnementService clientabonnementService;

    public clientabonnementController(clientabonnementService clientabonnementService) {
        this.clientabonnementService = clientabonnementService;
    }


    @PostMapping("/assign")
    public ClientAbonnements assignAbonnementToClient(
            @RequestParam UUID clientId,
            @RequestParam UUID abonnementId,

            @RequestParam String optionsSupplementaires) {
        return clientabonnementService.assignAbonnementToClient(clientId, abonnementId, optionsSupplementaires);

    }

}