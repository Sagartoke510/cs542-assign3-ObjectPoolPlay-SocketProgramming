package objectPoolPlay.driver;

import java.io.File;

import objectPoolPlay.impl.PrimeDetector;
import objectPoolPlay.userDefinedExceptions.CmdLineInputException;
import objectPoolPlay.validator.ValidatorFetcher;
import objectPoolPlay.validator.ValidatorUtil;

public class PrimeDetectorDriver {

	public static void main(String[] args) {
		System.out.println("I am in Prime Detector");
		final int REQUIRED_NUMBER_OF_ARGS = 6;
		
		if ((args.length != REQUIRED_NUMBER_OF_ARGS) || (args[0].equals("${inputFile}"))
				|| (args[1].equals("${numThreads}")) || (args[2].equals("${capacity}"))
				|| (args[3].equals("${persisterServiceIp}")) || (args[4].equals("${persisterServicePort}"))
				|| (args[5].equals("${debugValue}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.\n", REQUIRED_NUMBER_OF_ARGS);
			System.exit(0);
		}

		String ifilename = "";
		String workingDirectory = System.getProperty("user.dir");
		ifilename = workingDirectory + File.separator + args[0];

		try {
			ValidatorUtil.validate("failed",
					ValidatorFetcher.commandLineValidatorForPrime(args, REQUIRED_NUMBER_OF_ARGS),
					ValidatorFetcher.missingFileValidator(ifilename), ValidatorFetcher.emptyFileValidator(args[0]),
					ValidatorFetcher.numOfThreadsValidator(args[1]), ValidatorFetcher.capacityValidator(args[2]),
					ValidatorFetcher.portValidator(args[4]), ValidatorFetcher.debugValValidator(args[5]));

			PrimeDetector pd = new PrimeDetector(args[0], args[1], args[2], args[3], args[4], args[5]);
			pd.process(ifilename);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
