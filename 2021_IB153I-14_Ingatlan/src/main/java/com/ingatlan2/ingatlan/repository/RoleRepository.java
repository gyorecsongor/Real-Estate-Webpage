package com.ingatlan2.ingatlan.repository;

import com.ingatlan2.ingatlan.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}
