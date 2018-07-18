package com.gag;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com.gag")
public class SpringWebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/static/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("file:///./Users/HP/Desktop/uploads/");
		registry.addResourceHandler("/pdfs/**").addResourceLocations("/static/pdf/");
		registry.addResourceHandler("/html/**").addResourceLocations("/static/html/");
		registry.addResourceHandler("/js/**").addResourceLocations("/static/js/");
	}

	 
	  @Bean
	    public CommonsMultipartResolver multipartResolver() {
	        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	        resolver.setDefaultEncoding("utf-8");
	        return resolver;
	    }
	    
	  @Bean
	  public JavaMailSender getJavaMailSender() {
		  Properties prop = new Properties();
		  try {
			prop.load(getClass().getResourceAsStream("/application.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	      mailSender.setHost(prop.getProperty("spring.mail.host"));
	      mailSender.setPort(Integer.parseInt(prop.getProperty("spring.mail.port")));
	       
	      mailSender.setUsername(prop.getProperty("spring.mail.username"));
	      mailSender.setPassword(prop.getProperty("spring.mail.password"));
	       
	      Properties props = mailSender.getJavaMailProperties();
	      props.put("mail.transport.protocol", "smtp");
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.debug", "true");
	       
	      return mailSender;
	  }
	  
		@Bean
		public InternalResourceViewResolver getInternalResourceViewResolver() {
			InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setViewClass(JstlView.class);
			resolver.setPrefix("/WEB-INF/views/jsp/");
			resolver.setSuffix(".jsp");
			
			return resolver;
		}
		
		// localization configuration
		@Bean
		public MessageSource messageSource() {
			ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
			messageSource.setBasename("messages");
			return messageSource;
		}
		
		@Bean
		public LocaleResolver localeResolver() {
			SessionLocaleResolver resolver = new SessionLocaleResolver();
			resolver.setDefaultLocale(Locale.ENGLISH);
			return resolver;
		}
		
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
			changeInterceptor.setParamName("language");
			registry.addInterceptor(changeInterceptor);
		}
		
	}
