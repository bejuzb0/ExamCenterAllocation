import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.SwingConstants;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class StudentAllotmentDetails extends JFrame {

	private JPanel contentPane;
	String name_temp, add_temp;
	String sc_cen;
	String cen_id;
	int count;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public StudentAllotmentDetails(String student_id, String study_school_id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAllotmentDetails = new JLabel("Allotment Details");
		lblAllotmentDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllotmentDetails.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAllotmentDetails.setBounds(135, 22, 141, 28);
		contentPane.add(lblAllotmentDetails);

		JLabel lblCinid = new JLabel("cin_id");
		lblCinid.setBounds(189, 70, 46, 14);
		contentPane.add(lblCinid);
		lblCinid.setVisible(false);
		
		JButton btnSelectCenter = new JButton("Select Center");
		btnSelectCenter.setBounds(155, 192, 121, 23);
		contentPane.add(btnSelectCenter);
		btnSelectCenter.setVisible(false);
		
		JButton btnShow = new JButton("Show ");
		btnShow.setBounds(318, 66, 78, 23);
		contentPane.add(btnShow);
		btnShow.setVisible(false);
		
		JLabel lblCenterId = new JLabel("Center ID");
		lblCenterId.setBounds(54, 70, 96, 14);
		contentPane.add(lblCenterId);
		lblCenterId.setVisible(false);
		
		JLabel lblCenterName = new JLabel("Center Name");
		lblCenterName.setBounds(54, 106, 96, 14);
		contentPane.add(lblCenterName);
		lblCenterName.setVisible(false);
		
		JLabel lblCenterAddress = new JLabel("Center Address");
		lblCenterAddress.setBounds(54, 131, 96, 14);
		contentPane.add(lblCenterAddress);
		lblCenterAddress.setVisible(false);
		
		JLabel lblCenterStatus = new JLabel("Center_Status");
		lblCenterStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblCenterStatus.setBounds(74, 226, 270, 14);
		contentPane.add(lblCenterStatus);
		lblCenterStatus.setVisible(false);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(189, 67, 119, 20);
		contentPane.add(comboBox);
		comboBox.setVisible(false);
		
		JLabel lblCname = new JLabel("cname");
		lblCname.setBounds(189, 106, 207, 14);
		contentPane.add(lblCname);
		lblCname.setVisible(false);
		
		JLabel lblCadd = new JLabel("cadd");
		lblCadd.setBounds(189, 131, 119, 14);
		contentPane.add(lblCadd);
		lblCadd.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("Room No");
		lblNewLabel.setBounds(54, 156, 96, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);
		
		JLabel lblRoono = new JLabel("roo_no");
		lblRoono.setBounds(189, 156, 46, 14);
		contentPane.add(lblRoono);
		lblRoono.setVisible(false);
		
		/*try {
			// Query for count(*) of student ID in invigilates
			// Put Query in the temp variable
			//If Temp = 0, normal execution
			//else don't show select center, drop down , show button, Add room no and center id label also
		}*/
		
		try {
	        Class.forName("oracle.jdbc.driver.OracleDriver");					
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
	        			
	        Statement stmt = con.createStatement();			
	        ResultSet rs = stmt.executeQuery("select count(*) from writes where student_id ='"+student_id+"'");
	        rs.next();
	        count = rs.getInt("count(*)");
	        if(count != 0) {
	        	rs = stmt.executeQuery("select * from writes where student_id ='"+student_id+"'");
	        	rs.next();
	        	
	        	lblNewLabel.setVisible(true);
	        	lblRoono.setVisible(true);
	        	lblRoono.setText(rs.getInt("room_no")+"");
	        	
	        	String sc_id = rs.getString("school_id");
	        	rs = stmt.executeQuery("select * from school where school_id ='"+sc_id+"'");
	        	rs.next();
	        	cen_id = rs.getString("center_id");
	        	
	        	comboBox.setVisible(false);
	        	lblCenterId.setVisible(true);
	        	lblCinid.setText(cen_id);
	        	lblCinid.setVisible(true);
	        	lblCname.setText(rs.getString("name"));
	        	lblCname.setVisible(true);
	        	lblCenterName.setVisible(true);
	        	lblCadd.setText(rs.getString("address"));
	        	lblCadd.setVisible(true);
	        	lblCenterAddress.setVisible(true);
	        	btnSelectCenter.setVisible(false);
	        	btnShow.setVisible(false);
	        		
	        }
	        
	        else {
	        	lblCinid.setVisible(false);
	        	comboBox.setVisible(true);
	        	lblCenterId.setVisible(true);
	        	
	        	System.out.println("select center_id from school where center_id <> -1 and school_id <> '"+study_school_id+"'");
		        rs = stmt.executeQuery("select center_id from school where center_id <> -1 and school_id <> '"+study_school_id+"'");
		        while(rs.next()) {
		        	comboBox.addItem(rs.getString("center_id"));
		        }
		        
		        btnShow.setVisible(true);
		        btnSelectCenter.setVisible(true);
		        
		        
		        
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
	        
	        
	        con.close();
	        
		}
		catch (SQLException ex) {
		   System.out.println("Error Connecting");
		}
		catch (ClassNotFoundException ex) {
		        System.out.println(ex);			
		    }
		

		
	//	JButton btnSelectCenter = new JButton("Select Center");
		btnSelectCenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        //call writes_insert procedure
			        CallableStatement cstmt = con.prepareCall ("{call writes_insert (?, ?)}");
			        //
			        
			        cstmt.setString (1, student_id);
			        cstmt.setString (2, sc_cen);       
			        cstmt.execute ();

			        //show popup
			        JOptionPane.showMessageDialog(null,"Center Alloted!", "Success",JOptionPane.INFORMATION_MESSAGE);
			        
			        con.close();
			        
				}
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
				   JOptionPane.showMessageDialog(null,"Couldn't allot center", "Error",JOptionPane.ERROR_MESSAGE);
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
				
			}
		});
		
		
		
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cen_id = comboBox.getSelectedItem().toString();
				
				int flag;
				
				try {
			        Class.forName("oracle.jdbc.driver.OracleDriver");					
			        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "aditya", "pass");	
			        			
			        Statement stmt = con.createStatement();
			        
			        //get the school id of the center
			        System.out.println("select school_id from school where center_id = "+cen_id);
			        ResultSet rt = stmt.executeQuery("select school_id from school where center_id = "+cen_id);
			        rt.next();
			        sc_cen = rt.getString("school_id");
			        System.out.println(sc_cen);
			        //
			        			        
			        //execute center_availability function
			        System.out.println("select center_availability('"+sc_cen+"') from dual");
			        rt = stmt.executeQuery("select center_availability('"+sc_cen+"') from dual");
			        //returns flag
			        
			        rt.next();
			        flag = rt.getInt(1);
			        System.out.println(flag);
			        
			        //IF FLAG == 0 the center is available
			        if(flag == 0) {
			        	System.out.println("select name, address from school where center_id = "+cen_id);
			        	ResultSet rs = stmt.executeQuery("select name, address from school where center_id = "+cen_id);
			        	rs.next();
			        	name_temp = rs.getString("name");
			        	add_temp = rs.getString("address");
			        	System.out.println(name_temp);
			        	System.out.println(add_temp);
			        	lblCname.setText(name_temp);
			        	lblCadd.setText(add_temp);
			        	lblCenterName.setVisible(true);
			        	lblCname.setVisible(true);
			        	lblCenterAddress.setVisible(true);
			        	lblCadd.setVisible(true);
			        	lblNewLabel.setVisible(false);
			        	lblRoono.setVisible(false);
			        	lblCenterStatus.setVisible(false);
			        	btnSelectCenter.setVisible(true);
			        }
			        
			        //if flag == 1 i.e center is not available
			        else {
			        	/*System.out.println("select room_no, school_id from writes where student_id = '"+student_id+"'");
			        	rt = stmt.executeQuery("select room_no, school_id from writes where student_id = '"+student_id+"'");
			        	rt.next();
			        	lblNewLabel.setText("Room No");
			        	lblRoono.setText(rt.getInt("room_no")+"");
			        	lblNewLabel.setVisible(true);
			        	lblRoono.setVisible(true);
			        	btnSelectCenter.setVisible(false);*/
			        	lblCenterName.setVisible(false);
			        	lblCname.setVisible(false);
			        	lblCenterAddress.setVisible(false);
						lblCadd.setVisible(false);
						JOptionPane.showMessageDialog(null,"Center Filled!", "Error",JOptionPane.ERROR_MESSAGE);
			        	//lblCenterStatus.setText("Center Not Available!");
			        	//lblCenterStatus.setVisible(true);
			        }
			        con.close();
				}   
				catch (SQLException ex) {
				   System.out.println("Error Connecting");
			//	   lblCenterAdded.setText("Error!");
			//	   lblCenterAdded.setVisible(true);
				}
				catch (ClassNotFoundException ex) {
				        System.out.println(ex);			
				    }
			}
		});
	
		
				
		
	
	
		
		
		
	}
}
