package es.cuenta_bancaria.repository;

import es.cuenta_bancaria.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepositorio extends JpaRepository<Transaccion,String> {
}
