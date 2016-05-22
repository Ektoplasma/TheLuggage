package encryptPackage;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encrypt {
	
	  	  public String encrypt(String value, String encryptionKey) throws Exception {
		    Key key = new SecretKeySpec(encryptionKey.getBytes(),"AES");
		    Cipher cipher = Cipher.getInstance("AES");
		    cipher.init(Cipher.ENCRYPT_MODE, key);
		    byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
		    String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
		    return encryptedValue64;     
		  }

		  public String decrypt(String value, String encryptionKey) throws Exception {
		    Key key = new SecretKeySpec(encryptionKey.getBytes(),"AES");
		    Cipher cipher = Cipher.getInstance("AES");
		    cipher.init(Cipher.DECRYPT_MODE, key);
		    byte [] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
		    byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
		    String decryptedValue = new String(decryptedByteValue,"utf-8");
		    return decryptedValue;
		  }
		  
		  public String generateEncryptKey() {
			    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			    StringBuilder  pass = new StringBuilder (chars.length());
			    for (int x = 0; x < 32; x++) {
			        int i = (int) (Math.random() * chars.length());
			        pass.append(chars.charAt(i));
			    }
			    return pass.toString();
		  }
		  
		  public String generateValidKey(String str){
			  
			  StringBuilder sb = new StringBuilder ();
			  sb.append(str);
			  
			  if(str.length()<16){
				  for(int i=0; i<16-str.length();i++){
					  sb.append("\0");
				  }
			  }
			  else if(str.length()>16 && str.length()<32){
				  for(int i=0; i<32-str.length();i++){
					  sb.append("\0");
				  }
			  }
	     	  return sb.toString();
			  
		  }
		  

}
