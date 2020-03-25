package numberPlay.driver;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

import numberPlay.filters.EventFilter;
import numberPlay.filters.FilterI;
import numberPlay.observer.NumberPeaksObserver;
import numberPlay.observer.ObserverI;
import numberPlay.observer.RunningAverageObserver;
import numberPlay.observer.TopKNumbersObserver;
import numberPlay.subject.NumberProcessorSubject;
import numberPlay.subject.SubjectI;
import numberPlay.util.Events;
import numberPlay.util.NumberPeaksData;
import numberPlay.util.NumberPeaksResultsI;
import numberPlay.util.PersisterI;
import numberPlay.util.RunningAverageData;
import numberPlay.util.RunningAverageResultsI;
import numberPlay.util.TopKNumbersData;
import numberPlay.util.TopKNumbersResultsI;
import numberPlay.validator.ValidatorFetcher;
import numberPlay.validator.ValidatorUtil;

/**
 * @author Abha Chaudhary
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the argument value
		 * is not given java takes the default value specified in build.xml. To avoid
		 * that, below condition is used FIXME Refactor commandline validation using the
		 * validation design taught in class.
		 */
		final int REQUIRED_NUMBER_OF_ARGS = 6;

		String ifilename = "";
		String ofilepath = "";
		String workingDirectory = System.getProperty("user.dir");
		ifilename = workingDirectory + File.separator + args[0];
		ofilepath = workingDirectory + File.separator;

		try {
			ValidatorUtil.validate("failed", ValidatorFetcher.commandLineValidator(args, REQUIRED_NUMBER_OF_ARGS),
					ValidatorFetcher.windowSizeValidator(args[1]), ValidatorFetcher.kSizeValidator(args[3]),
					ValidatorFetcher.missingFileValidator(ifilename), ValidatorFetcher.emptyFileValidator(ifilename),
					ValidatorFetcher.lineValidator(ifilename));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// FIXME Create an instance of each of the classes implementing PersisterI and
		// the
		// corresponding ResultsI interface.
		// Observers use these objects to dump data to be stored and eventually
		// persisted
		// to the corresponding output file.

		// FIXME Instantiate the subject.
		try {
			SubjectI numberProcessor = new NumberProcessorSubject();
			// FIXME Instantiate the observers, providing the necessary filter and the
			// results object.

			RunningAverageResultsI runningAvgResults = new RunningAverageData();
			TopKNumbersResultsI topKResults = new TopKNumbersData();
			NumberPeaksResultsI numPeakResults = new NumberPeaksData();

			PersisterI persistRunningResult = new RunningAverageData(ofilepath, args[2]);
			PersisterI persistTopKgResult = new TopKNumbersData(ofilepath, args[4]);
			PersisterI persistNumPeakResult = new NumberPeaksData(ofilepath, args[5]);

			ObserverI runningAvg = new RunningAverageObserver(runningAvgResults, persistRunningResult, args[1]);
			ObserverI topK = new TopKNumbersObserver(topKResults, persistTopKgResult, args[3]);
			ObserverI numPeak = new NumberPeaksObserver(numPeakResults, persistNumPeakResult);

			FilterI intFilter = new EventFilter(Events.INTEGER_EVENT);
			FilterI floatFilter = new EventFilter(Events.FLOATING_POINT_EVENT);
			FilterI processingFilter = new EventFilter(Events.PROCESSING_COMPLETE);

			List<FilterI> intAndProcessingFilter = new ArrayList<FilterI>();
			intAndProcessingFilter.add(intFilter);
			intAndProcessingFilter.add(processingFilter);

			List<FilterI> allEventFilter = new ArrayList<FilterI>();
			allEventFilter.add(intFilter);
			allEventFilter.add(floatFilter);
			allEventFilter.add(processingFilter);

			// FIXME Register each observer with the subject for the necessary set of
			// events.
			numberProcessor.register(intAndProcessingFilter, runningAvg);
			numberProcessor.register(allEventFilter, topK);
			numberProcessor.register(allEventFilter, numPeak);
			// FIXME Delegate control to a separate utility class/method that provides
			// numbers one at a time, from the FileProcessor,
			// to the subject.

			numberProcessor.process(ifilename);

		} catch (InvalidPathException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
