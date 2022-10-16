package exceptions;

public class DriverException extends Exception {
    public DriverException(String navegador) {
        super(String.format("Falha ao inicializar driver %s", navegador));
    }
}
