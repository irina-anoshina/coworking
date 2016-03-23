package com.regr.web.database.dao;

import com.regr.web.database.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by maratische on 22.03.16.
 */
public interface UserRepository extends CrudRepository<User, UUID> {

    User findByEmail(String email);

    User findByIdentifier(long identifier);

    User findByNickName(String nickName);
}
