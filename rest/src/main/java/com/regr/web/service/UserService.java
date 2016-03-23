package com.regr.web.service;

import com.regr.web.database.domain.User;
import com.regr.web.exception.EmailAlreadyExistsException;
import com.regr.web.exception.EmailWrongFormatException;
import com.regr.web.exception.NicknameAlreadyExistsException;
import com.regr.web.exception.PhoneWrongFormatException;

/**
 * Сервис для работы с сущностью User
 * Created by maratische on 23.03.16.
 */
public interface UserService {

    /**
     * Создание нового пользователя
     * @param user
     * @throws EmailAlreadyExistsException
     * @throws NicknameAlreadyExistsException
     * @throws EmailWrongFormatException
     * @throws PhoneWrongFormatException
     */
    void addUser(User user) throws EmailAlreadyExistsException, NicknameAlreadyExistsException,
            EmailWrongFormatException, PhoneWrongFormatException;
}
