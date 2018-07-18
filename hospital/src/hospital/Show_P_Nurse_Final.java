package hospital;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Show_P_Nurse_Final extends JFrame {

	private JPanel contentPane;

	
	
	public Show_P_Nurse_Final(MongoParameterPasser ob) {
		setTitle("PATIENT INFO");
		KEY k=new KEY();
		AES aes=new AES(k.key);
		String pid="N/A",pname="N/A",rno="N/A",med="N/A";
		try {
			 pid=aes.decrypt(ob.encrypted_patient_id);
				pname=aes.decrypt(ob.encrypted_p_name);
				rno=aes.decrypt(ob.encrypted_room_no);
				 med=aes.decrypt(ob.encrypted_meds);
		}catch(Exception e)
		{
			String ans="Invallid Patient Id";
			JTextArea output=new JTextArea(16,14);
			output.setText(ans);
			JOptionPane.showMessageDialog(null, ans," dialog box",JOptionPane.ERROR_MESSAGE );
			this.dispose();
			//setVisible(false);
		}

		//String dis=aes.decrypt(ob.encrypted_disease);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PATIENT ID");
		lblNewLabel.setBounds(44, 22, 135, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblPatientName = new JLabel("PATIENT NAME");
		lblPatientName.setBounds(44, 70, 125, 15);
		contentPane.add(lblPatientName);
		
		JLabel lblNewLabel_1 = new JLabel("ROOM NO");
		lblNewLabel_1.setBounds(44, 111, 112, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblMedicine = new JLabel("MEDICINE");
		lblMedicine.setBounds(44, 176, 70, 15);
		contentPane.add(lblMedicine);
		
		JLabel L1 = new JLabel(pid);
		L1.setBounds(257, 22, 70, 15);
		contentPane.add(L1);
		
		JLabel L2 = new JLabel(pname);
		L2.setBounds(257, 70, 70, 15);
		contentPane.add(L2);
		
		JLabel L3 = new JLabel(rno);
		L3.setBounds(257, 111, 70, 15);
		contentPane.add(L3);
		
		JLabel L5 = new JLabel(med);
		L5.setBounds(257, 176, 70, 15);
		contentPane.add(L5);
		setVisible(true);
	}

}
