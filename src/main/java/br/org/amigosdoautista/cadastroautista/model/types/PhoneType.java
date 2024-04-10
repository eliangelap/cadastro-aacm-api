package br.org.amigosdoautista.cadastroautista.model.types;

public enum PhoneType {

    WORK_PHONE("Comercial"),
    HOME_PHONE("Residencial"),
    CELL_PHONE("Celular");

    private String type;

    PhoneType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
