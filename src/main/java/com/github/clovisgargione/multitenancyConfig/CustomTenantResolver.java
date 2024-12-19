package com.github.clovisgargione.multitenancyConfig;

import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@PersistenceUnitExtension 
@RequestScoped
public class CustomTenantResolver implements TenantResolver {

	@Inject
    private RoutingContext context;
		
	@Override
	public String getDefaultTenantId() {
		return "base";
	}

	@Override
	public String resolveTenantId() {
		String tenant = context.request().getHeader("tenant");
		/*String path = context.request().path();
        String[] parts = path.split("/");

        if (parts.length == 0) {
            // resolve to default tenant config
            return getDefaultTenantId();
        }*/
		
		if (tenant == null) {
            // resolve to default tenant config
            return getDefaultTenantId();
        }

        return tenant;
	}

}
