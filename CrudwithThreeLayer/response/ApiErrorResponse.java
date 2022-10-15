package com.hb.crud.CrudwithThreeLayer.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hb.crud.CrudwithThreeLayer.validation.ErrorObjectFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse{
    private Integer statusCode;
    private HttpStatus status;
    //private LocalDateTime timestamp;
    private String message;
    private Object data;
    private List<ErrorObjectFormat> errors;
}
