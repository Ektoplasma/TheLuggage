import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Log {

   public static void main(String[] args) {

      Scanner sc=new Scanner(System.in);
      System.out.println("Entrez un profil : ");
      String profile=sc.nextLine();

      /*
      TODO RECHERCHER LE PROFIL DE L'UTILISATEUR QUI FAIT UNE REQUETE DANS LA BDD (USERNAME) EN TANT QUE 'PROFILE'
      */


      String rqSELECT="1@ ";
      String rqUPDATE="2@ ";

      try {
         writeLog(rqSELECT+encrypt("OK"),profile);
         writeLog(rqUPDATE+encrypt("OKOK"),profile);
         writeLog(rqUPDATE+encrypt("OKOKOK"),profile);
         writeLog(rqSELECT+encrypt("OKOKOKOK"),profile);

         recupLog("logs_"+profile+".txt");
      }
      catch (Exception e){
         e.printStackTrace();
      }
}
   
   public static void writeLog(String logToWrite, String profileName) throws Exception {
      
         File fileLog = new File("logs_"+profileName+".txt");
         FileWriter fw = new FileWriter(fileLog,true);
         SimpleDateFormat dateFormat = new SimpleDateFormat ("E-dd/MM/yyyy-HH:mm:ss-(S)-zzz");
         Date date = new Date();
         fw.write(dateFormat.format(date)+" # "+logToWrite);
         fw.write(System.lineSeparator());
         fw.close();
      
   }

   public static void recupLog (String fileLog) throws Exception {

         String ligne;
         BufferedReader buffer = new BufferedReader(new FileReader(fileLog));

         while ((ligne=buffer.readLine())!= null){
            String[] splitLigne=ligne.split(Pattern.quote(" # "));
            String[] backUpLigne=splitLigne[1].split(Pattern.quote("@ "));

            /* Exemple de récupération de requêtes portant l'ID 2 (ici des requêtes UPDATE) */
            if(backUpLigne[0].compareTo("2")==0){
               
               System.out.println(decrypt(backUpLigne[1]));
            }

         }

         buffer.close();

   }
 

   /********************************* CES FONCTIONS SONT DANS LA CLASSE ENCRYPT ***********************************/
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
      String encryptionKey="0123456789012yt6";
      Key key = new SecretKeySpec(encryptionKey.getBytes(),"AES");
      return key;
   }
   /***************************************************************************************************************/

}
