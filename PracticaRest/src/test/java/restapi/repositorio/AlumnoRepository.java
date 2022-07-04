/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package restapi.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import restapi.entity.Alumno;

/**
 *
 * @author ricky
 */
public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
    @Query("select a from Alumno a where a.id=1")
    List<Alumno> findAllCustom();
}
