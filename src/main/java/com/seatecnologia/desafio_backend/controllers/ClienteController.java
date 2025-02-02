package com.seatecnologia.desafio_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seatecnologia.desafio_backend.dtos.ClienteDTO;
import com.seatecnologia.desafio_backend.dtos.ClienteInsertDTO;
import com.seatecnologia.desafio_backend.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@PostMapping
	public ResponseEntity<ClienteDTO> insert (@RequestBody ClienteInsertDTO dto){
		ClienteDTO cliente = service.insert(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}
	
	@GetMapping
	public ResponseEntity<Page<ClienteDTO>> findAllPaged(Pageable pageable){
		Page<ClienteDTO> list = service.findAllPaged(pageable);
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Long id){
		ClienteDTO dto = service.findById(id);
		
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value = "/cpf/{cpf}")
	public ResponseEntity<ClienteDTO> findByCpf(@PathVariable String cpf){
		ClienteDTO dto = service.findByCpf(cpf);
		
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteInsertDTO dto){
		ClienteDTO updatedCliente = service.update(id, dto);
		
		return ResponseEntity.ok(updatedCliente);
	}
}
