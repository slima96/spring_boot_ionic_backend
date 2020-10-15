package br.com.viatekbrasil.industrial.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.viatekbrasil.industrial.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instatiateDatabase() {
		dbService.instatiateTestDatabase();		
		return true;
	}
}
