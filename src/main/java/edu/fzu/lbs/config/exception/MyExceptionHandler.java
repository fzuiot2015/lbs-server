package edu.fzu.lbs.config.exception;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import edu.fzu.lbs.entity.dto.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.SocketTimeoutException;

/**
 * 用于捕获异常并返回对应的反馈结果
 */
@RestControllerAdvice
public class MyExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(MyException.class)
    public ResultDTO handleMyException(MyException e) {
        logger.error("【自定异常】", e);
        return ResultDTO.error(e.getResultEnum());
    }

    @ExceptionHandler(Exception.class)
    public ResultDTO handle(Exception e) {
        logger.error("【系统异常】", e);
        return ResultDTO.error(ResultEnum.UN_KNOW_ERROR);
    }

    @ExceptionHandler(BindException.class)
    public ResultDTO handle(BindException e) {
        logger.error("【缺少请求参数】", e);
        return ResultDTO.error(ResultEnum.FORMAT_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultDTO handle(MissingServletRequestParameterException e) {
        logger.error("【缺少请求参数】", e);
        return ResultDTO.error(ResultEnum.MISSING_PARAM);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResultDTO handle(TokenExpiredException e) {
        logger.error("【Token超时】", e);
        return ResultDTO.error(ResultEnum.TOKEN_EXPIRED);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResultDTO handle(JWTVerificationException e) {
        logger.error("【Token认证失败】", e);
        return ResultDTO.error(ResultEnum.INCORRECT_TOKEN);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResultDTO handle(DuplicateKeyException e) {
        logger.error("【主键冲突异常】", e);
        return ResultDTO.error(ResultEnum.DUPLICATE_ID);
    }

    @ExceptionHandler(SocketTimeoutException.class)
    public ResultDTO handle(SocketTimeoutException e) {
        logger.error("【超时异常】", e);
        return ResultDTO.error(ResultEnum.TIMEOUT);
    }


}
