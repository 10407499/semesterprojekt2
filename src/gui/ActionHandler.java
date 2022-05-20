package gui;

import javax.swing.JTextField;
import java.awt.event.KeyEvent;

public class ActionHandler {
	
	public static void keyHandler(KeyEvent e, JTextField text) {
        if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE ) {
            text.setEditable(true);
        } else {
            text.setEditable(false);
        }
    }
}
