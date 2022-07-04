/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restapi.service;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restapi.entity.Alumno;
import restapi.entity.Estado;
import restapi.repository.EstadoRepository;

/**
 *
 * @author ricky
 */
@Service
public class EstadoServiceImpl implements EstadoService {

    @Autowired
    private EstadoRepository repositorio;

    @Override
    public List<Estado> findAll() {
        return repositorio.findAll();
    }

    @Override
    public List<Estado> findAllCustom() {
        return repositorio.findAllCustom();
    }

    @Override
    public Optional<Estado> findById(long id) {
        return repositorio.findById(id);
    }

    @Override
    public Estado add(Estado e) {
        return repositorio.save(e);
    }

    @Override
    public Estado update(Estado e) {
        Estado objEstado = repositorio.getById(e.getId());
        BeanUtils.copyProperties(e, objEstado);
        
        return repositorio.save(objEstado);
    }

    @Override
    public Estado delete(Estado e) {
         Estado objEstado = repositorio.getById(e.getId());
         objEstado.setNombre("asd");
         
         return repositorio.save(objEstado);
    }

}
