package co.com.mutants.application;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.mutants.domain.service.MutantDomainService;
import co.com.mutants.infrastructure.dto.RequestMutantDTO;
import co.com.mutants.infrastructure.dto.ResponseMutantDTO;
import co.com.mutants.infrastructure.dto.ResponseStatsDTO;

@RestController
public class MutantService {

	@Autowired
	private MutantDomainService mutantDomainService;

	@GetMapping(value = "api")
	public String hello() {
		return "API REST - MUTANT";
	}

	public MutantService(MutantDomainService mutantDomainService) {
		this.mutantDomainService = mutantDomainService;
	}

	@PostMapping(value = "/mutant/")
	public ResponseEntity<ResponseMutantDTO> validateHuman(@Valid @RequestBody RequestMutantDTO requestMutantDTO) {
		ResponseMutantDTO responseMutantDTO = new ResponseMutantDTO();

		responseMutantDTO.setMutant(mutantDomainService.isMutant(requestMutantDTO.getDna()));
		mutantDomainService.saveSequenceADN(requestMutantDTO.getDna(), responseMutantDTO.isMutant());

		if (responseMutantDTO.isMutant()) {
			responseMutantDTO.setMessage("Human is mutant");
			return ResponseEntity.ok(responseMutantDTO);
		} else {
			ResponseEntity.status(HttpStatus.FORBIDDEN);
			responseMutantDTO.setMessage("Human is not mutant");
			return new ResponseEntity<ResponseMutantDTO>(responseMutantDTO, HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping(value = "/stats")
	public @ResponseBody ResponseStatsDTO getStatsHuman() {
		return mutantDomainService.getResponseStatsHuman();
	}
}