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

public class DeleteSchool extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteSchool frame = new DeleteSchool();
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
	public DeleteSchool() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteSchool = new JLabel("Delete School");
		lblDeleteSchool.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDeleteSchool.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteSchool.setBounds(131, 27, 158, 14);
		contentPane.add(lblDeleteSchool);
		
		JLabel lblSchoolId = new JLabel("School ID");
		lblSchoolId.setBounds(47, 100, 92, 14);
		contentPane.add(lblSchoolId);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(218, 97, 127, 20);
		contentPane.add(comboBox);
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();
	        System.out.println("select school_id from school");
	        ResultSet rs = stmt.executeQuery("select school_id from school");
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
		
		JButton btnNewButton = new JButton("Delete School");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sid = comboBox.getSelectedItem().toString();
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();
			        System.out.println("delete from school where school_id = '"+sid+"'");
			        ResultSet rs = stmt.executeQuery("delete from school where school_id = '"+sid+"'");
			        
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
		btnNewButton.setBounds(131, 165, 127, 23);
		contentPane.add(btnNewButton);
	}

}
