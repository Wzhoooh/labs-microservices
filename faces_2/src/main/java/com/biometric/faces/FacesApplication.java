package com.biometric.faces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
public class FacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacesApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		Locale rus = new Locale("ru", "RU");
		Locale eng = new Locale("en", "US");
		//Locale ital = new Locale("it", "IT");
		Locale ital = Locale.ITALIAN;
		localeResolver.setDefaultLocale(rus);
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource =
				new ResourceBundleMessageSource();
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasenames("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	InfoContributor getInfoContributor() {
		Map<String, Object> details = new HashMap<>();
		details.put("nameApp", "Face Recognition Terminals");
		details.put("description", "This is an application for working with a catalog of biometric facial recognition terminals.");
		details.put("developers", "Mayborodina Irina, Savinoff Alexey");
		details.put("email", "example@gmail.com");
		Map<String, Object> wrapper = new HashMap<>();
		wrapper.put("info", details);
		return new MapInfoContributor(wrapper);
	}

}
