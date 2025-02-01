package com.seatecnologia.desafio_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
