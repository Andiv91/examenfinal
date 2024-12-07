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
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "compra_id")
    private Compra compra;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipo_pago_id")
    private TipoPago tipoPago;
    private String tarjetaTipo;
    private BigDecimal valor;
    private Integer cuotas;
}