package objectPoolPlay.driver;

import objectPoolPlay.validator.ValidatorFetcher;
import objectPoolPlay.validator.ValidatorUtil;

public class PersistorServiceDriver {

	public static void main(String[] args) {
		System.out.println("I am in Persistor");
		final int REQUIRED_NUMBER_OF_ARGS_PERSISTOR = 2;

		try {
			ValidatorUtil.validate("failed", ValidatorFetcher.commandLineValidatorForPersistor(args, REQUIRED_NUMBER_OF_ARGS_PERSISTOR));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
