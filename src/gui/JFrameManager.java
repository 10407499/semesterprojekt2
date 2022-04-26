package gui;

import javax.swing.JFrame;

public class JFrameManager {

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
	
}
