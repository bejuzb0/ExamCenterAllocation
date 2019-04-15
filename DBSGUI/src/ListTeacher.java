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
import java.util.Base64;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListTeacher extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListTeacher frame = new ListTeacher();
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
	public ListTeacher(String school_id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeacherList = new JLabel("Teacher List");
		lblTeacherList.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacherList.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblTeacherList.setBounds(136, 11, 145, 14);
		contentPane.add(lblTeacherList);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(183, 62, 120, 20);
		contentPane.add(comboBox);
		
		JLabel lblTeacherId = new JLabel("Teacher ID");
		lblTeacherId.setBounds(57, 65, 98, 14);
		contentPane.add(lblTeacherId);
		
		
		JLabel lblTeacherName = new JLabel("Teacher Name");
		lblTeacherName.setBounds(57, 98, 98, 14);
		contentPane.add(lblTeacherName);
		lblTeacherName.setVisible(false);
		
		JLabel lblPhoneNo = new JLabel("Phone No.");
		lblPhoneNo.setBounds(57, 133, 98, 14);
		contentPane.add(lblPhoneNo);
		lblPhoneNo.setVisible(false);
		
		JLabel lblInvigilates = new JLabel("Invigilates");
		lblInvigilates.setBounds(57, 169, 98, 14);
		contentPane.add(lblInvigilates);
		lblInvigilates.setVisible(false);
		
		JLabel lblNoRoom = new JLabel("No room");
		lblNoRoom.setBounds(183, 169, 98, 14);
		contentPane.add(lblNoRoom);
		lblNoRoom.setVisible(false);
		
		JLabel lblPhoneno = new JLabel("phone_no");
		lblPhoneno.setBounds(183, 133, 120, 14);
		contentPane.add(lblPhoneno);
		lblPhoneno.setVisible(false);
		
		JLabel lblTname = new JLabel("t_name");
		lblTname.setBounds(183, 98, 100, 14);
		contentPane.add(lblTname);
		lblTname.setVisible(false);
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();			
			        System.out.println("select * from teacher where teacher_id = '"+comboBox.getSelectedItem().toString()+"'");
			        ResultSet rs = stmt.executeQuery("select * from teacher where teacher_id = '"+comboBox.getSelectedItem().toString()+"'");
			        rs.next();
			        lblTname.setText(rs.getString("name"));
			        lblPhoneno.setText(rs.getString("phone_no"));
			        
			        lblTname.setVisible(true);
			        lblPhoneno.setVisible(true);
			        lblPhoneNo.setVisible(true);
			        lblTeacherName.setVisible(true);
			        
			        
			        rs = stmt.executeQuery("select * from invigilates where teacher_id = '"+comboBox.getSelectedItem().toString()+"'");
			        rs.next();
			        lblNoRoom.setText("No Room");
			        
			        lblNoRoom.setText(rs.getInt("room_no")+"");
			        lblNoRoom.setVisible(true);
			        lblInvigilates.setVisible(true);
			       
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("No Room");
				   lblNoRoom.setVisible(false);
				   lblInvigilates.setVisible(false);
				   
				   
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
				
			}
		});
		btnShow.setBounds(313, 61, 89, 23);
		contentPane.add(btnShow);
		

		
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();			
	        System.out.println("select * from teacher where school_id = '"+school_id+"'");
	        ResultSet rs = stmt.executeQuery("select * from teacher where school_id = '"+school_id+"'");
	        while(rs.next()) {
	        	comboBox.addItem(rs.getString("teacher_id"));
	        }
	        
	       
	        con.close();
	        
		}
		catch (SQLException ex) {
		   System.out.println("Error Connecting");
		   
		}
		catch (ClassNotFoundException ex) {
		        System.out.println(ex);			
		    }
		
	}
}
