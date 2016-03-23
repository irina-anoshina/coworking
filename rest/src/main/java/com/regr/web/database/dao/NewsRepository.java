package com.regr.web.database.dao;

import com.regr.web.database.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by maratische on 23.03.16.
 */
public interface NewsRepository extends JpaRepository<News, UUID> {

    News findByUrl(String url);
}
