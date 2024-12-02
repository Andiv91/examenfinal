package co.edu.ufps.entityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaResponseDTO {
    private String status;
    private String message;
    private FacturaData data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FacturaData {
        private String numero; // Número de la factura (compra.id)
        private double total;  // Total de la factura
        private String fecha;  // Fecha de la factura (en formato "yyyy-MM-dd")
    }
}