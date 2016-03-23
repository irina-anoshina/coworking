package com.regr.web.service;

import com.regr.web.database.domain.News;

import java.util.List;

/**
 * Сервис для работы с новостями
 * Created by maratische on 23.03.16.
 */
public interface NewsService {

    void addNews();

    List<News> findAll();
}
