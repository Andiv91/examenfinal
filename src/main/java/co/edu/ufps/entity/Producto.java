package co.edu.ufps.entity;
import java.math.BigDecimal;
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
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_producto_id")
    private TipoProducto tipoProducto;
    private Integer cantidad;
    private String referencia;
}