package com.kyoborealco.kreps.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.kyoborealco.kreps.util.ArrayUtil;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware {
 
	private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    @Bean
    public ViewResolver htmlViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine(htmlTemplateResolver()));
        resolver.setContentType("text/html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setViewNames(ArrayUtil.array("*.html"));
        return resolver;
    }

    @Bean
    public ViewResolver javascriptViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine(javascriptTemplateResolver()));
        resolver.setContentType("application/javascript");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setViewNames(ArrayUtil.array("*.js"));
        return resolver;
    }

    @Bean
    public ViewResolver plainViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine(plainTemplateResolver()));
        resolver.setContentType("text/plain");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setViewNames(ArrayUtil.array("*.txt"));
        return resolver;
    }

    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new LayoutDialect(new GroupingStrategy()));
        engine.addDialect(new Java8TimeDialect());
        engine.setTemplateResolver(templateResolver);
        engine.setTemplateEngineMessageSource(messageSource());
        return engine;
    }
    
    private ITemplateResolver htmlTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/templates/");
        resolver.setCacheable(false);
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    private ITemplateResolver javascriptTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/static/js/");
        resolver.setCacheable(false);
        resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
        return resolver;
    }

    private ITemplateResolver plainTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/static/txt/");
        resolver.setCacheable(false);
        resolver.setTemplateMode(TemplateMode.TEXT);
        return resolver;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/css/**", "/js/**", "/webfonts/**", "/img/**", "/media/**")
          .addResourceLocations("classpath:/static/css/", "classpath:/static/js/",
        		  "classpath:/static/webfonts/", "classpath:/static/img/",
        		  "classpath:/static/media/");
    }
    
    /**
     * password encoder
     * 
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
	 * 한글메시지처리
	 * 
	 * @see https://qiita.com/yukina-ge/items/b4303920c60ec3251d37 (UTF8 message config)
	 */
	@Override
    public Validator getValidator() {
        return validator();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    private MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // validation message 실제위치
        messageSource.setBasename("classpath:/ValidationMessages");
        // UTF-8 설정
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }    
}