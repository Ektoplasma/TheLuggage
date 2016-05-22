package logPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Log {
	
	public void writeLogJAVA(String logToWrite, String profileName) throws Exception {
		      
	         File fileLog = new File("logs_"+profileName+".txt");
	         FileWriter fw = new FileWriter(fileLog,true);
	         SimpleDateFormat dateFormat = new SimpleDateFormat ("E-dd/MM/yyyy-HH:mm:ss-(S)-zzz");
	         Date date = new Date();
	         fw.write(dateFormat.format(date)+" # RQJAVA@ "+logToWrite);
	         fw.write(System.lineSeparator());
	         fw.write("EORQJAVA");
	         fw.write(System.lineSeparator());
	         fw.close();
	      
	   }
	
	public void writeLogADDON(String logToWrite, String profileName) throws Exception {
	      
        File fileLog = new File("logs_"+profileName+".txt");
        FileWriter fw = new FileWriter(fileLog,true);
        SimpleDateFormat dateFormat = new SimpleDateFormat ("E-dd/MM/yyyy-HH:mm:ss-(S)-zzz");
        Date date = new Date();
        fw.write(dateFormat.format(date)+" # RQADDON@ "+logToWrite);
        fw.write(System.lineSeparator());
        fw.write("EORQADDON");
        fw.write(System.lineSeparator());
        fw.close();
     
  }
		   

	public int recupCptJAVA (String fileLog) throws Exception {

	         String ligne;
	         int cpt=0;
	         BufferedReader buffer = new BufferedReader(new FileReader(fileLog));
	         
	         while ((ligne=buffer.readLine())!= null){
	        	 
	        	 if(ligne.contains("EORQJAVA")){
	        		 cpt+=1;
	        	 }
	         }

	         buffer.close();
	         
	         return cpt;

	   }
	
	public int recupCptADDON (String fileLog) throws Exception {

        String ligne;
        int cpt=0;
        BufferedReader buffer = new BufferedReader(new FileReader(fileLog));
        
        while ((ligne=buffer.readLine())!= null){
       	 
       	 if(ligne.contains("EORQADDON")){
       		 cpt+=1;
       	 }
        }

        buffer.close();
        
        return cpt;

  }
	   
	   public String[] recupLogJAVA (String fileLog, int lengthJAVA) throws Exception {

	         String ligne;
	         String[] splitLigneJAVA= new String[lengthJAVA];
	         String line="";
	         int i=0;
	         BufferedReader buffer = new BufferedReader(new FileReader(fileLog));

	         while ((ligne=buffer.readLine())!= null){
	        	if(ligne.contains("EORQJAVA")){
	        		System.out.println(line);
	        		splitLigneJAVA[i]=line;
	        		line="";
	        		i+=1;
	        	}
	        	else if (ligne.contains("EORQADDON")){
	        		//System.out.println(line);
	        		//splitLigneADDON[i]=line;
	        		line="";
	        		//i+=1;
	        	}
	        	else {
	        	 if(ligne.contains(" # RQJAVA@ ")){
	        		 String[] backUpLigne=ligne.split(Pattern.quote(" # RQJAVA@ "));
	        		 //System.out.println(backUpLigne[1]);
	        		 line=line+backUpLigne[1];
	        	 }else {
	        		 line=line+ligne;
	        	 }
	        	}
	        	//System.out.println(line);

	         }

	         buffer.close();
	         
	         return splitLigneJAVA;
	   }
	   
	   public String[] recupLogADDON (String fileLog, int lengthADDON) throws Exception {

	         String ligne;
	         String[] splitLigneADDON= new String[lengthADDON];
	         String line="";
	         int i=0;
	         BufferedReader buffer = new BufferedReader(new FileReader(fileLog));

	         while ((ligne=buffer.readLine())!= null){
	        	if(ligne.contains("EORQJAVA")){
	        		line="";
	        	}
	        	else if (ligne.contains("EORQADDON")){
	        		System.out.println(line);
	        		splitLigneADDON[i]=line;
	        		line="";
	        		i+=1;
	        	}
	        	else {
	        	 if(ligne.contains(" # RQADDON@ ")){
	        		 String[] backUpLigne=ligne.split(Pattern.quote(" # RQADDON@ "));
	        		 //System.out.println(backUpLigne[1]);
	        		 line=line+backUpLigne[1];
	        	 }else {
	        		 line=line+ligne;
	        	 }
	        	}
	        	//System.out.println(line);

	         }

	         buffer.close();
	         
	         return splitLigneADDON;
	   }
	   
		public int verifLogFile(String fileLog){
			File fd = new File(fileLog);
			if(!fd.exists()){
				return 1;
			}
			else if (fd.length()==0){
				return 2;
			}
			else {
				return 0;
			}
		}

}
