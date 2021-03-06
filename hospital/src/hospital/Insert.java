package hospital;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Insert extends JFrame {

	protected static final Component Insert = null;
	private JPanel contentPane;
	private JTextField Doctor_id;
	private JTextField Patient_id;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;


	public Insert() throws Exception{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Doctor_id = new JTextField();
		Doctor_id.setBounds(247, 22, 114, 19);
		contentPane.add(Doctor_id);
		Doctor_id.setColumns(10);
		
		Patient_id = new JTextField();
		Patient_id.setBounds(247, 82, 114, 19);
		contentPane.add(Patient_id);
		Patient_id.setColumns(10);
		
		JButton ENTER = new JButton("ENTER");
		ENTER.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String did=Doctor_id.getText();
				String pid=Patient_id.getText();
				Hasher h=new Hasher();
				String hid=h.HashingFunction(did, 0);
				MongoParameterPasser mp=new MongoParameterPasser();
				MongoHandler mh=new MongoHandler();
				mp.hased_did=hid;
				mp.patient_id=pid;
				try {
					JOptionPane.showMessageDialog(Insert, "Data Successfully entered");
					Doctor_id.setText("");
					Patient_id.setText("");
					boolean b=mh.insert_doc_patient(mp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ENTER.setBounds(148, 156, 117, 25);
		contentPane.add(ENTER);
		
		lblNewLabel = new JLabel("DOCTOR ID");
		lblNewLabel.setBounds(59, 24, 122, 15);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("PATIENT ID");
		lblNewLabel_1.setBounds(59, 84, 74, 15);
		contentPane.add(lblNewLabel_1);
		setVisible(true);
	}
}
