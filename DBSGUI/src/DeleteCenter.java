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
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DeleteCenter extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteCenter frame = new DeleteCenter();
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
	public DeleteCenter() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCenterDeleted = new JLabel("Center Deleted !");
		lblCenterDeleted.setHorizontalAlignment(SwingConstants.CENTER);
		lblCenterDeleted.setBounds(158, 200, 100, 14);
		contentPane.add(lblCenterDeleted);
		lblCenterDeleted.setVisible(false);
		
		JLabel lblDeleteCenter = new JLabel("Delete Center");
		lblDeleteCenter.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteCenter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDeleteCenter.setBounds(127, 30, 148, 14);
		contentPane.add(lblDeleteCenter);
		
		JLabel lblSchoolId = new JLabel("School ID");
		lblSchoolId.setBounds(62, 73, 74, 14);
		contentPane.add(lblSchoolId);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(28, 105, 137, 20);
		contentPane.add(comboBox);
		
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();
	        System.out.println("select school_id from school where center_id <> -1");
	        ResultSet rs = stmt.executeQuery("select school_id from school where center_id <> -1");
	        while(rs.next()) {
	        	comboBox.addItem(rs.getString("school_id"));
	        }
	        
	        con.close();
	        
		}
		catch (SQLException ex) {
		   System.out.println("Error Connecting");
		}
		catch (ClassNotFoundException ex) {
		        System.out.println(ex);			
		    }
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(248, 105, 129, 20);
		contentPane.add(comboBox_1);
		
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();
	        System.out.println("select center_id from school where center_id <> -1");
	        ResultSet rs = stmt.executeQuery("select center_id from school where center_id <> -1");
	        while(rs.next()) {
	        	comboBox_1.addItem(rs.getInt("center_id"));
	        }
	        
	        con.close();
	        
		}
		catch (SQLException ex) {
		   System.out.println("Error Connecting");
		}
		catch (ClassNotFoundException ex) {
		        System.out.println(ex);			
		    }
		
		JButton btnNewButton = new JButton("Delete by School ID");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sid = comboBox.getSelectedItem().toString();
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        		
			      
			        CallableStatement cstmt = con.prepareCall ("{call center_delete (?)}");

			        cstmt.setString (1, sid);       
			        cstmt.execute ();
			        
			        
			        JOptionPane.showMessageDialog(null,"Center deleted", "Succes",JOptionPane.INFORMATION_MESSAGE);
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				   JOptionPane.showMessageDialog(null,"Center not deleted", "Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
		btnNewButton.setBounds(10, 147, 170, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblCenterId = new JLabel("Center ID");
		lblCenterId.setBounds(280, 73, 74, 14);
		contentPane.add(lblCenterId);
		
		JButton btnNewButton_1 = new JButton("Delete by Center ID");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cid = comboBox_1.getSelectedItem().toString();
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        
			        Statement stmt = con.createStatement();		
			      
			        System.out.println("select school_id from school where center_id= '"+cid+"'");
			        ResultSet rs = stmt.executeQuery("select school_id from school where center_id= '"+cid+"'");
			        rs.next();
			        String r = rs.getString("school_id");
			        System.out.print(r);
			        
			        CallableStatement cstmt = con.prepareCall ("{call center_delete (?)}");

			        cstmt.setString (1, r);       
			        cstmt.execute ();

			        
			        JOptionPane.showMessageDialog(null,"Center Deleted", "Success",JOptionPane.INFORMATION_MESSAGE);
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				   JOptionPane.showMessageDialog(null,"Center ID not deleted", "Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
		btnNewButton_1.setBounds(229, 147, 170, 23);
		contentPane.add(btnNewButton_1);
		

		
		
	}

}
