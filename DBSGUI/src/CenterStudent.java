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

public class CenterStudent extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CenterStudent frame = new CenterStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public CenterStudent(String center_id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblCenterStudents = new JLabel("Center Students");
		lblCenterStudents.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCenterStudents.setHorizontalAlignment(SwingConstants.CENTER);
		lblCenterStudents.setBounds(118, 23, 164, 14);
		contentPane.add(lblCenterStudents);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(46, 72, 100, 14);
		contentPane.add(lblStudentId);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(181, 69, 119, 20);
		contentPane.add(comboBox);
		
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();			
	        System.out.println("select * from writes where school_id = '"+center_id+"'");
	        ResultSet rs = stmt.executeQuery("select * from writes where school_id = '"+center_id+"'");
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
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(46, 108, 100, 14);
		contentPane.add(lblName);
		lblName.setVisible(false);
		
		JLabel lblSchoolId = new JLabel("School ID");
		lblSchoolId.setBounds(46, 139, 89, 14);
		contentPane.add(lblSchoolId);
		lblSchoolId.setVisible(false);
		
		JLabel lblPhoneNo = new JLabel("Phone No.");
		lblPhoneNo.setBounds(46, 173, 100, 14);
		contentPane.add(lblPhoneNo);
		lblPhoneNo.setVisible(false);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(46, 198, 100, 14);
		contentPane.add(lblAddress);
		lblAddress.setVisible(false);
		
		JLabel lblSname = new JLabel("sname");
		lblSname.setBounds(181, 108, 101, 14);
		contentPane.add(lblSname);
		lblSname.setVisible(false);
		
		JLabel lblSid = new JLabel("sid");
		lblSid.setBounds(181, 139, 101, 14);
		contentPane.add(lblSid);
		lblSid.setVisible(false);
		
		JLabel lblSphoneno = new JLabel("sphoneno");
		lblSphoneno.setBounds(181, 173, 101, 14);
		contentPane.add(lblSphoneno);
		lblSphoneno.setVisible(false);
		
		JLabel lblSadd = new JLabel("sadd");
		lblSadd.setBounds(181, 198, 112, 14);
		contentPane.add(lblSadd);
		lblSadd.setVisible(false);
		
		JLabel lblRoomNo = new JLabel("Room No");
		lblRoomNo.setBounds(46, 223, 89, 14);
		contentPane.add(lblRoomNo);
		lblRoomNo.setVisible(false);
		
		JLabel lblRoono = new JLabel("roo_no");
		lblRoono.setBounds(181, 223, 101, 14);
		contentPane.add(lblRoono);
		lblRoono.setVisible(false);
		
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
			        lblSid.setText(rs.getString("school_id"));
			        lblSid.setVisible(true);
			        System.out.println(rs.getString("phone_no"));
			        lblSname.setText(rs.getString("name"));
			       lblSphoneno.setText(rs.getString("phone_no"));
			       lblSadd.setText(rs.getString("address"));
			        lblName.setVisible(true);
			        lblPhoneNo.setVisible(true);
			        lblSname.setVisible(true);
			        lblSphoneno.setVisible(true);
			        lblSchoolId.setVisible(true);
			        lblAddress.setVisible(true);
			        lblSadd.setVisible(true);
			        rs = stmt.executeQuery("select * from writes where student_id = '"+comboBox.getSelectedItem().toString()+"'");
			        rs.next();
			        lblRoono.setText(rs.getInt("room_no")+"");
			        lblRoomNo.setVisible(true);
			        lblRoono.setVisible(true);
			
			       
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("No Room");
			//	   lblCid.setVisible(true);
			//	   lblCenterId.setVisible(true);
				   
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
		btnShow.setBounds(310, 68, 89, 23);
		contentPane.add(btnShow);
		
		
	}
}
