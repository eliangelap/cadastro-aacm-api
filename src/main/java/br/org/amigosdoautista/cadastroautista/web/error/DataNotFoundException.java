package br.org.amigosdoautista.cadastroautista.web.error;

public class DataNotFoundException extends NotFoundException {

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(Integer id) {
        super("Busca com o código " + id + " não encontrou dados.");
    }

}
