package com.hb.crud.CrudwithThreeLayer.serviceimpl;

import com.hb.crud.CrudwithThreeLayer.dto.AddressDto;
import com.hb.crud.CrudwithThreeLayer.dto.EmployeeDto;
import com.hb.crud.CrudwithThreeLayer.dto.UserDto;
import com.hb.crud.CrudwithThreeLayer.model.AddressBO;
import com.hb.crud.CrudwithThreeLayer.model.EmployeeBO;
import com.hb.crud.CrudwithThreeLayer.model.UserBo;
import com.hb.crud.CrudwithThreeLayer.repository.EmployeeDAO;
import com.hb.crud.CrudwithThreeLayer.repository.UserDAO;
import com.hb.crud.CrudwithThreeLayer.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        employeeDto.setProjectIds(convertArrayToString(employeeDto.getProjectList()));
        EmployeeBO emp = modelMapper.map(employeeDto, EmployeeBO.class);
        emp = employeeDAO.save(emp);

        employeeDto = modelMapper.map(emp,EmployeeDto.class);
        employeeDto.setProjectList(convertStringToIntList(employeeDto.getProjectIds()));
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<EmployeeBO> empList= employeeDAO.findAll();
        List<EmployeeDto>empDtoList =empList.stream().map(em->{
            EmployeeDto employeeDto=modelMapper.map(em,EmployeeDto.class);
            employeeDto.setProjectList(convertStringToIntList(em.getProjectIds()));

            return employeeDto;
        }
        ).collect(Collectors.toList());

        return empDtoList;

    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        EmployeeBO emp=employeeDAO.findById(id);
        EmployeeDto employeeDto=modelMapper.map(emp,EmployeeDto.class);
        employeeDto.setProjectList(convertStringToIntList(emp.getProjectIds()));

        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        employeeDto.setProjectIds(convertArrayToString(employeeDto.getProjectList()));
        EmployeeBO emp1 =modelMapper.map(employeeDto,EmployeeBO.class);
        emp1=employeeDAO.update(emp1);

        EmployeeDto employeeDto1=modelMapper.map(emp1,EmployeeDto.class);
        employeeDto1.setProjectList(convertStringToIntList(employeeDto1.getProjectIds()));

        return employeeDto1;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public void deleteAllEmployee() {
        employeeDAO.deleteAll();

    }

    @Override
    public List<EmployeeDto> searchEmployee(String s) {
        List<EmployeeBO> emp= employeeDAO.searchAndGetAllEmp(s);
        List<EmployeeDto> empDto1= emp.stream().map(x->{
            EmployeeDto employeeDto= modelMapper.map(x,EmployeeDto.class);
            employeeDto.setProjectList(convertStringToIntList(x.getProjectIds()));
            return employeeDto;
        }
        ).collect(Collectors.toList());
        return empDto1;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        Optional user=userDAO.findByUsername(userDto.getUsername());
        if(!user.isPresent()){
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            UserBo userBo=modelMapper.map(userDto,UserBo.class);
            userBo=userDAO.save(userBo);

            return modelMapper.map(userBo,UserDto.class);
        }else {
            return null;
        }

    }

    public String convertArrayToString(List<Integer> intArray){
        String result = intArray.stream().map(i->i.toString()).collect(Collectors.joining(", "));
        return result;
    }

    public List<Integer> convertStringToIntList(String s){
        List<Integer> ar= Arrays.stream(s.split(", ")).map(x->Integer.valueOf(x))
                .collect(Collectors.toList());
        return ar;
    }


}
