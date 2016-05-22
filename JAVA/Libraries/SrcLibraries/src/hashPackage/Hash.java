package hashPackage;

import java.security.MessageDigest;
import java.util.regex.Pattern;

public class Hash {
	public String hashFunction(String stringToHash) throws Exception {

		String generatedString=null;

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(stringToHash.getBytes());
		byte[] bytes = md.digest();
		StringBuilder sb = new StringBuilder();

		for(int i=0; i< bytes.length ;i++){
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		generatedString = sb.toString();
		return generatedString;

	}

	public boolean verifPass(String password, String realPassword) throws Exception {

	/*
	TODO RECHERCHE DE STRING HACHEE EN TANT QUE 'REALPASSWORD' DANS LA BDD SUIVANT LE NOM D'UTILISATEUR + SPLIT DE CETTE STRING SUIVANT '$'
	*/

	String[] splitString = realPassword.split(Pattern.quote("$"));

    String generatedHash=hashFunction(password+splitString[0]);

	if(generatedHash.compareTo(splitString[1])==0){
		return true;
	}
	else {
		return false;
	}

	}

	public String generateSalt() {

	String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	StringBuilder  pass = new StringBuilder (chars.length());

	for (int x = 0; x < 10; x++) {
    	int i = (int) (Math.random() * chars.length());
    	pass.append(chars.charAt(i));
	}

	return pass.toString();
	}

}
