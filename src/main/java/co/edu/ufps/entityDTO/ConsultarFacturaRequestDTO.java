package co.edu.ufps.entityDTO;

import lombok.Data;

@Data
public class ConsultarFacturaRequestDTO {
    private String token;
    private String cliente;
    private Long factura;
}