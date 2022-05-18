package gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.OrderConfirmationDocument;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CompleteOrderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public CompleteOrderDialog(OrderUI orderUi, OrderConfirmationDocument dc) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CompleteOrderDialog.class.getResource("/images/KOMNU.png")));
		setTitle("Spændende Mad - Ordre færdiggjort");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 230);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Din ordre er blevet gemt");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(10, 39, 414, 28);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Ønsker du at åbne ordre dokumentet?");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(10, 78, 414, 31);
			contentPanel.add(lblNewLabel_1);
		}

		JButton btnJa = new JButton("Ja");
		btnJa.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				closeDialog(orderUi);
				dc.openGenerateDocument();
			}
		});
		btnJa.setBounds(104, 144, 89, 23);
		contentPanel.add(btnJa);

		JButton btnNej = new JButton("Nej");
		btnNej.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				closeDialog(orderUi);
			}
		});
		btnNej.setBounds(230, 144, 89, 23);
		contentPanel.add(btnNej);
	}

	private void closeDialog(OrderUI orderUi) {
		dispose();
		SWINGManager.goToMainUI(orderUi);
		orderUi.dispose();
	}

}
