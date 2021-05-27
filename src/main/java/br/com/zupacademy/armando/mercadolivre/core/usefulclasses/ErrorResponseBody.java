package br.com.zupacademy.armando.mercadolivre.core.usefulclasses;

public class ErrorResponseBody {

    private String error;
    private String message;

    public ErrorResponseBody(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

}
