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

import com.jonathanalvarez.webapp.biblioteca.model.Empleado;
import com.jonathanalvarez.webapp.biblioteca.service.EmpleadoService;

@Controller
@RestController
@RequestMapping(value = "")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado>listarEmpleados(){
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/empleado")
    public ResponseEntity<Empleado>buscarEmpleadoPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(empleadoService.buscarEmpleadoPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/empleado")
    public ResponseEntity<Map<String, String>> agregarEmpleado(@RequestBody Empleado empleado){
        Map<String,String> response = new HashMap<>();
        try {
                if(empleadoService.guardarEmpleado(empleado)){
                    response.put("message","Empleado Creado Con Exito");
                }else{
                    response.put("message","DPI Duplicado");
                    return ResponseEntity.badRequest().body(response);
                }    
            empleadoService.guardarEmpleado(empleado);
            response.put("message", "El Empleado Se Agrego Con Exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("err", "Hubo Un Error Al Crear El Empleado");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/empleado")
    public ResponseEntity<Map<String, String>>editarEmpleado(@RequestParam Long id, @RequestBody Empleado empleadoNuevo){
        Map<String,String> response = new HashMap<>();
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            empleado.setNombre(empleadoNuevo.getNombre());
            empleado.setApellido(empleadoNuevo.getApellido());
            empleado.setTelefono(empleadoNuevo.getTelefono());
            empleado.setDireccion(empleadoNuevo.getDireccion());
            empleado.setDpi(empleadoNuevo.getDpi());
            empleadoService.guardarEmpleado(empleado);
            if(empleadoService.guardarEmpleado(empleado)){
                response.put("message", "El Empleado Se Edito Con Exito");
            }else{
                response.put("message", "El Empleado Se Edito Con Exito");
                return ResponseEntity.badRequest().body(response);
            }    
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Editar El Empleado");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/empleado")
    public ResponseEntity<Map<String, String>>eliminarEmpleado(@RequestParam Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
            empleadoService.eliminarEmpleado(empleado); 
            response.put("message", "El Empleado Se Elimino Con Exito");
            return ResponseEntity.ok(response); 
        } catch (Exception e) {
            response.put("message", "Hubo Un Error Al Eliminar El Empleado");
            return ResponseEntity.badRequest().body(response);
        }
        

    }
}