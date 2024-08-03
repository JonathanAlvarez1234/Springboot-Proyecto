package com.jonathanalvarez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathanalvarez.webapp.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
