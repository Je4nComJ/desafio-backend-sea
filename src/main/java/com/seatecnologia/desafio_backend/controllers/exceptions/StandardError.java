package com.seatecnologia.desafio_backend.controllers.exceptions;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardError {
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;

}
