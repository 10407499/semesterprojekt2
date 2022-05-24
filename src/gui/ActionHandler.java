package gui;

import javax.swing.JTextField;
import java.awt.event.KeyEvent;

public class ActionHandler {
	
	/**
	 * This methods takes a keyEvent and JtextField as parameters to check the inputs the user is typing into the jtextfield, and if it anything other than numbers, it will make the jtextfield editable to false
	 * @param e
	 * @param text
	 */
	
	public static void keyHandler(KeyEvent e, JTextField text) {
        if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyChar() == KeyEvent.VK_BACK_SPACE || e.getKeyChar() == KeyEvent.VK_DELETE ) {
            text.setEditable(true);
        } else {
            text.setEditable(false);
        }
    }
}
