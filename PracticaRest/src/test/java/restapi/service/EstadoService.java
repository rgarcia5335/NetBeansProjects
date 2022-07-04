/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package restapi.service;

import java.util.List;
import java.util.Optional;
import restapi.entity.Estado;

/**
 *
 * @author ricky
 */
public interface EstadoService {

    public List<Estado> findAll();

    public List<Estado> findAllCustom();

    public Optional<Estado> findById(long id);

    public Estado add(Estado e);

    public Estado update(Estado e);

    public Estado delete(Estado e);
}
