package BinaryDatabase;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Window.Type;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Frame;

public class DatabasegirisGUI {

	private JFrame frmDatabaseLogn;
	private JPasswordField passwordField;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabasegirisGUI window = new DatabasegirisGUI();
					window.frmDatabaseLogn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DatabasegirisGUI() {
		
		initialize();
	}


	private void initialize() {
		DatabaseGUI dbGUI = new DatabaseGUI();
		String adminusername = "admin";
		String adminpass = "admin";
		frmDatabaseLogn = new JFrame();
		frmDatabaseLogn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDatabaseLogn.setFont(new Font("Arial Black", Font.PLAIN, 17));
		frmDatabaseLogn.setType(Type.UTILITY);
		frmDatabaseLogn.setTitle("Login");
		frmDatabaseLogn.setResizable(false);
		frmDatabaseLogn.setPreferredSize(new Dimension(800, 600));
		frmDatabaseLogn.getContentPane().setBackground(new Color(230, 230, 250));
		frmDatabaseLogn.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(380, 5, 1, 1);
		frmDatabaseLogn.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(800, 600));
		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(10, 31, 742, 411);
		frmDatabaseLogn.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" Admin Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Candara Light", Font.BOLD, 20));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setBounds(128, 113, 144, 35);
		panel_1.add(lblNewLabel);
		
		JLabel lblAdminPassword = new JLabel(" Admin Password");
		lblAdminPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdminPassword.setForeground(Color.BLACK);
		lblAdminPassword.setFont(new Font("Candara Light", Font.BOLD, 20));
		lblAdminPassword.setBackground(Color.BLACK);
		lblAdminPassword.setBounds(112, 171, 160, 35);
		panel_1.add(lblAdminPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 16));
		passwordField.setBounds(307, 176, 175, 30);
		panel_1.add(passwordField);
		
		textField = new JTextField();
		textField.setMargin(new Insets(5, 2, 2, 2));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Candara Light", Font.BOLD, 18));
		textField.setBounds(307, 114, 175, 30);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(null);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = textField.getText();
				String password =  passwordField.getText();
				
				if(username.equals(adminusername) && password.equals(adminpass) ) {
					
					System.out.println("giris basarili");
					frmDatabaseLogn.dispose();
					dbGUI.main(null);					

				}
				else {
					
					 JOptionPane.showMessageDialog(frmDatabaseLogn, "invalid username or password", "ERROR",
						        JOptionPane.ERROR_MESSAGE);
					 textField.setText("");
					 passwordField.setText("");
				}
				
			}
		});
		
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 182, 193));
		btnNewButton.setFont(new Font("Century", Font.BOLD, 16));
		btnNewButton.setBounds(307, 260, 173, 51);
		panel_1.add(btnNewButton);
		frmDatabaseLogn.setBounds(100, 100, 778, 519);
	}
}
