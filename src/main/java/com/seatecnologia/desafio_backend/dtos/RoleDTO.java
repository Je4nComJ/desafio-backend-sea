package com.seatecnologia.desafio_backend.dtos;

import com.seatecnologia.desafio_backend.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RoleDTO {
	
	private Long id;
	
	private String authority;
	
	public RoleDTO() {}
	
	public RoleDTO(Role entity) {
		id = entity.getId();
		authority = entity.getAuthority();
	}
}
