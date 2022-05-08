package per.poacher.beyoungmall.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 定义一个拦截器
 * @author poacher
 * @create 2022-05-04-13:12
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println(request.getSession());
        Object uid = request.getSession().getAttribute("uid");
        if (uid == null) {
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
