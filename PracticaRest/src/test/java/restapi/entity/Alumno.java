/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restapi.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import restapi.entity.Estado;

/**
 *
 * @author ricky
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Alumno")
@Table(name = "alumnos")
public class Alumno implements Serializable {

    private static final long serialVerionUID = 1L;

    @Id
    @Column(name = "alumnoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombreAlumno")
    private String nombre;

    @Column(name = "primerApellido")
    private String PrimerApellido;

    @Column(name = "segundoApellido")
    private String segundoApellido;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fechaDeNacimiento")
    private Date fechaDeNacimiento;

    @Column(name = "curp")
    private String curp;

    @Column(name = "sueldoMensual")
    private int sueldoMensual;

    @ManyToOne
    @JoinColumn(name = "idEstado", nullable = false)
    private Estado estado;
}
