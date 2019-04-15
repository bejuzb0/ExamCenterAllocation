import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddTeacherSchool extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTeacherSchool frame = new AddTeacherSchool();
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
	public AddTeacherSchool(String school_id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JLabel lblTeacheradded = new JLabel("TeacherAdded");
		lblTeacheradded.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacheradded.setBounds(140, 219, 90, 14);
		contentPane.add(lblTeacheradded);
		lblTeacheradded.setVisible(false);
		
		JLabel lblAddTeachers = new JLabel("Add Teachers");
		lblAddTeachers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddTeachers.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAddTeachers.setBounds(113, 27, 154, 27);
		contentPane.add(lblAddTeachers);
		
		JLabel lblTeacherId = new JLabel("Teacher ID");
		lblTeacherId.setBounds(65, 76, 118, 14);
		contentPane.add(lblTeacherId);
		
		textField = new JTextField();
		textField.setBounds(210, 73, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(65, 113, 46, 14);
		contentPane.add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(210, 110, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPhoneNo = new JLabel("Phone No.");
		lblPhoneNo.setBounds(65, 151, 106, 14);
		contentPane.add(lblPhoneNo);
		
		textField_2 = new JTextField();
		textField_2.setBounds(210, 148, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAddTeacher = new JButton("Add Teacher");
		btnAddTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = textField.getText();
				String name = textField_1.getText();
				String phone_no = textField_2.getText();
				if(id.equals("") || name.equals("")) {
					JOptionPane.showMessageDialog(null,"Teacher ID or Name empty!", "Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
				try {
					
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();			
			        System.out.println("Insert into teacher values ('"+id+"', '"+school_id+"','"+name+"',"+phone_no+")");
			         stmt.executeQuery("Insert into teacher values ('"+id+"', '"+school_id+"','"+name+"',"+phone_no+")");
			         JOptionPane.showMessageDialog(null,"Teacher Added", "Error",JOptionPane.INFORMATION_MESSAGE);
			         
			        con.close();
			        
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null,"Center ID can't be empty", "Error",JOptionPane.ERROR_MESSAGE);
					System.out.println("Error Connecting");
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
				}
			}
		});
		btnAddTeacher.setBounds(130, 179, 118, 23);
		contentPane.add(btnAddTeacher);
		
	}

}
