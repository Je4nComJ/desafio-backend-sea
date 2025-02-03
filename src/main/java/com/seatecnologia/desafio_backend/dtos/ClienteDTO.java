package com.seatecnologia.desafio_backend.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seatecnologia.desafio_backend.entities.Cliente;
import com.seatecnologia.desafio_backend.entities.Email;
import com.seatecnologia.desafio_backend.entities.Endereco;
import com.seatecnologia.desafio_backend.entities.Telefone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClienteDTO {
	
	private Long id;
	private String nome;
	private String cpf;
	private Endereco endereco;
	
	@JsonProperty("telefones")
	private List<TelefoneDTO> telefones = new ArrayList<>();
	
	@JsonProperty("emails")
	private List<EmailDTO> emails = new ArrayList<>();
	
	public ClienteDTO() {}
	
	public ClienteDTO(Cliente entity) {
		id = entity.getId();
		nome = entity.getNome();
		cpf = entity.getCpf();
		endereco = entity.getEndereco();
	}
	
	public ClienteDTO(Cliente entity, Set<Telefone> telefones, Set<Email> emails) {
		this(entity);
		
		telefones.forEach(telefone -> this.telefones.add(new TelefoneDTO(telefone)));
		 
		emails.forEach(email -> this.emails.add(new EmailDTO(email)));	
	}
	
}
