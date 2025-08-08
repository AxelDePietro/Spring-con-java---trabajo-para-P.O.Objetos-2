package com.oo2.grupo4.services.implementation;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.oo2.grupo4.dto.LoginCreateDTO;
import com.oo2.grupo4.entities.Login;
import com.oo2.grupo4.entities.Persona;
import com.oo2.grupo4.repositories.ILoginRepository;
import com.oo2.grupo4.services.interfaces.ILoginService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService implements ILoginService {

	private final ILoginRepository loginRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public boolean existsByCorreo(String correo) {
		return loginRepository.existsByCorreo(correo);
	}

	@Override
	public Optional<Login> validarCredenciales(String correo) {
		return loginRepository.findByCorreo(correo);
	}

	public Login crearLogin(@ModelAttribute ("login") LoginCreateDTO dto, Persona persona) {

		Login login = new Login();
		login.setCorreo(dto.correo());
		login.setContrasenia(passwordEncoder.encode(dto.contrasenia()));
		login.setPersona(persona);

		return loginRepository.save(login);
	}

}
