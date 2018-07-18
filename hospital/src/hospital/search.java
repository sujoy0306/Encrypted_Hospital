package hospital;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;

/**
 *
 * @author wolf
 */
public class search {
   KEY k=new KEY();
   AES  aes=new AES(k.key);
   private	MongoClient mg;
   	search()
	{
		mg=new MongoClient("localhost",27017);
	}
   public ArrayList<MongoParameterPasser> search1(MongoParameterPasser obj) throws Exception
    {
        
        DB dbs=mg.getDB("Hospital");
        DBCollection coll=dbs.getCollection("doctor");
        ArrayList<MongoParameterPasser> mylist=new ArrayList<>();
        BasicDBObject whereQuery = new BasicDBObject();
	whereQuery.put("d_id",  obj.hased_did);
        Cursor cur=coll.find(whereQuery);
      
        while(cur.hasNext())
        {
            MongoParameterPasser obj5=new MongoParameterPasser();
            DBObject dbo=cur.next();
            MongoParameterPasser obj1=new MongoParameterPasser();
            MongoParameterPasser obj2=new MongoParameterPasser();
            MongoParameterPasser obj3=new MongoParameterPasser();
            obj5.encrypted_patient_id=(String)dbo.get("p_id");
            mylist.add(obj5);

        }

        return mylist;
    
    }
        MongoParameterPasser Patient3(String enc_id) throws Exception{
            KEY key=new KEY();
            AES aes=new AES(key.key);
            Hasher h=new Hasher();
            String dec_ed=(String)aes.decrypt(enc_id);
            String hid=h.HashingFunction(dec_ed,4);
            DB dbs=mg.getDB("Hospital");
            DBCollection coll=dbs.getCollection("Patient3");
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("patient_id", hid);
                Cursor cur=coll.find(whereQuery);
                MongoParameterPasser obj=new MongoParameterPasser();
                
                while(cur.hasNext())
                {
                    DBObject dbo=cur.next();
                    obj.encrypted_disease=(String) dbo.get("disease");
                }             
       return obj;
   }
        
        MongoParameterPasser Patient2(String enc_id) throws Exception{
            KEY key=new KEY();
            AES aes=new AES(key.key);
            Hasher h=new Hasher();
            String dec_ed=(String)aes.decrypt(enc_id);
            String hid=h.HashingFunction(dec_ed,3);
            DB dbs=mg.getDB("Hospital");
            DBCollection coll=dbs.getCollection("Patient2");
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("patient_id", hid);
                Cursor cur=coll.find(whereQuery);
                MongoParameterPasser obj=new MongoParameterPasser();
                
                while(cur.hasNext())
                {
                    DBObject dbo=cur.next();
                    obj.encrypted_meds=(String) dbo.get("medicine");
                    obj.encrypted_patient_id=enc_id;
                }             
       return obj;
   }

        MongoParameterPasser Patient1(String enc_id) throws Exception{
            KEY key=new KEY();
            AES aes=new AES(key.key);
            Hasher h=new Hasher();
            String dec_ed=(String)aes.decrypt(enc_id);
            String hid=h.HashingFunction(dec_ed,2);
            DB dbs=mg.getDB("Hospital");
            DBCollection coll=dbs.getCollection("Patient1");
                BasicDBObject whereQuery = new BasicDBObject();
                whereQuery.put("patient_id", hid);
                Cursor cur=coll.find(whereQuery);
                MongoParameterPasser obj=new MongoParameterPasser();
                
                while(cur.hasNext())
                {
                    DBObject dbo=cur.next();
                    obj.encrypted_p_name=(String) dbo.get("name");
                    obj.encrypted_room_no=(String) dbo.get("room_no");
                }             
       return obj;
   }
MongoParameterPasser show_id_name_disease(String encrypted_id) throws Exception {
    MongoParameterPasser mpp=new MongoParameterPasser();
    DB dbs=mg.getDB("Hospital");
    KEY key=new KEY();
    AES aes=new AES(key.key);
    String dec_id=aes.decrypt(encrypted_id);
    Hasher h=new Hasher();
    String hashP1=h.HashingFunction(dec_id, 2);
    String hashP3=h.HashingFunction(dec_id, 4);
    DBCollection coll3=dbs.getCollection("Patient3");
    DBCollection coll1=dbs.getCollection("Patient1");
    MongoParameterPasser obj=new MongoParameterPasser();
           BasicDBObject whereQuery1 = new BasicDBObject();
           whereQuery1.put("patient_id", hashP1);
           Cursor cur1=coll1.find(whereQuery1);
        while(cur1.hasNext())
        {
            DBObject dbo=cur1.next();
            obj.encrypted_p_name=(String) dbo.get("name");
        }             
           BasicDBObject whereQuery2 = new BasicDBObject();
           whereQuery2.put("patient_id", hashP3);
           Cursor cur2=coll3.find(whereQuery2);
        while(cur2.hasNext())
        {
            DBObject dbo=cur2.next();
            obj.encrypted_disease=(String) dbo.get("disease");
        } 
        obj.encrypted_patient_id=encrypted_id;
       return obj;
    
	}        
	ArrayList<MongoParameterPasser> guest_show(){
		ArrayList<MongoParameterPasser> mylist=new ArrayList<>();
        DB dbs=mg.getDB("Hospital");
        DBCollection coll=dbs.getCollection("Patient1");
        Cursor cur=coll.find();
        while(cur.hasNext())
        {
        	MongoParameterPasser m=new MongoParameterPasser();
        	DBObject dbo=cur.next();
        	m.patient_id=(String) dbo.get("patient_id");
        	m.encrypted_p_name=(String) dbo.get("name");
        	m.encrypted_room_no=(String) dbo.get("room_no");
        	mylist.add(m);
        }
        return mylist;
   
	}
	int count(String disease) {
        KEY key=new KEY();
        AES aes=new AES(key.key);
        DB dbs=mg.getDB("Hospital");
        DBCollection coll=dbs.getCollection("Patient3");
        int c=0;
        String enc;
        try {
			 enc=aes.encrypt(disease);
	           BasicDBObject whereQuery1 = new BasicDBObject();
	           whereQuery1.put("disease", enc);
	           Cursor cur=coll.find(whereQuery1);
	           while(cur.hasNext())
	           {
	        	   DBObject dbo=cur.next();
	        	   c++;
	           }
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return -1;
		}
        return c;
        
	}
	ArrayList<MongoParameterPasser> nurse_pids(String nid){
		ArrayList<MongoParameterPasser> mylist=new ArrayList<>();
		Hasher h=new Hasher();
		String hashid=h.HashingFunction(nid,1);
        DB dbs=mg.getDB("Hospital");
        DBCollection coll=dbs.getCollection("Nurse_Patient");
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("nurse_id",hashid);
        Cursor cur=coll.find(whereQuery);
        while(cur.hasNext())
        {
        	DBObject dbo=cur.next();
        	MongoParameterPasser obj=new MongoParameterPasser();
        	obj.encrypted_patient_id=(String)dbo.get("patient_id");
        	mylist.add(obj);
        }
        return mylist;
		
	}
}