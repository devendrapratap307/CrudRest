package com.hb.crud.CrudwithThreeLayer.controller;

import com.hb.crud.CrudwithThreeLayer.dto.EmployeeDto;
import com.hb.crud.CrudwithThreeLayer.response.ResponseData;
import com.hb.crud.CrudwithThreeLayer.service.EmployeeService;
import com.hb.crud.CrudwithThreeLayer.validation.EmployeeValidation;
import com.hb.crud.CrudwithThreeLayer.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    @Qualifier("EmployeeValidation")
    private EmployeeValidation employeeValidation;

    @InitBinder
    private void initBinder(WebDataBinder binder){
        binder.setValidator(employeeValidation);
    }



    @PostMapping("/add")
    public ResponseEntity<?> saveEmp(@Valid @RequestBody EmployeeDto employeeDto, BindingResult errors){
        if (errors.hasErrors()){
            return new ResponseEntity<>(ResponseData.setApiErrorResponse( null,"error", ValidationError.validateError(errors)), HttpStatus.OK);
        }

        return new ResponseEntity<>(ResponseData.setApiErrorResponse(employeeService.saveEmployee(employeeDto),"Data saved successfully...",null), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllEmp(){
        return new ResponseEntity<>(ResponseData.setApiErrorResponse(employeeService.getAllEmployee(),"All data found",null),HttpStatus.OK);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmp(@PathVariable ("id") long id){
        return new ResponseEntity<>(ResponseData.setApiErrorResponse(employeeService.getEmployee(id),"data found",null),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmp(@Valid @RequestBody EmployeeDto employeeDto, Errors errors){
        if (errors.hasErrors()){
            return new ResponseEntity<>(ResponseData.setApiErrorResponse(null,"error",ValidationError.validateError(errors)), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseData.setApiErrorResponse(employeeService.updateEmployee(employeeDto),"Data updated...",null),HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmp(@PathVariable("id") long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(ResponseData.setApiErrorResponse(null,"Employee deleted at id :"+id,null),HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllEmp(){
        employeeService.deleteAllEmployee();
        return new ResponseEntity<>(ResponseData.setApiErrorResponse(null,"All employees are deleted...",null), HttpStatus.OK);
    }


    @GetMapping("/search/{s}")
    public ResponseEntity<?> getAndSearch(@PathVariable ("s") String s){
        return new ResponseEntity<>(ResponseData.setApiErrorResponse(employeeService.searchEmployee(s),"data found",null),HttpStatus.OK);
    }

}