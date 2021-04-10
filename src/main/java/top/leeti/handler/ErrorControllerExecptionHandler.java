package top.leeti.handler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;
import java.util.Map;

@Controller
@RequestMapping("/error")
class ExceptionController implements ErrorController {

    /**
     * 定义404的ModelAndView
     */
    @RequestMapping("404")
    public ModelAndView errorHtml404(HttpServletRequest request,
                                     HttpServletResponse response) {
        response.setStatus(404);
        Map<String, Object> model = new Hashtable<>();
        model.put("msg", "页面不存在！");
        return new ModelAndView("error", model);
    }

//    /**
//     * 定义404的JSON数据
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "404")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> error404(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request,
//                isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<Map<String, Object>>(body, status);
//    }

    /**
     * 定义500的ModelAndView
     */
    @RequestMapping("500")
    public ModelAndView errorHtml500(HttpServletRequest request,
                                     HttpServletResponse response) {
        response.setStatus(500);
        Map<String, Object> model = new Hashtable<>();
        model.put("msg", "服务器这个笨蛋，不能处理您刚才提交的请求！");
        return new ModelAndView("error", model);
    }


//    /**
//     * 定义500的错误JSON信息
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "500")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> error500(HttpServletRequest request) {
//        Map<String, Object> body = getErrorAttributes(request,
//                isIncludeStackTrace(request, MediaType.TEXT_HTML));
//        HttpStatus status = getStatus(request);
//        return new ResponseEntity<Map<String, Object>>(body, status);
//    }

    /**
     * 实现错误路径,暂时无用
     */
    @Override
    public String getErrorPath() {
        return "";
    }

}