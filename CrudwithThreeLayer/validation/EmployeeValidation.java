package com.hb.crud.CrudwithThreeLayer.validation;

import ch.qos.logback.core.util.DatePatternToRegexUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hb.crud.CrudwithThreeLayer.controller.EmployeeController;
import com.hb.crud.CrudwithThreeLayer.dto.EmployeeDto;
import org.apache.el.util.Validation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.ValidationAnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.text.DateFormat;
import java.text.Format;
import java.util.regex.Pattern;

@ControllerAdvice(assignableTypes = EmployeeController.class)
@Qualifier("EmployeeValidation")
public class EmployeeValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeDto employeeDto= (EmployeeDto) target;

        ValidationUtils.rejectIfEmpty(errors,"gender",null,"Gender should not be null");
        ValidationUtils.rejectIfEmpty(errors,"dob",null,"DOB should not be null");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors,"salary",null,"Salary should not be null");
        //ValidationUtils.rejectIfEmpty(errors,"projectIds",null,"Project IDs should not be null");

        if(employeeDto.getFirstName()==null || employeeDto.getFirstName().length()<3){
            errors.rejectValue("firstName",null,"First name length should be grater than 3 and not null");

        }

        if(employeeDto.getLastName()==null || employeeDto.getLastName().length()<3){
            errors.rejectValue("lastName",null,"Last name length should be grater than 3 and not null");

        }

        String regex="^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        if(employeeDto.getEmail()==null || (!employeeDto.getEmail().matches(regex))){
            errors.rejectValue("email",null,"Invalid email");
        }

        if(employeeDto.getSalary()<=0.0){
            errors.rejectValue("salary",null,"Salary should be greater than 0.");
        }


    }
}
