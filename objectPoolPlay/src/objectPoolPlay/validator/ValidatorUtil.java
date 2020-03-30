package objectPoolPlay.validator;

public final class ValidatorUtil {
	/**
	 * Method for input file validation by creating multiple validators
	 * @param baseErrMsg to be displayed when exception is thrown
	 * @param validators to be handled
	 * @throws Exception if any validator fails
	 */
	public static void validate(String baseErrMsg, Validator... validators) throws Exception {
		for (Validator v : validators) {
			try {
				v.run();
			} catch (Exception e) {
				throw new Exception(baseErrMsg.concat(": " + e.getMessage()), e);
			}
		}

	}
	
}
