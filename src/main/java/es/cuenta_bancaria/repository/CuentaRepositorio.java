package es.cuenta_bancaria.repository;

import es.cuenta_bancaria.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepositorio extends JpaRepository<Cuenta,String> {
}
