package com.regr.web.controller.api;

import com.regr.web.controller.dto.NewsDto;
import com.regr.web.controller.dto.ObjectResultDto;
import com.regr.web.controller.dto.ResultDto;
import com.regr.web.controller.dto.UserDto;
import com.regr.web.database.domain.News;
import com.regr.web.service.NewsService;
import com.regr.web.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Новости, создание, редактирование и все остальное
 * Created by maratische on 23.03.16.
 */
@RestController
@RequestMapping(value = "/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResultDto> addParkingRating(@RequestBody @Valid NewsDto newsDto,
                                                      BindingResult bindingResult, HttpSession session) {
        List<ObjectResultDto> messages = new ArrayList<>();
        HttpStatus httpStatus = HttpStatus.OK;

        if (bindingResult.hasFieldErrors())
        //Есть ошибки ввода, опознанные Hibernate validator
        {
            messages = ControllerUtils.parseFieldErrors(bindingResult.getFieldErrors(), "FIELD_ERROR");
            httpStatus = HttpStatus.BAD_REQUEST;
        }


        return new ResponseEntity<>(new ResultDto(messages.size() == 0, messages), httpStatus);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<ResultDto> list(HttpSession session) {
        List<ObjectResultDto> messages = new ArrayList<>();
        HttpStatus httpStatus = HttpStatus.OK;

        List<News> newsAll = newsService.findAll();
        if (newsAll != null) {
            for (News news : newsAll) {
                messages.add(new NewsDto(news.getTitle(), news.getUrl(), news.getBody()));
            }
        }

        return new ResponseEntity<>(new ResultDto(messages.size() == 0, messages), httpStatus);
    }

}
