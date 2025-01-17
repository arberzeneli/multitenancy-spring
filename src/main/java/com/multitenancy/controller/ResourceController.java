package com.multitenancy.controller;

import com.multitenancy.service.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ResourceController {

    private final TenantService tenantService;

    public ResourceController(TenantService tenantService) {
        this.tenantService = tenantService;
    }


    @GetMapping("/resource")
    public ResponseEntity<?> handleRequest(@RequestHeader("X-Tenant-ID") String tenantId) {
        Optional<String> resourceUrl = tenantService.getTenantResourceUrl(tenantId);

        if (resourceUrl.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tenant not found");
        }

        return ResponseEntity.ok("Routed to resource: " + resourceUrl.get());
    }
}
