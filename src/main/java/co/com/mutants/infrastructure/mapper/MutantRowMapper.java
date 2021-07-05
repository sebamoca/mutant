package co.com.mutants.infrastructure.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mutants.domain.model.HumanADN;

public class MutantRowMapper implements RowMapper<HumanADN> {

	@Override
	public HumanADN mapRow(ResultSet rs, int rowNum) throws SQLException {
		HumanADN humanADN = new HumanADN();
		humanADN.setId(rs.getLong("id_sequence"));
		humanADN.setSequence(rs.getString("sequence_adn"));
		humanADN.setHuman_mutant(rs.getString("human_mutant"));

		return humanADN;
	}

}
