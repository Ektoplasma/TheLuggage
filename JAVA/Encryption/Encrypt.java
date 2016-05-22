import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Encrypt {
  
  static String plaintext;
  static String encryptionKey;
  static int max;
  static String choice;
  static String otherChoice;
  static String cipher;

  private static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890`~!@#$%^&*()-=_+[]{}|;':,./<>?";

  public static void main(String [] args) {
    try {
      
      Scanner sc = new Scanner(System.in);

      System.out.println("\nAvez-vous déjà eu une clé pour le chiffrement ? (y/n)");

      choice = sc.nextLine();

      if(choice.compareTo("n")==0){

      do {
        encryptionKey=generateId(32);
      } while(encryptionKey.length()!=32);

        System.out.println("\nencryptionKey : "+encryptionKey+"\n");

        System.out.println("\nVeuillez saisir un mot :");

        String plaintext = sc.nextLine();

        System.out.println("\nClair : " + plaintext);

        cipher = encrypt(plaintext);

        System.out.print("\nChiffré : "+cipher+"\n");

        String decrypted = decrypt(cipher);

        System.out.println("\nDechiffré : " + decrypted+"\n");

      }
      else if(choice.compareTo("y")==0){
        System.out.println("\nQue voulez vous faire ?\n");
        System.out.println("\t1. Chiffrer des données ?\n");
        System.out.println("\t2. Déchiffrer des données ?\n");

        otherChoice=sc.nextLine();

        if(otherChoice.compareTo("1")==0){
          System.out.println("\nencryptionKey ?\n");
          encryptionKey=sc.nextLine();

          System.out.println("\nTexte à chiffrer ?\n");
          plaintext=sc.nextLine();

          cipher = encrypt(plaintext);

          System.out.print("\nChiffré : " + cipher);

        }
        else if(otherChoice.compareTo("2")==0){
          System.out.println("\nencryptionKey ?\n");
          encryptionKey=sc.nextLine();

          System.out.println("\nTexte à déchiffrer ?\n");
          cipher=sc.nextLine();
    
          String decrypted = decrypt(cipher);
          System.out.println("\nDechiffré : " + decrypted+"\n");
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    } 
  }

  public static String encrypt(String value) throws Exception {
    Key key = generateKey();
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
    String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
    return encryptedValue64;     
  }

  public static String decrypt(String value) throws Exception {
    Key key = generateKey();
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte [] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
    byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
    String decryptedValue = new String(decryptedByteValue,"utf-8");
    return decryptedValue;
  }
    
  private static Key generateKey() throws Exception {
    Key key = new SecretKeySpec(encryptionKey.getBytes(),"AES");
    return key;
  }

  public static String generateId(int length) {
    StringBuilder  pass = new StringBuilder (chars.length());
    for (int x = 0; x < length; x++) {
        int i = (int) (Math.random() * chars.length());
        pass.append(chars.charAt(i));
    }
    return pass.toString();
  }
}