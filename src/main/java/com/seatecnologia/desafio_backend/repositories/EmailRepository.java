package com.seatecnologia.desafio_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seatecnologia.desafio_backend.entities.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>{

}
