package utilities;

public class Utils {

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
