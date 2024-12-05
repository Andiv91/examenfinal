package co.edu.ufps.entityDTO;

import java.util.List;

import lombok.Data;

@Data
public class ConsultarFacturaResponseDTO {
    private double total;  // Cambiado a BigDecimal
    private double impuestos;  // Cambiado a BigDecimal
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
        private double precio;
        private double descuento;
        private double subtotal;
    }

    @Data
    public static class CajeroFacturaDTO {
        private String documento;
        private String nombre;
    }
}
