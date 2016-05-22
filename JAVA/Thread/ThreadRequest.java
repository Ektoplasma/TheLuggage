package threadPackage;

public class ThreadRequest extends Thread {
		
	   private String arg1 = null;
	   private String arg2 = null;
	   private int interrupt=0;
	 
	   public void setParam(String arg1, String arg2) {
	      this.arg1=arg1;
	      this.arg2=arg2;
	      
	   }
	   
	   public void interruptThread(){
		   this.interrupt=1;
	   }
	   
	   public void run() {
		   while(interrupt==0){
			   System.out.println("Parametres 1 et 2 : "+ this.arg1 +" & "+this.arg2);
			   try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		   }
	   }
}
