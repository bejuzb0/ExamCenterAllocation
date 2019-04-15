import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddSchoolRoom extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSchoolRoom frame = new AddSchoolRoom();
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
	public AddSchoolRoom(String school_id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRoomNo = new JLabel("Room No.");
		lblRoomNo.setBounds(57, 87, 89, 14);
		contentPane.add(lblRoomNo);
		
		JLabel lblCapacity = new JLabel("Capacity");
		lblCapacity.setBounds(57, 123, 89, 14);
		contentPane.add(lblCapacity);
		
		textField = new JTextField();
		textField.setBounds(194, 84, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(194, 120, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAddRooms = new JLabel("Add Rooms");
		lblAddRooms.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddRooms.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAddRooms.setBounds(140, 31, 117, 20);
		contentPane.add(lblAddRooms);
		
		JButton btnAddRoom = new JButton("Add Room");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().contentEquals("") || textField_1.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null,"Enter all details!", "Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
				try {
					
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();			
			        System.out.println("Insert into room values ('"+school_id+"', "+textField.getText()+","+textField_1.getText()+", 0)");
			         stmt.executeQuery("Insert into room values ('"+school_id+"', "+textField.getText()+","+textField_1.getText()+", 0)");
			         JOptionPane.showMessageDialog(null,"Room Added!", "Success",JOptionPane.INFORMATION_MESSAGE);
			         con.close();
			        
				}
				catch (SQLException ex) {
					JOptionPane.showMessageDialog(null,"Couldn't Add Room", "Error",JOptionPane.ERROR_MESSAGE);
					System.out.println("Error Connecting");
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
				}
			}
		});
		btnAddRoom.setBounds(140, 169, 117, 23);
		contentPane.add(btnAddRoom);
		

	}

}
