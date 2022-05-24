package gui;

import javax.swing.JFrame;
import model.OrderConfirmationDocument;

public class SWINGManager {

	/**
	 * This method takes mainUI as parameter to open the orderUI jframe, and place it as the same location as the mainUI had
	 * @param mainUI
	 */
	public static void goToOrderUI(MainUI mainUI) {
		OrderUI orderUI = new OrderUI();
		orderUI.setLocationRelativeTo(mainUI);
		orderUI.setResizable(false);
		orderUI.setVisible(true);
	}
	
	/**
	 * This method takes a jframe as parameter to open the mainUI, but as the same location as the jframe 
	 * @param jFrame
	 */
	public static void goToMainUI(JFrame jFrame) {
		MainUI mainUI = new MainUI();
		mainUI.setLocationRelativeTo(jFrame);
		mainUI.setVisible(true);
	}

	/**
	 * This method takes 2 parameters orderUI & OrderConfirmationDocument.
	 * @param orderUi
	 * @param dc
	 */
	public static void openCompleteOrderDialog(OrderUI orderUi, OrderConfirmationDocument dc) {
		CompleteOrderDialog cod = new CompleteOrderDialog(orderUi, dc);
		cod.setLocationRelativeTo(null);
		cod.setVisible(true);
	}
}