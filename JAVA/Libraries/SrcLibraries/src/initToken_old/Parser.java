package initToken;

import org.apache.commons.codec.binary.Base64;

public class Parser {
	
	
	public Parser(){
		
	}
	
	//macro
	public static final int ACTION_ID = 1;
	public static final int ACTION_PARAM_SITE = 2;
	public static final int ACTION_SUPPR = 3;
	
	public static final String MODF = "ModifForm";
	public static final String MODI = "ModifID";
	public static final String ADDS = "AjoutSite";
	public static final String ADDI = "AjoutID";
	public static final String FILLF = "RemplirForm";
	public static final String SUPP = "SupprID";
	public static final String CHKI = "CheckID";
	
	public static final String ACTION_ADD = "INSERT";
	public static final String ACTION_MAJ = "UPDATE";
	public static final String ACTION_DEL = "DELETE";
	public static final String ACTION_SEL ="SELECT";
	
	public static final String SEP_MSG = ":";
	public static final String SEP_PARAM = ";";
	public static final String SEP_ANSER = "|";
	
	public static final String QUERY = "Q";
	public static final String ANSER = "A";
	public static final String NO_ANSER = "X";
	
	public static final String REQUETE_MDP = "query";
	public static final String REQUETE_EXIST = "exist";
	
	
	private String actionType;
	private String action;
	private String URL;
	private String login;
	private String mdp;
	private String fieldLogin;
	private String fieldMdp;
	private boolean anser;
	
	//parse les message sortant contenant les mdp
	public String ParseOut(String[] login, String[] mdp){
		StringBuffer toSend = new StringBuffer();
		
		toSend.append(ANSER); //les messages retournés a l'add-on commence par A (anser) 
		toSend.append(SEP_MSG);
		toSend.append(REQUETE_MDP);
		
		
		if(login.length == mdp.length){
			for (int i = 0; i < mdp.length; i++) {
				toSend.append(SEP_MSG);//séparateur des différents champs 
				toSend.append(login[i]);
				toSend.append(SEP_ANSER);  //separateur pour les parametres (ici les mdp)
 				toSend.append(mdp[i]);
			}
		}
		else{
			return "erreurParseOut";
		}
		
		byte [] encodedToSend = Base64.encodeBase64(toSend.toString().getBytes());
		
		return new String(encodedToSend);
	}
	
	public String ParseOutExist(boolean anser){
		StringBuffer toSend = new StringBuffer();
		
		toSend.append(ANSER);
		toSend.append(SEP_MSG);
		toSend.append(REQUETE_EXIST);
		toSend.append(SEP_MSG);
		toSend.append(anser);
		
		byte [] encodedToSend = Base64.encodeBase64(toSend.toString().getBytes());
		
		return new String (encodedToSend);
	}
	
	
	
	
	//parser pour les messages entrant	
	public void ParseIn(String messageEncoded){
		byte [] decoded = Base64.decodeBase64(messageEncoded);
		String message = new String(decoded);
		
		
		
		flushAll();
		
		String [] msgSplit = message.split(SEP_MSG,4); //parsing selon le separateur :
		
		switch (msgSplit[0]) {
		case QUERY: // requete necessitant une reponse
			anser=true;
			break;
		case NO_ANSER: // requete sans reponse
			anser=false;
			break;
		default:
			System.out.println("erreur ParseIn: message incorrecte");
			return;
		}
		
		switch (msgSplit[1]) {
		case ACTION_ADD: //requete d'insertion pour le SQL
			actionType = msgSplit[1];
			switch (msgSplit[2]) {
			case ADDI: //requete d'ajout identifiant  
				action=msgSplit[2];
				parseParam(msgSplit[3], ACTION_ID);
				//inserer fonction de traitement
				break;

			case ADDS: // requete ajour d'un nouveau site
				action=msgSplit[2];
				parseParam(msgSplit[3], ACTION_PARAM_SITE);
				//inserer fonction de traitement
				break;
				
			default:
				System.out.println("erreur ParseIn: combinaison fausse1");
				return;
			}
			
			break;
		
		case ACTION_SEL: //requete pour recuperer des donnée sur le token
			actionType = msgSplit[1];
			switch (msgSplit[2]) {
			case FILLF: // recupération mdp pour le formulaire
				action=msgSplit[2];
				parseParam(msgSplit[3], ACTION_PARAM_SITE);
				//inserer fonction de traitement
				break;
				
			case CHKI:
				action = msgSplit[2];
					parseParam(msgSplit[3], ACTION_ID);
					

			default:
				System.out.println("erreur ParseIn: combinaison fausse2");
				return;
			}
				
			break;
		
		case ACTION_MAJ: // requetes de mise a jour pour les données
			actionType = msgSplit[1];
			
			switch (msgSplit[2]) {
			case MODF: // mise a jour des info sur le formulaire
				action=msgSplit[2];
				parseParam(msgSplit[3], ACTION_PARAM_SITE);
				//inserer fonction de traitement ou autre proposition
				break;
			
			case MODI: //mise a jour d'identifiant utilisateur
				action=msgSplit[2];
				parseParam(msgSplit[3], ACTION_ID);
				//inserer fonction traitement
				break;
				
			default:
				System.out.println("erreur ParseIn: combinaison fausse3");
				return;
			}
			
			
			break;
			
		case ACTION_DEL: //suppresion de données
			actionType = msgSplit[1];
			
			switch (msgSplit[2]) {
			case SUPP: // supression  d'identifiant utilisateur
				action=msgSplit[2];
				parseParam(msgSplit[3], ACTION_SUPPR);
				//inserser fonction de traitement
				break;

			default:
				System.out.println("erreur ParseIn: combinaison fausse4");
				return;
			}
			
			break;
			
		default:
			System.out.println("Erreur ParseIn: actionType inconnue ");
			return;
		}
		
	
		
	}
	
	// parsing du champs de parametre de 3 façons différentes
	
	public void parseParam(String param , int action){
		
		String [] paramSplit = param.split(SEP_PARAM); // parsing du champs en focntion du separateur ';'
		
		
		switch(action){
		case ACTION_ID:  //ajout mdp ou modif logs
			
			login = paramSplit[0];
			mdp = paramSplit[1];
			URL = paramSplit[2];
			break;
		case ACTION_PARAM_SITE: //changement champ ou ajout site ou remplir form
			URL = paramSplit[2];
			fieldLogin=paramSplit[0];
			fieldMdp=paramSplit[1];
			break;
		case ACTION_SUPPR: //suppression url
			URL=paramSplit[0];
			break;
		default :
			System.out.println("erreur parser argument");
						
		
		}
	
			
	}
	
	
	//cette fonction remet tous les 
	private void flushAll(){
		action=null;
		actionType=null;
		URL=null;
		login=null;
		mdp=null;
		fieldLogin=null;
		fieldMdp=null;
		anser=false;
	}
	

	public boolean getAnser() {
		return anser;
	}

	public String getAction(){
		return action;
	}
	
	public String getActionType(){
		return actionType;
	}
	
	public String getURL(){
		return URL;
	}
	
	public String getLogin(){
		return login;
	}
	
	public String getMdp(){
		return mdp;
	}

	public String getFieldLogin(){
		return fieldLogin;
	}
	
	public String getFieldMdp(){
		return fieldMdp;
	}
}
