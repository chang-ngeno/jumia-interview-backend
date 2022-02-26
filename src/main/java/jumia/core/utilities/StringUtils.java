package jumia.core.utilities;

public class StringUtils {
	public String convertToSentenceCase(String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
	}

	public boolean isNullOrEmpty(String text) {
		return text.isBlank() || text.equals(null) || text.isEmpty();
	}
}
