package es.cuenta_bancaria.exception;

public class CuentaNoEncontradaException extends RuntimeException{
    public CuentaNoEncontradaException(String message){
        super(message);
    }
}
