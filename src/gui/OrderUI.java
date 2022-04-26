package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPanel orderInfoPanel;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblEfternavn_5;
	private JTextField textField_7;
	private JLabel lblEfternavn_6;
	private JTextField textField_8;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public OrderUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/images/KOMNU.png")));
		setTitle("Spændende Mad - Opret ordre");
		setResizable(false);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		orderInfoPanel = new JPanel();
		orderInfoPanel.setBounds(33, 58, 372, 148);
		contentPane.add(orderInfoPanel);
		setLocationRelativeTo(null);
		orderInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		orderInfoPanel.setLayout(null);
		
		JLabel lblDate = new JLabel("Dato");
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDate.setBounds(22, 12, 141, 20);
		orderInfoPanel.add(lblDate);
		
		JLabel lblCovers = new JLabel("Antal kuverter");
		lblCovers.setHorizontalAlignment(SwingConstants.LEFT);
		lblCovers.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCovers.setBounds(22, 74, 141, 20);
		orderInfoPanel.add(lblCovers);
		
		textField = new JTextField();
		textField.setBounds(22, 105, 245, 20);
		orderInfoPanel.add(textField);
		textField.setColumns(10);
		
		JPanel customerorderInfoPanel = new JPanel();
		customerorderInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		customerorderInfoPanel.setBounds(33, 260, 372, 335);
		contentPane.add(customerorderInfoPanel);
		customerorderInfoPanel.setLayout(null);
		
		JLabel lblFName = new JLabel("Fornavn");
		lblFName.setBounds(23, 11, 65, 20);
		  customerorderInfoPanel.add(lblFName);
		  lblFName.setHorizontalAlignment(SwingConstants.LEFT);
		  lblFName.setFont(new Font("Tahoma", Font.BOLD, 14));
		  
		  JLabel lblEfternavn = new JLabel("Efternavn");
		  lblEfternavn.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn.setBounds(198, 11, 104, 20);
		  customerorderInfoPanel.add(lblEfternavn);
		  
		  JLabel lblEfternavn_1 = new JLabel("Adresse");
		  lblEfternavn_1.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_1.setBounds(23, 70, 104, 20);
		  customerorderInfoPanel.add(lblEfternavn_1);
		  
		  JLabel lblEfternavn_2 = new JLabel("Husnr.");
		  lblEfternavn_2.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_2.setBounds(269, 70, 82, 20);
		  customerorderInfoPanel.add(lblEfternavn_2);
		  
		  JLabel lblEfternavn_3 = new JLabel("Postnr.");
		  lblEfternavn_3.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_3.setBounds(23, 126, 104, 20);
		  customerorderInfoPanel.add(lblEfternavn_3);
		  
		  JLabel lblEfternavn_4 = new JLabel("By");
		  lblEfternavn_4.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_4.setBounds(120, 126, 104, 20);
		  customerorderInfoPanel.add(lblEfternavn_4);
		  
		  textField_1 = new JTextField();
		  textField_1.setColumns(10);
		  textField_1.setBounds(23, 39, 165, 20);
		  customerorderInfoPanel.add(textField_1);
		  
		  textField_2 = new JTextField();
		  textField_2.setColumns(10);
		  textField_2.setBounds(198, 39, 153, 20);
		  customerorderInfoPanel.add(textField_2);
		  
		  textField_3 = new JTextField();
		  textField_3.setColumns(10);
		  textField_3.setBounds(23, 94, 234, 20);
		  customerorderInfoPanel.add(textField_3);
		  
		  textField_4 = new JTextField();
		  textField_4.setColumns(10);
		  textField_4.setBounds(269, 94, 82, 20);
		  customerorderInfoPanel.add(textField_4);
		  
		  textField_5 = new JTextField();
		  textField_5.setColumns(10);
		  textField_5.setBounds(23, 157, 82, 20);
		  customerorderInfoPanel.add(textField_5);
		  
		  textField_6 = new JTextField();
		  textField_6.setColumns(10);
		  textField_6.setBounds(120, 157, 234, 20);
		  customerorderInfoPanel.add(textField_6);
		  
		  lblEfternavn_5 = new JLabel("Telefon nummer");
		  lblEfternavn_5.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_5.setBounds(23, 188, 185, 20);
		  customerorderInfoPanel.add(lblEfternavn_5);
		  
		  textField_7 = new JTextField();
		  textField_7.setColumns(10);
		  textField_7.setBounds(23, 218, 328, 20);
		  customerorderInfoPanel.add(textField_7);
		  
		  lblEfternavn_6 = new JLabel("Email");
		  lblEfternavn_6.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_6.setBounds(23, 249, 104, 20);
		  customerorderInfoPanel.add(lblEfternavn_6);
		  
		  textField_8 = new JTextField();
		  textField_8.setColumns(10);
		  textField_8.setBounds(23, 280, 328, 20);
		  customerorderInfoPanel.add(textField_8);
		  
		  btnNewButton = new JButton("Annuller");
		  btnNewButton.addActionListener(e -> {
			  JFrameManager.goToMainUI(this);
			  setVisible(false);
			  dispose();
			});
		  btnNewButton.setBounds(33, 24, 193, 23);
		  contentPane.add(btnNewButton);
		  init();
	}
	
	private void init() {
		DatePicker.createDatePicker(orderInfoPanel, 22, 43, 245, 20);
	}

}
