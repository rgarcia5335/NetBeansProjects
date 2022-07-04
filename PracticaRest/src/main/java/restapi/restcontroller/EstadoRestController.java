/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restapi.restcontroller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restapi.entity.Estado;
import restapi.service.EstadoService;

/**
 *
 * @author ricky
 */
@RestController
@RequestMapping("/estado")
public class EstadoRestController {

    @Autowired
    private EstadoService servicio;

    @GetMapping
    public List<Estado> findAll() {
        return servicio.findAll();
    }

    @GetMapping("/custom")
    public List<Estado> findAllCustom() {
        return servicio.findAllCustom();
    }

    @GetMapping("/{id}")
    public Optional<Estado> findAllById(@PathVariable Long id) {
        return servicio.findById(id);
    }

    @PostMapping
    public Estado add(@RequestBody Estado e) {
        return servicio.add(e);
    }
    
    @PutMapping("/{id}")
    public Estado update(@PathVariable long id, @RequestBody Estado e){
         e.setId(id);
         return servicio.update(e);
    }
    
     @DeleteMapping("/{id}")
    public Estado update(@PathVariable long id){
        Estado objEstado=new Estado();
        objEstado.setNombre("aaa");
         return servicio.delete(Estado.builder().id(id).build());
    }
}
