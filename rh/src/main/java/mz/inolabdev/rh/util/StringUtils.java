package mz.inolabdev.rh.util;

public class StringUtils {

	public static final String EMPTY_STRING = "";

	public static String clean(String in) {
		String out = in;

		if (in != null) {
			out = in.trim();
			if (out.equals(EMPTY_STRING)) {
				out = null;
			}
		}

		return out;
	}

}