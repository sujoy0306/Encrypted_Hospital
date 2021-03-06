package hospital;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JLabel;

public class dr_window extends JFrame {

	protected static final ArrayList<Object> MongoParameterPasser = null;
	private JPanel MEDICINE;
	private JTextField PATIENT_ID;
	private JTextField medfield;
	private String d_id;
	private JTextField PATIENT_FINAL;
	
	public dr_window(String s) {
		setTitle("DOCTOR WINDOW");
		d_id=s;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 326);
		MEDICINE = new JPanel();
		MEDICINE.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(MEDICINE);
		MEDICINE.setLayout(null);
		
		JButton SHOW = new JButton("SHOW");
		SHOW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String d=JOptionPane.showInputDialog("Enter the Doctor Id");
				KEY k=new KEY();
				AES as=new AES(k.key);
				try {
					String dec=as.decrypt(d_id);
					if(!d.equals(dec)) {
						String ans="Invallid Doctor Id";
						JTextArea output=new JTextArea(16,14);
						output.setText(ans);
						JOptionPane.showMessageDialog(null, ans," dialog box",JOptionPane.ERROR_MESSAGE );
					//	frame.dispose();
					}
					else {
						ArrayList<MongoParameterPasser> mylist=new ArrayList<>();
						search s=new search();
						MongoParameterPasser obj=new MongoParameterPasser();
						Hasher h=new Hasher();
						obj.hased_did=h.HashingFunction(dec, 0);
						mylist=s.search1(obj);
						ArrayList<MongoParameterPasser> res=new ArrayList<>();
						int n=mylist.size();
						for(int i=0;i<n;i++)
						{
							MongoParameterPasser o=new MongoParameterPasser();
							MongoParameterPasser resobj=new MongoParameterPasser();
							o=s.Patient1(mylist.get(i).encrypted_patient_id);
							resobj.encrypted_p_name=o.encrypted_p_name;
							o=s.Patient3(mylist.get(i).encrypted_patient_id);
							resobj.encrypted_disease=o.encrypted_disease;
							resobj.encrypted_patient_id=mylist.get(i).encrypted_patient_id;
							res.add(resobj);
						}
						new table1(res);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		SHOW.setBounds(148, 12, 117, 25);
		MEDICINE.add(SHOW);
		PATIENT_ID = new JTextField();
		PATIENT_ID.setBounds(148, 42, 114, 19);
		MEDICINE.add(PATIENT_ID);
		PATIENT_ID.setColumns(10);
		
		medfield = new JTextField();
		medfield.setBounds(158, 73, 203, 25);
		MEDICINE.add(medfield);
		medfield.setColumns(10);
		
		JButton ADD_MED = new JButton("ADD_MED");
		ADD_MED.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String d=JOptionPane.showInputDialog("Enter the Doctor Id");
				KEY k=new KEY();
				AES as=new AES(k.key);
				String dec="";
				try {
				 dec=as.decrypt(d_id);
					if(!d.equals(dec)) {
						String ans="Invallid Doctor Id";
						JTextArea output=new JTextArea(16,14);
						output.setText(ans);
						JOptionPane.showMessageDialog(null, ans," dialog box",JOptionPane.ERROR_MESSAGE );
					//	frame.dispose();
					}
					else
					{
						MongoParameterPasser obj=new MongoParameterPasser();
						obj.meds=medfield.getText();
						obj.patient_id=PATIENT_ID.getText();
						String pid1=obj.patient_id;
						MongoParameterPasser ob1=new MongoParameterPasser();
						Hasher h=new Hasher();
						String h1=h.HashingFunction(dec, 0);
						ob1.hased_did=h1;
						ArrayList<MongoParameterPasser> list1=new ArrayList<>();
						search s=new search();
						list1=s.search1(ob1);
						int n=list1.size();
						boolean ml=false;
						for(int i=0;i<n;i++)
						{
							if(pid1.equals(as.decrypt(list1.get(i).encrypted_patient_id)))
							{
								ml=true;
								break;
							}
						}
						if(!ml)
						{
							String ans="Invalid Patient ID!";
							JTextArea output=new JTextArea(16,14);
							output.setText(ans);
							JOptionPane.showMessageDialog(null, ans," dialog box",JOptionPane.ERROR_MESSAGE );
							medfield.setText("");
							PATIENT_ID.setText("");
						}
						else
						{
							MongoHandler mh=new MongoHandler();
							boolean f=mh.modify(obj);
							if(f)
							{
								String ans="Medicine Added Successfully.";
								JTextArea output=new JTextArea(16,14);
								output.setText(ans);
								JOptionPane.showMessageDialog(null, ans," dialog box",JOptionPane.INFORMATION_MESSAGE );
								medfield.setText("");
								PATIENT_ID.setText("");
							
							}
							else
							{
								String ans="Some Error Ocurred!";
								JTextArea output=new JTextArea(16,14);
								output.setText(ans);
								JOptionPane.showMessageDialog(null, ans," dialog box",JOptionPane.ERROR_MESSAGE );
							
							}
						}

						
						
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		ADD_MED.setBounds(138, 121, 117, 25);
		MEDICINE.add(ADD_MED);
		
		PATIENT_FINAL = new JTextField();
		PATIENT_FINAL.setBounds(165, 198, 114, 19);
		MEDICINE.add(PATIENT_FINAL);
		PATIENT_FINAL.setColumns(10);
		
		JButton SHOW_PATIENT = new JButton("SHOW_PATIENT");
		SHOW_PATIENT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String d=JOptionPane.showInputDialog("Enter the Doctor Id");
				String dec="";
				KEY k=new KEY();
				AES as=new AES(k.key);
				try {
					 dec=as.decrypt(d_id);
					if(!d.equals(dec)) {
						String ans="Invallid Doctor Id";
						JTextArea output=new JTextArea(16,14);
						output.setText(ans);
						JOptionPane.showMessageDialog(null, ans," dialog box",JOptionPane.ERROR_MESSAGE );
					//	frame.dispose();
					}
					else
					{
						Hasher h=new Hasher();
						String hashedid=h.HashingFunction(dec, 0);
						ArrayList<MongoParameterPasser> mylist=new ArrayList<>();
						MongoParameterPasser b=new MongoParameterPasser();
						b.hased_did=hashedid;
						search s=new search();
						mylist=s.search1(b);
						String pid_new=PATIENT_FINAL.getText();
						int n=mylist.size();
						boolean fl=true;
					//	KEY k=new KEY();
					//	AES aes=new AES();
						for(int i=0;i<n;i++) {
							String d1=as.decrypt(mylist.get(i).encrypted_patient_id);
							if(d1.equals(pid_new)) {
								fl=false;
								break;
							}
						}
							MongoParameterPasser obj=new MongoParameterPasser();
							String enc=as.encrypt(pid_new);
							MongoParameterPasser res=new MongoParameterPasser();
							obj=s.Patient1(enc);
							res.encrypted_p_name=obj.encrypted_p_name;
							res.encrypted_room_no=obj.encrypted_room_no;
							res.encrypted_patient_id=enc;
							if(fl)
							{
								res.encrypted_meds="EH/N/qm0bTaTreUNlLtFqg==";
								res.encrypted_disease="EH/N/qm0bTaTreUNlLtFqg==";
								new Show_patient_final(res);
							}
							else {
								obj=s.Patient2(enc);
								res.encrypted_meds=obj.encrypted_meds;
								obj=s.Patient3(enc);
								res.encrypted_disease=obj.encrypted_disease;
								PATIENT_FINAL.setText("");
								new Show_patient_final(res);
							}
							
						}
						
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}
				
			}
		});
		SHOW_PATIENT.setBounds(133, 246, 193, 25);
		MEDICINE.add(SHOW_PATIENT);
		
		JLabel lblNewLabel = new JLabel("PATIENT ID");
		lblNewLabel.setBounds(60, 44, 139, 15);
		MEDICINE.add(lblNewLabel);
		
		JLabel MEDICINE_ = new JLabel("MEDICINE");
		MEDICINE_.setBounds(60, 78, 70, 15);
		MEDICINE.add(MEDICINE_);
		
		JLabel lblNewLabel_1 = new JLabel("PATIENT ID");
		lblNewLabel_1.setBounds(60, 200, 117, 15);
		MEDICINE.add(lblNewLabel_1);
		ADD_MED.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				//	frame.dispose();
					new login();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		setVisible(true);
	}
}
