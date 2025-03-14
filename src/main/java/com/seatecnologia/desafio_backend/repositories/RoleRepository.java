package com.seatecnologia.desafio_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seatecnologia.desafio_backend.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
