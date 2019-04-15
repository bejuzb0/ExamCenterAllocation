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

public class ListCenter extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListCenter frame = new ListCenter();
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
	public ListCenter() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCenterDetails = new JLabel("Center Details");
		lblCenterDetails.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCenterDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblCenterDetails.setBounds(128, 23, 138, 14);
		contentPane.add(lblCenterDetails);
		
		JLabel lblSelectCenter = new JLabel("Select Center");
		lblSelectCenter.setBounds(49, 73, 107, 14);
		contentPane.add(lblSelectCenter);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(221, 70, 138, 20);
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
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(49, 141, 68, 14);
		contentPane.add(lblName);
		lblName.setVisible(false);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(49, 166, 68, 14);
		contentPane.add(lblAddress);
		lblAddress.setVisible(false);
		
		JLabel lblCenterId = new JLabel("Center ID");
		lblCenterId.setBounds(49, 191, 68, 14);
		contentPane.add(lblCenterId);
		lblCenterId.setVisible(false);
		
		JLabel lblName_1 = new JLabel("name1");
		lblName_1.setBounds(189, 141, 176, 14);
		contentPane.add(lblName_1);
		lblName_1.setVisible(false);
		
		JLabel lblName_2 = new JLabel("name2");
		lblName_2.setBounds(189, 166, 176, 14);
		contentPane.add(lblName_2);
		lblName_2.setVisible(false);
		
		JLabel lblName_3 = new JLabel("name3");
		lblName_3.setBounds(189, 191, 176, 14);
		contentPane.add(lblName_3);
		lblName_3.setVisible(false);
		
		
		
		JButton btnShowDetails = new JButton("Show Details");
		btnShowDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name, address;
				String center_id;
				String school_id = comboBox.getSelectedItem().toString();
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();
			        System.out.println("select name, address,center_id from school where school_id = '"+school_id+"'");
			        ResultSet rs = stmt.executeQuery("select name, address,center_id from school where school_id = '"+school_id+"'");
			        rs.next();
			        
			        name = rs.getString("name");
			        address = rs.getString("address");
			        center_id = rs.getInt("center_id")+"";
			        
			        lblName_1.setText(name);
			        lblName_2.setText(address);
			        lblName_3.setText(center_id);
			        
			        lblName.setVisible(true);
			        lblAddress.setVisible(true);
			        lblCenterId.setVisible(true);
			        lblName_1.setVisible(true);
			        lblName_2.setVisible(true);
			        lblName_3.setVisible(true);
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
		btnShowDetails.setBounds(128, 98, 132, 23);
		contentPane.add(btnShowDetails);
		
		
		
	}
}
