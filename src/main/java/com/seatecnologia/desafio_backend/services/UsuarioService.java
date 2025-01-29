package com.seatecnologia.desafio_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seatecnologia.desafio_backend.dtos.RoleDTO;
import com.seatecnologia.desafio_backend.dtos.UsuarioDTO;
import com.seatecnologia.desafio_backend.entities.Role;
import com.seatecnologia.desafio_backend.entities.Usuario;
import com.seatecnologia.desafio_backend.repositories.RoleRepository;
import com.seatecnologia.desafio_backend.repositories.UsuarioRepository;
import com.seatecnologia.desafio_backend.services.exceptions.EntityNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAllPaged(Pageable pageable){
		Page<Usuario> list = repository.findAll(pageable);
		return list.map(x -> new UsuarioDTO(x));
	}
	
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		Usuario usuario = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado!"));
		return new UsuarioDTO(usuario);
	}
	
	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {
		Usuario entity = new Usuario();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new UsuarioDTO(entity);	
	}
	
	@Transactional
	public UsuarioDTO update(Long id, UsuarioDTO dto) {
		Usuario entity = repository.findById(id).orElseThrow(
				() ->new EntityNotFoundException("Usuário não encontrado!"));
		
		copyDtoToEntity(dto, entity);
		Usuario savedUsuario= repository.save(entity);
		return new UsuarioDTO(savedUsuario);
	}
	
	@Transactional
	public void delete(Long id) {
		try {
		repository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("Usuário não encontrado!"));	
		repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não foi possível deletar, entidade em conflito!");
		}
	}
	
	private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setUsername(dto.getUsername());
		
		entity.getRoles().clear();
		for (RoleDTO roleDTO : dto.getRoles()) {
			Role role = roleRepository.getReferenceById(roleDTO.getId());
			entity.getRoles().add(role);
		}
	}
}
