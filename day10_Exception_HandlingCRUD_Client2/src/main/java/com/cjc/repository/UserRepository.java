package com.cjc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cjc.UserModel.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {



}
