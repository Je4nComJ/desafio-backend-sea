package com.seatecnologia.desafio_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seatecnologia.desafio_backend.config.TokenService;
import com.seatecnologia.desafio_backend.dtos.UsuarioInsertDTO;
import com.seatecnologia.desafio_backend.entities.Usuario;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenservice;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody UsuarioInsertDTO usuarioDTO) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(usuarioDTO.getUsername(), usuarioDTO.getPassword());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenservice.generateToken((Usuario)auth.getPrincipal());
		
		return ResponseEntity.ok().body(token);
	}
}
