package objectPoolPlay.driver;

import java.io.File;

import objectPoolPlay.impl.PersisterService;
import objectPoolPlay.validator.ValidatorFetcher;
import objectPoolPlay.validator.ValidatorUtil;

public class PersisterServiceDriver {

	public static void main(String[] args) {
		System.out.println("I am in Persistor");
		final int REQUIRED_NUMBER_OF_ARGS_PERSISTOR = 2;

		String ofilename = "";
		String workingDirectory = System.getProperty("user.dir");
		ofilename = workingDirectory + File.separator + args[1];

		try {
			ValidatorUtil.validate("failed",
					ValidatorFetcher.commandLineValidatorForPersistor(args, REQUIRED_NUMBER_OF_ARGS_PERSISTOR));
			PersisterService persisterService = new PersisterService(args[0], ofilename);
			persisterService.startService();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
