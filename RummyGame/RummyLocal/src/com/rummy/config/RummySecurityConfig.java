package com.rummy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class RummySecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
		authentication
			.inMemoryAuthentication()
			.withUser("rummyAdmin").password("P@s$w0rd").roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		security
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login*", "/register*", "/**").permitAll()
			//.anyRequest().authenticated()
		.and()
			.requiresChannel()
			.anyRequest().requiresSecure()
		.and()
			.portMapper().http(8090).mapsTo(8443)
		.and()
			.formLogin()
			.loginPage("/loginUser");
		// WOULD LIKE TO GET HERE BUT POST DOESN'T WORK FOR LOGIN
//		security
//			.csrf().disable()
//			.authorizeRequests()
//			.antMatchers("/login*").permitAll()
//			.anyRequest().authenticated()
//		.and()
//			.requiresChannel()
//			.anyRequest().requiresSecure()
//		.and()
//			.portMapper().http(8080).mapsTo(8443)
//		.and()
//			.sessionManagement()
//			.sessionFixation()
//			.none()
//		.and()
//			.formLogin()
//			.loginPage("/loginUser")
//			.loginProcessingUrl("/loginUserSubmit")
//			.defaultSuccessUrl("/welcome")
//			.failureUrl("/loginUser?error=true")
//		.and()
//			.logout()
//			.logoutUrl("/logoutUser")
//			.deleteCookies("JSESSIONID");
	}
}
