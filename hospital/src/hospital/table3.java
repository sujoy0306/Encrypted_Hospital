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

public class table3 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public table3(ArrayList<MongoParameterPasser> list) throws Exception {
		setTitle("Table");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 544, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JTable jt=new JTable();    
		 //jt.setBounds(30,40,200,300);   
		 jt.setPreferredScrollableViewportSize(new Dimension(1000,200));
		 
		DefaultTableModel model = new DefaultTableModel(new String[]{"Patient Id","Patient Name", "Room No"}, 0);
		//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		int sz=list.size();
		KEY k=new KEY();
		AES aes=new AES(k.key);
		for(int i=0;i<sz;i++) {
			String pid=(list.get(i).patient_id);
			String nm=list.get(i).encrypted_p_name;
			nm=aes.decrypt(nm);
			String rno=list.get(i).encrypted_room_no;
			rno=aes.decrypt(rno);
			model.addRow(new Object[]{pid,nm,rno});
			jt.setModel(model);
		}
		JScrollPane sp=new JScrollPane(jt);   
		 getContentPane().add(sp,BorderLayout.CENTER);
		//JTable jt=new JTable(buildTableModel(rs));    
		 //jt.setBounds(30,40,200,300);   
		 //jt.setPreferredScrollableViewportSize(new Dimension(1000,200));
		 //JScrollPane sp=new JScrollPane(jt);   
		// getContentPane().add(sp,BorderLayout.CENTER);
		 setVisible(true);
	}
};