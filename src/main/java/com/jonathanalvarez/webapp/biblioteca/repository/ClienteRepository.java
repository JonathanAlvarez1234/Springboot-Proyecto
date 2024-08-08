package com.jonathanalvarez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathanalvarez.webapp.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
