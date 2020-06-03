package com.softobt.core.config;
/**
 * @author aobeitor
 * @since 05/15/19
 */
import com.softobt.core.handlers.RequestOriginHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration("CoreWebConfig")
public class WebConfig implements WebMvcConfigurer{


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestOriginHandler());
    }
}
