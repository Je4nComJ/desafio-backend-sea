package com.seatecnologia.desafio_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seatecnologia.desafio_backend.entities.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>{

}
