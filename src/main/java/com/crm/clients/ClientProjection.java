package com.crm.clients;

import java.util.UUID;

public interface ClientProjection {
    UUID getId();
    String getNom();
    String getPrenom();
    String getEmail();
    String getTelephone();
    boolean getStatut();
}