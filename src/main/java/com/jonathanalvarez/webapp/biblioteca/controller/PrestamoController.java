package com.jonathanalvarez.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jonathanalvarez.webapp.biblioteca.model.Prestamo;
import com.jonathanalvarez.webapp.biblioteca.service.PrestamoService;

@Controller
@RestController
@RequestMapping(value = "")
public class PrestamoController {
    
    @Autowired
    PrestamoService prestamoService;

    @GetMapping("/prestamos")
    public ResponseEntity<List<Prestamo>>listarPrestamos(){
        try {
            return ResponseEntity.ok(prestamoService.listarPrestamos());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/prestamo")
    public ResponseEntity<Prestamo>buscarPrestamoPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(prestamoService.buscarPrestamoPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/prestamo")
    public ResponseEntity<Map<String, String>> agregarPrestamo(@RequestBody Prestamo prestamo){
        Map<String,String> response = new HashMap<>();
        try {
            prestamoService.guardarPrestamo(prestamo);
            response.put("message", "El Prestamo Se Agrego Con Exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo Un Error Al Crear El Prestamo");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/prestamo")
    public ResponseEntity<Map<String, String>>editarPrestamo(@RequestParam Long id, @RequestBody Prestamo prestamoNuevo){
        Map<String,String> response = new HashMap<>();
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            prestamo.setCliente(prestamoNuevo.getCliente());
            prestamo.setEmpleado(prestamoNuevo.getEmpleado());
            prestamo.setFechaDeDevolucion(prestamoNuevo.getFechaDeDevolucion());
            prestamo.setFechaDePrestamo(prestamoNuevo.getFechaDePrestamo());
            prestamo.setLibros(prestamoNuevo.getLibros());
            prestamo.setVigencia(prestamoNuevo.getVigencia());
            prestamoService.guardarPrestamo(prestamo);
            response.put("message", "El Prestamo Se Edito Con Exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Editar El Prestamo");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
