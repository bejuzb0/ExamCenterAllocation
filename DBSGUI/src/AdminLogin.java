import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminLogin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddCenter = new JButton("Add Center");
		btnAddCenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCenter adcenter = new AddCenter();
				adcenter.setVisible(true);
			}
		});
		btnAddCenter.setBounds(42, 88, 158, 23);
		contentPane.add(btnAddCenter);
		
		JButton btnDeleteCenter = new JButton("Delete Center");
		btnDeleteCenter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteCenter delcenter = new DeleteCenter();
				delcenter.setVisible(true);
			}
		});
		btnDeleteCenter.setBounds(231, 88, 158, 23);
		contentPane.add(btnDeleteCenter);
		
		JButton btnNewButton_1 = new JButton("Center Information");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListCenter listcenter = new ListCenter();
				listcenter.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(231, 130, 158, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Student Information");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentInfo studinf = new StudentInfo();
				studinf.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(42, 130, 158, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblAdminLoginPage = new JLabel("Admin Login Page");
		lblAdminLoginPage.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblAdminLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLoginPage.setBounds(113, 30, 198, 28);
		contentPane.add(lblAdminLoginPage);
		
		JButton btnNewButton = new JButton("Student Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentDelete studdel = new StudentDelete();
				studdel.setVisible(true);
			}
		});
		btnNewButton.setBounds(42, 164, 158, 23);
		contentPane.add(btnNewButton);
	}

}
