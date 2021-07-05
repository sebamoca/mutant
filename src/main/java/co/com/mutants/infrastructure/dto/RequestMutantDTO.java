package co.com.mutants.infrastructure.dto;

import javax.validation.constraints.NotEmpty;

public class RequestMutantDTO {

	@NotEmpty(message = "The array dna cannot be empty")
	private String[] dna;

	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		this.dna = dna;
	}
}
