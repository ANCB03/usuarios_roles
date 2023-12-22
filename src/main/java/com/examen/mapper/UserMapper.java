package com.examen.mapper;


import com.examen.entity.Usuario;
import com.examen.presentation.AuthorizationRequest;
import com.examen.presentation.UserResponse;

public class UserMapper {

	private UserMapper() {
	}

	public static UserResponse toResponse(Usuario user) {
		return UserResponse.builder().name(user.getEmail()).id(Integer.parseInt(user.getNumeroDocumento())).build();
	}

	public static Usuario toDomain(AuthorizationRequest authorizationRequest) {
		return Usuario.builder().email(authorizationRequest.getUserName()).password(authorizationRequest.getPassword())
				.build();
	}
}
