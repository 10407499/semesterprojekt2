package gui;

import javax.swing.JFrame;

import model.DocumentCreator;

public class SWINGManager {

	public static void goToOrderUI(MainUI mainUI) {
	
		OrderUI orderUI = new OrderUI();
		orderUI.setLocationRelativeTo(mainUI);
		orderUI.setResizable(false);
		orderUI.setVisible(true);
		
	}
	
	public static void goToMainUI(JFrame jFrame) {
		MainUI mainUI = new MainUI();
		mainUI.setLocationRelativeTo(jFrame);
		mainUI.setVisible(true);
	}

	
	
	public static void openCompleteOrderDialog(OrderUI orderUi, DocumentCreator dc) {
		CompleteOrderDialog cod = new CompleteOrderDialog(orderUi, dc);
		cod.setLocationRelativeTo(null);
		cod.setVisible(true);
	}
}


