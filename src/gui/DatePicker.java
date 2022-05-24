package gui;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;

import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public abstract class DatePicker {
	
	private static JDatePickerImpl datePicker;
	private static UtilDateModel model;

	/**
	 * This method checks if datePicker text is equals to empty, then it sets the text to LocalDate.now()
	 * @return date
	 */
	
	public static Date getDateValue() {
		Date date = null;
		if (datePicker.getJFormattedTextField().getText().equals("")) {
			date = Date.valueOf(LocalDate.now());
			model.setValue(date);
		} else {
			date = Date.valueOf(datePicker.getJFormattedTextField().getText());
		}
		return date;
	}

	/**
	 * This method is creating the date picker panel and placing the date panel onto the JPanel
	 * @param panel
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	
	public static void createDatePicker(JPanel panel, int x, int y, int width, int height) {
		// We found this solution on stackoverflow as seen in the following link
		// https://stackoverflow.com/questions/64668114/implementing-jdatepicker-in-swing-java		
		model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(x, y, width, height);
		model.setSelected(true);
		datePicker.setVisible(true);
		panel.add(datePicker);
	}

}
