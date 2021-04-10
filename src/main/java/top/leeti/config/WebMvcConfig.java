package top.leeti.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 无权访问，前往告知“无权访问”的页面
        registry.addViewController("/admin/noAccess").setViewName("no-access");
        registry.addViewController("/admin/index").setViewName("my-index");
    }
}
