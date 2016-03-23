package com.regr.web.controller.dto;

import java.util.List;

/**
 * Created by maratische on 23.03.16.
 */
public class ResultDto {
    private boolean success;
    private List<ObjectResultDto> messages;

    public ResultDto(boolean success, List<ObjectResultDto> messages)
    {
        this.success = success;
        this.messages = messages;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ObjectResultDto> getMessages() {
        return messages;
    }

    public void setMessages(List<ObjectResultDto> messages) {
        this.messages = messages;
    }
}
