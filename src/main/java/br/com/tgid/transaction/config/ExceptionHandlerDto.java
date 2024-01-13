package br.com.tgid.transaction.config;


public class ExceptionHandlerDto {
    private String field;
    private String message;

    public ExceptionHandlerDto(String field, String message) {
        this.field= field;
        this.message= message;
    }
}
