package com.jonathanalvarez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathanalvarez.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
    
}
