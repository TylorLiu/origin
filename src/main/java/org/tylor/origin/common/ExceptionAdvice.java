package org.tylor.origin.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author liubin10  2018/3/21
 */
@ControllerAdvice
@Slf4j
@Order
public class ExceptionAdvice {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ServerResponse> handleException(Exception e) {
        ServerResponse response = new ServerResponse(e);
        log.error("异常",e.getCause());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
