package es.cuenta_bancaria.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_transaccion")
    private String idTransaccion;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal monto;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal costo;

    @JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta", nullable = false)
    @ManyToOne
    @JsonBackReference
    private Cuenta cuenta;
}
