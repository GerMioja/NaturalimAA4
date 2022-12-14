package com.naturalim.demo.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import com.naturalim.demo.service.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioServicio);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(
				(req) -> req
						.antMatchers("/registro**",	"/js/**","/css/**","/img/**","/app/home","/app/productos","/app/sform").permitAll()
						.antMatchers("/app/form").hasRole("ADMIN")
						.antMatchers("/app/Editar/**").hasRole("ADMIN")
						.antMatchers("/app/Eliminar/**").hasRole("ADMIN")
						.antMatchers("/app/sugerencias").hasRole("ADMIN")
						.antMatchers("/app/sEditar/**").hasRole("ADMIN")
						.antMatchers("/app/rform/**").hasRole("ADMIN")
						.antMatchers("/app/rEditar/**").hasRole("ADMIN")
						.antMatchers("/app/sEliminar/**").hasRole("ADMIN")
						.anyRequest().authenticated()
						.and()
		).formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll();
	}
}






