package objectPoolPlay.validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

import objectPoolPlay.userDefinedExceptions.CmdLineInputException;

/**
 * The class {@code ValidatorFetcher} is to validate input from command line
 * @author Abha Chaudhary
 *
 */
public class ValidatorFetcher {
	public static Validator missingFileValidator(String inputFilePath) {
		return new Validator() {
			@Override
			public void run() throws FileNotFoundException {
				if (!Files.exists(Paths.get(inputFilePath))) {
					throw new FileNotFoundException("invalid input file or input file in incorrect location");
				}
			}
		};
	}

	public static Validator emptyFileValidator(String inputFilePath) {
		return new Validator() {
			@Override
			public void run() throws IOException {
				File file = new File(inputFilePath);
				if (file.length() == 0) {
					throw new IOException("Input file is empty");
				}

			}
		};
	}

	public static Validator lineValidator(String inputFilePath) {
		return new Validator() {
			@Override
			public void run() throws IOException {
				String text = "";
				String pattern = "^[0-9\\.\\-\\n]*$";
				text = new String(Files.readAllBytes(Paths.get(inputFilePath)));
				if (!text.matches(pattern)) {
					throw new IOException("Invalid line or line contains other than numbers");
				}

			}
		};
	}

	public static Validator commandLineValidatorForPrime(String[] args, int numOfArg) {
		return new Validator() {
			@Override
			public void run() throws CmdLineInputException {
				if ((args.length != numOfArg) || (args[0].equals("${inputFile}"))
						|| (args[1].equals("${numThreads}")) || (args[2].equals("${capacity}"))
						|| (args[3].equals("${persisterServiceIp}")) || (args[4].equals("${persisterServicePort}"))
						|| (args[5].equals("${debugValue}"))) {
					System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.\n", numOfArg);
					throw new CmdLineInputException("Incorrect number of arguments");
				}
			}
		};
	}
	
	public static Validator commandLineValidatorForPersistor(String[] args, int numOfArg) {
		return new Validator() {
			@Override
			public void run() throws CmdLineInputException {
				if ((args.length != numOfArg) || (args[0].equals("${port}"))
						|| (args[1].equals("${outputFile}"))) {
					System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.\n", numOfArg);
					throw new CmdLineInputException("Incorrect number of arguments");
				}
			}
		};
	}

	

	
}
