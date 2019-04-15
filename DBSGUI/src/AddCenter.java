import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JComboBox;
public class AddCenter extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	String cid;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCenter frame = new AddCenter();
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
	public AddCenter() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSchoolId = new JLabel("School ID");
		lblSchoolId.setBounds(65, 78, 96, 14);
		contentPane.add(lblSchoolId);
		
		JLabel lblCenterAdded = new JLabel("Center Added !");
		lblCenterAdded.setHorizontalAlignment(SwingConstants.CENTER);
		lblCenterAdded.setBounds(141, 188, 128, 14);
		contentPane.add(lblCenterAdded);
		lblCenterAdded.setVisible(false);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(208, 75, 117, 20);
		contentPane.add(comboBox);
		//Add to ComboBox
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();
	        System.out.println("select school_id from school where center_id = -1");
	        ResultSet rs = stmt.executeQuery("select school_id from school where center_id = -1");
	        while(rs.next()) {
	        	comboBox.addItem(rs.getString("school_id"));
	        }
	        
	        con.close();
	        
		}
		catch (SQLException ex) {
		   System.out.println("Error Connecting");
		   lblCenterAdded.setText("Error!");
		   lblCenterAdded.setVisible(true);
		}
		catch (ClassNotFoundException ex) {
		        System.out.println(ex);			
		    }
		
		////
		
		textField = new JTextField();
		textField.setBounds(208, 110, 117, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		JButton btnAddCenter = new JButton("Add Center");
		btnAddCenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sid = comboBox.getSelectedItem().toString();
				String cid = textField.getText();
				if(cid.equals("")) {
					JOptionPane.showMessageDialog(null,"Center ID can't be empty", "Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
				System.out.println(sid);
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        String s = comboBox.getSelectedItem().toString();			
			        Statement stmt = con.createStatement();
			       
			        System.out.println("update school set center_id = "+cid+" where school_id = '"+sid+"'");
			        stmt.executeQuery("update school set center_id = "+cid+" where school_id = '"+sid+"'");
		
			        
			        
			        JOptionPane.showMessageDialog(null,"Center Added", "Success",JOptionPane.INFORMATION_MESSAGE);
			        repaint();
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				   JOptionPane.showMessageDialog(null,"Couldn't add center", "Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
				}
			}
		});
		btnAddCenter.setBounds(141, 147, 128, 23);
		contentPane.add(btnAddCenter);
		
		JLabel lblAddCenter = new JLabel("Add Center");
		lblAddCenter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAddCenter.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddCenter.setBounds(121, 22, 148, 30);
		contentPane.add(lblAddCenter);
		
		JLabel lblEnterCenterId = new JLabel("Enter center ID");
		lblEnterCenterId.setBounds(65, 113, 96, 14);
		contentPane.add(lblEnterCenterId);
		
		

		
		
	}
}
