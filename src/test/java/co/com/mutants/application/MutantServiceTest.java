package co.com.mutants.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.mutants.domain.dependencies.IMutantRepository;
import co.com.mutants.domain.model.HumanADN;
import co.com.mutants.domain.service.MutantDomainService;
import co.com.mutants.domain.service.mock.HumanADNMock;
import co.com.mutants.infrastructure.dto.RequestMutantDTO;
import co.com.mutants.infrastructure.dto.ResponseMutantDTO;
import co.com.mutants.infrastructure.dto.ResponseStatsDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantServiceTest {

	@Autowired
	private MutantDomainService mutantDomainService;

	@MockBean
	IMutantRepository mutantRepository;

	private MutantService mutantService;

	@Before
	public void configurar() {
		mutantService = new MutantService(mutantDomainService);
	}

	@Test
	public void testHumanMutant() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		RequestMutantDTO requestMutantDTO = new RequestMutantDTO();
		requestMutantDTO.setDna(dna);
		ResponseEntity<ResponseMutantDTO> responseMutantDTO = mutantService.validateHuman(requestMutantDTO);
		assertEquals(HttpStatus.OK, responseMutantDTO.getStatusCode());
		assertEquals("Human is mutant", responseMutantDTO.getBody().getMessage());
	}

	@Test
	public void testHumanNotMutant() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" };
		RequestMutantDTO requestMutantDTO = new RequestMutantDTO();
		requestMutantDTO.setDna(dna);
		ResponseEntity<ResponseMutantDTO> responseMutantDTO = mutantService.validateHuman(requestMutantDTO);
		assertEquals(HttpStatus.FORBIDDEN, responseMutantDTO.getStatusCode());
	}

	@Test
	public void testGetStatsHuman() {
		List<HumanADN> listHumanADN = new ArrayList<>();
		listHumanADN.add(HumanADNMock.getHumanMutant());

		Mockito.when(mutantRepository.consultADNHumans()).thenReturn(listHumanADN);

		ResponseStatsDTO responseStatsDTO = mutantService.getStatsHuman();
		assertEquals(1, responseStatsDTO.getCount_mutant_dna().longValue());
		assertEquals(0, responseStatsDTO.getCount_human_dna().longValue());
		assertTrue(responseStatsDTO.getRatio().doubleValue() == 0);
	}
}
