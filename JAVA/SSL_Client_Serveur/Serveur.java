
import java.util.Scanner;
import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.*;


public class Serveur {
 
   public static void main(String[] test) {
  
     final SSLServerSocket sslserversocket1, sslserversocket2;
     final SSLSocket socket1, socket2;
     final BufferedReader in1, in2;
     final PrintWriter out1, out2;
     final Scanner sc=new Scanner(System.in);
  
     try {

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        SSLServerSocketFactory factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        sslserversocket1 = (SSLServerSocket) factory.createServerSocket(8080);
        sslserversocket2 = (SSLServerSocket) factory.createServerSocket(5050);
        socket1 = (SSLSocket) sslserversocket1.accept();
        socket2 = (SSLSocket) sslserversocket2.accept();

       out1 = new PrintWriter(socket1.getOutputStream());
       in1 = new BufferedReader (new InputStreamReader (socket1.getInputStream()));
       out2 = new PrintWriter(socket2.getOutputStream());
       in2 = new BufferedReader (new InputStreamReader (socket2.getInputStream()));


       Thread envoi= new Thread(new Runnable() {
          String msg;
          //@Override
          public void run() {
             while(true){
                msg=sc.nextLine();
                String[] splitString = msg.split(":");

                if(splitString[0].compareTo("1")==0){
                  out1.println(splitString[1]);
                  out1.flush();
                }
                else if(splitString[0].compareTo("2")==0){
                  out2.println(splitString[1]);
                  out2.flush();
                }
             }
          }
       });
       envoi.start();
   
       Thread recevoir1= new Thread(new Runnable() {
          String msg ;
          //@Override
          public void run() {
             try {
                msg = in1.readLine();
                //tant que le client est connecté
                while(msg!=null){
                   System.out.println("Client1 : "+msg);
                   msg = in1.readLine();
                }
                //sortir de la boucle si le client a déco
                System.out.println("Client1 déconnecté");
                //fermer le flux et la session socket
                out1.close();
                socket1.close();
                sslserversocket1.close();
             } catch (IOException e) {
                  e.printStackTrace();
             }
         }
      });
      recevoir1.start();

       Thread recevoir2= new Thread(new Runnable() {
          String msg ;
          //@Override
          public void run() {
             try {
                msg = in2.readLine();
              
                //Forward du message de client2 vers client1
                out1.println(msg);
                out1.flush();
                //tant que le client est connecté
                while(msg!=null){
                   System.out.println("Client2 : "+msg);
                   msg = in2.readLine();
                   out1.println(msg);
                   out1.flush();
                }
                //sortir de la boucle si le client a déco
                System.out.println("Client2 déconnecté");
                //fermer le flux et la session socket
                out2.close();
                socket2.close();
                sslserversocket2.close();
             } catch (IOException e) {
                  e.printStackTrace();
             }
         }
      });
      recevoir2.start();

      }catch (IOException e) {
         e.printStackTrace();
      }
   }
}