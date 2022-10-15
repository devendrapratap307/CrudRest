package com.hb.crud.CrudwithThreeLayer.security;

import com.hb.crud.CrudwithThreeLayer.model.UserBo;
import com.hb.crud.CrudwithThreeLayer.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // load user from DB by username of UserBO
        UserBo user =this.userDAO.findByUsername(username).orElseThrow(()->new EntityNotFoundException("Not found"));

        return user;
    }
}
