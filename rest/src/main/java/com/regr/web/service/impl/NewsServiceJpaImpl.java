package com.regr.web.service.impl;

import com.regr.web.database.dao.NewsRepository;
import com.regr.web.database.domain.News;
import com.regr.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с новостями
 * Created by maratische on 23.03.16.
 */
@Service
@Transactional
public class NewsServiceJpaImpl implements NewsService {

    private NewsRepository newsRepository;

    @Override
    public void addNews() {

    }

    @Override
    public List<News> findAll() {
        return  newsRepository.findAll();
    }

    @Autowired
    public void setNewsRepository(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
}
