/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package restapi.service;

import java.util.List;
import java.util.Optional;
import restapi.entity.Alumno;

/**
 *
 * @author ricky
 */
public interface AlumnoService {

    public List<Alumno> findAll();

    public List<Alumno> findAllCustom();

    public Optional<Alumno> findById(long id);

    public Alumno add(Alumno a);

    public Alumno update(Alumno a);

    public Alumno delete(Alumno a);
}
