package com.ingatlan2.ingatlan.repository;

import com.ingatlan2.ingatlan.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByFullname(String name);

    User findByActivation(String code);

    User findByUserid(Long userid);
}
