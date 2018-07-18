package hospital;


import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wolf
 */
public class login_back{
		String id;
        private	MongoClient mg;
        login_back(){
            mg=new MongoClient("localhost",27017);
    }
        public String lookup(String username, String Password){
        DB dbs=mg.getDB("Hospital");
        BasicDBObject andQuery = new BasicDBObject();
        DBCollection coll=dbs.getCollection("LoginTab");
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
	obj.add(new BasicDBObject("username", username));
        obj.add(new BasicDBObject("password",Password));
        andQuery.put("$and",obj);
        Cursor cur=coll.find(andQuery);
        if(cur.hasNext()){
            DBObject dbo=cur.next();
            String role=(String)dbo.get("role");
            id=(String)dbo.get("hid");
            return role;
        }
        else
            return "";
        }
}
