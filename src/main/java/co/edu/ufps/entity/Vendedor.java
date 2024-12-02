package co.edu.ufps.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(length = 20)
    private String documento;

    @Column(length = 50)
    private String email;
}