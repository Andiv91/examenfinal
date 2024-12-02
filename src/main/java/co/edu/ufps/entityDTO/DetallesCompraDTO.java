package co.edu.ufps.entityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallesCompraDTO {
    private String productoReferencia;
    private int cantidad;
    private double precio;
    private double descuento;
}