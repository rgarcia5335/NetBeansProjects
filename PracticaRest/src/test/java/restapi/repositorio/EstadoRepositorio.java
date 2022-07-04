/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package restapi.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import restapi.entity.Estado;

/**
 *
 * @author ricky
 */
public interface EstadoRepositorio extends JpaRepository<Estado , Long>{
@Query("select e from Estado e ")    
List<Estado> findAllCustom();
}
