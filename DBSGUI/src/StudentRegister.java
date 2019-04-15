import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class StudentRegister extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentRegister frame = new StudentRegister();
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
	public StudentRegister() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentRegisteration = new JLabel("Student Registeration");
		lblStudentRegisteration.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblStudentRegisteration.setBounds(128, 11, 172, 19);
		contentPane.add(lblStudentRegisteration);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(45, 50, 78, 14);
		contentPane.add(lblStudentId);
		
		textField = new JTextField();
		textField.setBounds(187, 47, 113, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(45, 81, 78, 14);
		contentPane.add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(187, 78, 113, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSchoolId = new JLabel("School ID");
		lblSchoolId.setBounds(45, 113, 78, 14);
		contentPane.add(lblSchoolId);
		
		textField_2 = new JTextField();
		textField_2.setBounds(187, 107, 113, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Phone No");
		lblNewLabel.setBounds(45, 147, 78, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(45, 172, 78, 14);
		contentPane.add(lblAddress);
		
		textField_3 = new JTextField();
		textField_3.setBounds(187, 138, 113, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(187, 169, 113, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(45, 203, 78, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(187, 200, 113, 20);
		contentPane.add(passwordField);
		

		JLabel lblRegisterationSuccessful = new JLabel("Registeration Successful !");
		lblRegisterationSuccessful.setBounds(260, 235, 133, 14);
		contentPane.add(lblRegisterationSuccessful);
		lblRegisterationSuccessful.setVisible(false);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				String student_id = textField.getText();
				String sname = textField_1.getText();
				String school_id = textField_2.getText();
				String phone = textField_3.getText();
				String saddress = textField_4.getText();
				String pass = new String(passwordField.getPassword());
				if(student_id.equals("") || sname.equals("") || school_id.equals("") || pass.equals("") || saddress.equals("") || phone.equals("") ) {
					JOptionPane.showMessageDialog(null,"Please fill all fields", "Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();			
			      
			        System.out.println("insert into student values ('"+student_id+"','"+school_id+"','"+sname+"',"+phone+",'"+saddress+"')");
			        stmt.executeQuery("insert into student values ('"+student_id+"','"+school_id+"','"+sname+"',"+phone+",'"+saddress+"')");
			        System.out.println("insert into login values ('"+student_id+"','"+pass+"',2)");
			        stmt.executeQuery("insert into login values ('"+student_id+"','"+pass+"',2)");
			        JOptionPane.showMessageDialog(null,"Student Registered. You can Login and choose center.", "Success",JOptionPane.INFORMATION_MESSAGE);
			
			        con.close();
			        
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null,"Some error occured.Please try again.", "Error",JOptionPane.ERROR_MESSAGE);
				   System.out.println("Error Connecting");
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
				}
			}
		});
		btnRegister.setBounds(114, 231, 122, 23);
		contentPane.add(btnRegister);
	
	}

}
