import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentDelete extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDelete frame = new StudentDelete();
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
	public StudentDelete() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentDelete = new JLabel("Student Delete ");
		lblStudentDelete.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblStudentDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentDelete.setBounds(112, 25, 168, 14);
		contentPane.add(lblStudentDelete);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(44, 73, 101, 14);
		contentPane.add(lblStudentId);
		

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(112, 189, 206, 14);
		contentPane.add(lblStatus);
		lblStatus.setVisible(false);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(201, 70, 147, 20);
		contentPane.add(comboBox);
		
		JButton btnDeleteStudent = new JButton("Delete Student");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();
			        
			        CallableStatement cstmt = con.prepareCall ("{call student_delete (?)}");

			        cstmt.setString (1, comboBox.getSelectedItem().toString());      
			        cstmt.execute();
			        JOptionPane.showMessageDialog(null,"Student Deleted", "Success",JOptionPane.INFORMATION_MESSAGE);
			      
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				   JOptionPane.showMessageDialog(null,"Center ID can't be empty", "Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
		btnDeleteStudent.setBounds(143, 123, 137, 23);
		contentPane.add(btnDeleteStudent);
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();			
	        System.out.println("select * from student");
	        ResultSet rs = stmt.executeQuery("select * from student");
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
		
	}

}
