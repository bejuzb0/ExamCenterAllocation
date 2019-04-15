import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AssignInvigilator extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignInvigilator frame = new AssignInvigilator();
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
	public AssignInvigilator(String school_id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(213, 90, 117, 20);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(213, 133, 117, 20);
		contentPane.add(comboBox_1);
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();
	        System.out.println("select teacher_id from teacher where school_id = '"+school_id+"' minus select teacher_id from invigilates where school_id ='"+school_id+"'");
	        ResultSet rs = stmt.executeQuery("select teacher_id from teacher where school_id = '"+school_id+"' minus select teacher_id from invigilates where school_id ='"+school_id+"'");
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
		
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();
	        System.out.println("select room_no from room where school_id = '"+school_id+"'");
	        ResultSet rs = stmt.executeQuery("select room_no from room where school_id = '"+school_id+"'");
	        while(rs.next()) {
	        	comboBox_1.addItem(rs.getString("room_no"));
	        }
	        
	        con.close();
	        
		}
		catch (SQLException ex) {
		   System.out.println("Error Connecting");
		}
		catch (ClassNotFoundException ex) {
		        System.out.println(ex);			
		    }
		
		JLabel lblTeacherId = new JLabel("Teacher ID");
		lblTeacherId.setBounds(65, 93, 83, 14);
		contentPane.add(lblTeacherId);
		
		JLabel lblInvigilatorAssigned = new JLabel("Invigilator Assigned !");
		lblInvigilatorAssigned.setBounds(137, 219, 131, 14);
		contentPane.add(lblInvigilatorAssigned);
		lblInvigilatorAssigned.setVisible(false);
		
		JLabel lblRoomNo = new JLabel("Room No");
		lblRoomNo.setBounds(65, 136, 72, 14);
		contentPane.add(lblRoomNo);
		
		JLabel lblNewLabel = new JLabel("Assign Invigilator");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(126, 28, 142, 29);
		contentPane.add(lblNewLabel);
		
		JButton btnAssignInvigilator = new JButton("Assign Invigilator");
		btnAssignInvigilator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teacher_id = comboBox.getSelectedItem().toString();
				String room_no = comboBox_1.getSelectedItem().toString();
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();			
			      
			        System.out.println("Insert into invigilates values ('"+teacher_id+"','"+school_id+"',"+room_no+")");
			        stmt.executeQuery("Insert into invigilates values ('"+teacher_id+"','"+school_id+"',"+room_no+")");
			        JOptionPane.showMessageDialog(null,"Invigilator Assigned !", "Error",JOptionPane.INFORMATION_MESSAGE);
			
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				   JOptionPane.showMessageDialog(null,"Invigilator couldn't be assigned!", "Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
		btnAssignInvigilator.setBounds(126, 174, 142, 23);
		contentPane.add(btnAssignInvigilator);
		

		
		
	}

}
