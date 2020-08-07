package com.zspt.demo.repository;

import com.zspt.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @program: demo
 * @description
 * @author: chenshuofang
 * @create: 2020-08-07 09:30
 **/
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findByOpenId(String openid);
}
