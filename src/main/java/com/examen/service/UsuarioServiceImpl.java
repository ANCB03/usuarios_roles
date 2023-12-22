package com.examen.service;


import com.examen.dto.UsuarioDto;
import com.examen.entity.Usuario;
import com.examen.mapper.UserDetailsMapper;
import com.examen.repository.RolRepository;
import com.examen.repository.UsuarioRepository;
import com.examen.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Locale;
@Slf4j
@Service("userDetailsService")
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

	private RolRepository roleRepository;

	private UsuarioRepository userRepository;

	@Autowired
	private MessageUtil messageUtil;



	@Autowired
	public UsuarioServiceImpl(UsuarioRepository userRepository, RolRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		final Usuario retrievedUser = userRepository.findByEmail(userName).get();
		log.info(userName);
		if (retrievedUser == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}

		return UserDetailsMapper.build(retrievedUser);
	}


	@Override
	public List<UsuarioDto> listarUsuarios() {
		return null;
	}

	@Override
	public void guardar(UsuarioDto usuarioDto) {

	}

	@Override
	public void eliminar(String documento) {

	}

	@Override
	public UsuarioDto encontrarUsuarioByDocumento(String documento) {
		return null;
	}

	@Override
	public UsuarioDto editarUsuario(String documento, UsuarioDto usuarioDto) {
		return null;
	}
}