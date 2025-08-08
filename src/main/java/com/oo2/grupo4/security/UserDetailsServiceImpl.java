package com.oo2.grupo4.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oo2.grupo4.entities.Login;
import com.oo2.grupo4.repositories.ILoginRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final ILoginRepository loginRepository;

	public UserDetailsServiceImpl(ILoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		Optional<Login> loginOpt = loginRepository.findByCorreo(correo);
		if (loginOpt.isEmpty()) {
			throw new UsernameNotFoundException("Usuario no encontrado con correo: " + correo);
		}
		return new UserDetailsImpl(loginOpt.get());
	}
}
