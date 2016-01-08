package com.startup.enginizer.config;

import com.startup.enginizer.security.DatasourceAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${crowdfunding.datasource.inMemoryDb}")
	private boolean inMemoryDb;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
			if(!inMemoryDb){
				authBuilder.authenticationProvider(datasourceAuthenticationProvider());
			}else{
				authBuilder.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMINISTRATOR");
				authBuilder.inMemoryAuthentication().withUser("consultant").password("consultant").roles("CONSULTANT");
				authBuilder.inMemoryAuthentication().withUser("client").password("client").roles("CLIENT");
			}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
		.antMatchers("/home**").access("hasAnyRole('ADMINISTRATOR','CONSULTANT','CLIENT')")
		.antMatchers("/faq**").access("hasAnyRole('ADMINISTRATOR','CONSULTANT','CLIENT')")
		.antMatchers("/consultants**").access("hasRole('ADMINISTRATOR')")
		.antMatchers("/myorders**").access("hasAnyRole('CONSULTANT','CLIENT')")
		.antMatchers("/consultantDetails**").access("hasRole('ADMINISTRATOR')")
		.antMatchers("/clients**").access("hasRole('ADMINISTRATOR')")
        .antMatchers("/myOrderDetails**").access("hasAnyRole('CONSULTANT','CLIENT')")
		.and().formLogin().loginPage("/login").failureUrl("/login?loginError=true")
		.and().exceptionHandling().accessDeniedPage("/denied")
		.and().logout().logoutUrl("/logout").and().csrf().disable();
	}

	@Bean
	public AuthenticationProvider datasourceAuthenticationProvider(){
		DatasourceAuthenticationProvider provider = new DatasourceAuthenticationProvider();
		return provider;
	}
}
