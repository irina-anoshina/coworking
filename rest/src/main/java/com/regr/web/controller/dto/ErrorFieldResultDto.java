package com.regr.web.controller.dto;

/**
 * Сообщение об ошибки с указанием где
 * Created by maratische on 23.03.16.
 */
public class ErrorFieldResultDto extends ObjectResultDto {
    private String field;
    private String message;
    private String messageCode;

    public ErrorFieldResultDto() { }
    public ErrorFieldResultDto(String field, String message, String messageCode)
    {
        this.field = field;
        this.message = message;
        this.messageCode = messageCode;
    }

    public String getField()
    {
        return field;
    }

    public String getMessage()
    {
        return message;
    }

    public String getMessageCode()
    {
        return messageCode;
    }

}
