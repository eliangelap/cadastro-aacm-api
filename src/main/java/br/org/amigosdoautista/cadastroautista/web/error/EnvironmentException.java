package br.org.amigosdoautista.cadastroautista.web.error;

public class EnvironmentException extends Exception {

    public EnvironmentException(String message) {
        super(message);
    }

    public EnvironmentException(Throwable cause) {
        super(cause);
    }

    public EnvironmentException(String message, Throwable cause) {
        super(message, cause);
    }

}
