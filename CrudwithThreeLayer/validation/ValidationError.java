package com.hb.crud.CrudwithThreeLayer.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationError {
    public static List<ErrorObjectFormat> validateError(Errors ex){
        //Map<String,String> errorMap= new HashMap<>();
        List<ErrorObjectFormat> errorList=new ArrayList<>();


        ex.getAllErrors().forEach((error)->{
            String fieldName=((FieldError) error).getField();
            String message=error.getDefaultMessage();
            //errorMap.put(fieldName,message);
            ErrorObjectFormat errorObjectFormat= new ErrorObjectFormat();
            errorObjectFormat.setFieldName(fieldName);
            errorObjectFormat.setMessage(message);
            errorList.add(errorObjectFormat);
        });
        return errorList;
    }
}
