package com.rummy.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class RummySecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	public RummySecurityInitializer() {
		super(RummySecurityConfig.class);
	}

}
