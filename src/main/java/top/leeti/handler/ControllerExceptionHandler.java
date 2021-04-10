//package edu.cust.admin.handler;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Slf4j
//@ControllerAdvice
//public class ControllerExceptionHandler {
//
//    @ExceptionHandler(Exception.class)
//    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e){
//        log.error("{} URL disappears exception: {}", request.getRequestURL().toString(), e.toString());
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("msg", e.toString());
//        modelAndView.setViewName("error/error");
//        return modelAndView;
//    }
//}
