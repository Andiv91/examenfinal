package co.edu.ufps.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Cajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String documento;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tienda_id")
    private Tienda tienda;
    private String email;
    private String token;
}
