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

@Controller
@RestController
@RequestMapping(value = "")
public class LibroController {

    @Autowired
    LibroService libroService;

    @GetMapping("/libros")
    public ResponseEntity<List<Libro>>listarLibros(){
        try {
            return ResponseEntity.ok(libroService.listarLibros());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/libro")
    public ResponseEntity<Libro>buscarLibroPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(libroService.buscarLibroPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/libro")
    public ResponseEntity<Map<String, String>> agregarLibro(@RequestBody Libro libro){
        Map<String,String> response = new HashMap<>();
        try {
            libroService.guardarLibro(libro);
            response.put("message", "El Libro Se Agrego Con Exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo Un Error Al Crear El Libro");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/libro")
    public ResponseEntity<Map<String, String>>editarLibro(@RequestParam Long id, @RequestBody Libro libroNuevo){
        Map<String,String> response = new HashMap<>();
        try {
            Libro libro = libroService.buscarLibroPorId(id);
            libro.setIsbn(libroNuevo.getIsbn());
            libro.setNombre(libroNuevo.getNombre());
            libro.setSinopsis(libroNuevo.getSinopsis());
            libro.setAutor(libroNuevo.getAutor());
            libro.setEditorial(libroNuevo.getEditorial());
            libro.setDisponibilidad(libroNuevo.getDisponibilidad());
            libro.setNumeroEstanteria(libroNuevo.getNumeroEstanteria());
            libro.setCluster(libroNuevo.getCluster());
            libro.setCategoria(libroNuevo.getCategoria());
            libroService.guardarLibro(libro);
            response.put("message", "El Libro Se Edito Con Exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Editar El Libro");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/libro")
    public ResponseEntity<Map<String, String>>eliminarLibro(@RequestParam Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Libro libro = libroService.buscarLibroPorId(id);
            libroService.eliminarLibro(libro); 
            response.put("message", "El Libro Se Elimino Con Exito");
            return ResponseEntity.ok(response); 
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Eliminar El Libro");
            return ResponseEntity.badRequest().body(response);
        }
        

    }
}