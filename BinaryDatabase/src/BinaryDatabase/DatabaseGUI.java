package BinaryDatabase;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import BinaryDatabase.Database.Column;
import BinaryDatabase.Database.Table;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import javax.swing.JList;
import java.awt.Window.Type;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.DebugGraphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JScrollBar;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;


public class DatabaseGUI {

	protected static final String NumberUtils = null;
	private JFrame frmDatabase;
	private JFrame frame1;
	private JTextField tablename;
	private JTextField columnname1;
	private JTextField columnname2;
	private JTextField columnname3;
	private JTextField columnname4;
	private JTextField columnname5;
	private JTextField columnname6;
	private JTextField columnname7;
	private JTextField columnname0;
	private JTextField columnname8;
	private JTextField columnname9;
	public int counter = 0;
	public String name;
	private JTable table_1;
	public boolean isend;
	public int progrescount;
	public String[] SearchArrayTemp = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField searchid;
	private JTextField updateid;
	private JTextField update0;
	private JTextField update1;
	private JTextField update2;
	private JTextField update3;
	private JTextField update4;
	private JTextField update5;
	private JTextField update6;
	private JTextField update7;
	private JTextField update8;
	private JTextField update9;
	private JTextField deleteid;
	private JTextField howmany;
	private JTextField searchByColumText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseGUI window = new DatabaseGUI();
					window.frmDatabase.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void readTxtFile(String filename, JTextArea textArea) throws IOException {
	    FileReader fr = new FileReader(filename);
	    BufferedReader br = new BufferedReader(fr);
	    String line;
	    while ((line = br.readLine()) != null) {
	        textArea.append(line + "\n");
	    }
	    br.close();
	    fr.close();
	}
	

	/**
	 * Create the application.
	 */
	public class CustomCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {
		private Font font;

		public CustomCellRenderer(Font font) {
			this.font = font;
			setHorizontalAlignment(CENTER);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			c.setFont(font);

			table.getTableHeader().setBackground(Color.LIGHT_GRAY);

			Color lightBlue = new Color(173, 216, 230);
			if (row % 2 == 0) {
				c.setBackground(lightBlue);
			} else {
				c.setBackground(Color.WHITE);
			}

			if (isSelected) {
				c.setBackground(Color.LIGHT_GRAY);
			}

			return c;
		}
	}

	public DatabaseGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {

		Database db = new Database();

		frmDatabase = new JFrame("binary file search");
		frmDatabase.getContentPane().setBackground(new Color(230, 230, 250));
		frmDatabase.setResizable(false);
		frmDatabase.setTitle("DBMeow");
		frmDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDatabase.setPreferredSize(new Dimension(800, 600)); // pencere boyutu
		frmDatabase.setBounds(100, 100, 831, 628);
		frmDatabase.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		JPanel panel = new JPanel();
		panel.setBorder(null);
		JPanel newtablepanel = new JPanel();
		JPanel searchpanel = new JPanel();
		searchpanel.setBackground(new Color(230, 230, 250));
		JPanel updatepanel = new JPanel();
		updatepanel.setBackground(new Color(230, 230, 250));
		JPanel deletepanel = new JPanel();
		deletepanel.setBackground(new Color(230, 230, 250));
		JPanel orderpanel = new JPanel();
		orderpanel.setBackground(new Color(230, 230, 250));
		JPanel addallpanel = new JPanel();
		addallpanel.setBackground(new Color(230, 230, 250));
		
		
		//NewTablePanel
		newtablepanel.setBackground(new Color(240, 240, 255));
		newtablepanel.setBounds(29, 54, 0, 0);
		frmDatabase.getContentPane().add(newtablepanel);
		newtablepanel.setLayout(null);

		newtablepanel.setVisible(false);

		JLabel lblNewLabel = new JLabel("Table Name");
		lblNewLabel.setForeground(new Color(26, 26, 26));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 0, 92, 46);
		newtablepanel.add(lblNewLabel);

		tablename = new JTextField();
		tablename.setBounds(112, 14, 122, 20);
		newtablepanel.add(tablename);
		tablename.setColumns(10);

		JLabel lblColumnName = new JLabel("Column Name");
		lblColumnName.setForeground(new Color(26, 26, 26));
		lblColumnName.setFont(new Font("Verdana", Font.BOLD, 12));
		lblColumnName.setBounds(131, 45, 122, 46);
		newtablepanel.add(lblColumnName);

		JLabel lblDataType = new JLabel("Data Type");
		lblDataType.setForeground(new Color(26, 26, 26));
		lblDataType.setFont(new Font("Verdana", Font.BOLD, 12));
		lblDataType.setBounds(263, 45, 92, 46);
		newtablepanel.add(lblDataType);

		columnname1 = new JTextField();
		columnname1.setHorizontalAlignment(SwingConstants.CENTER);
		columnname1.setFont(new Font("Dubai", Font.BOLD, 10));
		columnname1.setColumns(10);
		columnname1.setBounds(131, 125, 92, 20);
		newtablepanel.add(columnname1);

		JComboBox datatype1 = new JComboBox();
		datatype1.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
		datatype1.setFont(new Font("Dubai", Font.BOLD, 10));
		datatype1.setToolTipText("");
		datatype1.setModel(new DefaultComboBoxModel(new String[] { "", "int", "char" }));
		datatype1.setBounds(254, 124, 101, 22);
		newtablepanel.add(datatype1);

		columnname2 = new JTextField();
		columnname2.setHorizontalAlignment(SwingConstants.CENTER);
		columnname2.setFont(new Font("Dubai", Font.BOLD, 10));
		columnname2.setColumns(10);
		columnname2.setBounds(131, 165, 92, 20);
		newtablepanel.add(columnname2);

		JComboBox datatype2 = new JComboBox();
		datatype2.setFont(new Font("Dubai", Font.BOLD, 10));
		datatype2.setModel(new DefaultComboBoxModel(new String[] { "", "int", "char" }));
		datatype2.setToolTipText("");
		datatype2.setBounds(254, 164, 101, 22);
		newtablepanel.add(datatype2);

		columnname3 = new JTextField();
		columnname3.setHorizontalAlignment(SwingConstants.CENTER);
		columnname3.setFont(new Font("Dubai", Font.BOLD, 10));
		columnname3.setColumns(10);
		columnname3.setBounds(131, 205, 92, 20);
		newtablepanel.add(columnname3);

		JComboBox datatype3 = new JComboBox();
		datatype3.setFont(new Font("Dubai", Font.BOLD, 10));
		datatype3.setModel(new DefaultComboBoxModel(new String[] { "", "int", "char" }));
		datatype3.setToolTipText("");
		datatype3.setBounds(254, 204, 101, 22);
		newtablepanel.add(datatype3);

		columnname4 = new JTextField();
		columnname4.setHorizontalAlignment(SwingConstants.CENTER);
		columnname4.setFont(new Font("Dubai", Font.BOLD, 10));
		columnname4.setColumns(10);
		columnname4.setBounds(131, 247, 92, 20);
		newtablepanel.add(columnname4);

		JComboBox datatype4 = new JComboBox();
		datatype4.setFont(new Font("Dubai", Font.BOLD, 10));
		datatype4.setModel(new DefaultComboBoxModel(new String[] { "", "int", "char" }));
		datatype4.setToolTipText("");
		datatype4.setBounds(254, 246, 101, 22);
		newtablepanel.add(datatype4);

		columnname5 = new JTextField();
		columnname5.setHorizontalAlignment(SwingConstants.CENTER);
		columnname5.setFont(new Font("Dubai", Font.BOLD, 10));
		columnname5.setColumns(10);
		columnname5.setBounds(131, 289, 92, 20);
		newtablepanel.add(columnname5);

		JComboBox datatype5 = new JComboBox();
		datatype5.setFont(new Font("Dubai", Font.BOLD, 10));
		datatype5.setModel(new DefaultComboBoxModel(new String[] { "", "int", "char" }));
		datatype5.setToolTipText("");
		datatype5.setBounds(254, 288, 101, 22);
		newtablepanel.add(datatype5);

		columnname6 = new JTextField();
		columnname6.setHorizontalAlignment(SwingConstants.CENTER);
		columnname6.setFont(new Font("Dubai", Font.BOLD, 10));
		columnname6.setColumns(10);
		columnname6.setBounds(131, 331, 92, 20);
		newtablepanel.add(columnname6);

		JComboBox datatype6 = new JComboBox();
		datatype6.setFont(new Font("Dubai", Font.BOLD, 10));
		datatype6.setModel(new DefaultComboBoxModel(new String[] { "", "int", "char" }));
		datatype6.setToolTipText("");
		datatype6.setBounds(254, 330, 101, 22);
		newtablepanel.add(datatype6);

		columnname7 = new JTextField();
		columnname7.setHorizontalAlignment(SwingConstants.CENTER);
		columnname7.setFont(new Font("Dubai", Font.BOLD, 10));
		columnname7.setColumns(10);
		columnname7.setBounds(131, 373, 92, 20);
		newtablepanel.add(columnname7);

		JComboBox datatype7 = new JComboBox();
		datatype7.setFont(new Font("Dubai", Font.BOLD, 10));
		datatype7.setModel(new DefaultComboBoxModel(new String[] { "", "int", "char" }));
		datatype7.setToolTipText("");
		datatype7.setBounds(254, 372, 101, 22);
		newtablepanel.add(datatype7);

		columnname0 = new JTextField();
		columnname0.setEnabled(false);
		columnname0.setEditable(false);
		columnname0.setText("id");
		columnname0.setHorizontalAlignment(SwingConstants.CENTER);
		columnname0.setFont(new Font("Dubai", Font.BOLD, 10));
		columnname0.setColumns(10);
		columnname0.setBounds(131, 91, 92, 20);
		newtablepanel.add(columnname0);

		columnname8 = new JTextField();
		columnname8.setHorizontalAlignment(SwingConstants.CENTER);
		columnname8.setFont(new Font("Dubai", Font.BOLD, 10));
		columnname8.setColumns(10);
		columnname8.setBounds(131, 414, 92, 20);
		newtablepanel.add(columnname8);

		JComboBox datatype8 = new JComboBox();
		datatype8.setFont(new Font("Dubai", Font.BOLD, 10));
		datatype8.setModel(new DefaultComboBoxModel(new String[] { "", "int", "char" }));
		datatype8.setToolTipText("");
		datatype8.setBounds(254, 413, 101, 22);
		newtablepanel.add(datatype8);

		columnname9 = new JTextField();
		columnname9.setHorizontalAlignment(SwingConstants.CENTER);
		columnname9.setFont(new Font("Dubai", Font.BOLD, 10));
		columnname9.setColumns(10);
		columnname9.setBounds(131, 456, 92, 20);
		newtablepanel.add(columnname9);

		JComboBox datatype9 = new JComboBox();
		datatype9.setFont(new Font("Dubai", Font.BOLD, 10));
		datatype9.setModel(new DefaultComboBoxModel(new String[] { "", "int", "char" }));
		datatype9.setToolTipText("");
		datatype9.setBounds(254, 455, 101, 22);
		newtablepanel.add(datatype9);

		JComboBox datatype0 = new JComboBox();
		datatype0.setEnabled(false);
		datatype0.setFont(new Font("Dubai", Font.BOLD, 10));
		datatype0.setModel(new DefaultComboBoxModel(new String[] { "int" }));
		datatype0.setToolTipText("");
		datatype0.setBounds(254, 91, 101, 22);
		newtablepanel.add(datatype0);

		
		JComboBox[] datatype = {

				datatype0, datatype1, datatype2, datatype3, datatype4, datatype5, datatype6, datatype7, datatype8,
				datatype9 };

		for (int i = 1; i < datatype.length; i++) {

			datatype[i].setVisible(false);
			datatype[i].setSelectedIndex(-1);

		}

		JTextField[] columnname = {

				columnname0, columnname1, columnname2, columnname3, columnname4, columnname5, columnname6, columnname7,
				columnname8, columnname9 };

		for (int i = 1; i < columnname.length; i++) {

			columnname[i].setVisible(false);

		}
		JLabel primary_label = new JLabel("New label");
		primary_label.setBounds(56, 127, 46, 14);
		newtablepanel.add(primary_label);
		primary_label.setVisible(false);

		JButton btnAddRow = new JButton("Add Col");
		btnAddRow.setForeground(new Color(238, 238, 238));
		btnAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (counter < 9) {
					counter++;
				}
				if (counter==1) {
					
					primary_label.setVisible(true);
				}

				columnname[counter].setVisible(true);
				datatype[counter].setVisible(true);
			}
		});
		btnAddRow.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		btnAddRow.setBackground(new Color(50, 179, 200));
		btnAddRow.setBounds(252, 11, 107, 30);
		newtablepanel.add(btnAddRow);

		

		
		
		JButton btnDelCol = new JButton("Del Col");
		btnDelCol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (counter > 0) {
					columnname[counter].setVisible(false);
					datatype[counter].setVisible(false);
					counter--;
				}
				if (counter==0) {
					
					primary_label.setVisible(false);
				}

				
				
			}
		});
		btnDelCol.setForeground(new Color(238, 238, 238));
		btnDelCol.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		btnDelCol.setBackground(new Color(50, 179, 200));
		btnDelCol.setBounds(368, 11, 107, 30);
		newtablepanel.add(btnDelCol);
		
		JButton btnViewTable = new JButton("");
		btnViewTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewTable.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/view.png")));
		btnViewTable.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255), 2, true), "View Table", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 191, 255)));
		btnViewTable.setVisible(false);
		
		

		JButton btnNewButton = new JButton("");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255), 2, true), "New Table", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 191, 255)));
		btnNewButton.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/table_icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				orderpanel.setVisible(false);
				panel.setVisible(false);
				panel_1.setVisible(false);
				deletepanel.setVisible(false);
				newtablepanel.setVisible(false);
				searchpanel.setVisible(false);
				updatepanel.setVisible(false);				
				newtablepanel.setSize(752, 475);
				newtablepanel.setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(230, 230, 250));
		btnNewButton.setFont(new Font("Dubai", Font.BOLD, 10));
		btnNewButton.setBounds(29, 528, 100, 48);
		frmDatabase.getContentPane().add(btnNewButton);

		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(31, 304, 0, 0);
		frmDatabase.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label0 = new JLabel("New label");
		label0.setBorder(new EmptyBorder(0, 0, 0, 0));
		label0.setHorizontalAlignment(SwingConstants.RIGHT);
		label0.setFont(new Font("Georgia", Font.PLAIN, 16));
		label0.setBounds(0, 0, 180, 31);
		panel.add(label0);

		JLabel label1 = new JLabel("New label");
		label1.setBorder(new EmptyBorder(0, 0, 0, 0));
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		label1.setFont(new Font("Georgia", Font.PLAIN, 16));
		label1.setBounds(0, 31, 180, 31);
		panel.add(label1);

		JLabel label2 = new JLabel("New label");
		label2.setBorder(new EmptyBorder(0, 0, 0, 0));
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setFont(new Font("Georgia", Font.PLAIN, 16));
		label2.setBounds(0, 62, 180, 31);
		panel.add(label2);

		JLabel label3 = new JLabel("New label");
		label3.setBorder(new EmptyBorder(0, 0, 0, 0));
		label3.setHorizontalAlignment(SwingConstants.RIGHT);
		label3.setFont(new Font("Georgia", Font.PLAIN, 16));
		label3.setBounds(0, 93, 180, 31);
		panel.add(label3);

		JLabel label4 = new JLabel("New label");
		label4.setBorder(new EmptyBorder(0, 0, 0, 0));
		label4.setHorizontalAlignment(SwingConstants.RIGHT);
		label4.setFont(new Font("Georgia", Font.PLAIN, 16));
		label4.setBounds(0, 127, 180, 31);
		panel.add(label4);

		JLabel label5 = new JLabel("New label");
		label5.setBorder(new EmptyBorder(0, 0, 0, 0));
		label5.setHorizontalAlignment(SwingConstants.RIGHT);
		label5.setFont(new Font("Georgia", Font.PLAIN, 16));
		label5.setBounds(384, 0, 154, 31);
		panel.add(label5);

		JLabel label6 = new JLabel("New label");
		label6.setBorder(new EmptyBorder(0, 0, 0, 0));
		label6.setHorizontalAlignment(SwingConstants.RIGHT);
		label6.setFont(new Font("Georgia", Font.PLAIN, 16));
		label6.setBounds(384, 31, 154, 31);
		panel.add(label6);

		JLabel label7 = new JLabel("New label");
		label7.setBorder(new EmptyBorder(0, 0, 0, 0));
		label7.setHorizontalAlignment(SwingConstants.RIGHT);
		label7.setFont(new Font("Georgia", Font.PLAIN, 16));
		label7.setBounds(384, 62, 154, 31);
		panel.add(label7);

		JLabel label8 = new JLabel("New label");
		label8.setBorder(new EmptyBorder(0, 0, 0, 0));
		label8.setHorizontalAlignment(SwingConstants.RIGHT);
		label8.setFont(new Font("Georgia", Font.PLAIN, 16));
		label8.setBounds(384, 90, 154, 31);
		panel.add(label8);

		JLabel label9 = new JLabel("New label");
		label9.setBorder(new EmptyBorder(0, 0, 0, 0));
		label9.setHorizontalAlignment(SwingConstants.RIGHT);
		label9.setFont(new Font("Georgia", Font.PLAIN, 16));
		label9.setBounds(384, 127, 154, 31);
		panel.add(label9);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Georgia", Font.BOLD, 13));
		textField.setBounds(190, 5, 194, 20);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		textField_1.setFont(new Font("Georgia", Font.BOLD, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(190, 36, 194, 20);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.LEFT);
		textField_2.setFont(new Font("Georgia", Font.BOLD, 13));
		textField_2.setColumns(10);
		textField_2.setBounds(190, 67, 194, 20);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.LEFT);
		textField_3.setFont(new Font("Georgia", Font.BOLD, 13));
		textField_3.setColumns(10);
		textField_3.setBounds(190, 98, 194, 20);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.LEFT);
		textField_4.setFont(new Font("Georgia", Font.BOLD, 13));
		textField_4.setColumns(10);
		textField_4.setBounds(190, 132, 194, 20);
		panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.LEFT);
		textField_5.setFont(new Font("Georgia", Font.BOLD, 13));
		textField_5.setColumns(10);
		textField_5.setBounds(548, 5, 194, 20);
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.LEFT);
		textField_6.setFont(new Font("Georgia", Font.BOLD, 13));
		textField_6.setColumns(10);
		textField_6.setBounds(548, 36, 194, 20);
		panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setHorizontalAlignment(SwingConstants.LEFT);
		textField_7.setFont(new Font("Georgia", Font.BOLD, 13));
		textField_7.setColumns(10);
		textField_7.setBounds(548, 67, 194, 20);
		panel.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setHorizontalAlignment(SwingConstants.LEFT);
		textField_8.setFont(new Font("Georgia", Font.BOLD, 13));
		textField_8.setColumns(10);
		textField_8.setBounds(548, 98, 194, 20);
		panel.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setHorizontalAlignment(SwingConstants.LEFT);
		textField_9.setFont(new Font("Georgia", Font.BOLD, 13));
		textField_9.setColumns(10);
		textField_9.setBounds(548, 132, 194, 20);
		panel.add(textField_9);

		JTextField[] addcolumnname = {

				textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6, textField_7,
				textField_8, textField_9 };

		for (int i = 0; i < columnname.length; i++) {

			addcolumnname[i].setVisible(false);
		}

		JLabel[] labelname = {

				label0, label1, label2, label3, label4, label5, label6, label7, label8, label9 };

		for (int i = 0; i < columnname.length; i++) {

			labelname[i].setVisible(false);
		}

		JButton btnInsert = new JButton("");
		btnInsert.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInsert.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/add.png")));
		btnInsert.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255), 2, true), "Add Data", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 191, 255)));
		btnInsert.setVisible(false);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				orderpanel.setVisible(false);
				searchpanel.setVisible(false);
				deletepanel.setVisible(false);
				newtablepanel.setVisible(false);
				updatepanel.setVisible(false);

				String columsName[] = new String[Database.getTable(name).columns.length];
				String columarr[][] = new String[Database.getTable(name).columns.length][2];

				for (int i = 0; i < Database.getTable(name).columns.length; i++) {

					columarr[i][0] = Database.getTable(name).columns[i].name.toString();
					columarr[i][1] = Database.getTable(name).columns[i].type.toString();

				}
				
				for (int i = 1; i < Database.getTable(name).columns.length; i++) {

					columsName[i] = columarr[i][0] + " ( " + columarr[i][1] + " ) ";

				}

				for (int i = 1; i < Database.getTable(name).columns.length; i++) {

					if (i == 1) {
						labelname[i].setVisible(true);
						addcolumnname[i].setVisible(true);
						labelname[i].setText("(PK) " + Database.getTable(name).columns[i].name + " ( "
								+ Database.getTable(name).columns[i].type + " )");

					} else {

						labelname[i].setVisible(true);
						addcolumnname[i].setVisible(true);
						labelname[i].setText(Database.getTable(name).columns[i].name + "( "
								+ Database.getTable(name).columns[i].type + " )");

					}

				}

				panel.setSize(752, 200);
				panel.setVisible(true);

			}
		});
		btnInsert.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnInsert.setBackground(new Color(230, 230, 250));
		btnInsert.setBounds(249, 528, 113, 48);
		frmDatabase.getContentPane().add(btnInsert);

		panel_1.setBackground(new Color(230, 230, 250));
		panel_1.setBounds(33, 54, 0, 0);
		frmDatabase.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		//Search Panel
		searchpanel.setBounds(43, 321, 0, 0);
		frmDatabase.getContentPane().add(searchpanel);

		JButton btnSearchByID = new JButton("Search");
		btnSearchByID.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255), 3, true), "By ID", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, null));
		btnSearchByID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(searchid.getText());

				String[] arr = new String[Database.getTable(name).columns.length];

				try {
					long startTime = System.currentTimeMillis();
					arr = Database.seqSearchById(id, name);

					String temparr[][] = new String[1][Database.getTable(name).columns.length];
					String colname[] = new String[Database.getTable(name).columns.length];

					String columarr[][] = new String[Database.getTable(name).columns.length][2];

					for (int i = 0; i < Database.getTable(name).columns.length; i++) {

						columarr[i][0] = Database.getTable(name).columns[i].name.toString();
						columarr[i][1] = Database.getTable(name).columns[i].type.toString();

					}

					for (int i = 0; i < Database.getTable(name).columns.length; i++) {
						temparr[0][i] = arr[i];
						colname[i] = columarr[i][0] + " ( " + columarr[i][1] + " ) ";
					}

					panel_1.removeAll();
					JTable table = new JTable(temparr, colname);
					Font font = new Font("Arial", Font.PLAIN, 15); 
					CustomCellRenderer renderer = new CustomCellRenderer(font);
					for (int c = 0; c < table.getColumnCount(); c++) {
					    table.getColumnModel().getColumn(c).setCellRenderer(renderer);
					   
					    table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					}
					    JTableHeader headera = table.getTableHeader();
				        headera.setFont(headera.getFont().deriveFont(16f).deriveFont(Font.BOLD));

				        // Başlık kısmının kenarlığını ayarlama
				        headera.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					JScrollPane sp = new JScrollPane(table);

					JTableHeader header = table.getTableHeader();
					panel_1.setLayout(new BorderLayout());
					panel_1.add(header, BorderLayout.NORTH);
					panel_1.add(table, BorderLayout.CENTER);
					panel_1.revalidate();
					panel_1.repaint();
					long endTime = System.currentTimeMillis();
					
					JOptionPane.showMessageDialog(null, "Time consumed : " + (endTime-startTime) + "ms");
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSearchByID.setFont(new Font("Dubai", Font.BOLD, 10));
		btnSearchByID.setBackground(new Color(230, 230, 250));

		searchid = new JTextField();
		searchid.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("ID : ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		searchpanel.setVisible(false);
		
		JComboBox ColNameBox = new JComboBox();
		
		ColNameBox.setToolTipText("");

		JButton btnTableData = new JButton("");
		btnTableData.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTableData.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/update.png")));
		btnTableData.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255), 2, true), "Search Data", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 191, 255)));
		btnTableData.setVisible(false);
		btnTableData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColNameBox.removeAllItems();
				String colname[] = new String[Database.getTable(name).columns.length];
				for (int i = 1; i < Database.getTable(name).columns.length; i++) {
					
					colname[i] =Database.getTable(name).columns[i].name.toString();
					ColNameBox.addItem(colname[i]);
				}
				//
				
				addallpanel.setVisible(false);
				orderpanel.setVisible(false);
				panel.setVisible(false);
				deletepanel.setVisible(false);
				newtablepanel.setVisible(false);
				updatepanel.setVisible(false);
				searchpanel.setSize(752, 155);
				searchpanel.setVisible(true);
			
			}
		});
		btnTableData.setFont(new Font("Dubai", Font.BOLD, 10));
		btnTableData.setBackground(new Color(230, 230, 250));
		btnTableData.setBounds(372, 528, 100, 48);
		frmDatabase.getContentPane().add(btnTableData);

		JButton btnSearchByOffset = new JButton("Search ");
		btnSearchByOffset.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255), 3, true), "By Offset", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, null));
		btnSearchByOffset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(searchid.getText());
				String[] arr = new String[Database.getTable(name).columns.length];

				try {
					long startTime = System.currentTimeMillis();
					arr = Database.searchData(id, name);

					String temparr[][] = new String[1][Database.getTable(name).columns.length];
					String colname[] = new String[Database.getTable(name).columns.length];

					String columarr[][] = new String[Database.getTable(name).columns.length][2];

					for (int i = 0; i < Database.getTable(name).columns.length; i++) {

						columarr[i][0] = Database.getTable(name).columns[i].name.toString();
						columarr[i][1] = Database.getTable(name).columns[i].type.toString();

					}

					for (int i = 0; i < Database.getTable(name).columns.length; i++) {
						temparr[0][i] = arr[i];
						colname[i] = columarr[i][0] + " ( " + columarr[i][1] + " ) ";
					}

					panel_1.removeAll();
					JTable table = new JTable(temparr, colname);
					Font font = new Font("Arial", Font.PLAIN, 15); 
					CustomCellRenderer renderer = new CustomCellRenderer(font);
					for (int c = 0; c < table.getColumnCount(); c++) {
					    table.getColumnModel().getColumn(c).setCellRenderer(renderer);
					   
					    table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					}
					    JTableHeader headera = table.getTableHeader();
				        headera.setFont(headera.getFont().deriveFont(16f).deriveFont(Font.BOLD));

				        // Başlık kısmının kenarlığını ayarlama
				        headera.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					JScrollPane sp = new JScrollPane(table);

					JTableHeader header = table.getTableHeader();
					panel_1.setLayout(new BorderLayout());
					panel_1.add(header, BorderLayout.NORTH);
					panel_1.add(table, BorderLayout.CENTER);
					panel_1.revalidate();
					panel_1.repaint();
					long endtime = System.currentTimeMillis();
					
					 JOptionPane.showMessageDialog(null, "Time consumed : " + (endtime-startTime) + "ms");
					
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSearchByOffset.setFont(new Font("Dubai", Font.BOLD, 10));
		btnSearchByOffset.setBackground(new Color(230, 230, 250));
		
		JLabel lblNewLabel_5 = new JLabel("Search:");
		
		searchByColumText = new JTextField();
		searchByColumText.setColumns(10);
		
		JButton SearchColmBtn = new JButton("Search by");
		SearchColmBtn.setFont(new Font("Dubai", Font.BOLD, 10));
		SearchColmBtn.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255), 3, true), "Colum Name", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(0, 0, 0)));
		SearchColmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error =false;
				
				if (!searchByColumText.getText().equals("") ) {
					String selectedValue = ColNameBox.getSelectedItem().toString();
					String SearchText = searchByColumText.getText().toString();
					String[][] arr = null;
					
					try {
					arr =Database.searchWordOrNuM(selectedValue,SearchText, name);
					String temparr[][] = new String[1][Database.getTable(name).columns.length];
					String colname[] = new String[Database.getTable(name).columns.length];

					String columarr[][] = new String[Database.getTable(name).columns.length][2];

					for (int i = 0; i < Database.getTable(name).columns.length; i++) {

						columarr[i][0] = Database.getTable(name).columns[i].name.toString();
						columarr[i][1] = Database.getTable(name).columns[i].type.toString();

					}

					for (int i = 0; i < Database.getTable(name).columns.length; i++) {
						
						colname[i] = columarr[i][0] + " ( " + columarr[i][1] + " ) ";
					}

					panel_1.removeAll();
					JTable table = new JTable(arr, colname);
					Font font = new Font("Arial", Font.PLAIN, 15); 
					CustomCellRenderer renderer = new CustomCellRenderer(font);
					for (int c = 0; c < table.getColumnCount(); c++) {
					    table.getColumnModel().getColumn(c).setCellRenderer(renderer);
					   
					    table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					}
					    JTableHeader headera = table.getTableHeader();
				        headera.setFont(headera.getFont().deriveFont(16f).deriveFont(Font.BOLD));

				        // Başlık kısmının kenarlığını ayarlama
				        headera.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					JScrollPane sp = new JScrollPane(table);

					JTableHeader header = table.getTableHeader();
					panel_1.setLayout(new BorderLayout());
					panel_1.add(header, BorderLayout.NORTH);
					panel_1.add(table, BorderLayout.CENTER);
					panel_1.revalidate();
					panel_1.repaint();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
						
				
			}
				
				else {
					
					System.out.println("Boş olamaz...!");
				}
				
			}});
		GroupLayout gl_searchpanel = new GroupLayout(searchpanel);
		gl_searchpanel.setHorizontalGroup(
			gl_searchpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchpanel.createSequentialGroup()
					.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_searchpanel.createSequentialGroup()
							.addGap(551)
							.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_searchpanel.createSequentialGroup()
							.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_searchpanel.createSequentialGroup()
									.addGap(10)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addComponent(searchid, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_searchpanel.createSequentialGroup()
									.addGap(23)
									.addComponent(btnSearchByOffset, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
									.addGap(35)
									.addComponent(btnSearchByID, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)))
							.addGap(116)
							.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_searchpanel.createSequentialGroup()
									.addComponent(ColNameBox, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addComponent(searchByColumText, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_searchpanel.createSequentialGroup()
									.addGap(55)
									.addComponent(SearchColmBtn, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))))
					.addGap(73))
		);
		gl_searchpanel.setVerticalGroup(
			gl_searchpanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_searchpanel.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_searchpanel.createSequentialGroup()
							.addGap(1)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_searchpanel.createSequentialGroup()
							.addGap(1)
							.addComponent(searchid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(ColNameBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_searchpanel.createSequentialGroup()
							.addGap(1)
							.addComponent(searchByColumText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(25)
					.addGroup(gl_searchpanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_searchpanel.createSequentialGroup()
							.addGap(1)
							.addComponent(SearchColmBtn, 0, 0, Short.MAX_VALUE))
						.addComponent(btnSearchByOffset, 0, 0, Short.MAX_VALUE)
						.addComponent(btnSearchByID, GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE))
					.addContainerGap())
		);
		searchpanel.setLayout(gl_searchpanel);
		
		

		
		btnViewTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addallpanel.setVisible(false);
				orderpanel.setVisible(false);
				panel.setVisible(false);
				deletepanel.setVisible(false);
				newtablepanel.setVisible(false);
				newtablepanel.setVisible(false);
				searchpanel.setVisible(false);
				
				panel_1.setSize(752, 230);
				panel_1.setVisible(true);
				System.out.println(Database.getTable(name).columns.length);
				String columarr[][] = new String[Database.getTable(name).columns.length][2];

				for (int i = 0; i < Database.getTable(name).columns.length; i++) {

					columarr[i][0] = Database.getTable(name).columns[i].name.toString();
					columarr[i][1] = Database.getTable(name).columns[i].type.toString();

				}

				String dataarr[][] = null;

				try {
					dataarr = Database.readAll(name);
					
					String columsName[] = new String[Database.getTable(name).columns.length];

					for (int i = 0; i < Database.getTable(name).columns.length; i++) {

						columsName[i] = columarr[i][0] + " ( " + columarr[i][1] + " ) ";
						System.out.println(columsName[i]);
					}
					
					if(dataarr == null) {
						
						dataarr = new String[1][Database.getTable(name).columns.length];
						for(int i =0;i<Database.getTable(name).columns.length;i++) {
						
							dataarr[0][i] = "";
						}
					}
			
					panel_1.removeAll();
					
					JTable table = new JTable(dataarr, columsName);
					Font font = new Font("Arial", Font.PLAIN, 15); 
					CustomCellRenderer renderer = new CustomCellRenderer(font);
					for (int c = 0; c < table.getColumnCount(); c++) {
					    table.getColumnModel().getColumn(c).setCellRenderer(renderer);
					   
					    table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					    
					    JTableHeader headera = table.getTableHeader();
				        headera.setFont(headera.getFont().deriveFont(16f).deriveFont(Font.BOLD));

				        // Başlık kısmının kenarlığını ayarlama
				        headera.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				       
					
	                JScrollPane scrollPane = new JScrollPane(table);
	                panel_1.setLayout(new BorderLayout());
	                panel_1.add(scrollPane, BorderLayout.CENTER);

	                JTableHeader header = table.getTableHeader();
	                panel_1.add(header, BorderLayout.NORTH);
	               
	                panel_1.revalidate();
	                panel_1.repaint();
					}
					}
					
				 catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block


					e1.printStackTrace();
				}

			}

		});

		btnViewTable.setFont(new Font("Dubai", Font.BOLD, 10));
		btnViewTable.setBackground(new Color(230, 230, 250));
		btnViewTable.setBounds(139, 528, 100, 48);
		frmDatabase.getContentPane().add(btnViewTable);

		//Update Panel
		updatepanel.setBounds(31, 309, 0, 0);
		frmDatabase.getContentPane().add(updatepanel);
		updatepanel.setLayout(null);

		JLabel updatelabel0 = new JLabel("New label");
		updatelabel0.setFont(new Font("Georgia", Font.PLAIN, 11));
		updatelabel0.setHorizontalAlignment(SwingConstants.RIGHT);
		updatelabel0.setBounds(23, 40, 100, 20);
		updatepanel.add(updatelabel0);

		JLabel updatelabel1 = new JLabel("New label");
		updatelabel1.setFont(new Font("Georgia", Font.PLAIN, 11));
		updatelabel1.setHorizontalAlignment(SwingConstants.RIGHT);
		updatelabel1.setBounds(23, 71, 100, 20);
		updatepanel.add(updatelabel1);

		JLabel updatelabel2 = new JLabel("New label");
		updatelabel2.setFont(new Font("Georgia", Font.PLAIN, 11));
		updatelabel2.setHorizontalAlignment(SwingConstants.RIGHT);
		updatelabel2.setBounds(23, 102, 100, 20);
		updatepanel.add(updatelabel2);

		JLabel updatelabel3 = new JLabel("New label");
		updatelabel3.setFont(new Font("Georgia", Font.PLAIN, 11));
		updatelabel3.setHorizontalAlignment(SwingConstants.RIGHT);
		updatelabel3.setBounds(23, 130, 100, 20);
		updatepanel.add(updatelabel3);

		JLabel updatelabel4 = new JLabel("New label");
		updatelabel4.setFont(new Font("Georgia", Font.PLAIN, 11));
		updatelabel4.setHorizontalAlignment(SwingConstants.RIGHT);
		updatelabel4.setBounds(23, 161, 100, 20);
		updatepanel.add(updatelabel4);

		JLabel updatelabel5 = new JLabel("New label");
		updatelabel5.setFont(new Font("Georgia", Font.PLAIN, 11));
		updatelabel5.setHorizontalAlignment(SwingConstants.RIGHT);
		updatelabel5.setBounds(246, 43, 104, 20);
		updatepanel.add(updatelabel5);

		JLabel updatelabel6 = new JLabel("New label");
		updatelabel6.setFont(new Font("Georgia", Font.PLAIN, 11));
		updatelabel6.setHorizontalAlignment(SwingConstants.RIGHT);
		updatelabel6.setBounds(246, 68, 104, 20);
		updatepanel.add(updatelabel6);

		JLabel updatelabel7 = new JLabel("New label");
		updatelabel7.setFont(new Font("Georgia", Font.PLAIN, 11));
		updatelabel7.setHorizontalAlignment(SwingConstants.RIGHT);
		updatelabel7.setBounds(246, 102, 104, 20);
		updatepanel.add(updatelabel7);

		JLabel updatelabel8 = new JLabel("New label");
		updatelabel8.setFont(new Font("Georgia", Font.PLAIN, 11));
		updatelabel8.setHorizontalAlignment(SwingConstants.RIGHT);
		updatelabel8.setBounds(246, 130, 104, 20);
		updatepanel.add(updatelabel8);

		JLabel updatelabel9 = new JLabel("New label");
		updatelabel9.setFont(new Font("Georgia", Font.PLAIN, 11));
		updatelabel9.setHorizontalAlignment(SwingConstants.RIGHT);
		updatelabel9.setBounds(246, 161, 104, 20);
		updatepanel.add(updatelabel9);

		JLabel lblNewLabel_2_10 = new JLabel("ID : ");
		lblNewLabel_2_10.setFont(new Font("Lucida Handwriting", Font.BOLD, 11));
		lblNewLabel_2_10.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_10.setBounds(62, 9, 46, 20);
		updatepanel.add(lblNewLabel_2_10);

		updateid = new JTextField();
		updateid.setBounds(118, 8, 66, 20);
		updatepanel.add(updateid);
		updateid.setColumns(10);

		update0 = new JTextField();
		update0.setColumns(10);
		update0.setBounds(133, 41, 101, 20);
		updatepanel.add(update0);

		update1 = new JTextField();
		update1.setColumns(10);
		update1.setBounds(133, 71, 101, 20);
		updatepanel.add(update1);

		update2 = new JTextField();
		update2.setColumns(10);
		update2.setBounds(133, 102, 101, 20);
		updatepanel.add(update2);

		update3 = new JTextField();
		update3.setColumns(10);
		update3.setBounds(133, 130, 101, 20);
		updatepanel.add(update3);

		update4 = new JTextField();
		update4.setColumns(10);
		update4.setBounds(133, 161, 101, 20);
		updatepanel.add(update4);

		update5 = new JTextField();
		update5.setColumns(10);
		update5.setBounds(359, 40, 116, 20);
		updatepanel.add(update5);

		update6 = new JTextField();
		update6.setColumns(10);
		update6.setBounds(359, 71, 116, 20);
		updatepanel.add(update6);

		update7 = new JTextField();
		update7.setColumns(10);
		update7.setBounds(359, 102, 116, 20);
		updatepanel.add(update7);

		update8 = new JTextField();
		update8.setColumns(10);
		update8.setBounds(359, 130, 116, 20);
		updatepanel.add(update8);

		update9 = new JTextField();
		update9.setColumns(10);
		update9.setBounds(359, 161, 116, 20);
		updatepanel.add(update9);

		JTextField[] updatecolum = {

				update0, update1, update2, update3, update4, update5, update6, update7, update8, update9 };

		for (int i = 0; i < updatecolum.length; i++) {

			updatecolum[i].setVisible(false);
		}

		JLabel[] updatelabel = {

				updatelabel0, updatelabel1, updatelabel2, updatelabel3, updatelabel4, updatelabel5, updatelabel6,
				updatelabel7, updatelabel8, updatelabel9 };

		for (int i = 0; i < updatelabel.length; i++) {

			updatelabel[i].setVisible(false);
		}

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/uppdate.png")));
		btnNewButton_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255), 2, true), "Update Data", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 191, 255)));
		btnNewButton_3.setVisible(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addallpanel.setVisible(false);
				orderpanel.setVisible(false);
				panel.setVisible(false);
				deletepanel.setVisible(false);
				newtablepanel.setVisible(false);
				newtablepanel.setVisible(false);
				searchpanel.setVisible(false);
				updatepanel.setSize(752, 225);
				updatepanel.setVisible(true);

			}
		});
		btnNewButton_3.setFont(new Font("Dubai", Font.BOLD, 10));
		btnNewButton_3.setBackground(new Color(230, 230, 250));
		btnNewButton_3.setBounds(482, 528, 100, 48);
		frmDatabase.getContentPane().add(btnNewButton_3);

		JButton btnSearch = new JButton("");
		btnSearch.setToolTipText("Search");
		btnSearch.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/update.png")));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = new String[Database.getTable(name).columns.length];
				int id = Integer.parseInt(updateid.getText());

				try {
					arr = Database.seqSearchById(id, name);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				String columsName[] = new String[Database.getTable(name).columns.length];
				String columarr[][] = new String[Database.getTable(name).columns.length][2];

				for (int i = 0; i < Database.getTable(name).columns.length; i++) {

					columarr[i][0] = Database.getTable(name).columns[i].name.toString();
					columarr[i][1] = Database.getTable(name).columns[i].type.toString();

				}
				
				String dataarr[][] = null;

				try {
					dataarr = Database.readAll(name);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				for (int i = 1; i < Database.getTable(name).columns.length; i++) {

					columsName[i] = columarr[i][0] + " ( " + columarr[i][1] + " ) ";
					
					

				}
				
				
				
				for (int i = 1; i < Database.getTable(name).columns.length; i++) {

					if (i == 1) {
						updatecolum[i].setVisible(true);
						updatelabel[i].setVisible(true);
						updatelabel[i].setText("(PK) " + Database.getTable(name).columns[i].name + " ( "
								+ Database.getTable(name).columns[i].type + " )");
						updatecolum[i].setText(dataarr[id - 1][i].replaceAll("\\p{C}", ""));
					} else {

						updatecolum[i].setVisible(true);
						updatelabel[i].setVisible(true);
						updatelabel[i].setText(Database.getTable(name).columns[i].name + "( "
								+ Database.getTable(name).columns[i].type + " )");
						updatecolum[i].setText(dataarr[id - 1][i].replaceAll("\\p{C}", ""));
					}

				}
				
			}
		});
		btnSearch.setFont(new Font("Dubai", Font.BOLD, 10));
		btnSearch.setBackground(new Color(230, 230, 250));
		btnSearch.setBounds(194, 0, 52, 38);
		updatepanel.add(btnSearch);

		JButton btnNewButton_3_1 = new JButton("");
		btnNewButton_3_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "By ID", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(255, 140, 0)));
		btnNewButton_3_1.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/update_icon.png")));
		btnNewButton_3_1.setSelectedIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/update_icon.png")));
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(updateid.getText());
				String[] arr = new String[Database.getTable(name).columns.length];
				for (int i = 0; i < Database.getTable(name).columns.length; i++) {

					arr[i] = updatecolum[i].getText();

				}
				try {
					Database.updateDataById(id, name, arr);
					btnViewTable.doClick();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_3_1.setFont(new Font("Dubai", Font.BOLD, 10));
		btnNewButton_3_1.setBackground(new Color(230, 230, 250));
		btnNewButton_3_1.setBounds(485, 0, 100, 75);
		updatepanel.add(btnNewButton_3_1);

		JButton btnNewButton_3_2 = new JButton("");
		btnNewButton_3_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "By OFSET", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, new Color(255, 140, 0)));
		btnNewButton_3_2.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/update_icon.png")));
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(updateid.getText());
				String[] arr = new String[Database.getTable(name).columns.length];
				for (int i = 0; i < Database.getTable(name).columns.length; i++) {

					arr[i] = updatecolum[i].getText();

				}
				try {
					Database.updateDataByOffset(id, name, arr);
					btnViewTable.doClick();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3_2.setFont(new Font("Dubai", Font.BOLD, 10));
		btnNewButton_3_2.setBackground(new Color(230, 230, 250));
		btnNewButton_3_2.setBounds(588, 0, 100, 75);
		updatepanel.add(btnNewButton_3_2);

		//DeletePanel
		deletepanel.setBounds(31, 321, 0, 0);
		frmDatabase.getContentPane().add(deletepanel);
		deletepanel.setLayout(null);

		JButton btnSearch_1 = new JButton("");
		btnSearch_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnSearch_1.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/update.png")));
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(deleteid.getText());

				String[] arr = new String[Database.getTable(name).columns.length];

				try {
					arr = Database.seqSearchById(id, name);

					String temparr[][] = new String[1][Database.getTable(name).columns.length];
					String colname[] = new String[Database.getTable(name).columns.length];

					String columarr[][] = new String[Database.getTable(name).columns.length][2];

					for (int i = 0; i < Database.getTable(name).columns.length; i++) {

						columarr[i][0] = Database.getTable(name).columns[i].name.toString();
						columarr[i][1] = Database.getTable(name).columns[i].type.toString();

					}

					for (int i = 0; i < Database.getTable(name).columns.length; i++) {
						temparr[0][i] = arr[i];
						colname[i] = columarr[i][0] + " ( " + columarr[i][1] + " ) ";
					}
					panel_1.removeAll();
					JTable table = new JTable(temparr, colname);
					Font font = new Font("Arial", Font.PLAIN, 15); 
					CustomCellRenderer renderer = new CustomCellRenderer(font);
					for (int c = 0; c < table.getColumnCount(); c++) {
					    table.getColumnModel().getColumn(c).setCellRenderer(renderer);
					   
					    table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					}
					    JTableHeader headera = table.getTableHeader();
				        headera.setFont(headera.getFont().deriveFont(16f).deriveFont(Font.BOLD));

				        // Başlık kısmının kenarlığını ayarlama
				        headera.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					JScrollPane sp = new JScrollPane(table);

					JTableHeader header = table.getTableHeader();
					panel_1.setLayout(new BorderLayout());
					panel_1.add(header, BorderLayout.NORTH);
					panel_1.add(table, BorderLayout.CENTER);
					panel_1.revalidate();
					panel_1.repaint();
					

				}
				
					
				 catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		btnSearch_1.setFont(new Font("Dubai", Font.BOLD, 10));
		btnSearch_1.setBackground(new Color(230, 230, 250));
		btnSearch_1.setBounds(410, 48, 58, 40);
		deletepanel.add(btnSearch_1);

		deleteid = new JTextField();
		deleteid.setColumns(10);
		deleteid.setBounds(340, 59, 66, 20);
		deletepanel.add(deleteid);

		JLabel lblNewLabel_2_10_1 = new JLabel("ID : ");
		lblNewLabel_2_10_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_10_1.setBounds(291, 59, 46, 20);
		deletepanel.add(lblNewLabel_2_10_1);

		JButton deleteDataById = new JButton("By Id");
		deleteDataById.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255), 3, true), "Delete Data", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, null));
		deleteDataById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(deleteid.getText());

				try {
					Database.deleteDataById(id, name);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		deleteDataById.setFont(new Font("Dubai", Font.BOLD, 10));
		deleteDataById.setBackground(new Color(230, 230, 250));
		deleteDataById.setBounds(267, 104, 100, 40);
		deletepanel.add(deleteDataById);

		JButton deleteDataByOffset = new JButton("ByOffset");
		deleteDataByOffset.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255), 3, true), "Delete Data", TitledBorder.CENTER, TitledBorder.ABOVE_BOTTOM, null, null));
		deleteDataByOffset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int id = Integer.parseInt(deleteid.getText());

				try {
					Database.deleteDataByOffset(id, name);
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		deleteDataByOffset.setFont(new Font("Dubai", Font.BOLD, 10));
		deleteDataByOffset.setBackground(new Color(230, 230, 250));
		deleteDataByOffset.setBounds(390, 104, 100, 40);
		deletepanel.add(deleteDataByOffset);

		JButton btnNewButton_3_3 = new JButton("");
		btnNewButton_3_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3_3.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/delete.png")));
		btnNewButton_3_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255), 2, true), "Delete Data", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 191, 255)));
		btnNewButton_3_3.setVisible(false);
		btnNewButton_3_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addallpanel.setVisible(false);
				orderpanel.setVisible(false);
				panel.setVisible(false);
				updatepanel.setVisible(false);
				newtablepanel.setVisible(false);
				newtablepanel.setVisible(false);
				searchpanel.setVisible(false);
				
				deletepanel.setSize(752, 200);
				deletepanel.setVisible(true);
			}
		});
		btnNewButton_3_3.setFont(new Font("Dubai", Font.BOLD, 10));
		btnNewButton_3_3.setBackground(new Color(230, 230, 250));
		btnNewButton_3_3.setBounds(592, 528, 100, 48);
		frmDatabase.getContentPane().add(btnNewButton_3_3);
		
		
		orderpanel.setBounds(31, 321, 0, 0);
		frmDatabase.getContentPane().add(orderpanel);
		orderpanel.setLayout(null);
		
		
		
		
		JComboBox OrderComboBox = new JComboBox();
		OrderComboBox.setBounds(123, 48, 182, 21);
		orderpanel.add(OrderComboBox);
		
		JComboBox combo = new JComboBox();
		combo.setModel(new DefaultComboBoxModel(new String[] {"By ID", "By Offset"}));
		combo.setBounds(123, 124, 182, 21);
		orderpanel.add(combo);

		JButton OrderBtn = new JButton("");
		OrderBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		OrderBtn.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/order_icon.png")));
		OrderBtn.setBorder(new TitledBorder(new LineBorder(new Color(0, 191, 255), 2, true), "Order", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 191, 255)));
		OrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addallpanel.setVisible(false);
				searchpanel.setVisible(false);
				panel.setVisible(false);
				deletepanel.setVisible(false);
				newtablepanel.setVisible(false);
				updatepanel.setVisible(false);
				orderpanel.setSize(752,200);
				orderpanel.setVisible(true);
				
				
				OrderComboBox.removeAllItems();
				OrderComboBox.addItem(Database.getTable(name).columns[0].name.toString());
				OrderComboBox.addItem(Database.getTable(name).columns[1].name.toString());
				
				
			}
		});
		class MetadataFileFilter extends FileFilter {
		    @Override
		    public boolean accept(File f) {
		        if (f.isDirectory()) {
		            return true;
		        }
		        String name = f.getName().toLowerCase();
		        return name.endsWith("_metadata.bin");
		    }

		    @Override
		    public String getDescription() {
		        return "Metadata files (bin)";
		    }
		}
		
		OrderBtn.setVisible(false);
		OrderBtn.setFont(new Font("Dubai", Font.BOLD, 10));
		OrderBtn.setBackground(new Color(230, 230, 250));
		OrderBtn.setBounds(702, 528, 100, 48);
		frmDatabase.getContentPane().add(OrderBtn);
		
		JButton btnInsertTable = new JButton("Upload Table");
		btnInsertTable.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnInsertTable.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/upload.png")));
		btnInsertTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				JFileChooser j = new JFileChooser("..\\DbMeow");
				j.setAcceptAllFileFilterUsed(false); // Disable "All files" filter
				// only show bin files
				j.setFileFilter(new MetadataFileFilter());

				int secim = j.showOpenDialog(JOptionPane.getRootFrame());
				if (secim == JFileChooser.APPROVE_OPTION) {
					File file = j.getSelectedFile();
					String fileName = file.getName();
					String temp = fileName;
					
					name = fileName;
					name = name.split("_")[0];  
					
					try {
						 
						System.out.println(temp.split("_")[1].equals("metadata.bin"));
							btnViewTable.setVisible(true);
							btnInsert.setVisible(true);
							btnTableData.setVisible(true);
							btnNewButton_3.setVisible(true);
							btnNewButton_3_3.setVisible(true);
							OrderBtn.setVisible(true);
							btnViewTable.doClick();
							
						
					
					}
							
					
					catch (Exception e1) {
						JOptionPane.showMessageDialog(panel, "Could not open file", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					
					

				}
				
			}
			
		});
		
		
		btnInsertTable.setFont(new Font("Georgia", Font.BOLD, 14));
		btnInsertTable.setBackground(new Color(230, 230, 250));
		btnInsertTable.setBounds(320, 11, 173, 40);
		frmDatabase.getContentPane().add(btnInsertTable);

		JButton btnInsertTable_1 = new JButton("");
		btnInsertTable_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnInsertTable_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addallpanel.setVisible(false);
				orderpanel.setVisible(false);
				updatepanel.setVisible(false);
				deletepanel.setVisible(false);
				panel.setVisible(false);
				newtablepanel.setVisible(false);
				searchpanel.setVisible(false);
				btnViewTable.setVisible(false);
				btnInsert.setVisible(false);
				btnTableData.setVisible(false);
				btnNewButton_3.setVisible(false);
				btnNewButton_3_3.setVisible(false);
				OrderBtn.setVisible(false);
				panel_1.setSize(0, 0);

			}
		});
		btnInsertTable_1.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/home.png")));
		btnInsertTable_1.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnInsertTable_1.setBackground(new Color(230, 230, 250));
		btnInsertTable_1.setBounds(31, 11, 50, 40);
		frmDatabase.getContentPane().add(btnInsertTable_1);
		
		

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setSelected(true);
		btnNewButton_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "ADD", TitledBorder.CENTER, TitledBorder.BOTTOM, null, new Color(0, 255, 0)));
		btnNewButton_2.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/tik_icon.png")));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNewButton_2.setBackground(new Color(230, 230, 250));
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBounds(new Rectangle(5, 10, 0, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean empty = false;
				boolean type = false;

				for (int i = 1; i < Database.getTable(name).columns.length; i++) {

					if (addcolumnname[i].getText().equals("")) {

						empty = true;

					}

					try {

						if (Database.getTable(name).columns[i].type.equals("int")) {
							int value = Integer.parseInt(addcolumnname[i].getText());

						}

					} catch (Exception e1) {

						type = true;

					}

				}

				if (empty) {
					JOptionPane.showMessageDialog(null, "Table name is not be null.", "ERROR",
							JOptionPane.ERROR_MESSAGE);

				} else if (type) {
					JOptionPane.showMessageDialog(null, "Please check type.", "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {

					String arr[] = new String[Database.getTable(name).columns.length];
					for (int i = 1; i < Database.getTable(name).columns.length; i++) {
						arr[0] = -1 + "";
						arr[i] = addcolumnname[i].getText();

					}

					try {
						Database.newData(arr, name);
					} catch (ClassNotFoundException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					for (int i = 1; i < Database.getTable(name).columns.length; i++) {

						addcolumnname[i].setText("");

					}
					
					
					btnViewTable.doClick();
					

				}

			}
		});
		btnNewButton_2.setBounds(372, 163, 89, 36);
		panel.add(btnNewButton_2);
		
		
		
		JButton DescendingButton = new JButton("Descending Order");
		DescendingButton.setBorder(new LineBorder(new Color(0, 191, 255), 3, true));
		DescendingButton.setBackground(new Color(230, 230, 250));
		DescendingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			String column = OrderComboBox.getSelectedItem().toString();
			int secim = OrderComboBox.getSelectedIndex();
			String order = "descending";
			
			Object [][] data = null;
		
			try {
				long endTime=0;
				long startTime = System.currentTimeMillis();
				if(combo.getSelectedIndex()== 0)
				{
					data = Database.OrderListById(name, secim, order);
					 endTime = System.currentTimeMillis();
				}
				
				else
				{
					data = Database.OrderListByIndex(name, secim, order);
					 endTime = System.currentTimeMillis();
				}
				
				String columarr[][] = new String[Database.getTable(name).columns.length][2];

				for (int i = 0; i < Database.getTable(name).columns.length; i++) {

					columarr[i][0] = Database.getTable(name).columns[i].name.toString();
					columarr[i][1] = Database.getTable(name).columns[i].type.toString();

				}
				
				String columsName[] = new String[Database.getTable(name).columns.length];

				for (int i = 0; i < Database.getTable(name).columns.length; i++) {

					columsName[i] = columarr[i][0] + " ( " + columarr[i][1] + " ) ";
					System.out.println(columsName[i]);
				}
				
				
				panel_1.removeAll();
				
				JTable table = new JTable(data, columsName);
				Font font = new Font("Arial", Font.PLAIN, 15); 
				CustomCellRenderer renderer = new CustomCellRenderer(font);
				for (int c = 0; c < table.getColumnCount(); c++) {
				    table.getColumnModel().getColumn(c).setCellRenderer(renderer);
				   
				    table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				}
				    JTableHeader headera = table.getTableHeader();
			        headera.setFont(headera.getFont().deriveFont(16f).deriveFont(Font.BOLD));

			        // Başlık kısmının kenarlığını ayarlama
			        headera.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JScrollPane scrollPane = new JScrollPane(table);
                panel_1.setLayout(new BorderLayout());
                panel_1.add(scrollPane, BorderLayout.CENTER);

                JTableHeader header = table.getTableHeader();
                panel_1.add(header, BorderLayout.NORTH);
                
                panel_1.revalidate();
                panel_1.repaint();
			
                JOptionPane.showMessageDialog(null, "Time consumed : "+ (endTime-startTime) + " ms");
                
                
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			
			
			}
		});
		DescendingButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		DescendingButton.setBounds(422, 43, 131, 30);
		orderpanel.add(DescendingButton);
		
		
		JButton AscendingButton = new JButton("Ascending Order");
		AscendingButton.setBorder(new LineBorder(new Color(0, 191, 255), 3, true));
		AscendingButton.setBackground(new Color(230, 230, 250));
		AscendingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				String column = OrderComboBox.getSelectedItem().toString();
				int secim = OrderComboBox.getSelectedIndex();
				String order = "ascending";
				
				Object [][] data = null;
				
				try {
					
					if(combo.getSelectedIndex()== 0)
					{
						data = Database.OrderListById(name, secim, order);
					}
					
					else
					{
						data = Database.OrderListByIndex(name, secim, order);
					}
					
					String columarr[][] = new String[Database.getTable(name).columns.length][2];

					for (int i = 0; i < Database.getTable(name).columns.length; i++) {

						columarr[i][0] = Database.getTable(name).columns[i].name.toString();
						columarr[i][1] = Database.getTable(name).columns[i].type.toString();

					}
					
					String columsName[] = new String[Database.getTable(name).columns.length];

					for (int i = 0; i < Database.getTable(name).columns.length; i++) {

						columsName[i] = columarr[i][0] + " ( " + columarr[i][1] + " ) ";
						System.out.println(columsName[i]);
					}
					
					
					panel_1.removeAll();
					
					JTable table = new JTable(data, columsName);
					Font font = new Font("Arial", Font.PLAIN, 15); 
					CustomCellRenderer renderer = new CustomCellRenderer(font);
					for (int c = 0; c < table.getColumnCount(); c++) {
					    table.getColumnModel().getColumn(c).setCellRenderer(renderer);
					   
					    table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					}
					    JTableHeader headera = table.getTableHeader();
				        headera.setFont(headera.getFont().deriveFont(16f).deriveFont(Font.BOLD));

				        // Başlık kısmının kenarlığını ayarlama
				        headera.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	                JScrollPane scrollPane = new JScrollPane(table);
	                panel_1.setLayout(new BorderLayout());
	                panel_1.add(scrollPane, BorderLayout.CENTER);

	                JTableHeader header = table.getTableHeader();
	                panel_1.add(header, BorderLayout.NORTH);

	                panel_1.revalidate();
	                panel_1.repaint();
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			
				}
			});
		AscendingButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		AscendingButton.setBounds(422, 115, 131, 30);
		orderpanel.add(AscendingButton);
		//addallpanel
		addallpanel.setBackground(new Color(230, 230, 250));
		addallpanel.setBounds(43, 304, 0, 0);
		frmDatabase.getContentPane().add(addallpanel);
		addallpanel.setLayout(null);
		addallpanel.setVisible(true);
		
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setVisible(false);
		progressBar.setStringPainted(true);
		progressBar.setBounds(280, 42, 225, 28);
		addallpanel.add(progressBar);
		
		howmany = new JTextField();
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/wink (1).png")));
		lblNewLabel_3.setBounds(206, 24, 64, 64);
		addallpanel.add(lblNewLabel_3);
		
		JLabel meowText = new JLabel("Please !");
		meowText.setFont(new Font("Tempus Sans ITC", Font.BOLD, 11));
		meowText.setBounds(267, 0, 98, 26);
		addallpanel.add(meowText);
		
		JButton btnRandomData = new JButton("Random Data");
		btnRandomData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			addallpanel.setSize(752,200);
			addallpanel.setVisible(true);
			progressBar.setVisible(false);
			progressBar.setValue(0);
			panel.setSize(0,0);
			
				
			}
		});
		btnRandomData.setHorizontalTextPosition(SwingConstants.LEFT);
		btnRandomData.setHorizontalAlignment(SwingConstants.LEFT);
		btnRandomData.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/geri_button.png")));
		btnRandomData.setFont(new Font("Georgia", Font.BOLD, 12));
		btnRandomData.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnRandomData.setBackground(new Color(230, 230, 250));
		btnRandomData.setBounds(20, 159, 142, 40);
		panel.add(btnRandomData);

		
		

		howmany.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
		    printText();
		  }
		  public void insertUpdate(DocumentEvent e) {
		    printText();
		  }
		  public void removeUpdate(DocumentEvent e) {
		    printText();
		  }
		  
		  public void printText() {
			  
			try {
				String text = howmany.getText().toString();
		    if (Integer.parseInt(howmany.getText())<1000)
		    {
		    	lblNewLabel_3.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/wink (1).png")));
		    	meowText.setText("Please !");
		    	
		    }
		    else if (Integer.parseInt(howmany.getText())<10000)
		    {
		    	lblNewLabel_3.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/sad (1).png")));
		    	meowText.setText("PLEASE !!!");
		    }
		    else 
		    {
		    	lblNewLabel_3.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/angry (1).png")));
		    	meowText.setText("PLEASEEE HELLLP !!!");
		    }
		    
		    }
	
			 catch (Exception e) {
				 
			}  
		  }});
		
		howmany.setBounds(328, 80, 86, 20);
		addallpanel.add(howmany);
		howmany.setColumns(10);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("How many records do you want?");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(280, 46, 202, 14);
		addallpanel.add(lblNewLabel_2);
		
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setRolloverEnabled(false);
		howmany.setText("");
		
		
		howmany.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                
            	//System.out.println("bişey");
            }
            @Override
            public void focusLost(FocusEvent e) {
            	
            	btnNewButton_4.setBorder(new TitledBorder(null, ""+howmany.getText(), TitledBorder.CENTER, TitledBorder.BELOW_BOTTOM, null, new Color(0, 191, 255)));

            }
            });
		
		
		btnNewButton_4.setBackground(new Color(230, 230, 250));
		btnNewButton_4.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/add.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(howmany.getText().toString().equals("")) {
					System.out.println("bos");
				}
				else {
					
				
				btnNewButton_4.setEnabled(false);
				int number = Integer.parseInt(howmany.getText());
				btnNewButton_4.setName(howmany.getText().toString());
				btnNewButton_4.revalidate();
				btnNewButton_4.repaint();
				progressBar.setVisible(true);
				Thread addDataThread = new Thread(() -> {
					
					int colnumber = Database.getTable(name).columns.length;
						Random rand = new Random();
						String ALPHABET = "bcdfghjklmnpqrstvwxyz";
						String ALPHABETSESLI = "aeiou";
					
						String columarr[][] = new String[colnumber][2];
						String data[][] = new String[colnumber][2];

						for (int i = 0; i <colnumber; i++) {

							columarr[i][0] = Database.getTable(name).columns[i].name.toString();
							columarr[i][1] = Database.getTable(name).columns[i].type.toString();

						}
						
						
			
						String[] arr = new String[colnumber];
						
						 isend = false;
					for( int i = 0;i<number;i++) {
						
					
						for (int k = 1 ;k<colnumber;k++) {
					
						if(columarr[k][1].equals("int")) {
							
							 	int min = 10; //
						        int max = 9999; //
						        int randomNumber = rand.nextInt((max - min) + 1) + min;
						        arr[k] = randomNumber+"";
						       
					
						}
						else if(columarr[k][1].equals("char")){
							int length = rand.nextInt(9) + 2; 
					        StringBuilder sb = new StringBuilder();
					        for (int i1 = 0; i1 < length; i1++) {
					        	int index;
					            if(i1%2==0) {
					            	index  = rand.nextInt(ALPHABET.length());
					            	char randomChar = ALPHABET.charAt(index);
						            sb.append(randomChar);
					            }
					            else {
					            	index  = rand.nextInt(ALPHABETSESLI.length());
					            	 char randomChar = ALPHABETSESLI.charAt(index);
							           sb.append(randomChar);
					            }
				
						}
					        arr[k] = sb.toString(); 
					       
						
					}
					
						
						}
						
						 try {
								Database.newData(arr, name);
						
							}
						 catch (ClassNotFoundException | IOException  | java.lang.ArrayIndexOutOfBoundsException e2 ) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
			
						
						 int progress = (i + 1) * 100 / number;
					        SwingUtilities.invokeLater(() -> {
					            progressBar.setValue(progress);
					        });
				    }
				
					btnViewTable.doClick();
					progressBar.setValue(0);
					howmany.setText("");
					progressBar.setValue(0);
					btnNewButton_4.setEnabled(true);
					
				
				});
				Thread updateProgressBarThread = new Thread(() -> {
				    while (addDataThread.isAlive()) {
				       progrescount = progressBar.getValue();
				         if(progrescount < 100) {
				        	progrescount++;
				        	
				            SwingUtilities.invokeLater(() -> {
				            	
				            	progressBar.setValue(progrescount);
				                
				            });
				        }
				        

				        try {
				            Thread.sleep(100);
				        } catch (InterruptedException e1) {
				            e1.printStackTrace();
				        }
				    }
				});
		
				
				addDataThread.start();
				updateProgressBarThread.start();
			}
			}
		});
			
		btnNewButton_4.setBounds(328, 122, 86, 54);
		addallpanel.add(btnNewButton_4);
		
		JButton GeriBtn = new JButton("");
		GeriBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addallpanel.setSize(0,0);
				btnInsert.doClick();
			}
		});
		GeriBtn.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/geri_button.png")));
		GeriBtn.setBackground(new Color(230, 230, 250));
		GeriBtn.setBounds(10, 11, 64, 41);
		addallpanel.add(GeriBtn);
		
		
		JButton btnNewButton_1 = new JButton("Create Table");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean error = false;
				if (!tablename.getText().equals("") ) {
					System.out.println(counter);
					if (counter==0) {
						error=true;
					}
					for(int i = 1 ;i<counter+1;i++) {
						
						if(columnname[i].getText().equals("") ) {
							
							error=true;
						}
						if(datatype[i].getSelectedIndex()== -1  ||datatype[i].getSelectedItem().equals("")) {
							error=true;
						}
					}
					
					
					
					if(!error) {
						
					
					

					String[][] records = new String[counter + 1][2];

					String nametable = tablename.getText();
					name= tablename.getText();

					for (int i = 0; i < counter + 1; i++) {

						records[i][0] = columnname[i].getText();
						records[i][1] = datatype[i].getSelectedItem().toString();

					}

					try {
						Database.newTable(records, nametable);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
					
					

					for(int i=1;i<columnname.length;i++) {
						

					columnname[i].setVisible(false);
					datatype[i].setVisible(false);
					columnname[i].setText("");
					datatype[i].setSelectedIndex(-1);
					tablename.setText("");
					counter=0;
					
					btnViewTable.setVisible(true);
					btnInsert.setVisible(true);
					btnTableData.setVisible(true);
					btnNewButton_3.setVisible(true);
					btnNewButton_3_3.setVisible(true);
					OrderBtn.setVisible(true);
					btnViewTable.doClick();
					
					}
					

				}

				else {
					JOptionPane.showMessageDialog(null, "Column name  or data type is not be null.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				}
				else {
					JOptionPane.showMessageDialog(null, "Table name is not be null.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
				
				newtablepanel.setSize(0,0);
				
		
				
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
		btnNewButton_1.setBackground(new Color(50, 179, 200));
		btnNewButton_1.setBounds(581, 430, 161, 46);
		newtablepanel.add(btnNewButton_1);

		JButton Helpbtn = new JButton("");
		Helpbtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       
		    	  
		    	        try {
		    	            File htmlFile = new File("help/index_help.html");
		    	            Desktop.getDesktop().browse(htmlFile.toURI());
		    	        } catch (IOException ex) {
		    	            ex.printStackTrace();
		    	        }
		    	    }
		    
		    
		});
		Helpbtn.setHorizontalAlignment(SwingConstants.CENTER);
		Helpbtn.setIcon(new ImageIcon(DatabaseGUI.class.getResource("/icon/Button-Help-icon.png")));
		Helpbtn.setBounds(746, 11, 45, 43);
		frmDatabase.getContentPane().add(Helpbtn);
		
		
		
		
	
			}
}
			
			
		
			
