/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restapi.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.entity.Alumno;
import restapi.repository.AlumnoRepository;

/**
 *
 * @author ricky
 */
@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository repositorio;

    @Override
    public List<Alumno> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<Alumno> findAllCustom() {
        return repositorio.findAllCustom();
    }

    @Override
    public Optional<Alumno> findById(long id) {
        return repositorio.findById(id);
    }

    @Override
    public Alumno add(Alumno a) {
        return repositorio.save(a);
    }

    @Override
    public Alumno update(Alumno a) {
        Alumno objAlumno = repositorio.getById(a.getId());
        BeanUtils.copyProperties(a, objAlumno);

        return repositorio.save(objAlumno);
    }

    @Override
    public Alumno delete(Alumno a) {
        Alumno objAlumno = repositorio.getById(a.getId());
        objAlumno.setNombre("asd");

        return repositorio.save(objAlumno);
    }

}
