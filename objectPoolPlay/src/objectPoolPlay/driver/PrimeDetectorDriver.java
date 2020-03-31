package objectPoolPlay.driver;

import java.io.File;

import objectPoolPlay.impl.PrimeDetector;
import objectPoolPlay.validator.ValidatorFetcher;
import objectPoolPlay.validator.ValidatorUtil;

public class PrimeDetectorDriver {

	public static void main(String[] args) {
		System.out.println("I am in Prime Detector");
		final int REQUIRED_NUMBER_OF_ARGS = 6;
		
		String ifilename = "";
		String workingDirectory = System.getProperty("user.dir");
		ifilename = workingDirectory + File.separator + args[0];

		try {
			ValidatorUtil.validate("failed",
					ValidatorFetcher.commandLineValidatorForPrime(args, REQUIRED_NUMBER_OF_ARGS),
					ValidatorFetcher.missingFileValidator(ifilename), ValidatorFetcher.emptyFileValidator(args[0]));
			
			PrimeDetector pd = new PrimeDetector(args[0], args[1], args[2], args[3], args[4], args[5]);
			pd.process(ifilename);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
