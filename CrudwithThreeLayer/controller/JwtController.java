package com.hb.crud.CrudwithThreeLayer.controller;

import com.hb.crud.CrudwithThreeLayer.dto.UserDto;
import com.hb.crud.CrudwithThreeLayer.jwtHelper.JwtUtil;
import com.hb.crud.CrudwithThreeLayer.model.JwtResponse;
import com.hb.crud.CrudwithThreeLayer.response.ResponseData;
import com.hb.crud.CrudwithThreeLayer.security.CustomUserDetailService;
import com.hb.crud.CrudwithThreeLayer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;


@RestController
@CrossOrigin(origins = "*")
public class JwtController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    CustomUserDetailService customUserDetailService;


    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody UserDto userDto) throws Exception {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(),userDto.getPassword()));
        UserDetails userDetails= (UserDetails) this.customUserDetailService.loadUserByUsername(userDto.getUsername());
        String token = this.jwtUtil.generateToken((UserDetails) userDetails);

        System.out.println("jwt  : "+token);
        return new  ResponseEntity<>(ResponseData.setApiErrorResponse(new JwtResponse(token),"Token generated...",null), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
        UserDto user=employeeService.saveUser(userDto);
        return new  ResponseEntity<>(ResponseData.setApiErrorResponse(user,user!=null?"User saved...":"Username already present",null), HttpStatus.OK);
    }




}
