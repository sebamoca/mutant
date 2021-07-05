package co.com.mutants.infrastructure.dto;

public class ResponseMutantDTO {

	private String message;
	private boolean mutant;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isMutant() {
		return mutant;
	}

	public void setMutant(boolean mutant) {
		this.mutant = mutant;
	}
}
