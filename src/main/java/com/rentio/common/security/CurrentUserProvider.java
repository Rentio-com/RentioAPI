package com.rentio.common.security;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class CurrentUserProvider {
    
    public UUID getCompanyId() {
        return UUID.fromString("c0000000-0000-0000-0000-000000000001");
    }

    public UUID getUserId() {
        return UUID.fromString("f1111111-1111-1111-1111-111111111111");
    }
}
