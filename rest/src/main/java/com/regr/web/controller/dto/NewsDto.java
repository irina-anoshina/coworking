package com.regr.web.controller.dto;

import javax.validation.constraints.NotNull;

/**
 * Новость
 * Created by maratische on 23.03.16.
 */
public class NewsDto extends ObjectResultDto {
    @NotNull(message = "title can not be empty")
    private String title;
    @NotNull(message = "url can not be empty")
    private String url;
    private String body;

    public NewsDto() {

    }

    public NewsDto(String title, String url, String body) {
        setTitle(title);
        setUrl(url);
        setBody(body);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
