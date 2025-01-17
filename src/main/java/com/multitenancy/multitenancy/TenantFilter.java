package com.multitenancy.multitenancy;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
class TenantFilter extends OncePerRequestFilter {

    private final Map<String, String> varFiltersCg = new HashMap<>(); // __define-ocg__
    @Autowired
    TenantIdentifierResolver tenantIdentifierResolver;


    public TenantFilter() {
        varFiltersCg.put("tenant-1", "filter1");
        varFiltersCg.put("tenant-2", "filter2");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        String requestUri = request.getRequestURI();
        if (requestUri.startsWith("/h2-console")) {
            filterChain.doFilter(request, response);
            return;
        }

        String tenantId = request.getHeader("X-Tenant-ID");
        boolean b = varFiltersCg.containsKey(tenantId);
        if (tenantId == null || !b) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing or invalid X-Tenant-ID header");
            return;
        }

        // Adding a tenant-specific attribute for further processing
        request.setAttribute("tenantFilter", varFiltersCg.get(tenantId));


        try {
            TenantContext.setCurrentTenant(tenantId);
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}
