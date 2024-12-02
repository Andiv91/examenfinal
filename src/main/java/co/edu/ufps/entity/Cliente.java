package co.edu.ufps.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String documento;

    @ManyToOne
    @JoinColumn(name = "tipo_documento_id", nullable = false)
    private TipoDocumento tipoDocumento;

    // Constructor personalizado
    public Cliente(String nombre, String documento, TipoDocumento tipoDocumento) {
        this.nombre = nombre;
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
    }

    // Si prefieres que Lombok genere el constructor, usa la anotación @AllArgsConstructor
    // @AllArgsConstructor
}