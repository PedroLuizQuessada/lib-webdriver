package exceptions;

public class FecharNavegadorException extends Exception {
    public FecharNavegadorException() {
        super("Falha ao tentar fechar navegador");
    }
}
