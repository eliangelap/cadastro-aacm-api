package br.org.amigosdoautista.cadastroautista.model.types;

public enum QuestionType {

    SINGLE_CHOICE("Única escolha"),
    MULTIPLE_CHOICE("Múltipla escolha"),
    AGE("Idade"),
    DATE("Data"),
    TIME("Hora"),
    DATE_OF_BIRTH("Data de nascimento"),
    NUMERIC("Número"),
    MONEY("Monetário"),
    CEP("CEP"),
    DDD("DDD"),
    PHONE_NUMBER("Telefone"),
    SHORT_TEXT("Texto curto"),
    LONG_TEXT("Texto longo");

    private String type;

    QuestionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
