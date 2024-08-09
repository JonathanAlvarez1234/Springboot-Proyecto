package com.jonathanalvarez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathanalvarez.webapp.biblioteca.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
    
}
