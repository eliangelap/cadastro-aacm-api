package br.org.amigosdoautista.cadastroautista.model.schemas.general;

public enum AddressType {

    BUSINESS("Comercial"), HOME("Residencial");

    private String type;

    AddressType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
