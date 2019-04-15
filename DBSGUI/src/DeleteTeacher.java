import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DeleteTeacher extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteTeacher frame = new DeleteTeacher();
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
	public DeleteTeacher(String school_id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteTeacher = new JLabel("Delete Teacher");
		lblDeleteTeacher.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteTeacher.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDeleteTeacher.setBounds(134, 26, 144, 14);
		contentPane.add(lblDeleteTeacher);
		
		JLabel lblTeacherId = new JLabel("Teacher ID");
		lblTeacherId.setBounds(63, 82, 89, 14);
		contentPane.add(lblTeacherId);
		
		JLabel lblTeacherDeleted = new JLabel("Teacher Deleted");
		lblTeacherDeleted.setBounds(150, 200, 128, 14);
		contentPane.add(lblTeacherDeleted);
		lblTeacherDeleted.setVisible(false);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(218, 79, 116, 20);
		contentPane.add(comboBox);
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();
	        System.out.println("select teacher_id from teacher where school_id = '"+school_id+"'");
	        ResultSet rs = stmt.executeQuery("select teacher_id from teacher where school_id = '"+school_id+"'");
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
		
		JButton btnNewButton = new JButton("Delete Teacher");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tid = comboBox.getSelectedItem().toString();
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();			
			      
			        System.out.println("delete from teacher where teacher_id = '"+tid+"' and school_id ='"+school_id+"'");
			        stmt.executeQuery("delete from teacher where teacher_id = '"+tid+"' and school_id ='"+school_id+"'");
			        lblTeacherDeleted.setVisible(true);
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				   lblTeacherDeleted.setText("Error!");
				   lblTeacherDeleted.setVisible(true);
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
		btnNewButton.setBounds(134, 128, 144, 23);
		contentPane.add(btnNewButton);
		
		
		

	}
}
