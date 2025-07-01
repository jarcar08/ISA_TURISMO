package com.example.demo.config;

import com.example.demo.security.CustomSuccessHandler;
import com.example.demo.security.UsuarioDetalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	private final UsuarioDetalleService usuarioDetalleService;
    private final CustomSuccessHandler customSuccessHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/homeAdmin").hasRole("ADMIN")
                .requestMatchers("/homeGuest").hasRole("GUEST")
                .requestMatchers("/persona/ListaPersona").hasRole("ADMIN")
                .requestMatchers("/persona/eliminar").hasRole("ADMIN")
                .requestMatchers("/usuario/ListaUsuario").hasRole("ADMIN")
                .requestMatchers("/usuario/eliminar/**").hasRole("ADMIN")
                .requestMatchers("/tarifas/ListaTarifas/**").hasRole("ADMIN")
                .requestMatchers("/tarifas/eliminar/**").hasRole("ADMIN")
                .requestMatchers("/tipozonas/ListaTipoZonas/**").hasRole("ADMIN")
                .requestMatchers("/tipozonas/eliminar/**").hasRole("ADMIN") 
                .requestMatchers("/trenes/ListaTrenes/**").hasRole("ADMIN") 
                .requestMatchers("/trenes/eliminar/**").hasRole("ADMIN")
                .requestMatchers("/rol/ListaRoles").hasRole("ADMIN")
                .requestMatchers("/rol/eliminar").hasRole("ADMIN")  
                .requestMatchers("/clima/ListaClima").hasRole("ADMIN")
                .requestMatchers("/clima/eliminar").hasRole("ADMIN")
                .requestMatchers("/estacion/ListaEstacion").hasRole("ADMIN")
                .requestMatchers("/estacion/eliminar").hasRole("ADMIN")
                .requestMatchers("/horario/ListaHorario").hasRole("ADMIN")
                .requestMatchers("/horario/eliminar").hasRole("ADMIN")
                .requestMatchers("/zona/ListaZonaTuristica").hasRole("ADMIN")
                .requestMatchers("/zona/eliminar").hasRole("ADMIN")
                .requestMatchers("/tipopasajero/ListaZonaTuristica").hasRole("ADMIN")
                .requestMatchers("/tipopasajero/eliminar").hasRole("ADMIN")           
                .requestMatchers("/","/registro", "/login", "/assets/**", "/img/**", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable())
            .formLogin(login -> login
                .loginPage("/login")
                .successHandler(customSuccessHandler)
                .permitAll()
            )
            .logout(logout -> logout
            .logoutSuccessUrl("/")
            .permitAll()
            )
            .exceptionHandling(ex -> ex.accessDeniedPage("/error/403"))
            .build();
    }
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(usuarioDetalleService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}