package com.regr.web.controller.api;

import com.regr.web.controller.dto.ObjectResultDto;
import com.regr.web.controller.dto.ResultDto;
import com.regr.web.controller.dto.UserDto;
import com.regr.web.service.UserService;
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
 * Контролллер обеспечивающий работу с пользователя
 * Создание, редактирование и тд
 *
 * Created by maratische on 23.03.16.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Создание пользователя
     * @param userDto
     * @param bindingResult
     * @param session
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResultDto> addParkingRating(@RequestBody @Valid UserDto userDto,
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
}
