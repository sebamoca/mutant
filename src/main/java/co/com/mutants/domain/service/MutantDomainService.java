package co.com.mutants.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import co.com.mutants.domain.dependencies.IMutantRepository;
import co.com.mutants.domain.exceptions.BusinessError;
import co.com.mutants.domain.model.HumanADN;
import co.com.mutants.infrastructure.dto.ResponseStatsDTO;

public class MutantDomainService {

	private static final String VALID_CHARACTERS = "[ATCG]";
	private static final String SI = "S";
	private static final String NO = "N";

	private IMutantRepository mutantRepository;

	public void setMutantRepository(IMutantRepository mutantRepository) {
		this.mutantRepository = mutantRepository;
	}

	public boolean isMutant(String[] dna) {
		boolean isHumanMutant = false;

		if (isValidSequencesDNA(dna)) {
			String[][] arraySequenceDNA = createSequenceDNAFromVector(dna);

			String[] arrayRows = extractRows(arraySequenceDNA);
			String[] arrayColumns = extractColumns(arraySequenceDNA);
			String[] arrayPrinDiagonal = extractPrincipalDiagonal(arraySequenceDNA);
			String[] arraySeconDiagonal = extractSecondaryDiagonal(arraySequenceDNA);
			String[] arregloResult = Stream.of(arrayRows, arrayColumns, arrayPrinDiagonal, arraySeconDiagonal)
					.flatMap(Stream::of).toArray(String[]::new);

			isHumanMutant = checkSequencesDNA(arregloResult);
		} else {
			throw new BusinessError("Character sequences can only contain letters: " + VALID_CHARACTERS);
		}

		return isHumanMutant;
	}

	private static boolean isValidSequencesDNA(String[] dna) {
		String regexp = "^" + VALID_CHARACTERS + "*";
		Pattern pat = Pattern.compile(regexp);

		Predicate<String> filter = sequence -> pat.matcher(sequence).matches();
		return Arrays.asList(dna).stream().allMatch(filter);
	}

	private String[][] createSequenceDNAFromVector(String[] dna) {
		int n = dna.length;
		String[][] arraySequenceDNA = new String[n][n];

		for (int i = 0; i < n; i++) {
			String sequence = dna[i];
			int j = 0;
			for (char character : sequence.toCharArray()) {
				arraySequenceDNA[i][j++] = String.valueOf(character);
			}
		}

		return arraySequenceDNA;
	}

	private String[] extractRows(String[][] arraySequenceDNA) {
		String[] arrayRows = new String[arraySequenceDNA.length];
		String sequenceRow;

		for (int i = 0; i < arraySequenceDNA.length; i++) {
			sequenceRow = null;
			for (String item : arraySequenceDNA[i]) {
				sequenceRow = sequenceRow != null ? sequenceRow + item : item;
			}

			arrayRows[i] = sequenceRow;
		}

		return arrayRows;
	}

	private String[] extractColumns(String[][] arraySequenceDNA) {
		String[] arrayColumns = new String[arraySequenceDNA.length];
		String sequenceColumn;

		for (int i = 0; i < arraySequenceDNA.length; i++) {
			sequenceColumn = null;
			for (int j = 0; j < arraySequenceDNA[i].length; j++) {
				sequenceColumn = sequenceColumn != null ? sequenceColumn + arraySequenceDNA[j][i]
						: arraySequenceDNA[j][i];
			}

			arrayColumns[i] = sequenceColumn;
		}

		return arrayColumns;
	}

	private String[] extractPrincipalDiagonal(String[][] arraySequenceDNA) {
		String[] arrayPrinDiagonal = new String[1];
		String sequencePri = null;

		for (int i = 0; i < arraySequenceDNA.length; i++) {
			sequencePri = sequencePri != null ? sequencePri + arraySequenceDNA[i][i] : arraySequenceDNA[i][i];
		}

		arrayPrinDiagonal[0] = sequencePri;

		return arrayPrinDiagonal;
	}

	private String[] extractSecondaryDiagonal(String[][] arraySequenceDNA) {
		String[] arraySeconDiagonal = new String[1];
		String sequenceSec = null;

		for (int i = 0; i < arraySequenceDNA.length; i++) {
			sequenceSec = sequenceSec != null ? sequenceSec + arraySequenceDNA[i][(arraySequenceDNA.length - 1) - i]
					: arraySequenceDNA[i][(arraySequenceDNA.length - 1) - i];
		}

		arraySeconDiagonal[0] = sequenceSec;

		return arraySeconDiagonal;
	}

	private boolean checkSequencesDNA(String[] sequenceDNA) {
		int tope = 4;
		int count = 0;

		char caracterAnterior;
		char caracterActual;

		for (String cadena : sequenceDNA) {
			count = 0;
			char[] arregloCaracteres = cadena.toCharArray();

			for (int i = 0; i < arregloCaracteres.length; i++) {
				caracterAnterior = (i == 0) ? arregloCaracteres[0] : arregloCaracteres[i - 1];
				caracterActual = cadena.toCharArray()[i];

				if (caracterAnterior == caracterActual) {
					count++;

					if (count == tope) {
						return true;
					}
				} else {
					count = 0;
				}
			}
		}

		return false;
	}

	public ResponseStatsDTO getResponseStatsHuman() {
		List<HumanADN> listHumanADN = (List<HumanADN>) mutantRepository.consultADNHumans();

		Predicate<HumanADN> filterMutant = humanADN -> humanADN.getHuman_mutant().equalsIgnoreCase("S");
		Predicate<HumanADN> filterNotMutant = humanADN -> humanADN.getHuman_mutant().equalsIgnoreCase("N");

		Long count_mutant_dna = listHumanADN.stream().filter(filterMutant).count();
		Long count_human_dna = listHumanADN.stream().filter(filterNotMutant).count();

		ResponseStatsDTO responseStatsDTO = new ResponseStatsDTO();
		responseStatsDTO.setCount_mutant_dna(count_mutant_dna);
		responseStatsDTO.setCount_human_dna(count_human_dna);
		responseStatsDTO
				.setRatio(
						new BigDecimal(
								responseStatsDTO.getCount_human_dna() != 0
										? responseStatsDTO.getCount_mutant_dna().doubleValue()
												/ responseStatsDTO.getCount_human_dna().doubleValue()
										: 0D).setScale(2, RoundingMode.HALF_UP).doubleValue());

		return responseStatsDTO;
	}

	public void saveSequenceADN(String[] dna, boolean isMutant) {
		HumanADN humanADN = new HumanADN();

		StringBuffer sb = new StringBuffer();

		Arrays.asList(dna).stream().forEach(sequence -> {
			if (dna[dna.length - 1].equalsIgnoreCase(sequence)) {
				sb.append(sequence);
			} else {
				sb.append(sequence).append(",");
			}
		});

		humanADN.setSequence(sb.toString());

		if (isMutant) {
			humanADN.setHuman_mutant(SI);
		} else {
			humanADN.setHuman_mutant(NO);
		}

		mutantRepository.saveADNHuman(humanADN);
	}

}
