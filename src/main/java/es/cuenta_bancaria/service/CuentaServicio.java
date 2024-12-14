package es.cuenta_bancaria.service;

import es.cuenta_bancaria.dto.CuentaDTO;
import es.cuenta_bancaria.exception.CuentaNoEncontradaException;
import es.cuenta_bancaria.exception.SaldoInsuficienteException;
import es.cuenta_bancaria.mapper.DTOMapper;
import es.cuenta_bancaria.model.Cuenta;
import es.cuenta_bancaria.model.Transaccion;
import es.cuenta_bancaria.repository.CuentaRepositorio;
import es.cuenta_bancaria.repository.TransaccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaServicio implements ICuentaServicio{

    @Autowired
    TransaccionRepositorio transaccionRepositorio;
    @Autowired
    CuentaRepositorio cuentaRepositorio;

    @Autowired
    private DTOMapper dtoMapper;

    @Override
    public List<CuentaDTO> listarCuentas() {
        var cuentas = cuentaRepositorio.findAll();
        return cuentas.stream()
                .map(dtoMapper::convertirACuentaDTO) // Uso del método del mapper
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDTO crearCuenta(CuentaDTO cuentaDTO) {
        var cuenta = new Cuenta();
        cuenta.setTitular(cuentaDTO.getTitular());
        cuenta.setSaldo(cuentaDTO.getSaldo());
        Cuenta cuentaGuardada = cuentaRepositorio.save(cuenta);
        return dtoMapper.convertirACuentaDTO(cuentaGuardada);
    }

    @Override
    public CuentaDTO obtenerCuentaPorId(String id) {
        Cuenta cuenta = cuentaRepositorio.findById(id).orElse(null);
        if (cuenta != null){
            return dtoMapper.convertirACuentaDTO(cuenta);
        }
        return null;
    }

    @Override
    public CuentaDTO realizarTransaccion(String cuentaId, BigDecimal monto, String tipo) {

        var cuenta  = cuentaRepositorio.findById(cuentaId)
                .orElseThrow(() -> new CuentaNoEncontradaException("La cuenta con ID " + cuentaId + " no fue encontrada"));

            BigDecimal costo = calcularCostoTransaccion(tipo);

            if (tipo.startsWith("RETIRO") || tipo.equals("COMPRA_WEB") || tipo.equals("COMPRA_ESTABLECIMIENTO")) {
                BigDecimal totalADescontar = monto.add(costo);

                if (cuenta.getSaldo().compareTo(totalADescontar) < 0) {
                    throw new SaldoInsuficienteException("Saldo insuficiente para realizar esta transacción");
                }

                cuenta.setSaldo(cuenta.getSaldo().subtract(totalADescontar));
            } else {
                cuenta.setSaldo(cuenta.getSaldo().add(monto).subtract(costo)); // Descontar costo del depósito
            }
            Transaccion transaccion = new Transaccion();
            transaccion.setMonto(monto);
            transaccion.setTipo(tipo);
            transaccion.setCosto(costo);
            transaccion.setCuenta(cuenta);

            //cuenta.setSaldo(cuenta.getSaldo().add(montoFinal));
            cuentaRepositorio.save(cuenta);
            transaccionRepositorio.save(transaccion);
            return dtoMapper.convertirACuentaDTO(cuenta);

    }

    private BigDecimal calcularCostoTransaccion(String tipo) {
        switch (tipo) {
            case "DEPOSITO_SUCURSAL":
                return BigDecimal.ZERO;
            case "DEPOSITO_CAJERO":
                return BigDecimal.valueOf(2);
            case "DEPOSITO_OTRA_CUENTA":
                return BigDecimal.valueOf(1.5);
            case "COMPRA_WEB":
                return BigDecimal.valueOf(5);
            case "RETIRO_CAJERO":
                return BigDecimal.ONE;
            case "COMPRA_ESTABLECIMIENTO":
                return BigDecimal.ZERO;
            default:
                return BigDecimal.ZERO;
        }
    }
}
