package objectPoolPlay.driver;

import java.io.File;

import objectPoolPlay.impl.PersisterService;
import objectPoolPlay.userDefinedExceptions.CmdLineInputException;
import objectPoolPlay.validator.ValidatorFetcher;
import objectPoolPlay.validator.ValidatorUtil;

public class PersisterServiceDriver {

	public static void main(String[] args) {
		System.out.println("I am in Persistor");
		final int REQUIRED_NUMBER_OF_ARGS_PERSISTOR = 2;

		if ((args.length != REQUIRED_NUMBER_OF_ARGS_PERSISTOR) || (args[0].equals("${port}"))
				|| (args[1].equals("${outputFile}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.\n",
					REQUIRED_NUMBER_OF_ARGS_PERSISTOR);
			System.exit(0);
		}

		String ofilename = "";
		String workingDirectory = System.getProperty("user.dir");
		ofilename = workingDirectory + File.separator + args[1];

		try {
			ValidatorUtil.validate("failed",
					ValidatorFetcher.commandLineValidatorForPersistor(args, REQUIRED_NUMBER_OF_ARGS_PERSISTOR),
					ValidatorFetcher.portValidator(args[0]));

			PersisterService persisterService = new PersisterService(args[0], ofilename);
			persisterService.startService();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
