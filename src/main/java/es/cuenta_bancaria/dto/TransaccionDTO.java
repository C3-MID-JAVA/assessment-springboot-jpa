package es.cuenta_bancaria.dto;

import es.cuenta_bancaria.model.Cuenta;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionDTO {

    private String idTransaccion;

    @NotNull(message = "El monto de la transacción es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a cero")
    private BigDecimal monto;

    @NotBlank(message = "El tipo de transacción es obligatorio")
    @Size(max= 50)
    private String tipo;

    @DecimalMin(value = "0.0", inclusive = true, message = "El costo de transacción no puede ser negativo")
    private BigDecimal costo;

    private Cuenta cuenta;
}
