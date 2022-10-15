package com.hb.crud.CrudwithThreeLayer.response;
import com.hb.crud.CrudwithThreeLayer.validation.ErrorObjectFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResponseData {
    public static ApiErrorResponse setApiErrorResponse(Object data, String message, List<ErrorObjectFormat> errors) {


        ApiErrorResponse apiError = new ApiErrorResponse();
        if(data==null & errors==null){
            apiError.setStatusCode(500);
        } else if (data==null & errors !=null) {
            apiError.setStatusCode(400);
        } else {
            apiError.setStatusCode(200);
        }

        apiError.setStatus(HttpStatus.resolve(apiError.getStatusCode()));
        //apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage(message);
        apiError.setData(data);
        apiError.setErrors(errors);

        return apiError;
    }

//    public static Map<String,String> validateError(Errors er){
//        Map<String, String> erMap = new LinkedHashMap<>();
//        if (er!=null && er.hasErrors()) {
//
//            er.getAllErrors().forEach(res -> {
//                String codes = ((FieldError) res).getField();
//                String messages = res.getDefaultMessage();
//                erMap.put(codes, messages);
//
//            });
//        }
//        return erMap;
//    }
}