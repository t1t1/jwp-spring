package next.config;

import java.util.List;

import next.aop.PerformanceAspect;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import core.web.argumentresolver.LoginUserHandlerMethodArgumentResolver;

@Configuration
@EnableWebMvc
@ComponentScan(
	basePackages = { "next.controller", "next.aop" },
//	includeFilters = @ComponentScan.Filter(value = {Controller.class, PerformanceAspect.class}, type = FilterType.ANNOTATION)
//	basePackages = { "next.controller" },
	includeFilters = @ComponentScan.Filter(value = {Controller.class}, type = FilterType.ANNOTATION)
)
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    private static final int CACHE_PERIOD = 31556926; // one year
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");
        return bean;
    }
    
    @Bean
    public MessageSource messageSource() {
    	ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
//    	reloadableResourceBundleMessageSource.setBasename("classpath:messages.properties");
    	reloadableResourceBundleMessageSource.setBasename("classpath:messages");
    	reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");;
    	reloadableResourceBundleMessageSource.setCacheSeconds(10);
    	return reloadableResourceBundleMessageSource;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/resources/**")
                .addResourceLocations("/WEB-INF/static_resources/")
                .setCachePeriod(CACHE_PERIOD);
    }
    
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    	argumentResolvers.add(new LoginUserHandlerMethodArgumentResolver());
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // Serving static files using the Servlet container's default Servlet.
        configurer.enable();
    }    
}
