import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Font;
public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
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
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(52, 94, 116, 14);
		contentPane.add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(225, 91, 116, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(52, 137, 86, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(225, 134, 116, 20);
		contentPane.add(passwordField);
		
		JLabel lblInvalidUsernamepassword = new JLabel("Invalid Username/Password");
		lblInvalidUsernamepassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvalidUsernamepassword.setBounds(76, 236, 235, 14);
		contentPane.add(lblInvalidUsernamepassword);
		lblInvalidUsernamepassword.setVisible(false);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String pass = new String(passwordField.getPassword());
				int access = -1;
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();			
			      
			        System.out.println("SELECT access_id FROM login WHERE username = '"+username+"' and password = '"+pass+"'");
			        ResultSet rs = stmt.executeQuery("SELECT access_id FROM login WHERE username = '"+username+"' and password = '"+pass+"'");
			        rs.next();
			
			        access = rs.getInt("access_id");
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
				if(access == 0) {
			        AdminLogin adlogin = new AdminLogin();
					adlogin.setVisible(true);
				}
				else if(access == 1) {
					SchoolLogin scllogin = new SchoolLogin(username);
					scllogin.setVisible(true);
				}
				else if (access == 2) {
					StudentLogin stdlogin = new StudentLogin(username);
					stdlogin.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null,"Wrong Username/Password", "Error",JOptionPane.ERROR_MESSAGE);
				} 
			}
		});
		btnLogin.setBounds(139, 171, 116, 23);
		contentPane.add(btnLogin);
		
		JButton btnNewButton = new JButton("Student Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentRegister stdreg = new StudentRegister();
				stdreg.setVisible(true);
			}
		});
		btnNewButton.setBounds(52, 206, 137, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("School Register");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SchoolRegister sclreg = new SchoolRegister();
				sclreg.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(215, 206, 137, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblExamCenterAllotment = new JLabel("Exam Center Allotment");
		lblExamCenterAllotment.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblExamCenterAllotment.setHorizontalAlignment(SwingConstants.CENTER);
		lblExamCenterAllotment.setBounds(112, 33, 199, 20);
		contentPane.add(lblExamCenterAllotment);
		

	}
}
