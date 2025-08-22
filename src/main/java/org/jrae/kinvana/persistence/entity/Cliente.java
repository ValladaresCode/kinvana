package org.jrae.kinvana.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Clientes")
//Lombok
@Data // Genera Setters y Getters
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode//Codigo de autentificacion de la entidad
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoCliente;// perite null en vez de 0

    @Column
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String genero;// enum()
    private int edad;



}
