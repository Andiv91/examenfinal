package co.edu.ufps.entityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private String referencia;
    private String nombre;
    private int cantidad;
    private double precio;
}