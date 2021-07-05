package co.com.mutants.infrastructure.persistence.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.com.mutants.domain.dependencies.IMutantRepository;
import co.com.mutants.domain.model.HumanADN;
import co.com.mutants.infrastructure.mapper.MutantRowMapper;

@Repository
public class MutantRepository implements IMutantRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<HumanADN> consultADNHumans() {
		String sql = "SELECT id_sequence, sequence_adn, human_mutant FROM adn_human";
		RowMapper<HumanADN> rowMapper = new MutantRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public void saveADNHuman(HumanADN humanADN) {
		String sql = "INSERT INTO adn_human (sequence_adn, human_mutant) VALUES (?, ?)";
		jdbcTemplate.update(sql, humanADN.getSequence(), humanADN.getHuman_mutant());
	}

}
