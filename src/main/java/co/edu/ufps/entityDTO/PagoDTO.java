package co.edu.ufps.entityDTO;
import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import co.edu.ufps.entity.Pago;
import co.edu.ufps.entity.TipoPago;
import lombok.Data;

@Data
public class PagoDTO {
	@JsonProperty("tipo_pago")
    private String tipoPago;
	
	@JsonProperty("tipo_tarjeta")
    private String tipoTarjeta;
    private Integer cuotas;
    private BigDecimal valor;

    public static PagoDTO fromEntity(Pago pago) {
        PagoDTO dto = new PagoDTO();
        if (pago.getTipoPago() != null) {
            dto.setTipoPago(pago.getTipoPago().getNombre());
        }
        
        dto.setTipoTarjeta(pago.getTarjetaTipo());
        dto.setCuotas(pago.getCuotas());
        dto.setValor(pago.getValor());
        return dto;
    }

    public Pago toEntity() {
        Pago pago = new Pago();
        if (this.tipoPago != null) {
            TipoPago tipoPago = new TipoPago();
            tipoPago.setNombre(this.tipoPago);
            pago.setTipoPago(tipoPago);
        }
        pago.setTarjetaTipo(this.tipoTarjeta);
        pago.setCuotas(this.cuotas);
        pago.setValor(this.valor);
        return pago;
    }
}