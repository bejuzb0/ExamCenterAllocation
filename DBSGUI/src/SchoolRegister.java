import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SchoolRegister extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchoolRegister frame = new SchoolRegister();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public SchoolRegister() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSchoolRegistration = new JLabel("School Registration");
		lblSchoolRegistration.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblSchoolRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchoolRegistration.setBounds(126, 23, 163, 14);
		contentPane.add(lblSchoolRegistration);
		
		JLabel lblSchoolId = new JLabel("School ID");
		lblSchoolId.setBounds(58, 70, 65, 14);
		contentPane.add(lblSchoolId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(58, 95, 65, 14);
		contentPane.add(lblName);
		
		JLabel lblNewLabel = new JLabel("Address");
		lblNewLabel.setBounds(58, 120, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(58, 145, 65, 14);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(203, 67, 111, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(203, 92, 111, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(203, 117, 111, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblSchoolRegisstered = new JLabel("School Registered !");
		lblSchoolRegisstered.setBounds(142, 211, 111, 14);
		contentPane.add(lblSchoolRegisstered);
		lblSchoolRegisstered.setVisible(false);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(203, 142, 111, 20);
		contentPane.add(passwordField);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String school_id = textField.getText();
				String name = textField_1.getText();
				String address = textField_2.getText();
				String pass = new String(passwordField.getPassword());
				
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();			
			        System.out.println("insert into school values('"+school_id+"','"+name+"', '"+address+"', -1)");
			        stmt.executeQuery("insert into school values('"+school_id+"','"+name+"', '"+address+"', -1)");
			        System.out.println("insert into login values ('"+school_id+"', '"+pass+"', 1)");
			        stmt.executeQuery("insert into login values ('"+school_id+"', '"+pass+"', 1)");
			        lblSchoolRegisstered.setVisible(true);
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				   lblSchoolRegisstered.setText("Not Registered!");
				   lblSchoolRegisstered.setVisible(true);
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
		btnRegister.setBounds(132, 177, 121, 23);
		contentPane.add(btnRegister);
		
		
	}
}
