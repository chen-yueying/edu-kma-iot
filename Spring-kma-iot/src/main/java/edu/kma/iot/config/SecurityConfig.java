package edu.kma.iot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import edu.kma.iot.service.IOTUserDetailsService;
import edu.kma.iot.service.UserAuthProvider;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserAuthProvider userAuthPro;
	@Autowired
	private IOTUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/rest/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/user/**").hasAnyRole("ROLE_USER")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.usernameParameter("j_username")
		.passwordParameter("j_password")
		.loginProcessingUrl("/user_check_security")
		.defaultSuccessUrl("/user")
		.failureUrl("/login?error=1")
		.and()
		.logout()
		.logoutUrl("/user_logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID")
		.and()
		.rememberMe().tokenValiditySeconds(3600).rememberMeParameter("remember-me");
		http.sessionManagement().maximumSessions(5).expiredUrl("/user");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userAuthPro);
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}
