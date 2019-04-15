import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SchoolStudent extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchoolStudent frame = new SchoolStudent();
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
	public SchoolStudent(String school_id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSchoolStudentInformation = new JLabel("School Student Information");
		lblSchoolStudentInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblSchoolStudentInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchoolStudentInformation.setBounds(87, 23, 251, 14);
		contentPane.add(lblSchoolStudentInformation);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(187, 69, 127, 20);
		contentPane.add(comboBox);
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();			
	        System.out.println("select * from student where school_id = '"+school_id+"'");
	        ResultSet rs = stmt.executeQuery("select * from student where school_id = '"+school_id+"'");
	        while(rs.next()) {
	        	comboBox.addItem(rs.getString("student_id"));
	        }
	        
	       
	        con.close();
	        
		}
		catch (SQLException ex) {
		   System.out.println("Error Connecting");
		   
		}
		catch (ClassNotFoundException ex) {
		        System.out.println(ex);			
		    }
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(55, 72, 78, 14);
		contentPane.add(lblStudentId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(55, 111, 109, 14);
		contentPane.add(lblName);
		lblName.setVisible(false);
		
		JLabel lblPhoneNo = new JLabel("Phone No.");
		lblPhoneNo.setBounds(55, 136, 109, 14);
		contentPane.add(lblPhoneNo);
		lblPhoneNo.setVisible(false);
		
		JLabel lblCenterId = new JLabel("Center ID");
		lblCenterId.setBounds(55, 185, 109, 14);
		contentPane.add(lblCenterId);
		lblCenterId.setVisible(false);
		
		JLabel lblSname = new JLabel("s_name");
		lblSname.setBounds(187, 111, 151, 14);
		contentPane.add(lblSname);
		lblSname.setVisible(false);
		
		JLabel lblPhone = new JLabel("phone");
		lblPhone.setBounds(187, 136, 127, 14);
		contentPane.add(lblPhone);
		lblPhone.setVisible(false);
		
		JLabel lblCid = new JLabel("No Center");
		lblCid.setBounds(187, 185, 127, 14);
		contentPane.add(lblCid);
		lblCid.setVisible(false);
		
		JLabel lblStudentAddress = new JLabel("Student Address");
		lblStudentAddress.setBounds(55, 161, 109, 14);
		contentPane.add(lblStudentAddress);
		lblStudentAddress.setVisible(false);
		
		JLabel lblSadd = new JLabel("sadd");
		lblSadd.setBounds(187, 160, 151, 14);
		contentPane.add(lblSadd);
		lblSadd.setVisible(false);
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();			
			        System.out.println("select * from student where student_id = '"+comboBox.getSelectedItem().toString()+"'");
			        ResultSet rs = stmt.executeQuery("select * from student where student_id = '"+comboBox.getSelectedItem().toString()+"'");
			        rs.next();
			        System.out.println(rs.getString("name"));
			        System.out.println(rs.getString("phone_no"));
			        lblSname.setText(rs.getString("name"));
			        lblPhone.setText(rs.getString("phone_no"));
			        lblSadd.setText(rs.getString("address"));
			        lblName.setVisible(true);
			        lblPhoneNo.setVisible(true);
			        lblSname.setVisible(true);
			        lblPhone.setVisible(true);
			        lblSadd.setVisible(true);
			        lblStudentAddress.setVisible(true);
			        
			        rs = stmt.executeQuery("select * from writes where student_id = '"+comboBox.getSelectedItem().toString()+"'");
			        rs.next();
			        lblCid.setText("No Room");
			        
			        lblCid.setText(rs.getString("school_id"));
			        lblCenterId.setVisible(true);
			        lblCid.setVisible(true);
			       
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("No Room");
				   lblCid.setVisible(false);
				   lblCenterId.setVisible(false);
				   
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
		btnShow.setBounds(324, 68, 89, 23);
		contentPane.add(btnShow);
		
		
		

	}

}
