package co.com.mutants.domain.service.mock;

import java.util.ArrayList;
import java.util.List;

import co.com.mutants.domain.model.HumanADN;

public class HumanADNMock {

	public static List<HumanADN> getListHumanADN() {
		List<HumanADN> listHumanADN = new ArrayList<>();
		listHumanADN.add(getHumanMutant());
		listHumanADN.add(getHumanNotMutant());

		return listHumanADN;
	}

	public static HumanADN getHumanMutant() {
		HumanADN humanADN = new HumanADN();
		humanADN.setId(1L);
		humanADN.setSequence("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG");
		humanADN.setHuman_mutant("S");

		return humanADN;
	}

	public static HumanADN getHumanNotMutant() {
		HumanADN humanADN = new HumanADN();
		humanADN.setId(2L);
		humanADN.setSequence("ATGCGA,CAGTGC,TTATTT,AGACGG,GCGTCA,TCACTG");
		humanADN.setHuman_mutant("N");

		return humanADN;
	}
}
