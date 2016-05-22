import initToken.Projet;

public class Main {
	public static void main(String[] args){
		
		//args[0] = Token port ("COM3, /dev/ttyACM0, ...");
		//args[1] = Persistence mode ("Persistent" or "NotPersistent")
		
		if (args.length == 2){
			try{
				Projet p=new Projet();
				Ihm i = new Ihm();
				p.init(args[0],args[1]);
				i.launch(p);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else {
			System.out.println("usage : java -jar theluggage_token.jar <token_port> <persistence_mode>");
		}
	}
}
