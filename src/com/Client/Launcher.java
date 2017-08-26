package com.Client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Launcher extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JCheckBox chckbxEnterAsGuest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher frame = new Launcher();
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
	public Launcher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		setTitle("Deck Of Cards Client Launcher [Pre-Alpha v0.01]");
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//=================================LoginLabel==================================================
		JLabel lblLoginLabel = new JLabel("Log in");
		lblLoginLabel.setForeground(Color.BLACK);
		lblLoginLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
		lblLoginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginLabel.setBounds(123, 27, 138, 42);
		contentPane.add(lblLoginLabel);
		//=================================LoginLabel==================================================
		
		//=================================GuestCheckbox==================================================
		chckbxEnterAsGuest = new JCheckBox("Enter as guest");
		chckbxEnterAsGuest.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxEnterAsGuest.setFont(new Font("Tahoma", Font.ITALIC, 12));
		chckbxEnterAsGuest.setBounds(133, 75, 118, 23);
		chckbxEnterAsGuest.setSelected(true); 
		contentPane.add(chckbxEnterAsGuest);
		//=================================GuestCheckbox==================================================
				
				
		//=================================Username====================================================
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.ITALIC, 12));
		txtUsername.setText("Enter Username");
		txtUsername.setEditable(false);
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setBounds(88, 105, 207, 28);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		//=================================Username==================================================
		
		//=================================Password==================================================
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setEchoChar('●');
		passwordField.setText("Enter Password");
		passwordField.setEditable(false);
		passwordField.setEchoChar('●');
		passwordField.setFont(new Font("Tahoma", Font.ITALIC, 12));
		passwordField.setBounds(88, 144, 207, 28);
		contentPane.add(passwordField);
		//=================================Password==================================================
		
		//=================================ConnectLabel==================================================
		JLabel lblConnectToServer = new JLabel("Connect to server");
		lblConnectToServer.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnectToServer.setForeground(Color.BLACK);
		lblConnectToServer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 23));
		lblConnectToServer.setBounds(39, 199, 315, 42);
		contentPane.add(lblConnectToServer);
		//=================================ConnectLabel==================================================
		
		//=================================SelectServer==================================================
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setForeground(Color.BLACK);
		comboBox.setFont(new Font("Tahoma", Font.ITALIC, 12));
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"<Select Room>", "<No current rooms available>"}));//Server.getTables()));
		comboBox.setBounds(77, 244, 240, 20);
		contentPane.add(comboBox);
		//=================================SelectServer==================================================
		
		//=================================ConnectButton==================================================
		JButton btnConnect = new JButton("Connect");
		btnConnect.setBounds(82, 346, 219, 34);
		contentPane.add(btnConnect);
		//=================================ConnectButton==================================================
	
		//=================================EventListeners==================================================
		//password
		passwordField.addFocusListener(new FocusListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if (passwordField.getText().matches("Enter Password")) {
					passwordField.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(passwordField.getPassword().length == 0) {
					passwordField.setText("Enter Password");
				}
			}
		});
	
		//username
		txtUsername.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtUsername.getText().matches("Enter Username"))
				{
					txtUsername.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if (txtUsername.getText().length() == 0) {
					txtUsername.setText("Enter Username");
				}
			}
		});
		
		//chckbx
		chckbxEnterAsGuest.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if (chckbxEnterAsGuest.isSelected()) {
					passwordField.setEditable(false);
					txtUsername.setEditable(false);
				} else {
					passwordField.setEditable(true);
					txtUsername.setEditable(true);
				}
			}
		});
		
		//combobox
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"<Select Room>", "<no current available rooms>"}));//Server.getTables()));
			}
		});
		//=================================EventListeners==================================================
	
		//=================================Confirming&Connecting==================================================
		btnConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean succeeded = true;
				
				//login check
				/*if (!chckbxEnterAsGuest.isSelected()) {
					if (txtUsername.getText() == null && passwordField.getPassword().equals(null)) {
						System.out.println("Login Successful!");
						System.out.println("Logged in as: " + txtUsername.getText());
						succeeded = true;
					} else {
						succeeded = false;
					}
				} else {
					System.out.println("Login Successful!");
					System.out.println("Logged in as: Guest" + random.nextInt(1337));
				}
				*/
				//END: login check
				
				if (succeeded) {
					dispose();
					new Player("kevin");
				} else {
					System.out.println("Unregistered Account! Please try again:");
				}
			}
		});
		//=================================Confirming&Connecting==================================================
	}
}
