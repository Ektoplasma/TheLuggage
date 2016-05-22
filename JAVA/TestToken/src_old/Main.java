import initToken.Projet;

public class Main {
	public static void main(String[] args){
		try{
			Projet p=new Projet();
			Ihm i = new Ihm();
			p.initDesinstall("/dev/ttyACM0");
			i.launch(p);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
