package hospital;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
/**
 *
 * @author wolf
 */
public class MongoHandler {
    KEY k=new KEY();
    AES aes=new AES(k.key);
    Hasher h=new Hasher();
    private	MongoClient mg;
    MongoHandler()
    {
            mg=new MongoClient("localhost",27017);
    }	
    public boolean insert1(MongoParameterPasser object)
    {
            try
            {               
                String hashedPid=h.HashingFunction(object.patient_id,2);
                DB dbs=mg.getDB("Hospital");
                object.encrypted_p_name=aes.encrypt(object.p_name);
                object.encrypted_room_no=aes.encrypt(object.room_no);
                DBCollection coll=dbs.getCollection("Patient1");
                BasicDBObject patnt=new BasicDBObject("patient_id",hashedPid)
                                .append("name",object.encrypted_p_name)
                                .append("room_no",object.encrypted_room_no);
                coll.insert(patnt);
            }catch(Exception e)
            {
                    return false;
            }
            return true;
    }
    public boolean insert2(MongoParameterPasser object)
    {
            try
            {               
                String hashedPid=h.HashingFunction(object.patient_id,3);
                DB dbs=mg.getDB("Hospital");
                object.encrypted_meds=aes.encrypt(object.meds);
                DBCollection coll=dbs.getCollection("Patient2");
                BasicDBObject patnt=new BasicDBObject("patient_id",hashedPid)
                                .append("medicine",object.encrypted_meds);
                coll.insert(patnt);
            }catch(Exception e)
            {
                    return false;
            }
            return true;
    }
    public boolean insert3(MongoParameterPasser object)
    {
            try
            {               
                String hashedPid=h.HashingFunction(object.patient_id,4);
                DB dbs=mg.getDB("Hospital");
                object.encrypted_disease=aes.encrypt(object.disease);
                DBCollection coll=dbs.getCollection("Patient3");
                BasicDBObject doc1=new BasicDBObject("patient_id",hashedPid)
                                .append("disease",object.encrypted_disease);
                coll.insert(doc1);
            }catch(Exception e)
            {
                    return false;
            }
            return true;
    }
    public boolean insert_doc_patient(MongoParameterPasser object) throws Exception
    {
        
        KEY k=new KEY();
        AES aes=new AES(k.key);
        String enc=aes.encrypt(object.patient_id);
            try
            {
                DB dbs=mg.getDB("Hospital");
                DBCollection coll=dbs.getCollection("doctor");
                BasicDBObject doc1p=new BasicDBObject("d_id",object.hased_did)
                                .append("p_id",enc);
                coll.insert(doc1p);
            }catch(Exception e)
            {
                    return false;
            }
            return true;
    }
    public boolean modify(MongoParameterPasser object) 
	{
		try
		{
                    String meds=object.meds;
                    KEY k=new KEY();    
                    AES aes=new AES(k.key);
                    String enc_med=(String)aes.encrypt(meds);
                    Hasher h=new Hasher();
                    String hashedPid=h.HashingFunction(object.patient_id, 3);
                    DB dbs=mg.getDB("Hospital");
                    DBCollection coll=dbs.getCollection("Patient2");
                    BasicDBObject updatedmed=new BasicDBObject();
                    updatedmed.put("medicine",enc_med);
                    BasicDBObject update=new BasicDBObject();
		    update.put("$set",updatedmed);
		    BasicDBObject old=new BasicDBObject().append("patient_id",hashedPid);
                    coll.update(old, update);
		}catch(Exception e)
		{
			return false;
		}
		return true;
     }
    public boolean insert_nurse_patient(MongoParameterPasser object) throws Exception
    {
        
        KEY k=new KEY();
        AES aes=new AES(k.key);
        String enc=aes.encrypt(object.patient_id);
            try
            {               
                String hashedNid=object.hashed_nid;
                DB dbs=mg.getDB("Hospital");
                DBCollection coll=dbs.getCollection("Nurse_Patient");
                BasicDBObject noc1p=new BasicDBObject("nurse_id",hashedNid)
                		.append("patient_id",enc);
                coll.insert(noc1p);
            }catch(Exception e)
            {
                    return false;
            }
            return true;
    }  
}