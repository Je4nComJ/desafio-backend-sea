package com.seatecnologia.desafio_backend.dtos;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClienteInsertDTO {
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String nome;
	
	@NotBlank
	@Pattern(regexp = "(\\d{3}.?\\d{3}.?\\d{3}-?\\d{2})", message = "O CPF deve conter 11 dígitos numéricos")
	private String cpf;
	
	@NotBlank
	@Pattern(regexp = "\\d{5}-?\\d{3}", message = "O CEP deve conter 8 dígitos numéricos")
	private String cep;
	
	private String complemento;
	
	@NotEmpty(message = "O Cliente deve ter pelo menos um telefone")
	private Set<TelefoneDTO> telefones = new HashSet<>();
	
	@NotEmpty(message = "O Cliente deve ter pelo menos um email")
	private Set<EmailDTO> emails = new HashSet<>();
	
	public ClienteInsertDTO() {}
}
