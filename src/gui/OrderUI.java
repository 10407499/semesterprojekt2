package gui;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

import controller.OrderController;
import controller.OrderControllerIF;
import model.Customer;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OrderUI extends JFrame {

	// Imports
	private OrderControllerIF orderController;

	private JPanel contentPane;
	private JTextField coverField;
	private JPanel orderInfoPanel;
	private JTextField textFieldFName;
	private JTextField textFieldLName;
	private JTextField textFieldAdresse;
	private JTextField textHouseNo;
	private JTextField textFieldZipCode;
	private JTextField textFieldCity;
	private JLabel lblPhoneNo;
	private JTextField textFieldPhoneNo;
	private JLabel lblEmail;
	private JTextField textFieldEmail;
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
	private JComboBox comboBoxRole;
	private JCheckBox chckbxPickup;
	private JCheckBox chckbxBillingAddress;

	private String btnCompleteText = "F�rdigg�re order";
	private JLabel lblFailureCovers;
	private boolean textBoxError = false;
	private JComboBox comboBoxFName;
	private DefaultComboBoxModel model;

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

		lblFailureCovers = new JLabel("");
		lblFailureCovers.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFailureCovers.setForeground(Color.RED);
		lblFailureCovers.setBounds(130, 95, 194, 14);
		orderInfoPanel.add(lblFailureCovers);

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

		JLabel lblLName = new JLabel("Efternavn");
		lblLName.setHorizontalAlignment(SwingConstants.LEFT);
		lblLName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLName.setBounds(196, 34, 104, 20);
		customerorderInfoPanel.add(lblLName);

		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdresse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAdresse.setBounds(21, 93, 104, 20);
		customerorderInfoPanel.add(lblAdresse);

		JLabel lblHouseNo = new JLabel("Husnr.");
		lblHouseNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblHouseNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHouseNo.setBounds(267, 93, 82, 20);
		customerorderInfoPanel.add(lblHouseNo);

		JLabel lblZipCode = new JLabel("Postnr.");
		lblZipCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblZipCode.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblZipCode.setBounds(21, 149, 104, 20);
		customerorderInfoPanel.add(lblZipCode);

		JLabel lblCity = new JLabel("By");
		lblCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCity.setBounds(118, 149, 104, 20);
		customerorderInfoPanel.add(lblCity);

		textFieldFName = new JTextField();
		textFieldFName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				addElementsToComboBoxCustomer();
			}
		});
		textFieldFName.setColumns(10);
		textFieldFName.setBounds(21, 62, 165, 20);
		customerorderInfoPanel.add(textFieldFName);

		textFieldLName = new JTextField();
		textFieldLName.setColumns(10);
		textFieldLName.setBounds(196, 62, 153, 20);
		customerorderInfoPanel.add(textFieldLName);

		textFieldAdresse = new JTextField();
		textFieldAdresse.setColumns(10);
		textFieldAdresse.setBounds(21, 117, 234, 20);
		customerorderInfoPanel.add(textFieldAdresse);

		textHouseNo = new JTextField();
		textHouseNo.setColumns(10);
		textHouseNo.setBounds(267, 117, 82, 20);
		customerorderInfoPanel.add(textHouseNo);

		textFieldZipCode = new JTextField();
		textFieldZipCode.setColumns(10);
		textFieldZipCode.setBounds(21, 180, 82, 20);
		customerorderInfoPanel.add(textFieldZipCode);

		textFieldCity = new JTextField();
		textFieldCity.setColumns(10);
		textFieldCity.setBounds(118, 180, 231, 20);
		customerorderInfoPanel.add(textFieldCity);

		lblPhoneNo = new JLabel("Telefon nummer");
		lblPhoneNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPhoneNo.setBounds(21, 211, 185, 20);
		customerorderInfoPanel.add(lblPhoneNo);

		textFieldPhoneNo = new JTextField();
		textFieldPhoneNo.setColumns(10);
		textFieldPhoneNo.setBounds(21, 241, 328, 20);
		customerorderInfoPanel.add(textFieldPhoneNo);

		lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(21, 272, 104, 20);
		customerorderInfoPanel.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(21, 303, 328, 20);
		customerorderInfoPanel.add(textFieldEmail);

		model = new DefaultComboBoxModel();
		comboBoxFName = new JComboBox(model);
		comboBoxFName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == comboBoxFName) {
					setCustomer();
				}
			}
		});
		comboBoxFName.setBounds(21, 62, 165, 20);
		customerorderInfoPanel.add(comboBoxFName);

		btnNewButton = new JButton("Annuller");
		btnNewButton.addActionListener(e -> {
			JFrameManager.goToMainUI(this);
			setVisible(false);
			dispose();
		});
		btnNewButton.setBounds(33, 24, 193, 23);
		contentPane.add(btnNewButton);

		JPanel productPanel = new JPanel();
		String titleProduct = "Produkt tilf�jelse";
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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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

		comboBoxRole = new JComboBox();
		comboBoxRole.setBounds(30, 102, 120, 22);
		deliveryPanel2.add(comboBoxRole);

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
		setOrderInfo();
		// 2. method call
	}

	private void setCustomer() {
		Customer c = null;
		c = orderController.getCustomers().get(0);
		textFieldFName.setText(c.getfName());
		textFieldLName.setText(c.getlName());
		textFieldAdresse.setText(c.getStreet());
		textHouseNo.setText(c.getHouseNo());
		textFieldZipCode.setText(c.getZipCode());
		textFieldPhoneNo.setText(c.getPhoneNo());
		textFieldEmail.setText(c.getEmail());
	}

	private void init() {
		orderController = new OrderController();

		orderController.createOrder();

		DatePicker.createDatePicker(orderInfoPanel, 22, 59, 245, 30);

		comboBoxFName.setEnabled(false);
	}

	private void setOrderInfo() {
		int coverAmount = 4; // Special requirement, cover amount must be minimum 4
		if (!coverField.getText().equals("")) {
			if (Integer.parseInt(coverField.getText()) >= coverAmount) {
				coverAmount = Integer.parseInt(coverField.getText());
				orderController.setOrderInfo(coverAmount, DatePicker.getDateValue());
			}else {
				textBoxError = true;
				lblFailureCovers.setText("Udfyld antal kuverter, minimum 4*");
			}
		} else {
			textBoxError = true;
			lblFailureCovers.setText("Udfyld antal kuverter, minimum 4*");

		}
	}
	
	private void addElementsToComboBoxCustomer() {
		if (textFieldFName.getText().length() > 0) {
			comboBoxFName.removeAllItems();
			List<String> customerStr = orderController.customerDetailsToString(textFieldFName.getText());
			for (String s : customerStr) {
				if (model.getIndexOf(s) == -1) {
					model.addElement(s);
				}
			}
			comboBoxFName.showPopup();
		} else {
			comboBoxFName.removeAllItems();
		}
	}

}
