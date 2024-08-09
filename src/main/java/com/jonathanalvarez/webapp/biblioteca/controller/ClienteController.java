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

import com.jonathanalvarez.webapp.biblioteca.model.Cliente;
import com.jonathanalvarez.webapp.biblioteca.service.ClienteService;

@Controller
@RestController
@RequestMapping("")
public class ClienteController {
    
    @Autowired
    ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente>listarClientes(){
        return clienteService.listarClientes();
    }

    @GetMapping("/cliente")
    public ResponseEntity<Cliente>buscarClientePorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(clienteService.buscarClientePorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/cliente")
    public ResponseEntity<Map<String, String>> agregarCliente(@RequestBody Cliente cliente){
        Map<String,String> response = new HashMap<>();
        try {
            clienteService.guardarCliente(cliente);
            response.put("message", "El Cliente Se Agrego Con Exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "Hubo Un Error Al Crear El Cliente");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/cliente")
    public ResponseEntity<Map<String, String>>editarCliente(@RequestParam Long id, @RequestBody Cliente clienteNuevo){
        Map<String,String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorId(id);
            cliente.setNombre(clienteNuevo.getNombre());
            cliente.setApellido(clienteNuevo.getApellido());
            cliente.setTelefono(clienteNuevo.getTelefono());
            clienteService.guardarCliente(cliente);
            response.put("message", "El Cliente Se Edito Con Exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Editar El Cliente");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/cliente")
    public ResponseEntity<Map<String, String>>eliminarCliente(@RequestParam Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorId(id);
            clienteService.eliminarCliente(cliente); 
            response.put("message", "El Cliente Se Elimino Con Exito");
            return ResponseEntity.ok(response); 
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Eliminar El Cliente");
            return ResponseEntity.badRequest().body(response);
        }
        

    }

}
