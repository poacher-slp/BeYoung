package per.poacher.beyoungmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import per.poacher.beyoungmall.interceptor.LoginInterceptor;

import java.util.ArrayList;
import java.util.List;

/** 处理器拦截器的注册
 * @author poacher
 * @create 2022-05-04-12:24
 */
@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginInterceptor();
        //    配置白名单
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/web/adminLogin.html");
        patterns.add("/web/adminRegister.html");
        patterns.add("/web/search.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/products/**");
//        patterns.add("/products/search/**");

        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
