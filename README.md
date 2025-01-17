# Multi-Tenancy Spring Boot Application

This is a Spring Boot application that demonstrates multi-tenancy with a single database using Hibernate's `@TenantId`.
The application supports tenant-specific data segregation and routing, along with API endpoints for resource management
and user CRUD operations.

---

## Features

- **Multi-Tenancy**:
    - Implements single database multi-tenancy using Hibernate's `@TenantId`.
    - Separates data by tenants using a `tenant_id` discriminator column.

- **Dynamic Tenant Resolution**:
    - Resolves tenants dynamically from the `X-Tenant-ID` HTTP header using a request filter.

- **Resource Routing**:
    - Routes requests to tenant-specific resources dynamically.

- **Fault Tolerance** (Optional Enhancements):
    - Ready to integrate with Resilience4j for circuit breakers and retries.

- **H2 Console**:
    - Excludes `/h2-console` from tenant filtering for easy database inspection during development.

 
