package br.org.amigosdoautista.cadastroautista.web.error;

import java.util.Date;

import lombok.Data;

@Data
public class InternalServerErrorSchema {

    private Date timestamp;
    private Integer status;
    private String error;
    private String className;
    private String message;
    private String path;
    private String stacktrace;

}
