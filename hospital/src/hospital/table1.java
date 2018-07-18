package hospital;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class table1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public table1(ArrayList<MongoParameterPasser> list) throws Exception {
		setTitle("Table");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 544, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JTable jt=new JTable();    
		 //jt.setBounds(30,40,200,300);   
		 jt.setPreferredScrollableViewportSize(new Dimension(1000,200));
		 
		DefaultTableModel model = new DefaultTableModel(new String[]{"Patient Id","Patient Name", "Disease"}, 0);
		//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		int sz=list.size();
		KEY k=new KEY();
		AES aes=new AES(k.key);
		for(int i=0;i<sz;i++) {
			String pid=(list.get(i).encrypted_patient_id);
			pid=aes.decrypt(pid);
			String nm=list.get(i).encrypted_p_name;
			nm=aes.decrypt(nm);
			String dis=list.get(i).encrypted_disease;
			dis=aes.decrypt(dis);
			model.addRow(new Object[]{pid,nm,dis});
			jt.setModel(model);
		}
		contentPane.setLayout(null);
		JScrollPane sp=new JScrollPane(jt);   
		sp.setBounds(5, 5, 534, 409);
		 getContentPane().add(sp);
		//JTable jt=new JTable(buildTableModel(rs));    
		 //jt.setBounds(30,40,200,300);   
		 //jt.setPreferredScrollableViewportSize(new Dimension(1000,200));
		 //JScrollPane sp=new JScrollPane(jt);   
		// getContentPane().add(sp,BorderLayout.CENTER);
		 setVisible(true);
	}

}
