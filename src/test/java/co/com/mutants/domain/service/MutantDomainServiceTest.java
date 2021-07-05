package co.com.mutants.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.mutants.domain.dependencies.IMutantRepository;
import co.com.mutants.domain.exceptions.BusinessError;
import co.com.mutants.domain.model.HumanADN;
import co.com.mutants.domain.service.mock.HumanADNMock;
import co.com.mutants.infrastructure.dto.ResponseStatsDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantDomainServiceTest {

	@Autowired
	private MutantDomainService mutantDomainService;

	@MockBean
	IMutantRepository mutantRepository;

	@Test
	public void testHumanMutant() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		assertTrue(mutantDomainService.isMutant(dna));
	}

	@Test
	public void testHumanNotMutant() {
		String[] dna = { "ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" };
		assertFalse(mutantDomainService.isMutant(dna));
	}

	@Test
	public void testSequenceDNANotValid() {
		String[] dna = { "ATGCGZ", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG" };
		boolean isMutant = false;
		try {
			isMutant = mutantDomainService.isMutant(dna);
		} catch (BusinessError businessError) {
			assertFalse(isMutant);
			assertEquals("Character sequences can only contain letters: [ATCG]", businessError.getMessage());
		}
	}

	@Test
	public void testGetStatsHuman() {
		List<HumanADN> listHumanADN = HumanADNMock.getListHumanADN();
		Mockito.when(mutantRepository.consultADNHumans()).thenReturn(listHumanADN);

		ResponseStatsDTO responseStatsDTO = mutantDomainService.getResponseStatsHuman();
		assertEquals(1, responseStatsDTO.getCount_mutant_dna().longValue());
		assertEquals(1, responseStatsDTO.getCount_human_dna().longValue());
	}
}
