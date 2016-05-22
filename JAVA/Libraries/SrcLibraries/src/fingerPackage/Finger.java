package fingerPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Finger {
    
    public int writeFingerFile(String fingerToWrite, String profile) throws Exception {
          
        File fileLog = new File("fp_"+profile+".txt");
        if(fileLog.length()!=0){
            return 1;
        }
        else {
            FileWriter fw = new FileWriter(fileLog,true);
            fw.write(fingerToWrite);
            fw.close();
            return 0;
        }
    }

    public String readFingerFile (String profile) throws Exception {

        String ligne;
        BufferedReader buffer = new BufferedReader(new FileReader("fp_"+profile+".txt"));

        ligne=buffer.readLine();

        buffer.close();

        return ligne;

   }

    public int verifFingerFile(String profile){
        File fd = new File("fp_"+profile+".txt");
        if(!fd.exists()){
            return 1;
        }
        else {
            return 0;
        }
    }
    
    public void deleteFingerFile(String profile){
    	if(verifFingerFile(profile)==0){
    		File f = new File("fp_"+profile+".txt");
    		f.delete();
    	}
    }

}