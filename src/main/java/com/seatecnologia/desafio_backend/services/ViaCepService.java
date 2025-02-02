package com.seatecnologia.desafio_backend.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.seatecnologia.desafio_backend.entities.Endereco;
import com.seatecnologia.desafio_backend.services.exceptions.AddressNotFoundException;

@Service
public class ViaCepService {
	
	private static final String URL = "https://viacep.com.br/ws/%s/json/";
	
	public Endereco buscarEndereco(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		Endereco endereco = restTemplate.getForObject(String.format(URL, cep), Endereco.class);
		
		if(endereco == null || endereco.getLogradouro() == null) {
			throw new AddressNotFoundException("CEP: " +cep+" inválido ou não encontrado");
		}
		
		return endereco;
	}
	
}
