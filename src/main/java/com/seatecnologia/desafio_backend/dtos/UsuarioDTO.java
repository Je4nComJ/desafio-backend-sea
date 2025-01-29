package com.seatecnologia.desafio_backend.dtos;

import java.util.HashSet;
import java.util.Set;

import com.seatecnologia.desafio_backend.entities.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
	
	private Long id;
	
	private String username;
	
	private Set<RoleDTO> roles = new HashSet<>();
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(Usuario entity) {
		id = entity.getId();
		username = entity.getUsername();
	}
}
