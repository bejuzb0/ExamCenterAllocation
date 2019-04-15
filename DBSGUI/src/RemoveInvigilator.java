import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class RemoveInvigilator extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveInvigilator frame = new RemoveInvigilator();
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
	public RemoveInvigilator(String school_id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblInvigilatorRemoved = new JLabel("Invigilator Removed");
		lblInvigilatorRemoved.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvigilatorRemoved.setBounds(141, 200, 137, 14);
		contentPane.add(lblInvigilatorRemoved);
		lblInvigilatorRemoved.setVisible(false);
		
		
		JLabel lblRemoveInvigilator = new JLabel("Remove Invigilator");
		lblRemoveInvigilator.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblRemoveInvigilator.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveInvigilator.setBounds(126, 25, 189, 19);
		contentPane.add(lblRemoveInvigilator);
		
		JLabel lblTeacherId = new JLabel("Teacher ID");
		lblTeacherId.setBounds(57, 90, 84, 14);
		contentPane.add(lblTeacherId);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(216, 87, 128, 20);
		contentPane.add(comboBox);
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();
	        System.out.println("select teacher_id from invigilates where school_id ='"+school_id+"'");
	        ResultSet rs = stmt.executeQuery("select teacher_id from invigilates where school_id ='"+school_id+"'");
	        while(rs.next()) {
	        	comboBox.addItem(rs.getString("teacher_id"));
	        }
	        
	        con.close();
	        
		}
		catch (SQLException ex) {
		   System.out.println("Error Connecting");
		   lblInvigilatorRemoved.setText("Error Occured !");
		   lblInvigilatorRemoved.setVisible(true);
		}
		catch (ClassNotFoundException ex) {
		        System.out.println(ex);			
		    }
		
		JButton btnRemoveInvigilator = new JButton("Remove Invigilator");
		btnRemoveInvigilator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();
			        System.out.println("delete from invigilates where teacher_id = '"+comboBox.getSelectedItem()+"'");
			        stmt.executeQuery("delete from invigilates where teacher_id = '"+comboBox.getSelectedItem()+"'"); 
			        JOptionPane.showMessageDialog(null,"Invigilator Removed!", "Error",JOptionPane.INFORMATION_MESSAGE);
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				   JOptionPane.showMessageDialog(null,"Couldn't remove Invigilator", "Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
		btnRemoveInvigilator.setBounds(126, 149, 168, 23);
		contentPane.add(btnRemoveInvigilator);
		

	}
}
