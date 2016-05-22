import java.util.Scanner;
import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.*;


public class Client1 {

   public static void main(String[] args) {
      
      final SSLSocket socket;
      final BufferedReader in;
      final PrintWriter out;
      final Scanner sc = new Scanner(System.in);//pour lire à partir du clavier
  
      try {

         Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
         SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
         socket = (SSLSocket) factory.createSocket("localhost", 8080);
   
         //flux pour envoyer
         out = new PrintWriter(socket.getOutputStream());
         //flux pour recevoir
         in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
   
         Thread envoyer = new Thread(new Runnable() {
             String msg;
              //@Override
              public void run() {
                while(true){
                  msg = sc.nextLine();
                  out.println(msg);
                  out.flush();
                }
             }
         });
         envoyer.start();
   
        Thread recevoir = new Thread(new Runnable() {
            String msg;
            //@Override
            public void run() {
               try {
                 msg = in.readLine();
                 while(msg!=null){
                    System.out.println("Serveur : "+msg);
                    msg = in.readLine();
                 }
                 System.out.println("Serveur déconnecté");
                 out.close();
                 socket.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
            }
        });
        recevoir.start();
   
      } catch (IOException e) {
           e.printStackTrace();
      }
  }
}