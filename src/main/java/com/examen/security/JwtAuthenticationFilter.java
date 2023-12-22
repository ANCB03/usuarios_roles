package com.examen.security;

import com.examen.dto.UserLoginDto;
import com.examen.presentation.AuthorizationRequest;
import com.examen.util.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.examen.util.Constants.HEADER_AUTHORIZATION_KEY;
import static com.examen.util.Constants.TOKEN_BEARER_PREFIX;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		super.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			AuthorizationRequest userCredentials = new ObjectMapper().readValue(request.getInputStream(), AuthorizationRequest.class);

			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userCredentials.getUserName(), userCredentials.getPassword()));
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException {
		String token = TokenProvider.generateToken(authResult);
		response.addHeader(HEADER_AUTHORIZATION_KEY, TOKEN_BEARER_PREFIX + " " + token);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");

		//CONSULTA DEL USUARIO ANTES PARA ENVIAR MAS DATA QUE SIRVE EN EL FRONTEND....

		response.getWriter().write(new ObjectMapper().writeValueAsString(new UserLoginDto(token, authResult.getName(), authResult.getAuthorities().toArray()[0]+"")));
	}
}
