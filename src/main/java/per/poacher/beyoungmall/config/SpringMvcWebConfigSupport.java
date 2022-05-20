package per.poacher.beyoungmall.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author poacher
 * @create 2022-05-10-14:09
 */
public class SpringMvcWebConfigSupport implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/**");
    }

}
