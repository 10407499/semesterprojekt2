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
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;

import controller.OrderController;
import controller.OrderControllerIF;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OrderUI extends JFrame {

	//	Imports
	private OrderControllerIF orderController;
	
	private JPanel contentPane;
	private JTextField coverField;
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
	private JPanel customerorderInfoPanel;
	private JTextField textField_9;
	private JTextField textField_10;
	private JPanel deliveryPanel;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JLabel lblEfternavn_7;
	private JLabel lblEfternavn_8;
	private JLabel lblEfternavn_9;
	private JLabel lblEfternavn_10;
	private JTextField textField_14;
	private JTextField textField_16;
	private JLabel lblEfternavn_12;
	private JPanel deliveryPanel2;

	private JCheckBox chckbxDelivery;
	private JComboBox comboBox;
	private JCheckBox chckbxPickup;
	private JCheckBox chckbxBillingAddress;
	
	private String btnCompleteText = "Færdiggøre order";
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
		setTitle("SpÃ¦ndende Mad - Opret ordre");
		setResizable(false);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		orderInfoPanel = new JPanel();
		String titleOrderInfo = "Ordre information";
		Border borderOrderInfo = BorderFactory.createTitledBorder(titleOrderInfo);
		orderInfoPanel.setBorder(borderOrderInfo);
		orderInfoPanel.setBounds(33, 58, 372, 172);
		contentPane.add(orderInfoPanel);
		setLocationRelativeTo(null);
		orderInfoPanel.setLayout(null);
		
		JLabel lblDate = new JLabel("Dato");
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDate.setBounds(21, 28, 141, 20);
		orderInfoPanel.add(lblDate);
		
		JLabel lblCovers = new JLabel("Antal kuverter");
		lblCovers.setHorizontalAlignment(SwingConstants.LEFT);
		lblCovers.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCovers.setBounds(21, 90, 141, 20);
		orderInfoPanel.add(lblCovers);
		
		coverField = new JTextField();
		coverField.setBounds(21, 121, 245, 20);
		orderInfoPanel.add(coverField);
		coverField.setColumns(10);
		
		customerorderInfoPanel = new JPanel();
		String titleCustomer = "Kunde";
		Border borderCustomer = BorderFactory.createTitledBorder(titleCustomer);
		customerorderInfoPanel.setBorder(borderCustomer);
		customerorderInfoPanel.setBounds(33, 250, 372, 345);
		contentPane.add(customerorderInfoPanel);
		customerorderInfoPanel.setLayout(null);
		
		JLabel lblFName = new JLabel("Fornavn");
		lblFName.setBounds(21, 34, 65, 20);
		  customerorderInfoPanel.add(lblFName);
		  lblFName.setHorizontalAlignment(SwingConstants.LEFT);
		  lblFName.setFont(new Font("Tahoma", Font.BOLD, 14));
		  
		  JLabel lblEfternavn = new JLabel("Efternavn");
		  lblEfternavn.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn.setBounds(196, 34, 104, 20);
		  customerorderInfoPanel.add(lblEfternavn);
		  
		  JLabel lblEfternavn_1 = new JLabel("Adresse");
		  lblEfternavn_1.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_1.setBounds(21, 93, 104, 20);
		  customerorderInfoPanel.add(lblEfternavn_1);
		  
		  JLabel lblEfternavn_2 = new JLabel("Husnr.");
		  lblEfternavn_2.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_2.setBounds(267, 93, 82, 20);
		  customerorderInfoPanel.add(lblEfternavn_2);
		  
		  JLabel lblEfternavn_3 = new JLabel("Postnr.");
		  lblEfternavn_3.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_3.setBounds(21, 149, 104, 20);
		  customerorderInfoPanel.add(lblEfternavn_3);
		  
		  JLabel lblEfternavn_4 = new JLabel("By");
		  lblEfternavn_4.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_4.setBounds(118, 149, 104, 20);
		  customerorderInfoPanel.add(lblEfternavn_4);
		  
		  textField_1 = new JTextField();
		  textField_1.setColumns(10);
		  textField_1.setBounds(21, 62, 165, 20);
		  customerorderInfoPanel.add(textField_1);
		  
		  textField_2 = new JTextField();
		  textField_2.setColumns(10);
		  textField_2.setBounds(196, 62, 153, 20);
		  customerorderInfoPanel.add(textField_2);
		  
		  textField_3 = new JTextField();
		  textField_3.setColumns(10);
		  textField_3.setBounds(21, 117, 234, 20);
		  customerorderInfoPanel.add(textField_3);
		  
		  textField_4 = new JTextField();
		  textField_4.setColumns(10);
		  textField_4.setBounds(267, 117, 82, 20);
		  customerorderInfoPanel.add(textField_4);
		  
		  textField_5 = new JTextField();
		  textField_5.setColumns(10);
		  textField_5.setBounds(21, 180, 82, 20);
		  customerorderInfoPanel.add(textField_5);
		  
		  textField_6 = new JTextField();
		  textField_6.setColumns(10);
		  textField_6.setBounds(118, 180, 231, 20);
		  customerorderInfoPanel.add(textField_6);
		  
		  lblEfternavn_5 = new JLabel("Telefon nummer");
		  lblEfternavn_5.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_5.setBounds(21, 211, 185, 20);
		  customerorderInfoPanel.add(lblEfternavn_5);
		  
		  textField_7 = new JTextField();
		  textField_7.setColumns(10);
		  textField_7.setBounds(21, 241, 328, 20);
		  customerorderInfoPanel.add(textField_7);
		  
		  lblEfternavn_6 = new JLabel("Email");
		  lblEfternavn_6.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_6.setBounds(21, 272, 104, 20);
		  customerorderInfoPanel.add(lblEfternavn_6);
		  
		  textField_8 = new JTextField();
		  textField_8.setColumns(10);
		  textField_8.setBounds(21, 303, 328, 20);
		  customerorderInfoPanel.add(textField_8);
		  
		  btnNewButton = new JButton("Annuller");
		  btnNewButton.addActionListener(e -> {
			  JFrameManager.goToMainUI(this);
			  setVisible(false);
			  dispose();
			});
		  btnNewButton.setBounds(33, 24, 193, 23);
		  contentPane.add(btnNewButton);
		  
		  JPanel productPanel = new JPanel();
		  String titleProduct = "Produkt tilføjelse";
		  Border borderProduct = BorderFactory.createTitledBorder(titleProduct);
		  productPanel.setBorder(borderProduct);
		  productPanel.setBounds(450, 58, 777, 364);
		  contentPane.add(productPanel);
		  productPanel.setLayout(null);
		  
		  textField_9 = new JTextField();
		  textField_9.setBounds(26, 59, 500, 20);
		  productPanel.add(textField_9);
		  textField_9.setColumns(10);
		  
		  textField_10 = new JTextField();
		  textField_10.setColumns(10);
		  textField_10.setBounds(536, 59, 86, 20);
		  productPanel.add(textField_10);
		  
		  JButton btnAdd = new JButton("Tilf\u00F8j");
		  btnAdd.setBounds(632, 58, 117, 23);
		  productPanel.add(btnAdd);
		  
		  JLabel lblProduct = new JLabel("Produkt");
		  lblProduct.setHorizontalAlignment(SwingConstants.LEFT);
		  lblProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblProduct.setBounds(26, 28, 82, 20);
		  productPanel.add(lblProduct);
		  
		  JLabel lblQuantity = new JLabel("Antal");
		  lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
		  lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblQuantity.setBounds(536, 28, 82, 20);
		  productPanel.add(lblQuantity);
		  
		  JScrollPane scrollPane = new JScrollPane();
		  scrollPane.setBounds(26, 98, 723, 242);
		  productPanel.add(scrollPane);
		  
		  JList list = new JList();
		  scrollPane.setViewportView(list);
		  deliveryPanel = new JPanel();
		  String titleDelivery = "Levering/afhentning";
		  Border borderDelivery = BorderFactory.createTitledBorder(titleDelivery);
		  deliveryPanel.setBorder(borderDelivery);
		  deliveryPanel.setBounds(450, 442, 523, 153);
		  contentPane.add(deliveryPanel);
		  deliveryPanel.setLayout(null);
		  deliveryPanel.add(new JSeparator());
		  
		  textField_12 = new JTextField();
		  textField_12.setBounds(225, 54, 170, 20);
		  textField_12.setColumns(10);
		  deliveryPanel.add(textField_12);
		  
		  textField_11 = new JTextField();
		  textField_11.setBounds(317, 108, 166, 20);
		  deliveryPanel.add(textField_11);
		  textField_11.setColumns(10);
		  
		  chckbxDelivery = new JCheckBox("Levering");
		  chckbxDelivery.addKeyListener(new KeyAdapter() {
		  	@Override
		  	public void keyReleased(KeyEvent e) {
		  		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
		  			chckbxDelivery.setSelected(true);
		  		}
		  	}
		  });
		  chckbxDelivery.setBounds(28, 29, 97, 23);
		  deliveryPanel.add(chckbxDelivery);
		  
		  chckbxPickup = new JCheckBox("Afhentning");
		  chckbxPickup.setBounds(28, 69, 97, 23);
		  deliveryPanel.add(chckbxPickup);
		  
		  chckbxBillingAddress = new JCheckBox("Brug faktureringsadresse");
		  chckbxBillingAddress.setBounds(28, 107, 191, 23);
		  deliveryPanel.add(chckbxBillingAddress);
		  
		  textField_13 = new JTextField();
		  textField_13.setColumns(10);
		  textField_13.setBounds(225, 108, 82, 20);
		  deliveryPanel.add(textField_13);
		  
		  lblEfternavn_7 = new JLabel("Adresse");
		  lblEfternavn_7.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_7.setBounds(225, 28, 82, 20);
		  deliveryPanel.add(lblEfternavn_7);
		  
		  lblEfternavn_8 = new JLabel("Postnr.");
		  lblEfternavn_8.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_8.setBounds(225, 86, 82, 20);
		  deliveryPanel.add(lblEfternavn_8);
		  
		  lblEfternavn_9 = new JLabel("By");
		  lblEfternavn_9.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_9.setBounds(317, 86, 82, 20);
		  deliveryPanel.add(lblEfternavn_9);
		  
		  lblEfternavn_10 = new JLabel("Husnr.");
		  lblEfternavn_10.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		  lblEfternavn_10.setBounds(401, 28, 82, 20);
		  deliveryPanel.add(lblEfternavn_10);
		  
		  textField_14 = new JTextField();
		  textField_14.setColumns(10);
		  textField_14.setBounds(403, 54, 80, 20);
		  deliveryPanel.add(textField_14);
		  
		  deliveryPanel2 = new JPanel();
		  String titleDelivery2 = "Tidspunkt";
		  Border borderDelivery2 = BorderFactory.createTitledBorder(titleDelivery2);
		  deliveryPanel2.setBorder(borderDelivery2);
		  deliveryPanel2.setBounds(966, 442, 261, 153);
		  contentPane.add(deliveryPanel2);
		  deliveryPanel2.setLayout(null);
		  
		  textField_16 = new JTextField();
		  textField_16.setBounds(30, 58, 120, 20);
		  deliveryPanel2.add(textField_16);
		  textField_16.setColumns(10);
		  
		  lblEfternavn_12 = new JLabel("Spise tidspunkt");
		  lblEfternavn_12.setBounds(30, 30, 106, 17);
		  deliveryPanel2.add(lblEfternavn_12);
		  lblEfternavn_12.setHorizontalAlignment(SwingConstants.LEFT);
		  lblEfternavn_12.setFont(new Font("Tahoma", Font.BOLD, 14));
		  
		  comboBox = new JComboBox();
		  comboBox.setBounds(30, 102, 120, 22);
		  deliveryPanel2.add(comboBox);
		  
		  JButton btnAddServiceRole = new JButton("Tilf\u00F8j");
		  btnAddServiceRole.setBounds(160, 102, 89, 23);
		  deliveryPanel2.add(btnAddServiceRole);
		  
		  JButton btnFrdiggrOrdre = new JButton(btnCompleteText);
		  btnFrdiggrOrdre.addMouseListener(new MouseAdapter() {
		  	@Override
		  	public void mousePressed(MouseEvent e) {
		  		completeOrder();
		  	}
		  });
		  btnFrdiggrOrdre.setBounds(1077, 629, 150, 23);
		  contentPane.add(btnFrdiggrOrdre);
		  
		  init();
	}
	
	private void completeOrder() {
		// 1. method call
		orderController.setOrderInfo(coverField.getText() != null && Integer.parseInt(coverField.getText()) >= 4 ? Integer.parseInt(coverField.getText()) : 0, DatePicker.getDateValue());
		// 2. method call
		
	}
	
	private void init() {
		orderController = new OrderController();
		
		orderController.createOrder();
		
		DatePicker.createDatePicker(orderInfoPanel, 22, 59, 245, 30);
	}
}
