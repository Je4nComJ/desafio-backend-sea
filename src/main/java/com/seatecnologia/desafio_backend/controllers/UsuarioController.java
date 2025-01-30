package com.seatecnologia.desafio_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seatecnologia.desafio_backend.dtos.UsuarioDTO;
import com.seatecnologia.desafio_backend.dtos.UsuarioInsertDTO;
import com.seatecnologia.desafio_backend.services.UsuarioService;

@RestController
@RequestMapping(value = "/users")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public ResponseEntity<Page<UsuarioDTO>> findAll(Pageable pageable){
		Page<UsuarioDTO> list = service.findAllPaged(pageable);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
		UsuarioDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> insert (@RequestBody UsuarioInsertDTO dto){
		UsuarioDTO newDto = service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(newDto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO dto){
		dto = service.update(id, dto);
		
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}	
}
