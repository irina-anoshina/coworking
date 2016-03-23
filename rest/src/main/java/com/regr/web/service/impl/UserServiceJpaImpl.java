package com.regr.web.service.impl;

import com.regr.web.config.Constants;
import com.regr.web.database.dao.UserRepository;
import com.regr.web.database.domain.User;
import com.regr.web.exception.EmailAlreadyExistsException;
import com.regr.web.exception.EmailWrongFormatException;
import com.regr.web.exception.NicknameAlreadyExistsException;
import com.regr.web.exception.PhoneWrongFormatException;
import com.regr.web.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.regr.web.utils.HashUtil.calcMd5Hash;
/**
 * Сервис для работы с сущностью User

 * Created by maratische on 23.03.16.
 */
@Service
@Transactional
public class UserServiceJpaImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public void addUser(User user) throws EmailAlreadyExistsException, NicknameAlreadyExistsException, EmailWrongFormatException, PhoneWrongFormatException {
        checkUserDataBeforeSave(user);

        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            /* Проверяем, есть ли уже в БД юзер с таким E-Mail */
            throw new EmailAlreadyExistsException();
        }

        /* Если юзер ввел никнейм, проверяем, есть ли уже такой никнейм у другого юзера в БД */
        if (user.getNickName() != null && !user.getNickName().trim().equals("")) {
            User userByNickname = userRepository.findByNickName(user.getNickName());
            if (userByNickname != null)     //Юзер с таким никнеймом уже присутствует в БД
            {
                throw new NicknameAlreadyExistsException();
            }
        }

        user.setPassword(calcMd5Hash(user.getPassword()));  /* Хешируем пароль перед сохранением в БД */
        userRepository.save(user);

//        addUserActivationForUser(user);//TODO активация пользователя через емайл

    }

    /**
     * Проверка некоторых полей объекта User на ограничения, которые должны соблюдаться
     * как в методе добавления, так и в методе удаления объекта User */
    private void checkUserDataBeforeSave(User user)
            throws EmailWrongFormatException, PhoneWrongFormatException
    {
        if(user == null || user.getEmail() == null || user.getPassword() == null)
        {
            throw new IllegalArgumentException("Объект User, поля E-mail и password не должны быть пустыми.");
        }

        /* TODO: Проверка валидности введенного E-mail */
        if(user.getEmail().trim().equals("")/* || не_удовл_нашим_условиям TODO */)
        {
            throw new EmailWrongFormatException();
        }

        /** Если юзер ввел номер телефона, проверяем, удовлетворяет ли этот номер нашим требованиям */
        if (user.getPhone() != null && !user.getPhone().trim().equals("")) {
            if (!user.getPhone().matches(Constants.PHONE_NUMBER_REGEX)) {
                throw new PhoneWrongFormatException();
            }
        }
    }

}
