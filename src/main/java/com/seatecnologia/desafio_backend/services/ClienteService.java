package com.seatecnologia.desafio_backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seatecnologia.desafio_backend.dtos.ClienteDTO;
import com.seatecnologia.desafio_backend.dtos.ClienteInsertDTO;
import com.seatecnologia.desafio_backend.dtos.EmailDTO;
import com.seatecnologia.desafio_backend.dtos.TelefoneDTO;
import com.seatecnologia.desafio_backend.entities.Cliente;
import com.seatecnologia.desafio_backend.entities.Email;
import com.seatecnologia.desafio_backend.entities.Endereco;
import com.seatecnologia.desafio_backend.entities.Telefone;
import com.seatecnologia.desafio_backend.repositories.ClienteRepository;
import com.seatecnologia.desafio_backend.services.exceptions.EntityNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private ViaCepService cepService;
	
	@Transactional
	public ClienteDTO insert(ClienteInsertDTO dto) {
	    Endereco endereco = cepService.buscarEndereco(dto.getCep());
	    endereco.setComplemento(dto.getComplemento());

	    Cliente cliente = new Cliente();
	    cliente.setEndereco(endereco);

	    copyDtoToEntity(dto, cliente);

	    cliente = repository.save(cliente);
	    
	    return new ClienteDTO(cliente, cliente.getTelefones(), cliente.getEmails());
	}
	
	@Transactional(readOnly = true)
	public Page<ClienteDTO> findAllPaged(Pageable pageable) {
		Page<Cliente> list = repository.findAll(pageable);	
		return list.map(x -> new ClienteDTO(x));
	}
	
	@Transactional(readOnly = true)
	public ClienteDTO findById(Long id) {
		Cliente cliente = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID:" + id));
		
		return new ClienteDTO(cliente, cliente.getTelefones(), cliente.getEmails());
	}
	
	@Transactional(readOnly = true)
	public ClienteDTO findByCpf(String cpf) {
		Cliente cliente = repository.findByCpf(cpf)
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o CPF:" + cpf));
		
		return new ClienteDTO(cliente, cliente.getTelefones(), cliente.getEmails());
	}
	
	@Transactional
	public ClienteDTO update(Long id ,ClienteInsertDTO dto) {
		
		Cliente cliente = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID:" + id));
		
		Endereco endereco = cepService.buscarEndereco(dto.getCep());
	    endereco.setComplemento(dto.getComplemento()); 
	    cliente.setEndereco(endereco);
	    
	    copyDtoToEntity(dto, cliente);
	    
	    cliente = repository.save(cliente);
	    
	    return new ClienteDTO(cliente, cliente.getTelefones(), cliente.getEmails());   
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			repository.findById(id).orElseThrow(
					() -> new EntityNotFoundException("Cliente não encontrado com o ID:" + id));	
			repository.deleteById(id);
			}
			catch(DataIntegrityViolationException e) {
				throw new DataIntegrityViolationException("Não foi possível deletar, entidade em conflito!");
			}
	}
	
	private void copyDtoToEntity(ClienteInsertDTO dto, Cliente entity) {
		entity.setNome(dto.getNome());
		entity.setCpf(dto.getCpf());

	    List<Telefone> telefones = new ArrayList<>();
	    List<Email> emails = new ArrayList<>();

	    // Adicionar telefones e emails às listas
	    for (TelefoneDTO telefoneDTO : dto.getTelefones()) {
	        Telefone telefone = new Telefone();
	        telefone.setTipoTelefone(telefoneDTO.getTipoTelefone());
	        telefone.setNumero(telefoneDTO.getNumero());
	        telefone.setCliente(entity);  // Associar ao cliente
	        telefones.add(telefone);  // Adicionar à lista
	    }

	    for (EmailDTO emailDTO : dto.getEmails()) {
	        Email email = new Email();
	        email.setEnderecoEmail(emailDTO.getEnderecoEmail());
	        email.setCliente(entity);  // Associar ao cliente
	        emails.add(email);  // Adicionar à lista
	    }

	    // Adicionar os objetos de telefone e email ao cliente
	    entity.getTelefones().addAll(telefones);
	    entity.getEmails().addAll(emails);
	}
	
}
