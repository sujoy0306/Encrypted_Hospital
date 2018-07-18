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
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class nr_window extends JFrame {

	private JPanel contentPane;
	private JTextField patientfield;
	private String n_id;

	/**
	 * Create the frame.
	 */
	public nr_window(String s) {
		setTitle("NURSE WINDOW");
		n_id=s;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton show = new JButton("SHOW");
		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String d=JOptionPane.showInputDialog("Enter the Nurse Id");
				KEY k=new KEY();
				AES as=new AES(k.key);
				String dec;
				try {
					 dec=as.decrypt(n_id);
					if(!d.equals(dec)) {
						String ans="Invallid Nurse Id";
						JTextArea output=new JTextArea(16,14);
						output.setText(ans);
						JOptionPane.showMessageDialog(null, ans," dialog box",JOptionPane.ERROR_MESSAGE );
					//	frame.dispose();
					}
					else {
						ArrayList<MongoParameterPasser> mylist=new ArrayList<>();
						search s=new search();
						mylist=s.nurse_pids(dec);
						int n=mylist.size();
						ArrayList<MongoParameterPasser> res=new ArrayList<>();
						for(int i=0;i<n;i++)
						{
							MongoParameterPasser obj1=new MongoParameterPasser();
							obj1=s.Patient1(mylist.get(i).encrypted_patient_id);
							MongoParameterPasser obj2=new MongoParameterPasser();
							MongoParameterPasser obj3=new MongoParameterPasser();
							obj2=s.Patient2(mylist.get(i).encrypted_patient_id);
							obj3.encrypted_p_name=obj1.encrypted_p_name;
							obj3.encrypted_meds=obj2.encrypted_meds;
							obj3.encrypted_patient_id=obj2.encrypted_patient_id;
							res.add(obj3);
						}
						new table2(res);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		show.setBounds(128, 47, 117, 25);
		
		contentPane.add(show);
		
		JButton SHOW_PATIENT_DETAILS = new JButton("SHOW_PATIENT DETAILS");
		SHOW_PATIENT_DETAILS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String d=JOptionPane.showInputDialog("Enter the Nurse Id");
				String pat_id=patientfield.getText();
				KEY k=new KEY();
				AES as=new AES(k.key);
				String enc="";
				try {
					enc = as.encrypt(pat_id);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					String dec=as.decrypt(n_id);
					if(!d.equals(dec)) {
						String ans="Invallid Nurse Id";
						JTextArea output=new JTextArea(16,14);
						output.setText(ans);
						JOptionPane.showMessageDialog(null, ans," dialog box",JOptionPane.ERROR_MESSAGE );
					//	frame.dispose();
					}
					else {
						ArrayList<MongoParameterPasser> mylist=new ArrayList<>();
						search s=new search();
						mylist=s.nurse_pids(dec);
						int n=mylist.size();
						String pid_new=patientfield.getText();
						patientfield.setText("");
						boolean fl=true;
						for(int i=0;i<n;i++) {
							String dec1=as.decrypt(mylist.get(i).encrypted_patient_id);
							if(dec1.equals(pid_new)) {
								fl=false;
								break;
							}
						}
							MongoParameterPasser obj2=new MongoParameterPasser();
							MongoParameterPasser obj1=new MongoParameterPasser();
							MongoParameterPasser res=new MongoParameterPasser();
							search s1=new search();
							obj2=s1.Patient2(enc);
							obj1=s1.Patient1(enc);
							res.encrypted_p_name=obj1.encrypted_p_name;
							res.encrypted_room_no=obj1.encrypted_room_no;
							res.encrypted_patient_id=obj2.encrypted_patient_id;
							if(fl)
							{
								res.encrypted_meds="EH/N/qm0bTaTreUNlLtFqg==";
								new Show_P_Nurse_Final(res);
							}
							else {
								res.encrypted_meds=obj2.encrypted_meds;
								new Show_P_Nurse_Final(res);								
							}

						
						
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		SHOW_PATIENT_DETAILS.setBounds(71, 187, 285, 25);
		contentPane.add(SHOW_PATIENT_DETAILS);
		
		patientfield = new JTextField();
		patientfield.setBounds(221, 115, 114, 19);
		contentPane.add(patientfield);
		patientfield.setColumns(10);
		
		JLabel lblPatientId = new JLabel("PATIENT ID");
		lblPatientId.setBounds(86, 117, 117, 15);
		contentPane.add(lblPatientId);
		setVisible(true);
	}

}
