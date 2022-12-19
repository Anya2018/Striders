package com.stride.striders.repository;

import com.stride.striders.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

}
