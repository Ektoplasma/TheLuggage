package initToken;

public class QEP_IDs {
	/* EP_QEP class must exist in every application. It allows to interact hardcoded QEPs inside SGBD.
	 * Application QEP start id should be greater than the value of last element of this class. */
	public static class EP_QEP // 1
	{
		public static final int EP_QEP_INSERT = 0;
	}
	
	/* Application QEP ids */
	public static class EP_Project
	{
		
		/**************************** Requete AJOUTSITE ****************************/
		public static final int EP_AJOUTSITECHECK = EP_QEP.EP_QEP_INSERT + 1; // Application QEP start id
		public static final int EP_PREAJOUTSITE = EP_AJOUTSITECHECK + 1;
		public static final int EP_AJOUTSITE = EP_PREAJOUTSITE + 1;
		/**************************** Requete CONNEXION ****************************/
		public static final int EP_PRECONNEXION = EP_AJOUTSITE + 1;
		public static final int EP_CONNEXION = EP_PRECONNEXION + 1;
		/**************************** Requete INSCRIPTION ****************************/
		public static final int EP_INSCRIPTION= EP_CONNEXION + 1;
		public static final int EP_PREINSCRIPTION = EP_INSCRIPTION + 1;
		public static final int EP_INSCRIPTIONINSERT = EP_PREINSCRIPTION + 1;
		/**************************** Requete MODIFFORM ****************************/
		public static final int EP_PREMODIFFORM = EP_INSCRIPTIONINSERT + 1;
		public static final int EP_MODIFFORMDELETE = EP_PREMODIFFORM + 1;
		public static final int EP_MODIFFORM = EP_MODIFFORMDELETE + 1;
		/**************************** Requete MODIFID ****************************/
		public static final int EP_PREMODIFID = EP_MODIFFORM + 1;
		public static final int EP_MODIFIDSELECT = EP_PREMODIFID + 1;
		public static final int EP_MODIFIDDELETE = EP_MODIFIDSELECT + 1;
		public static final int EP_MODIFID = EP_MODIFIDDELETE + 1;
		/**************************** Requete REMPLIRFORM ****************************/
		public static final int EP_PREREMPLIRFORM = EP_MODIFID + 1;
		public static final int EP_REMPLIRFORMDELETE = EP_PREREMPLIRFORM + 1;
		public static final int EP_REMPLIRFORMINSERT = EP_REMPLIRFORMDELETE + 1;
		public static final int	EP_REMPLIRFORMW = EP_REMPLIRFORMINSERT + 1;
		public static final int EP_REMPLIRFORMI = EP_REMPLIRFORMW + 1;
		public static final int EP_REMPLIRFORM = EP_REMPLIRFORMI + 1;
		/**************************** Requete SUPPRID ****************************/
		public static final int EP_PRESUPPRID = EP_REMPLIRFORM + 1;
		public static final int EP_SUPPRID = EP_PRESUPPRID + 1;
		/**************************** Requete SUPPRUSER ****************************/
		public static final int EP_PRESUPPRUSER = EP_SUPPRID + 1;
		public static final int EP_SUPPRUSER_USER = EP_PRESUPPRUSER + 1;
		public static final int EP_SUPPRUSER_IDS = EP_SUPPRUSER_USER + 1;
		/**************************** Requete AJOUTID ****************************/
		public static final int EP_PREAJOUTID = EP_SUPPRUSER_IDS + 1;
		public static final int EP_AJOUTIDCHECK = EP_PREAJOUTID + 1;
		public static final int EP_AJOUTIDSELECT = EP_AJOUTIDCHECK + 1;
		public static final int EP_AJOUTID = EP_AJOUTIDSELECT + 1;
		/**************************** Requetes TESTS **********************************/
		public static final int EP_TESTUSERS = EP_AJOUTID + 1;
		public static final int EP_TESTWEBSITES = EP_TESTUSERS + 1;
		public static final int EP_TESTIDS = EP_TESTWEBSITES + 1;
		
	}

	
}

