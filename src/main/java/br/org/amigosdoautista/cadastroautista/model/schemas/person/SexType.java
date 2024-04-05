package br.org.amigosdoautista.cadastroautista.model.schemas.person;

public enum SexType {

    MALE("Masculino"), FEMALE("Feminino");

    private String type;

    SexType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
