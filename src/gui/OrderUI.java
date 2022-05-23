package gui;

import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.metal.MetalBorders.TextFieldBorder;

import java.awt.Color;

import controller.OrderController;
import controller.OrderControllerIF;
import db.DataAccessException;
import model.Customer;
import model.EmployeeRole;
import model.OrderLine;
import model.Product;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JList;

public class OrderUI extends JFrame {
	
	private OrderControllerIF orderController;
	
	private JPanel contentPane;
	private JPanel deliveryPanel2;
	private JPanel deliveryPanel;
	private JPanel customerorderInfoPanel;
	private JPanel orderInfoPanel;
	
	private JScrollPane scrollPane;
	
	private JTextField coverField;
	private JTextField textFieldFName;
	private JTextField textFieldLName;
	private JTextField textFieldAdresse;
	private JTextField textHouseNo;
	private JTextField textFieldZipCode;
	private JTextField textFieldCity;
	private JTextField textFieldDeliveryHouseNo;
	private JTextField textFieldDeliveryCity;
	private JTextField textFieldDeliveryAdd;
	private JTextField textFieldDeliveryZipCode;
	private JTextField textFieldPhoneNo;
	private JTextField textFieldEmail;
	private JTextField textFieldProduct;
	private JTextField textFieldProductQuantity;
	
	private JLabel lblEmail;
	private JLabel lblEfternavn_7;
	private JLabel lblEfternavn_8;
	private JLabel lblEfternavn_9;
	private JLabel lblEfternavn_10;
	private JLabel lblEfternavn_12;
	private JLabel lblCustomerError;
	private JLabel lblTotalCoverQuantity;
	private JLabel lblPhoneNo;
	private JLabel lblFailureCovers;
	private JLabel lblProductQuantityError;
	
	private JComboBox comboBoxEatClock;
	private JComboBox comboBoxRole;
	private JComboBox comboBoxProduct;
	private JComboBox comboBoxFName;
	
	private DefaultComboBoxModel model;
	private DefaultComboBoxModel modelProduct;
	
	private JCheckBox chckbxDelivery;
	private JCheckBox chckbxPickup;
	private JCheckBox chckbxAlternativeAdd;
	
	private JButton btnNewButton;
	private boolean error = false;

	private int customerNo = 0;
	private JTable table;

	private ProductListModel productModel;
	private DefaultListModel eList;
	private JList serviceList;

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
		lblDate.setBounds(21, 28, 98, 20);
		orderInfoPanel.add(lblDate);

		JLabel lblCovers = new JLabel("Antal kuverter");
		lblCovers.setHorizontalAlignment(SwingConstants.LEFT);
		lblCovers.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCovers.setBounds(21, 90, 141, 20);
		orderInfoPanel.add(lblCovers);

		coverField = new JTextField();
		coverField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ActionHandler.keyHandler(e, coverField);
			}
		});
		coverField.setBounds(21, 121, 98, 20);
		orderInfoPanel.add(coverField);
		coverField.setColumns(10);

		lblFailureCovers = new JLabel("");
		lblFailureCovers.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFailureCovers.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblFailureCovers.setForeground(Color.RED);
		lblFailureCovers.setBounds(135, 11, 227, 14);
		orderInfoPanel.add(lblFailureCovers);

		comboBoxEatClock = new JComboBox();
		comboBoxEatClock.setBounds(192, 121, 120, 20);
		orderInfoPanel.add(comboBoxEatClock);

		lblEfternavn_12 = new JLabel("Spise tidspunkt");
		lblEfternavn_12.setBounds(192, 92, 152, 17);
		orderInfoPanel.add(lblEfternavn_12);
		lblEfternavn_12.setHorizontalAlignment(SwingConstants.LEFT);
		lblEfternavn_12.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblTotalCoverQuantity = new JLabel("");
		lblTotalCoverQuantity.setBounds(73, 33, 289, 14);
		orderInfoPanel.add(lblTotalCoverQuantity);

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
				findCustomers();
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
		textFieldZipCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textFieldZipCode.getText().length() >= 4) {
					
					String city = orderController.getCitiesWithZipcode(textFieldZipCode.getText());
					textFieldCity.setText(city);
				}
			}
		});
		
	
		textFieldZipCode.setColumns(10);
		textFieldZipCode.setBounds(21, 180, 82, 20);
		customerorderInfoPanel.add(textFieldZipCode);

		textFieldCity = new JTextField();
		textFieldCity.setEditable(false);
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
					comboBoxFName.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (e.getModifiers() != 0) { // This if checks if its anything other than keyboard picking
															// the combobox
								if (!textFieldLName.getText().equals(null)) {
									setCustomerToTextFields(comboBoxFName.getSelectedIndex());
								}
							}
						}
					});
				}
			}
		});
		comboBoxFName.setBounds(21, 62, 165, 20);
		customerorderInfoPanel.add(comboBoxFName);

		lblCustomerError = new JLabel("");
		lblCustomerError.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCustomerError.setBounds(61, 11, 301, 14);
		customerorderInfoPanel.add(lblCustomerError);
		lblCustomerError.setForeground(Color.RED);

		btnNewButton = new JButton("Tilbage");
		btnNewButton.addActionListener(e -> {
			SWINGManager.goToMainUI(this);
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

		textFieldProduct = new JTextField();
		textFieldProduct.addKeyListener(new KeyAdapter() {

	@Override
			public void keyTyped(KeyEvent e) {
				findProducts();
			}
		});
		textFieldProduct.setBounds(26, 59, 500, 20);
		productPanel.add(textFieldProduct);
		textFieldProduct.setColumns(10);

		textFieldProductQuantity = new JTextField();
		textFieldProductQuantity.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ActionHandler.keyHandler(e, textFieldProductQuantity);
				if(e.getKeyCode() == KeyEvent.VK_ENTER && comboBoxProduct.getSelectedIndex() != -1) {
					addProductToOrder(comboBoxProduct.getSelectedIndex());
					updateProductTable();
				}
			}
		});
		textFieldProductQuantity.setColumns(10);
		textFieldProductQuantity.setBounds(536, 59, 86, 20);
		productPanel.add(textFieldProductQuantity);

		JButton btnAdd = new JButton("Tilføj");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (comboBoxProduct.getSelectedIndex() != -1) {
					addProductToOrder(comboBoxProduct.getSelectedIndex());
					updateProductTable();
				}
			}
		});
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
		lblQuantity.setBounds(536, 29, 82, 20);
		productPanel.add(lblQuantity);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 89, 723, 242);
		productPanel.add(scrollPane);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE && table.getSelectedRow() != -1) {
					deleteProductFromTable(table.getSelectedRow());
				}
			}
		});
		
		scrollPane.setViewportView(table);
		modelProduct = new DefaultComboBoxModel();
		comboBoxProduct = new JComboBox(modelProduct);
		comboBoxProduct.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == comboBoxProduct) {
					comboBoxProduct.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (e.getModifiers() != 0 && comboBoxProduct.getSelectedIndex() != -1) { // This if checks if its anything other than keyboard picking
															// the combobox
								textFieldProduct.setText(orderController.getProducts()
										.get(comboBoxProduct.getSelectedIndex()).getDescription());
							}
						}
					});
				}
			}
		});
		comboBoxProduct.setBounds(26, 59, 500, 20);
		productPanel.add(comboBoxProduct);

		lblProductQuantityError = new JLabel("");
		lblProductQuantityError.setBounds(638, 28, 45, 19);
		productPanel.add(lblProductQuantityError);
		deliveryPanel = new JPanel();
		String titleDelivery = "Levering/afhentning";
		Border borderDelivery = BorderFactory.createTitledBorder(titleDelivery);
		deliveryPanel.setBorder(borderDelivery);
		deliveryPanel.setBounds(450, 442, 523, 153);
		contentPane.add(deliveryPanel);
		deliveryPanel.setLayout(null);
		deliveryPanel.add(new JSeparator());

		textFieldDeliveryAdd = new JTextField();
		textFieldDeliveryAdd.setBounds(225, 54, 170, 20);
		textFieldDeliveryAdd.setColumns(10);
		deliveryPanel.add(textFieldDeliveryAdd);

		textFieldDeliveryCity = new JTextField();
		textFieldDeliveryCity.setBounds(317, 108, 166, 20);
		deliveryPanel.add(textFieldDeliveryCity);
		textFieldDeliveryCity.setColumns(10);

		chckbxDelivery = new JCheckBox("Levering");
		chckbxDelivery.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chckbxPickup.setSelected(false);
				chckbxAlternativeAdd.setSelected(false);
				setAlternativDeliveryTextEditable(false);
				chckbxDelivery.setEnabled(false);
				chckbxAlternativeAdd.setEnabled(true);
				chckbxPickup.setEnabled(true);
				
			}
		});
		chckbxDelivery.setBounds(28, 68, 97, 23);
		deliveryPanel.add(chckbxDelivery);

		chckbxPickup = new JCheckBox("Afhentning");
		chckbxPickup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chckbxDelivery.setSelected(false);
				chckbxAlternativeAdd.setSelected(false);
				eList.clear();
				
				setAlternativDeliveryTextEditable(false);
				chckbxDelivery.setEnabled(true);
				chckbxAlternativeAdd.setEnabled(true);
				chckbxPickup.setEnabled(false);
			}
		});
		chckbxPickup.setBounds(28, 29, 97, 23);
		deliveryPanel.add(chckbxPickup);

		chckbxAlternativeAdd = new JCheckBox("Levering til anden adresse");
		chckbxAlternativeAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				chckbxDelivery.setSelected(false);
				chckbxPickup.setSelected(false);
			
				setAlternativDeliveryTextEditable(true);
				chckbxDelivery.setEnabled(true);
				chckbxAlternativeAdd.setEnabled(false);
				chckbxPickup.setEnabled(true);
			}
		});
		chckbxAlternativeAdd.setBounds(28, 107, 191, 23);
		deliveryPanel.add(chckbxAlternativeAdd);

		textFieldDeliveryZipCode = new JTextField();
		textFieldDeliveryZipCode.setColumns(10);
		textFieldDeliveryZipCode.setBounds(225, 108, 82, 20);
		deliveryPanel.add(textFieldDeliveryZipCode);

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

		textFieldDeliveryHouseNo = new JTextField();
		textFieldDeliveryHouseNo.setColumns(10);
		textFieldDeliveryHouseNo.setBounds(405, 54, 80, 20);
		deliveryPanel.add(textFieldDeliveryHouseNo);

		deliveryPanel2 = new JPanel();
		String titleDelivery2 = "Service";
		Border borderDelivery2 = BorderFactory.createTitledBorder(titleDelivery2);
		deliveryPanel2.setBorder(borderDelivery2);
		deliveryPanel2.setBounds(966, 442, 261, 153);
		contentPane.add(deliveryPanel2);
		deliveryPanel2.setLayout(null);

		comboBoxRole = new JComboBox();
		comboBoxRole.setBounds(22, 28, 120, 22);
		deliveryPanel2.add(comboBoxRole);

		JButton btnAddServiceRole = new JButton("Tilføj");
		btnAddServiceRole.addActionListener(e -> {
			if (!chckbxPickup.isSelected()) {
				addService();
			}

		});

		btnAddServiceRole.setBounds(152, 28, 89, 23);
		deliveryPanel2.add(btnAddServiceRole);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 67, 219, 63);
		deliveryPanel2.add(scrollPane_1);

		eList = new DefaultListModel();
		serviceList = new JList(eList);
		serviceList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE && serviceList.getSelectedIndex() != -1) {
					eList.remove(serviceList.getSelectedIndex());
				}
			}
		});
		scrollPane_1.setViewportView(serviceList);

		JButton btnFrdiggrOrdre = new JButton("Færdiggør ordre");
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
		// 1st method call
		setOrderInfo();
		// 2nd method call
		setCustomer();
		// 3rd method call
		setService();
		// 4th method call
		if (!error) {
			orderController.completeOrder();
			SWINGManager.openCompleteOrderDialog(this, orderController.getOrder().getOrderConfirmationDocument());
		}else
			error = false;
	}

	private void resetCustomer() {
		textFieldLName.setText(null);
		textFieldAdresse.setText(null);
		textHouseNo.setText(null);
		textFieldZipCode.setText(null);
		textFieldPhoneNo.setText(null);
		textFieldEmail.setText(null);
		customerNo = 0;
	}

	private void setCustomerToTextFields(int index) {
		Customer c = null;
		if (orderController.getCustomers().get(index) != null) {
			c = orderController.getCustomers().get(index);
			textFieldFName.setText(c.getfName());
			textFieldLName.setText(c.getlName());
			textFieldAdresse.setText(c.getStreet());
			textHouseNo.setText(c.getHouseNo());
			textFieldZipCode.setText(c.getZipCode());
			textFieldCity.setText(c.getCity());
			textFieldPhoneNo.setText(c.getPhoneNo());
			textFieldEmail.setText(c.getEmail());
			customerNo = c.getCustomerNo();
		}
	}

	private void setCustomer() {
		// This checks if all the textFields in customer panel isnt null
		if (checkCustomerTextFields() && customerNo != 0) {
			orderController.setCustomer(customerNo);
		} else if (checkCustomerTextFields() && customerNo == 0) {
			try {
				orderController.insertNewCustomer(textFieldFName.getText(), textFieldLName.getText(),
						textFieldAdresse.getText(), textHouseNo.getText(), textFieldPhoneNo.getText(),
						textFieldEmail.getText(), textFieldZipCode.getText(), textFieldCity.getText());
			} catch (DataAccessException e) {
				lblCustomerError.setText("Mail eller telefonnummer findes allerede*");
				if(e.getCause().getMessage().contains("zipcod")) {
					lblCustomerError.setText("Postnummer og by passer ikke sammen");
				}
				error = true;
			} 
		}

	}

	private void init() {
		orderController = new OrderController();
		orderController.createOrder();

		DatePicker.createDatePicker(orderInfoPanel, 22, 59, 245, 30);
		comboBoxFName.setEnabled(false);
		comboBoxProduct.setEnabled(false);

		addElementsToComboBoxRole();
		addElementsToComboBoxTime();

		this.productModel = new ProductListModel();
		table.setModel(productModel);
		updateProductTable();
		chckbxPickup.setSelected(true);
		threadCheckCoverDate();
		setAlternativDeliveryTextEditable(false);
		chckbxPickup.setEnabled(false);
	}

	private void threadCheckCoverDate() {
		Thread t1 = new Thread(() -> {
			while(true) {
				int coverQuantity = orderController.checkCoverQuantityOnDate(DatePicker.getDateValue());
				lblTotalCoverQuantity.setText("Der er " + coverQuantity + " kuverter på datoen");
			}
		});
		t1.start();
	}

	private void setOrderInfo() {
		String eatingTime = comboBoxEatClock.getSelectedItem().toString();
		int coverQuantity = 0;
		if (coverField.getText().isEmpty() || coverField.getText().length() > 9) {
			error = true;
			lblFailureCovers.setText("Udfyld antal kuverter, minimum 4*");
		} else {
			coverQuantity = Integer.parseInt(coverField.getText()); // Special requirement, cover amount must be minimum 4 | Maybe get it
													// from
			// database
		}
		if (orderController.setOrderInfo(coverQuantity, DatePicker.getDateValue(), eatingTime)) {
			lblFailureCovers.setText(null);
		} else {
			error = true;
			lblFailureCovers.setText("Udfyld antal kuverter, minimum 4*");
		}
	}

	private void findCustomers() {
		if (textFieldFName.getText().length() > 0) {
			comboBoxFName.removeAllItems();
			resetCustomer();
			List<Customer> customers = orderController.findCustomers(textFieldFName.getText());
			for (Customer c : customers) {
				String currStr = "<html>" + c.getfName() + " " + c.getlName() + "<br>" + c.getEmail();
				if (model.getIndexOf(currStr) == -1) {
					model.addElement(currStr);
				}
			}
			comboBoxFName.showPopup();
		} else {
			comboBoxFName.removeAllItems();
			resetCustomer();
		}
	}

	
	private void addElementsToComboBoxRole() {
		EmployeeRole role[] = EmployeeRole.values();
		for (EmployeeRole r : role) {
			comboBoxRole.addItem(r);
		}
	}

	private void addElementsToComboBoxTime() {
		for (String s : TimePicker.generateTime())
			comboBoxEatClock.addItem(s);
	}

	private void findProducts() {
		if (!textFieldProduct.getText().isEmpty()) { 
			comboBoxProduct.removeAllItems();
			List<Product> products = orderController.findProducts(textFieldProduct.getText());
			if(products != null) {
				for (Product p : products) {
					String currStr = "<html>" + p.getDescription() + "<br>" + p.getPrice();
					if (modelProduct.getIndexOf(currStr) == -1) {
						modelProduct.addElement(currStr);
					}
				}
				comboBoxProduct.showPopup();
			}
		} else {
			comboBoxProduct.removeAllItems();
		}
	}

	private void addProductToOrder(int index) {
		Product p = null;
		if (orderController.getProducts().get(index) != null && !textFieldProductQuantity.getText().isEmpty()) {
			p = orderController.getProducts().get(index);
			orderController.addProduct(p.getProductNo(), Integer.parseInt(textFieldProductQuantity.getText()));
		} else {
			error = true;
		}
	}

	private void updateProductTable() {
		List<OrderLine> orderLines = orderController.getOrder().getOrderLines();
		this.productModel.setModelData(orderLines);
	}

	private void deleteProductFromTable(int index) {
		orderController.removeProductFromOrder(index);
		updateProductTable();
	}

	private boolean checkCustomerTextFields() {
		boolean res = false;
		if (!textFieldFName.getText().isEmpty() && !textFieldLName.getText().isEmpty()
				&& !textFieldAdresse.getText().isEmpty() && !textHouseNo.getText().isEmpty()
				&& !textFieldZipCode.getText().isEmpty() && !textFieldCity.getText().isEmpty()
				&& !textFieldPhoneNo.getText().isEmpty() && !textFieldEmail.getText().isEmpty()) {
			res = true;
			lblCustomerError.setText("");
		} else {
			error = true;
			lblCustomerError.setText("Alle felter skal udfyldes*");
		}
		return res;
	}

	private void setService() {
		setDelivery();
		List<EmployeeRole> er = new ArrayList<>();
		for (int i = 0; i < serviceList.getModel().getSize(); i++) {
			String s = serviceList.getModel().getElementAt(i).toString();
			EmployeeRole e = EmployeeRole.valueOf(s);
			er.add(e);
		}
		orderController.addService(er);
	}

	private void setDelivery() {
		if (chckbxAlternativeAdd.isSelected()) {
			if (!textFieldDeliveryAdd.getText().isEmpty() && !textFieldDeliveryCity.getText().isEmpty()
					&& !textFieldDeliveryZipCode.getText().isEmpty() && !textFieldDeliveryHouseNo.getText().isEmpty()) {
				orderController.setDelivery(textFieldDeliveryHouseNo.getText(), textFieldDeliveryAdd.getText(),
						textFieldDeliveryCity.getText(), textFieldDeliveryZipCode.getText());
			}
		} else if (chckbxDelivery.isSelected() && checkCustomerTextFields()) {
			orderController.setDelivery(textHouseNo.getText(), textFieldAdresse.getText(), textFieldCity.getText(),
					textFieldZipCode.getText());
		}
	}

	private void addService() {
		EmployeeRole er = (EmployeeRole) comboBoxRole.getSelectedItem();
		eList.addElement(er);
	}

	/**
	 * This method takes a boolean parameter and calls the alternative checkboxes seteditable with the given boolean & clears the textboxes
	 * @param isEditable
	 */
	
	private void setAlternativDeliveryTextEditable(boolean isEditable) {
		textFieldDeliveryAdd.setEditable(isEditable);
		textFieldDeliveryCity.setEditable(isEditable);
		textFieldDeliveryHouseNo.setEditable(isEditable);
		textFieldDeliveryZipCode.setEditable(isEditable);
		textFieldDeliveryAdd.setText("");
		textFieldDeliveryCity.setText("");
		textFieldDeliveryHouseNo.setText("");
		textFieldDeliveryZipCode.setText("");
	}
}
