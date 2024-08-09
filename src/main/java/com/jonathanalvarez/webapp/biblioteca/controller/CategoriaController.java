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

import com.jonathanalvarez.webapp.biblioteca.model.Categoria;
import com.jonathanalvarez.webapp.biblioteca.service.CategoriaService;

@Controller
@RestController
@RequestMapping(value = "")
public class CategoriaController {
    
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/categorias")
    public List<Categoria>listarCategorias(){
        return categoriaService.listarCategorias();
    }

    @GetMapping("/categoria")
    public ResponseEntity<Categoria>buscarCategoriaPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/categoria")
    public ResponseEntity<Map<String, String>> agregarCategoria(@RequestBody Categoria categoria){
        Map<String,String> response = new HashMap<>();
        try {
            if (categoriaService.guardarCategoria(categoria)) {
                response.put("message", "La Categoria Se Agrego Con Exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("err", "Hubo Un Error Al Crear La Categoria");
                return ResponseEntity.badRequest().body(response);
            }            
        } catch (Exception e) {
            response.put("err", "Hubo Un Error Al Crear La Categoria");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/categoria")
    public ResponseEntity<Map<String, String>>editarCategoria(@RequestParam Long id, @RequestBody Categoria categoriaNueva){
        Map<String,String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoria.setNombreCategoria(categoriaNueva.getNombreCategoria());
            if (categoriaService.guardarCategoria(categoria)) {
                response.put("message", "La Categoria Se Edito Con Exito");
                return ResponseEntity.ok(response);
            }else{
                response.put("message", "Hubo Un Error Al Editar La Categoria");
                return ResponseEntity.badRequest().body(response);
            }   
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Editar La Categoria");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/categoria")
    public ResponseEntity<Map<String, String>>eliminarCategoria(@RequestParam Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria); 
            response.put("message", "La Categoria Se Elimino Con Exito");
            return ResponseEntity.ok(response); 
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Eliminar La Categoria");
            return ResponseEntity.badRequest().body(response);
        }
        

    }
}
