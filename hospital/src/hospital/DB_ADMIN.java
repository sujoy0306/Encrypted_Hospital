package hospital;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class DB_ADMIN extends JFrame {

	private JPanel contentPane;

	
	public DB_ADMIN() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton INSERT_DR_PATIENT = new JButton("INSERT_DR_PATIENT");
		INSERT_DR_PATIENT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Insert();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		INSERT_DR_PATIENT.setBounds(43, 12, 248, 25);
		contentPane.add(INSERT_DR_PATIENT);
		
		JButton DELETE = new JButton("DELETE");
		DELETE.setBounds(235, 189, 117, 25);
		contentPane.add(DELETE);
		
		JButton MODIFY = new JButton("MODIFY");
		MODIFY.setBounds(43, 189, 117, 25);
		contentPane.add(MODIFY);
		
		JButton INSERT_PATIENT = new JButton("INSERT_PATIENT");
		INSERT_PATIENT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Insert_patient();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		INSERT_PATIENT.setBounds(43, 116, 248, 25);
		contentPane.add(INSERT_PATIENT);
		
		JButton INSERT_NR = new JButton("INSERT NURSE PATIENT");
		INSERT_NR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new insert_nurse();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		INSERT_NR.setBounds(43, 65, 248, 25);
		contentPane.add(INSERT_NR);
		setVisible(true);
	}
}
