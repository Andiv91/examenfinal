package co.edu.ufps.entityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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

    // Constructor personalizado para crear la respuesta
    public FacturaResponseDTO(String status, String message, Integer numero, double total, LocalDateTime fecha) {
        this.status = status;
        this.message = message;
        this.data = new FacturaData(
                numero.toString(),  // Convertimos el número a String
                total,
                fecha.toLocalDate().toString() // Convertimos la fecha a String en formato yyyy-MM-dd
        );
    }
}