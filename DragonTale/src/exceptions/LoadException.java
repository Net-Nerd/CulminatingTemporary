package exceptions;

public class LoadException extends Exception {

	private static final long serialVersionUID = 1L;

	private ExceptionType exceptionType;

	public enum ExceptionType {

		MISSING_FILE("One or more of the data files are missing."), 
		CORRUPT_FILE("Unable to load game data."), 
		UNKNOWN_ERROR("Unknown error.");

		String details;

		ExceptionType(String details) {
			this.details = details;
		}
	}

	public LoadException(ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}

	public String getMessage() {
		return exceptionType.details;
	}
}
