package com.multitenancy.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TenantService {

    private final Map<String, String> tenantConfigurations = new HashMap<>();

    public TenantService() {
        tenantConfigurations.put("tenant-1", "http://google.com");
        tenantConfigurations.put("tenant-2", "http://facebook.com");
    }

    public Optional<String> getTenantResourceUrl(String tenantId) {
        return Optional.ofNullable(tenantConfigurations.get(tenantId));
    }
}