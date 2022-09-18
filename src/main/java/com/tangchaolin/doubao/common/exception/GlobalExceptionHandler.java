package com.tangchaolin.doubao.common.exception;

import com.tangchaolin.doubao.common.api.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ApiResult<Map<String,Object>> handlle(ApiException ex){

        if(ex.getErrorCode()!=null){
            return ApiResult.failed(ex.getErrorCode());
        }
        return ApiResult.failed(ex.getMessage());

    }


}
