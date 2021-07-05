package co.com.mutants.infrastructure.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import co.com.mutants.domain.dependencies.IMutantRepository;
import co.com.mutants.domain.service.MutantDomainService;

@Configuration
@ComponentScan
@EnableAutoConfiguration()
public class AppConfig {

	@Autowired
	private IMutantRepository mutantRepository;

	@Bean(name = "mutantDomainService")
	public MutantDomainService mutantDomainService() {
		MutantDomainService mutantDomainService = new MutantDomainService();
		mutantDomainService.setMutantRepository(mutantRepository);
		return mutantDomainService;
	}
}
