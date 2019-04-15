import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SchoolLogin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchoolLogin frame = new SchoolLogin();
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
	public SchoolLogin(String school_id) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Teacher");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTeacherSchool ateachscl = new AddTeacherSchool(school_id);
				ateachscl.setVisible(true);
			}
		});
		btnNewButton.setBounds(49, 83, 162, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete Teacher");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteTeacher delteach = new DeleteTeacher(school_id);
				delteach.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(233, 83, 162, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("List Teachers");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListTeacher lTeacher = new ListTeacher(school_id);
				lTeacher.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(49, 117, 162, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("School Students");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SchoolStudent schlstud = new SchoolStudent(school_id);
				schlstud.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(233, 117, 162, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Center Students");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CenterStudent cinstud = new CenterStudent(school_id);
				cinstud.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(49, 153, 162, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Add Rooms");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSchoolRoom adsclroom = new AddSchoolRoom(school_id);
				adsclroom.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(233, 151, 162, 23);
		contentPane.add(btnNewButton_5);
		
		JLabel lblSchoolLoginPage = new JLabel("School Login Page");
		lblSchoolLoginPage.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblSchoolLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblSchoolLoginPage.setBounds(103, 30, 230, 23);
		contentPane.add(lblSchoolLoginPage);
		
		JButton btnNewButton_6 = new JButton("Assign Invigilator");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AssignInvigilator assinvi = new AssignInvigilator(school_id);
				assinvi.setVisible(true);
			}
		});
		btnNewButton_6.setBounds(49, 187, 162, 23);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Remove Invigilator");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RemoveInvigilator rmInvi = new RemoveInvigilator(school_id);
				rmInvi.setVisible(true);
			}
			
		});
		btnNewButton_7.setBounds(233, 187, 162, 23);
		contentPane.add(btnNewButton_7);
	}

}
