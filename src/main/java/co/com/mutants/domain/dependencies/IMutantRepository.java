package co.com.mutants.domain.dependencies;

import java.util.List;

import co.com.mutants.domain.model.HumanADN;

public interface IMutantRepository {

	List<HumanADN> consultADNHumans();

	void saveADNHuman(HumanADN humanADN);
}
