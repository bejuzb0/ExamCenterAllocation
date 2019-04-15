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
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentInfo extends JFrame {
	String std_id;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentInfo frame = new StudentInfo();
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
	public StudentInfo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentInformation = new JLabel("Student Information");
		lblStudentInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentInformation.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblStudentInformation.setBounds(117, 24, 177, 14);
		contentPane.add(lblStudentInformation);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(43, 78, 102, 14);
		contentPane.add(lblStudentId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(43, 113, 85, 14);
		contentPane.add(lblName);
		lblName.setVisible(false);
		
		JLabel lblSchoolName = new JLabel("School ID");
		lblSchoolName.setBounds(43, 136, 85, 14);
		contentPane.add(lblSchoolName);
		lblSchoolName.setVisible(false);
		
		JLabel lblPhoneNo = new JLabel("Phone No.");
		lblPhoneNo.setBounds(43, 161, 102, 14);
		contentPane.add(lblPhoneNo);
		lblPhoneNo.setVisible(false);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(43, 186, 102, 14);
		contentPane.add(lblAddress);
		lblAddress.setVisible(false);
		
		JLabel lblCenterId = new JLabel("Center ID");
		lblCenterId.setBounds(43, 211, 85, 14);
		contentPane.add(lblCenterId);
		lblCenterId.setVisible(false);
		
		
		JLabel lblRoomNo = new JLabel("Room no.");
		lblRoomNo.setBounds(43, 236, 85, 14);
		contentPane.add(lblRoomNo);
		lblRoomNo.setVisible(false);
		
		
		
		JLabel lblTname = new JLabel("t_name");
		lblTname.setBounds(172, 113, 136, 14);
		contentPane.add(lblTname);
		lblTname.setVisible(false);
		
		JLabel lblTsname = new JLabel("t_ID");
		lblTsname.setBounds(172, 136, 164, 14);
		contentPane.add(lblTsname);
		lblTsname.setVisible(false);
		
		JLabel lblTphoneno = new JLabel("t_phoneno");
		lblTphoneno.setBounds(172, 161, 164, 14);
		contentPane.add(lblTphoneno);
		lblTphoneno.setVisible(false);
		
		JLabel lblTadd = new JLabel("t_add");
		lblTadd.setBounds(172, 186, 186, 14);
		contentPane.add(lblTadd);
		lblTadd.setVisible(false);
		
		JLabel lblTcenterid = new JLabel("t_centerid");
		lblTcenterid.setBounds(172, 211, 164, 14);
		contentPane.add(lblTcenterid);
		lblTcenterid.setVisible(false);
		
		JLabel lblTroomno = new JLabel("t_roomno");
		lblTroomno.setBounds(172, 236, 122, 14);
		contentPane.add(lblTroomno);
		lblTroomno.setVisible(false);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(172, 75, 136, 20);
		contentPane.add(comboBox);
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();
	        System.out.println("select student_id from student");
	        ResultSet rs = stmt.executeQuery("select student_id from student");
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
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				std_id = comboBox.getSelectedItem().toString();
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();
			        System.out.println("select * from student where student_id = '"+std_id+"'");
			        ResultSet rs = stmt.executeQuery("select * from student where student_id = '"+std_id+"'");
			        rs.next();
			      //  System.out.println("Before Assigning");
			        lblTname.setText(rs.getString("name"));
			        lblTsname.setText(rs.getString("school_id"));
			        lblTphoneno.setText(rs.getString("phone_no"));
			        lblTadd.setText(rs.getString("address"));
			        lblTname.setVisible(true);
			        lblTsname.setVisible(true);
			        lblTphoneno.setVisible(true);
			        lblTadd.setVisible(true);
			        lblName.setVisible(true);
			        lblSchoolName.setVisible(true);
			        lblAddress.setVisible(true);
			        lblPhoneNo.setVisible(true);
			        
			        System.out.println("select * from writes where student_id = '"+std_id+"'");
			        rs = stmt.executeQuery("select * from writes where student_id = '"+std_id+"'");
			        rs.next();
			        lblTcenterid.setText(rs.getString("school_id"));
			        lblTroomno.setText(rs.getString("room_no"));
			        lblTcenterid.setVisible(true);
			        lblTroomno.setVisible(true);
			        lblCenterId.setVisible(true);
			        lblRoomNo.setVisible(true);
			    
			        
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
		btnShow.setBounds(321, 74, 89, 23);
		contentPane.add(btnShow);
		
		
	}
}
