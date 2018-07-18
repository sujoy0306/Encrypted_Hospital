package hospital;



public class MongoParameterPasser {
	public String patient_id;
	public String doctor_id;
	public String Specs;
        public String room_no;
        public String p_name;
        public String meds;
        public String disease;
        public String username;
        public String password;
        public String hased_did;
        public String nurse_id;
        public String hashed_nid;
        
        
        
        //encrypted
        public String encrypted_doctor_id;
        public String encrypted_patient_id;
        public String encrypted_disease;
        public String encrypted_meds;
        public String encrypted_room_no;
        public String encrypted_p_name;
	MongoParameterPasser()
	{
		patient_id="null";
		doctor_id="null";
                Specs="null";
                p_name="null";
                room_no="null";
                meds="null";
                disease="null";
	}

}