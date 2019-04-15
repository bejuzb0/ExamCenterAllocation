import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	String pho, add;
	String schoolidret;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentLogin frame = new StudentLogin();
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
	public StudentLogin(String username) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblStudentLoginPage = new JLabel("Student Login Page");
		lblStudentLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentLoginPage.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblStudentLoginPage.setBounds(112, 21, 185, 26);
		contentPane.add(lblStudentLoginPage);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(60, 74, 88, 14);
		contentPane.add(lblStudentId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(60, 99, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblSchoolId = new JLabel("School ID");
		lblSchoolId.setBounds(60, 124, 88, 14);
		contentPane.add(lblSchoolId);
		
		JLabel lblNewLabel = new JLabel("Phone No.");
		lblNewLabel.setBounds(60, 152, 88, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(60, 177, 88, 14);
		contentPane.add(lblAddress);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(60, 202, 88, 14);
		contentPane.add(lblPassword);
		
		JLabel lblId = new JLabel("ID");
		lblId.setText(username);
		lblId.setBounds(158, 74, 158, 14);
		contentPane.add(lblId);
		
		JLabel lblPutname = new JLabel("PutName");
		lblPutname.setBounds(158, 99, 158, 14);
		contentPane.add(lblPutname);
		
		JLabel lblPutsid = new JLabel("PutSID");
		lblPutsid.setBounds(158, 124, 158, 14);
		contentPane.add(lblPutsid);
		
		
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();			
	      
	        System.out.println("select phone_no, address from student where student_id = '"+username+"'");
	        ResultSet rs = stmt.executeQuery("select phone_no, address from student where student_id = '"+username+"'");
	        rs.next();
	        pho = rs.getString("phone_no");
	        add = rs.getString("address");
	        con.close();
	        
		}
		catch (SQLException ex) {
		   System.out.println("Error Connecting");
		}
		catch (ClassNotFoundException ex) {
		        System.out.println(ex);			
		    }
		
		textField = new JTextField();
		textField.setBounds(158, 149, 109, 20);
		textField.setText(pho);
		contentPane.add(textField);
		
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(158, 174, 109, 20);
		contentPane.add(textField_1);
		textField_1.setText(add);
		
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(158, 199, 109, 20);
		contentPane.add(passwordField);
		
		JButton btnUpdatePhone = new JButton("Update Phone");
		btnUpdatePhone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String p_no = textField.getText();
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();			
			      
			        System.out.println("update student set phone_no = "+p_no+" where student_id = '"+username+"'");
			        stmt.executeQuery("update student set phone_no = "+p_no+" where student_id = '"+username+"'");
			      con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
		btnUpdatePhone.setBounds(277, 148, 135, 23);
		contentPane.add(btnUpdatePhone);
		
		JButton btnNewButton = new JButton("Update Address");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String add = textField_1.getText();
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();			
			      
			        System.out.println("update student set address = '"+add+"' where student_id = '"+username+"'");
			        stmt.executeQuery("update student set address = '"+add+"' where student_id = '"+username+"'");
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
		btnNewButton.setBounds(277, 173, 135, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update Password");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pass = new String(passwordField.getPassword());
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();			
			      
			        System.out.println("update login set password = '"+pass+"' where username = '"+username+"'");
			        stmt.executeQuery("update login set password = '"+pass+"' where username = '"+username+"'");
			
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
		btnNewButton_1.setBounds(277, 198, 135, 23);
		contentPane.add(btnNewButton_1);
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();			
	      
	        System.out.println("select name from student student_id = '"+username+"'");
	        ResultSet rs = stmt.executeQuery("select name from student where student_id = '"+username+"'");
	        rs.next();
	        lblPutname.setText(rs.getString("name"));
	        
	        System.out.println("select school_id from student student_id = '"+username+"'");
	        rs = stmt.executeQuery("select school_id from student where student_id = '"+username+"'");
	        rs.next();
	        schoolidret = rs.getString("school_id");
	        lblPutsid.setText(rs.getString("school_id"));
	        
	        
	        con.close();
	        
		}
		catch (SQLException ex) {
		   System.out.println("Error Connecting");
		}
		catch (ClassNotFoundException ex) {
		        System.out.println(ex);			
		    }
		
		JButton btnNewButton_2 = new JButton("Allotment Details");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		StudentAllotmentDetails stdallocdet = new StudentAllotmentDetails(username, schoolidret);
        		stdallocdet.setVisible(true);
        	}
        });
        btnNewButton_2.setBounds(112, 230, 185, 23);
        contentPane.add(btnNewButton_2);
	}
}
