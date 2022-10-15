package com.hb.crud.CrudwithThreeLayer.service;

import com.hb.crud.CrudwithThreeLayer.dto.EmployeeDto;
import com.hb.crud.CrudwithThreeLayer.dto.UserDto;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    // create
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    // get all
    List<EmployeeDto> getAllEmployee();

    // get by id
    EmployeeDto getEmployee(Long id);

    //update
    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    // delete by id
    void deleteEmployee(Long id);

    // delete all
    void deleteAllEmployee();

    List<EmployeeDto> searchEmployee(String s);

    // save User
    public UserDto saveUser(UserDto userDto);


}
