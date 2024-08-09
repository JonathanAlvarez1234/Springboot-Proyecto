package com.jonathanalvarez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathanalvarez.webapp.biblioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

}
