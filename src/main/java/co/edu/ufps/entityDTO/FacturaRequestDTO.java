package co.edu.ufps.entityDTO;

import java.util.List;

import lombok.Data;

@Data
public class FacturaRequestDTO {
    private double impuesto;
    private ClienteRequestDTO cliente;
    private List<ProductoRequestDTO> productos;
    private List<MedioPagoRequestDTO> mediosPago;
    private VendedorRequestDTO vendedor;
    private CajeroRequestDTO cajero;

    @Data
    public static class ClienteRequestDTO {
        private String documento;
        private String nombre;
        private String tipoDocumento;
    }

    @Data
    public static class ProductoRequestDTO {
        private String referencia;
        private int cantidad;
        private double descuento;
    }

    @Data
    public static class MedioPagoRequestDTO {
        private String tipoPago;
        private String tipoTarjeta;
        private int cuotas;
        private double valor;
    }

    @Data
    public static class VendedorRequestDTO {
        private String documento;
    }

    @Data
    public static class CajeroRequestDTO {
        private String token;
    }
}