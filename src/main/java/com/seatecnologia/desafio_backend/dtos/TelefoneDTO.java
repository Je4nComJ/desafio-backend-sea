package com.seatecnologia.desafio_backend.dtos;

import com.seatecnologia.desafio_backend.entities.Telefone;
import com.seatecnologia.desafio_backend.entities.TipoTelefone;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TelefoneDTO {
	
	private Long id;
	
	private TipoTelefone tipoTelefone;
	
	@NotBlank(message = "O número de telefone não pose estar em branco")
	private String numero;
	
	public TelefoneDTO() {}
	
	public TelefoneDTO(Telefone entity) {
		tipoTelefone = entity.getTipoTelefone();
		numero = entity.getNumero();
	}
}
