package hospital;

import java.util.Base64;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class AES {
	private static final String ALGO="AES";
    private byte[] keyvalue;
    public AES(String key)
    {
    	keyvalue=key.getBytes();
    }
    public String encrypt(String data) throws Exception
    {
    	Key key=generateKey();
    	Cipher c=Cipher.getInstance(ALGO);
    	c.init(Cipher.ENCRYPT_MODE, key);
    	byte[] encval=c.doFinal(data.getBytes());
    	String encryptedvalue=Base64.getEncoder().encodeToString(encval);
    	return encryptedvalue;
    }
    public String decrypt(String data) throws Exception
    {
        Key key = generateKey();
        Cipher chiper = Cipher.getInstance(ALGO);
        chiper.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue =Base64.getDecoder().decode(data);
        byte[] decValue = chiper.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private Key generateKey() throws Exception
    {
    	Key key=new SecretKeySpec(keyvalue,ALGO);
    	return key;
    }

}