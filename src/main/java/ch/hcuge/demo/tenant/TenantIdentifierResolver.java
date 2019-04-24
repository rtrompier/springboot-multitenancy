package ch.hcuge.demo.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private final String DEFAULT_TENANT_ID = "concerto";

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = ThreadLocalStorage.getTenantName();
        if (tenantId != null) {
            return tenantId;
        }
        return DEFAULT_TENANT_ID;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
