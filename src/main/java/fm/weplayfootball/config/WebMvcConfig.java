package fm.weplayfootball.config;

import java.util.List;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles2.TilesConfigurer;
import org.springframework.web.servlet.view.tiles2.TilesView;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"fm.weplayfootball.web","fm.weplayfootball.common.utils"}, excludeFilters = { @Filter(Configuration.class) })
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Inject
	private ConnectionRepository connectionRepository;

	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] {
				"/WEB-INF/layouts/tiles.xml",
				"/WEB-INF/views/**/tiles.xml"                           
		});
		configurer.setCheckRefresh(true);
		return configurer;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages/messages");
		return messageSource;
	}

	public void configureMessageConverters(final List<HttpMessageConverter<?>> messageConverters) {

		final ClassLoader classLoader = getClass().getClassLoader();
		if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", classLoader)) {
			messageConverters.add(new MappingJackson2HttpMessageConverter());
		} else if (ClassUtils.isPresent("org.codehaus.jackson.map.ObjectMapper", classLoader)) {
			messageConverters.add(new MappingJacksonHttpMessageConverter());
		}

		super.configureMessageConverters(messageConverters);
	}


	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(1000000);
		return resolver;
	}
	/*
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        mappings.put("org.springframework.web.servlet.PageNotFound", "p404");
        mappings.put(MaxUploadSizeExceededException.class.getName(), "/");
        mappings.put("org.springframework.transaction.TransactionException", "dataAccessFailure");
        b.setExceptionMappings(mappings);
        return b;
    }
	 */
}
