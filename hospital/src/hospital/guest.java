package hospital;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class guest extends JFrame {

	private JPanel contentPane;
	private JTextField D1;
	private JButton COUNT;
	private JLabel lblNewLabel_1;
	
	
	public guest() {
		setTitle("GUEST WINDOW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton SHOW = new JButton(" SHOW ALL");
		SHOW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ArrayList<MongoParameterPasser> mylist=new ArrayList<>();
			search s=new search();
			mylist=s.guest_show();
			try {
				new table3(mylist);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			}
		});
		SHOW.setBounds(148, 168, 117, 25);
		contentPane.add(SHOW);
		
		D1 = new JTextField();
		D1.setBounds(46, 31, 114, 19);
		contentPane.add(D1);
		D1.setColumns(10);
		COUNT = new JButton("COUNT");
		COUNT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dis=D1.getText();
				search s=new search();
				int c=0;
				c=s.count(dis);
				lblNewLabel_1.setText("No.Patients: "+Integer.toString(c));
				D1.setText("");
			}
		});
		COUNT.setBounds(234, 28, 117, 25);
		contentPane.add(COUNT);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(99, 65, 225, 15);
		contentPane.add(lblNewLabel_1);
		setVisible(true);
	}

}
