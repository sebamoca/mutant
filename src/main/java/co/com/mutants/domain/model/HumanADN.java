package co.com.mutants.domain.model;

public class HumanADN {

	private Long id;
	private String sequence;
	private String human_mutant;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getHuman_mutant() {
		return human_mutant;
	}

	public void setHuman_mutant(String human_mutant) {
		this.human_mutant = human_mutant;
	}

}
