package hospital;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class table extends JFrame {

	private JPanel contentPane;

	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	public table(ArrayList<SQLParameterPasser> sql,ArrayList<MongoParameterPasser> mongo) throws SQLException {
		setTitle("Table");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JTable jt=new JTable();    
		 //jt.setBounds(30,40,200,300);   
		 jt.setPreferredScrollableViewportSize(new Dimension(1000,200));
		 
		DefaultTableModel model = new DefaultTableModel(new String[]{"Patient Id","Name", "Age","Sex","Assigned Doctor Id","Room Number","Address","Phone Number","Disease","Doctor advice","Symptoms","Last medication given"}, 0);
		//DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		int sz=sql.size();
		for(int i=0;i<sz;i++) {
			String pid=Integer.toString(sql.get(i).patient_id);
			String nm=sql.get(i).patient_name;
			String ag=Integer.toString(sql.get(i).patient_age);
			String sx=sql.get(i).patient_sex;
			String doc=Integer.toString(sql.get(i).assigned_doctor_id);
			String rno=Integer.toString(sql.get(i).patient_room_no);
			String addr=mongo.get(i).Address;
			String ph_no=mongo.get(i).Ph_no;
			String dis=mongo.get(i).disease;
			String dr_adv=mongo.get(i).Doctor_advice;
			String sym=mongo.get(i).Symptoms;
			String lst_mdctn=mongo.get(i).last_medication;
			model.addRow(new Object[]{pid,nm,ag,sx,doc,rno,addr,ph_no,dis,dr_adv,sym,lst_mdctn});
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

}