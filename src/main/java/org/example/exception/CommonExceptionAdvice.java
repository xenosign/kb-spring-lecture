package org.example.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
    private String context = "/exception";

    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        log.error(e.getMessage());

        List<StackTraceElement> errStackList = Arrays.asList(e.getStackTrace());

        for (StackTraceElement stackTraceElement : errStackList) {
            log.error(stackTraceElement.toString());
        }

        model.addAttribute("errStackList", errStackList);
        return context + "/error-page";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException e) {
        return "/exception/404";
    }
}
