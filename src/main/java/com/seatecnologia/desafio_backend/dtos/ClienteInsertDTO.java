package com.seatecnologia.desafio_backend.dtos;

import java.util.ArrayList;
import java.util.List;

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
public class ClienteInsertDTO extends ClienteDTO{
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String nome;
	
	@NotBlank
	@Pattern(regexp = "(\\d{3}.?\\d{3}.?\\d{3}-?\\d{2})", message = "O CPF deve conter 11 dígitos numéricos")
	private String cpf;
	
	private String complemento;
	
	@NotBlank
	@Pattern(regexp = "\\d{5}-?\\d{3}", message = "O CEP deve conter 8 dígitos numéricos")
	private String cep;
	
	@NotEmpty(message = "O Cliente deve ter pelo menos um telefone")
	private List<TelefoneDTO> telefones = new ArrayList<>();
	
	@NotEmpty(message = "O Cliente deve ter pelo menos um email")
	private List<EmailDTO> emails = new ArrayList<>();
	
	public ClienteInsertDTO() {}
}
