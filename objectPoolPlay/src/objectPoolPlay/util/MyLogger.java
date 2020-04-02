package objectPoolPlay.util;

public class MyLogger {

	public static enum DebugLevel {
		CONSTRUCTOR, RUN,RESULT_ENTRY,RESULT_CONTENTS,RELEASE;
	};

	public static DebugLevel debugLevel;

	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
		case 4:
			debugLevel = DebugLevel.CONSTRUCTOR;
			break;
		case 3:
			debugLevel = DebugLevel.RUN;
			break;
		case 2:
			debugLevel = DebugLevel.RESULT_ENTRY;
			break;
		case 1:
			debugLevel = DebugLevel.RESULT_CONTENTS;
			break;
		case 0:
			debugLevel = DebugLevel.RELEASE;
			break;
		}
	}

	public static void setDebugValue(DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	public static void writeMessage(String message, DebugLevel levelIn) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	public String toString() {
		return "The debug level has been set to the following " + debugLevel;
	}
}