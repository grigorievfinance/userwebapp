package org.softmaker.userwebapp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softmaker.userwebapp.util.ValidationUtil;
import org.softmaker.userwebapp.util.exception.ApplicationException;
import org.softmaker.userwebapp.util.exception.ErrorType;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final MessageSourceAccessor messageSourceAccessor;

    public GlobalExceptionHandler(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView wrongRequest(HttpServletRequest request, NoHandlerFoundException e) throws Exception {
        return null;
    }

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView applicationErrorHandler(HttpServletRequest request, ApplicationException exception) throws Exception {
        return null;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        log.error("Exception at request " + request.getRequestURL(), e);
        return null;
    }

    private ModelAndView logAndGetExceptionView(HttpServletRequest request, Exception e, boolean logException, ErrorType errorType, String message) {
        Throwable rootCause = ValidationUtil.logAndGetRootCause(log, request, e, logException, errorType);

        HttpStatus httpStatus = errorType.getStatus();
        ModelAndView modelAndView = new ModelAndView("exception",
                Map.of("exception", rootCause, "message", message != null ? message : ValidationUtil.getMessage(rootCause),
                        "status", httpStatus));
        modelAndView.setStatus(httpStatus);
        return modelAndView;
    }
}
