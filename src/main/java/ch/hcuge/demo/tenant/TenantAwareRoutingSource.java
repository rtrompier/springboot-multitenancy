package ch.hcuge.demo.tenant;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

public class TenantAwareRoutingSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String tenant = ThreadLocalStorage.getTenantName();
        if(StringUtils.isEmpty(tenant)) {
            return "concerto";
        }

        return tenant;
    }

}