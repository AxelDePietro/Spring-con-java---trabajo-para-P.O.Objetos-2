package com.oo2.grupo4.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.oo2.grupo4.entities.Login;

public class UserDetailsImpl implements UserDetails {

	private final Login login;

	public UserDetailsImpl(Login login) {
		this.login = login;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Obtenemos rol desde la Persona (Empleado o Cliente)
		String role = "ROLE_CLIENTE"; // default

		if (login.getPersona() instanceof com.oo2.grupo4.entities.Empleado) {
			role = ((com.oo2.grupo4.entities.Empleado) login.getPersona()).getRol();
			if (!role.startsWith("ROLE_")) {
				role = "ROLE_" + role.toUpperCase();
			}
		}
		return Collections.singletonList(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {
		return login.getContrasenia();
	}

	@Override
	public String getUsername() {
		return login.getCorreo();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Login getLogin() {
		return login;
	}
}
