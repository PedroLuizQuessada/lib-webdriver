package exceptions;

public class NavegadorNaoIdentificadoException extends Exception {
    public NavegadorNaoIdentificadoException(String navegador) {
        super(String.format("Navegador %s não identificado", navegador));
    }
}
