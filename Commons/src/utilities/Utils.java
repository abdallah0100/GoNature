package utilities;

/**
 * Provides utility methods for common tasks within the application.
 * such as converting month names to their corresponding numerical representation.
 */
public class Utils {

	/**
	 * Converts a month's name to its numerical representation (1 for January, 2 for February, etc.).
	 * 
	 * @param month The full name of the month.
	 * @return The month number (1-12) corresponding to the month name, or -1 if the name is invalid.
	 */
	public static int getMonthNumberByName(String month) {
		String[] months = {"January", "February", "March", "April",
				"May", "June", "july", "August",
				"September", "October", "November", "December"};
		
		for (int i = 0; i < months.length; i++)
			if (months[i].equals(month))
				return i + 1;
		System.out.println("[Utils] - Invalid month string");
		return -1;
	}	
}
