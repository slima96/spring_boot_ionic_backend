package br.com.viatekbrasil.industrial.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.viatekbrasil.industrial.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	public String strategy;
	
	@Bean
	public boolean instatiateDatabase() {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbService.instatiateTestDatabase();		
		return true;
	}
}
