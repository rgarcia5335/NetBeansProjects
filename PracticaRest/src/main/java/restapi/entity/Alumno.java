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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "primer_apellido")
    private String Primer_apellido;

    @Column(name = "segundo_apellido")
    private String segundo_apellido;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;

    @Column(name = "curp")
    private String curp;

    @Column(name = "sueldo_mensual")
    private int sueldo_mensual;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;
}
