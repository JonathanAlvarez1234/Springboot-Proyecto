package com.jonathanalvarez.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jonathanalvarez.webapp.biblioteca.model.Libro;
import com.jonathanalvarez.webapp.biblioteca.service.LibroService;

public class LibroController {
    @Autowired
    LibroService libroService;

    @GetMapping("/libros")
    public ResponseEntity<List<Libro>> listaLibros(){
        try{
            return ResponseEntity.ok(libroService.listarLibros());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/libro")
    public ResponseEntity<Map<String, String>> agregarLibro(@RequestBody Libro libro){
        Map<String, String> response = new HashMap<>();
        try{
            libroService.guardarLibro(libro);
            response.put("message", "Libro creado con exito");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message", "Error al crear el libro");
            response.put("err", "Ocurrio un error al crear el libro");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
