package br.com.tgid.transaction.config;

import java.sql.Timestamp;

public class ResponseDto {

    private static final String R_MSG_EMPTY = "";

    private final String message;
    private final Timestamp localDate;

    public ResponseDto (final String message) {
        this.message = message == null ? ResponseDto.R_MSG_EMPTY : message;
        this.localDate = new Timestamp(System.currentTimeMillis());
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getLocalDate() {
        return localDate;
    }

}
