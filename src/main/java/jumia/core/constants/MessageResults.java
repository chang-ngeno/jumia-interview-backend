package jumia.core.constants;

import java.util.HashMap;
import java.util.Map;

public class MessageResults {
	public static final Map<String, String> models = new HashMap<>();

	static {
		models.put("contact", "customer contact");
		models.put("countryInfo", "country information");
		models.put("phoneDetailDTO", "phone detail");
		models.put("phoneDetailDTOPaged", "phone detail paginated");
	}

	public static final String emptyField = "Please do not leave this field blank!";
	public static final String emptyFields = "Please do not leave these fields blank!";
	public static final String error = "Sorry, an error has occurred.";

	public static String alreadyExists(String model) {
		return String.format("This %s is already registered. Try another one.", models.get(model)); //
	}

	public static String notFound(String model) {
		return String.format("No such record %s was found.", models.get(model));
	}

	public static String allDataListed(String model) {
		return String.format("All %s data listed.", models.get(model));
	}

	public static String dataListed(String model) {
		return String.format("%s data listed.", models.get(model));
	}

	public static String saved(String model) {
		return String.format("%s successfully added to the system.", models.get(model));
	}

	public static String saveds(String model) {
		return String.format("%s data successfully added to the system.", models.get(model));
	}

	public static String saved(String model, String extraText) {
		return String.format("%s registration successfully added to the system. %s", models.get(model), extraText);
	}

	public static String deleted(String model) {
		return String.format("%s successfully deleted from the system.", models.get(model));
	}

	public static String deleteds(String model) {
		return String.format("%s data successfully deleted from the system.", models.get(model));
	}

	public static String updated(String model) {
		return String.format("%s successfully updated on the system.", models.get(model));
	}

}
