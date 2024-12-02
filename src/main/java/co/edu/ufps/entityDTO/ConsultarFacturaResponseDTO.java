package co.edu.ufps.entityDTO;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ConsultarFacturaResponseDTO {
    private BigDecimal total;  // Cambiado a BigDecimal
    private BigDecimal impuestos;  // Cambiado a BigDecimal
    private ClienteFacturaDTO cliente;
    private List<ProductoFacturaDTO> productos;
    private CajeroFacturaDTO cajero;

    @Data
    public static class ClienteFacturaDTO {
        private String documento;
        private String nombre;
        private String tipoDocumento;
    }

    @Data
    public static class ProductoFacturaDTO {
        private String referencia;
        private String nombre;
        private int cantidad;
        private BigDecimal precio;
        private BigDecimal descuento;
        private BigDecimal subtotal;
    }

    @Data
    public static class CajeroFacturaDTO {
        private String documento;
        private String nombre;
    }
}
