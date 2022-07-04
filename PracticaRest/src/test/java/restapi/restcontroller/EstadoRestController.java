/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restapi.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
