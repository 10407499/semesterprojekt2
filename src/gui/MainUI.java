package gui;

import javax.swing.*;  
import java.awt.*;
import java.util.Properties;

import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtSgEfterKunde;
	private JFrameManager jFrameMan;
	private MainUI mainUI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI frame = new MainUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public MainUI() {
		mainUI = this;
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/images/KOMNU.png")));
		setTitle("Spændende Mad");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnProductMenu = new JButton("Produkt menu");
		btnProductMenu.setBounds(49, 619, 167, 38);
		contentPane.add(btnProductMenu);
		
		JButton btnCreateOrder = new JButton("Opret ordre");
		btnCreateOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				JFrameManager.goToOrderUI(mainUI);
				setVisible(false);
				dispose();
			}
		});
		btnCreateOrder.setBounds(1050, 619, 167, 38);
		contentPane.add(btnCreateOrder);
		
		JButton btnUpdateOrder = new JButton("Rediger ordre");
		btnUpdateOrder.setBounds(856, 619, 167, 38);
		contentPane.add(btnUpdateOrder);
		
		txtSgEfterKunde = new JTextField();
		txtSgEfterKunde.setText("Søg efter kundenavn");
		txtSgEfterKunde.setBounds(737, 80, 355, 32);
		contentPane.add(txtSgEfterKunde);
		txtSgEfterKunde.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		  scrollPane.setBounds(49, 190, 1168, 387);
		  contentPane.add(scrollPane);
		  
		  JList list = new JList();
		  scrollPane.setViewportView(list);
		  
		  JPanel panel = new JPanel();
		  panel.setBounds(49, 134, 1168, 46);
		  contentPane.add(panel);
		  panel.setLayout(new GridLayout(0, 5, 0, 0));
		  
		  JLabel lblDate = new JLabel("Dato");
		  panel.add(lblDate);
		  lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		  lblDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		  
		  JLabel lblNavn = new JLabel("Navn");
		  panel.add(lblNavn);
		  lblNavn.setHorizontalAlignment(SwingConstants.CENTER);
		  lblNavn.setFont(new Font("Tahoma", Font.BOLD, 16));
		  
		  JLabel lblPhoneno = new JLabel("Telefon nr.");
		  panel.add(lblPhoneno);
		  lblPhoneno.setHorizontalAlignment(SwingConstants.CENTER);
		  lblPhoneno.setFont(new Font("Tahoma", Font.BOLD, 16));
		  
		  JLabel lblEmail = new JLabel("Email");
		  panel.add(lblEmail);
		  lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		  lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		  
		  JLabel lblOrdernr = new JLabel("Ordre nr.");
		  lblOrdernr.setAlignmentX(Component.CENTER_ALIGNMENT);
		  panel.add(lblOrdernr);
		  lblOrdernr.setFont(new Font("Tahoma", Font.BOLD, 16));
		  lblOrdernr.setHorizontalAlignment(SwingConstants.CENTER);
		init();
	}
	
	private void init() {
		DatePicker.createDatePicker(contentPane, 737, 47, 286, 30);
	}
}
