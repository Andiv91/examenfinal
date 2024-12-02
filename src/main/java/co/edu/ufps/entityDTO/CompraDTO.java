package co.edu.ufps.entityDTO;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompraDTO {
    private Long id;
    private String clienteNombre;
    private String vendedorNombre;
    private String cajeroNombre;
    private double total;
    private LocalDateTime fecha;
}