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
import restapi.entity.Alumno;
import restapi.service.AlumnoService;

/**
 *
 * @author ricky
 */
@RestController
@RequestMapping("/alumno")
public class AlumnoRestController {

    @Autowired
    private AlumnoService servicio;

    @GetMapping
    public List<Alumno> findAll() {
        return servicio.findAll();
    }

    @GetMapping("/custom")
    public List<Alumno> findAllCustom() {
        return servicio.findAllCustom();
    }

    @GetMapping("/{id}")
    public Optional<Alumno> findAllById(@PathVariable Long id) {
        return servicio.findById(id);
    }

    @PostMapping
    public Alumno add(@RequestBody Alumno a) {
        return servicio.add(a);
    }

    @PutMapping("/{id}")
    public Alumno update(@PathVariable long id, @RequestBody Alumno a) {
        a.setId(id);
        return servicio.update(a);
    }

    @DeleteMapping("/{id}")
    public Alumno update(@PathVariable long id) {
        Alumno objAlumno = new Alumno();
        objAlumno.setNombre("aaa");
        return servicio.delete(Alumno.builder().id(id).build());
    }
}
