package gui;

import java.util.ArrayList;
import java.util.List;

public class TimePicker {

	/**
	 * Generates a list of the time from 00:00 to 23:45 by increasing the minutes
	 * with 15 while minutes < 45
	 * @return timeList
	 */
	public static List<String> generateTime() {
		List<String> timeList = new ArrayList<>();
		int hour = 0;
		int minute = 0;

		while (hour < 24) {
			if (minute == 0 && hour < 10) {
				timeList.add("0" + hour + ":00");
			} else if (minute == 0)
				timeList.add(hour + ":00");

			for (int min = minute; min < 45; min += 15) {
				minute += 15;

				if (hour < 10) {
					timeList.add("0" + hour + ":" + minute);
				} else
					timeList.add(hour + ":" + minute);
			}

			if (minute == 45) {
				hour++;
				minute = 0;
			}
		}
		return timeList;
	}
}
