package com.imooc.exception;

import cn.quantgroup.dinglog.util.DingTalkSendMsgUtil;
import com.imooc.log.LogTraceIdUtil;
import com.imooc.properties.CommonProperties;
import com.imooc.result.Result;
import com.imooc.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private CommonProperties commonProperties;

    ExecutorService executorService = Executors.newFixedThreadPool(4);


    @ExceptionHandler(value = {ConstraintViolationException.class})
    public Object constraintViolationHandle(ConstraintViolationException e) {
        log.warn("参数校验错误！", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        if (violations != null) {
            StringBuilder buffer = new StringBuilder();
            for (ConstraintViolation<?> constraintViolation : violations) {
                if (constraintViolation != null) {
                    buffer.append(constraintViolation.getMessage()).append(";");
                }
            }
            return Result.failure(ResultCode.PARAM_ERROR, buffer.toString());
        }
        return Result.failure(ResultCode.PARAM_ERROR);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public Object validHandle(MethodArgumentNotValidException e) {
        log.warn("方法参数校验错误！", e);
        BindingResult result = e.getBindingResult();
        if (result != null && result.getAllErrors() != null) {
            StringBuilder buffer = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                buffer.append(error.getDefaultMessage()).append(";");
            }
            return Result.failure(buffer.toString());
        }
        return Result.failure(ResultCode.PARAM_ERROR);
    }

    @ExceptionHandler(value = {BindException.class})
    public Object validHandle(BindException e) {
        log.warn("参数校验错误！", e);
        BindingResult result = e.getBindingResult();
        if (result != null && result.getAllErrors() != null) {
            StringBuilder buffer = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                buffer.append(error.getDefaultMessage()).append(";");
            }
            return Result.failure(ResultCode.PARAM_ERROR, buffer.toString());
        }
        return Result.failure(ResultCode.PARAM_ERROR);

    }

    @ExceptionHandler(value = {BizException.class})
    public Object bizExceptionHandle(BizException e) {
        log.warn("业务处理异常！", e);
        ResultCode resultCode = e.getResultCode();
        if (null != resultCode) {
            if (ResultCode.CUSTOM_ERROR_MSG.equals(resultCode)) {
                return Result.failure(e.getMessage());
            } else {
                return Result.failure(resultCode, e.getMessage());
            }
        } else {
            return Result.failure(e.getMessage());
        }

    }

    @ExceptionHandler(value = {Throwable.class, Exception.class})
    public Object errorHandle(Exception e) {
        log.error("服务器异常！", e);
        if (!(e instanceof DuplicateKeyException)) {
            sendMsg(e, LogTraceIdUtil.getTraceId());
        }
        return Result.failure(ResultCode.SERVICE_ERROR, e.getMessage());
    }

    private void sendMsg(Exception e, String traceId) {
        executorService.execute(() -> {
            if (!(e instanceof DuplicateKeyException)) {
                String token = commonProperties.getKdspApiDingtalkToken();
                String keyWords = commonProperties.getKdspApiDingtalkKeyWords();
                keyWords = StringUtils.isEmpty(keyWords) ? traceId : keyWords + traceId;
                DingTalkSendMsgUtil.sendTextMsg(String.valueOf(e), token, keyWords, "quantgroup");
            }
        });
    }


}