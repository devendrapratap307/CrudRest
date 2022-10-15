package com.hb.crud.CrudwithThreeLayer.repository;

import com.hb.crud.CrudwithThreeLayer.model.UserBo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserDAO extends JpaRepository<UserBo,String> {
    Optional<UserBo> findByUsername(String username);
}
