package gui;

import java.sql.Date;
import java.util.Properties;

import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public abstract class DatePicker {
	private static JDatePickerImpl datePicker;
	
	public static Date getDateValue() {
		Date date = Date.valueOf(datePicker.getJFormattedTextField().getText());
		return date;
	}
	
	public static void createDatePicker(JPanel panel, int x, int y, int width, int height) {
		//https://stackoverflow.com/questions/64668114/implementing-jdatepicker-in-swing-java
				UtilDateModel model = new UtilDateModel();
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
