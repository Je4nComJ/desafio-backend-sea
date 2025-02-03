package com.seatecnologia.desafio_backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_email")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Email {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotBlank
	@jakarta.validation.constraints.Email
	@Column(unique = true, nullable = false)
	private String enderecoEmail;
	
	@ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
	@JsonBackReference
	private Cliente cliente;
	
	public Email(String enderecoEmail, Cliente cliente) {
		this.enderecoEmail = enderecoEmail;
		this.cliente = cliente;
	}
}
