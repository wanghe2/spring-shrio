package com.wang.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wang.shirobean.MyExceptionResolver;

@Configuration
@ImportResource(locations= {"classpath:beans-shiro.xml"})
public class ShiroConfig implements WebMvcConfigurer{

	@Bean
	public MyExceptionResolver myExceptionResolver() {
		return new MyExceptionResolver();
	}
	
	public void configurePathMatch(PathMatchConfigurer configurer) {	
	}

	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		 
		
	}

	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		 
		
	}

	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	}

	public void addFormatters(FormatterRegistry registry) {
		 
		
	}

	public void addInterceptors(InterceptorRegistry registry) {
		 
		
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 
		
	}

	public void addCorsMappings(CorsRegistry registry) {
		 
		
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		 
		
	}

	public void configureViewResolvers(ViewResolverRegistry registry) {
		 
		
	}

	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		 
		
	}

	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		 
		
	}

	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		 
		
	}

	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		 
		
	}

	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		 
		
	}

	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		 
		
	}

	public Validator getValidator() {
		 
		return null;
	}

	public MessageCodesResolver getMessageCodesResolver() {
		 
		return null;
	}
	
}
