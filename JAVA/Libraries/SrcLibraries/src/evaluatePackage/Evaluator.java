package evaluatePackage;

public class Evaluator {

	private String CARACTERES_SPECIAUX = "&~#?{([-|`_^@)]=}+°$£*µ%=¨	 !§:/;.,?<>²)'";
	
	private String MINUSCULES = "abcdefghijklmnopqrstuvwxyz";
	
	private String MAJUSCULES = MINUSCULES.toUpperCase();
	
	private String CHIFFRES = "0123456789";

	private int[] marges = new int[]{0, 20, 40, 60, 80, 100, 120, 140, 160, 180};

	public int evaluate(String mp){
		if(mp.length()==0){
			return 0;
		}
		else {
			double qualite = estimerQualite(mp.length(), nombreCaracteresAtester(mp));
			int res=10;
			for(int i=0; i<marges.length; i++){
				if(qualite<marges[i]){
					res=i;
					break;
				}
			}
			return res;
		}
	}
	
	private double estimerQualite(int taille, int nbTest){
		return nbTest*Math.log(taille);
	}

	private int nombreCaracteresAtester(String mp){
		boolean[] contient = new boolean[]{false, false, false, false};
		int res=0;
		for(int i=0; i<mp.length(); i++){
			if(CHIFFRES.contains(mp.charAt(i)+"")){
				if(!contient[0]){
					contient[0]=true;
					res+=CHIFFRES.length();
				}
			}
			if(MINUSCULES.contains(mp.charAt(i)+"")){
				if(!contient[1]){
					contient[1]=true;
					res+=MINUSCULES.length();
				}
			}
			if(MAJUSCULES.contains(mp.charAt(i)+"")){
				if(!contient[2]){
					contient[2]=true;
					res+=MAJUSCULES.length();
				}
			}
			if(CARACTERES_SPECIAUX.contains(mp.charAt(i)+"")){
				if(!contient[3]){
					contient[3]=true;
					res+=CARACTERES_SPECIAUX.length();
				}
			}
		}
		return res;
	}

}