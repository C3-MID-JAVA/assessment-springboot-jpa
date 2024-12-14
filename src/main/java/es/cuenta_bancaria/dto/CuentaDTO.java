package es.cuenta_bancaria.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CuentaDTO {

    private String idCuenta;

    @NotBlank(message = "El nombre del titular es obligatorio")
    @Size(max = 100, message = "El nombre del titular no debe exceder 100 caracteres")
    private String titular;

    @NotNull(message = "El saldo inicial es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El saldo no puede ser negativo")
    private BigDecimal saldo;


    private List<TransaccionDTO> transacciones;

    public CuentaDTO(String idCuenta, String titular, BigDecimal saldo){
        this.idCuenta=idCuenta;
        this.titular=titular;
        this.saldo=saldo;
    }
}
