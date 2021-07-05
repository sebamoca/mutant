package co.com.mutants.infrastructure.exceptions;

import java.util.UUID;

public class ErrorInfo {

	public final String mensaje;
	public final String error;
	public final String uuid;

	public ErrorInfo(String mensaje, String error, UUID uuid) {
		this.mensaje = mensaje;
		this.error = error;
		this.uuid = uuid.toString();
	}
}
