package initToken;


public class QEP 
{
	/**************************** Requete AJOUTSITE ****************************/
	public static String EP_AJOUTSITECHECK=
		"/*EP \u0001 0 2 2 4 # 1 1 1 2 r0 2 4 1 17 20 # 4 0 0 1 20 0 ?1 r2 # \u0000 1 1 1 IdGlobal # \u0000*/";

	public static String EP_PREAJOUTSITE=
		"/*EP \u0000 0 1 1 4 # 1 0 0 1 r0 1 4 1 17 # \u0000 1 1 1 IdGlobal # \u0000*/";

	public static String EP_AJOUTSITE=
		"/*EP \u0005 6 1 1 -1 2 ?1 # 5 0 0 1 5 4 1 17 r0 18 ?2 19 ?3 20 ?4 21 ?5 # \u0000*/";

	/**************************** Requete CONNEXION ****************************/
	public static String EP_PRECONNEXION=
		"/*EP \u0002 0 3 3 3 # 1 2 2 3 r0 3 3 1 13 12 14 # 4 1 1 2 12 0 ?1 r2 # 4 0 0 1 14 0 ?2 r3 # \u0000 1 0 1 ValeurPIN # \u0000*/";

	public static String EP_CONNEXION=
		"/*EP \u0003 0 4 4 3 # 1 3 3 4 r0 5 3 1 11 15 12 14 13 # 4 2 2 3 12 0 ?1 r3 # 4 1 1 2 14 0 ?2 r4 # 4 0 0 1 13 0 ?3 r5 # \u0000 2 1 1 IdGlobal 0 2 BackupKey # \u0000*/";

	/**************************** Requete INSCRIPTION ****************************/
	public static String EP_INSCRIPTION=
		"/*EP \u0001 0 2 2 3 # 1 1 1 2 r0 2 3 1 11 12 # 4 0 0 1 12 0 ?1 r2 # \u0000 1 1 1 IdGlobal # \u0000*/";

	public static String EP_PREINSCRIPTION=
		"/*EP \u0000 0 1 1 3 # 1 0 0 1 r0 1 3 1 11 # \u0000 1 1 1 IdGlobal # \u0000*/";

	public static String EP_INSCRIPTIONINSERT=
		"/*EP \u0006 6 1 1 -1 1 ?1 # 5 0 0 1 6 3 1 11 r0 12 ?2 13 ?3 14 ?4 15 ?5 16 ?6 # \u0000*/";

	/**************************** Requete MODIFFORM ****************************/
	public static String EP_PREMODIFFORM=
		"/*EP \u0001 0 2 2 4 # 1 1 1 2 r0 5 4 1 17 18 19 21 20 # 4 0 0 1 20 0 ?1 r5 # \u0000 4 1 1 IdGlobal 0 2 NomFieldLogin 0 3 NomFieldPass 1 4 VersionSite # \u0000*/";

	public static String EP_MODIFFORMDELETE=
		"/*EP \u0001 2 2 2 -1 2 ?1 # 5 1 1 2 3 0 1 0 v14 1 r1 2 v10 # 9 0 0 1 4 r1 # \u0000*/";

	public static String EP_MODIFFORM=
		"/*EP \u0005 6 1 1 -1 2 ?1 # 5 0 0 1 5 4 1 17 r0 18 ?2 19 ?3 20 ?4 21 ?5 # \u0000*/";

	/**************************** Requete MODIFID ****************************/
	public static String EP_PREMODIFID=
		"/*EP \u0001 0 2 2 4 # 1 1 1 2 r0 2 4 1 17 20 # 4 0 0 1 20 0 ?1 r2 # \u0000 1 1 1 IdGlobal # \u0000*/";

	public static String EP_MODIFIDSELECT=
		"/*EP \u0002 0 3 3 5 # 1 2 2 3 r0 6 5 1 22 25 26 27 24 23 # 4 1 1 2 24 0 ?1 r5 # 4 0 0 1 23 0 ?2 r6 # \u0000 4 1 1 IdGlobal 0 2 ValeurLogin 0 3 ValeurPass 1 4 VersionID # \u0000*/";

	public static String EP_MODIFIDDELETE=
		"/*EP \u0001 2 2 2 -1 3 ?1 # 5 1 1 2 3 0 1 0 v15 1 r1 2 v10 # 9 0 0 1 5 r1 # \u0000*/";

	public static String EP_MODIFID=
		"/*EP \u0006 2 4 4 -1 1 ?2 # 2 5 5 -1 2 ?3 # 7 3 3 4 5 # 6 2 2 3 3 ?1 # 5 1 1 2 2 0 0 0 r1 1 r3 # 5 0 0 1 6 5 1 22 r4 23 ?2 24 ?3 25 ?4 26 ?5 27 ?6 # \u0000*/";

	/**************************** Requete REMPLIRFORM ****************************/
	public static String EP_PREREMPLIRFORM=
		"/*EP \u0001 0 2 2 4 # 1 1 1 2 r0 5 4 1 17 18 19 21 20 # 4 0 0 1 20 0 ?1 r5 # \u0000 4 1 1 IdGlobal 0 2 NomFieldLogin 0 3 NomFieldPass 1 4 VersionSite # \u0000*/";

	public static String EP_REMPLIRFORMDELETE=
		"/*EP \u0001 2 2 2 -1 2 ?1 # 5 1 1 2 3 0 1 0 v14 1 r1 2 v10 # 9 0 0 1 4 r1 # \u0000*/";

	public static String EP_REMPLIRFORMINSERT=
		"/*EP \u0005 6 1 1 -1 2 ?1 # 5 0 0 1 5 4 1 17 r0 18 ?2 19 ?3 20 ?4 21 ?5 # \u0000*/";

	public static String EP_REMPLIRFORMW=
		"/*EP \u0001 2 1 1 -1 2 ?1 # 1 0 0 1 r1 2 4 1 18 19 # \u0000 2 0 2 NomFieldLogin 0 3 NomFieldPass # \u0000*/";

	public static String EP_REMPLIRFORMI=
		"/*EP \u0001 0 2 2 5 # 1 1 1 2 r0 4 5 1 24 25 26 23 # 4 0 0 1 23 0 ?1 r4 # \u0000 3 1 1 IdSite 0 2 ValeurLogin 0 3 ValeurPass # \u0000*/";

	public static String EP_REMPLIRFORM=
		"/*EP \u0002 0 5 5 5 # 1 4 4 5 r0 3 5 1 25 26 23 # 4 3 3 4 23 0 ?2 r3 # 1 2 2 3 r0 1 0 0 1 # 1 1 1 2 r5 2 4 1 18 19 # 4 0 0 1 17 0 ?1 r0 # \u0000 4 0 6 NomFieldLogin 0 7 NomFieldPass 0 1 ValeurLogin 0 2 ValeurPass # \u0000*/";

	/**************************** Requete SUPPRID ****************************/
	public static String EP_PRESUPPRID=
		"/*EP \u0001 0 2 2 4 # 1 1 1 2 r0 2 4 1 17 20 # 4 0 0 1 20 0 ?1 r2 # \u0000 1 1 1 IdGlobal # \u0000*/";

	public static String EP_SUPPRID=
		"/*EP \u0002 0 5 5 5 # 1 4 4 5 r0 3 5 1 22 24 23 # 4 3 3 4 24 0 ?1 r2 # 4 2 2 3 23 0 ?2 r3 # 5 1 1 2 3 0 1 0 v15 1 r0 2 v10 # 9 0 0 1 5 r0 # \u0000*/";

	/**************************** Requete SUPPRUSER ****************************/
	public static String EP_PRESUPPRUSER=
		"/*EP \u0001 0 2 2 3 # 1 1 1 2 r0 3 3 1 11 13 12 # 4 0 0 1 12 0 ?1 r3 # \u0000 2 1 1 IdGlobal 0 2 ValeurPIN # \u0000*/";

	public static String EP_SUPPRUSER_USER=
		"/*EP \u0001 2 2 2 -1 1 ?1 # 5 1 1 2 3 0 1 0 v13 1 r1 2 v10 # 9 0 0 1 3 r1 # \u0000*/";

	public static String EP_SUPPRUSER_IDS=
		"/*EP \u0001 0 4 4 5 # 1 3 3 4 r0 2 5 1 22 23 # 4 2 2 3 23 0 ?1 r2 # 5 1 1 2 3 0 1 0 v15 1 r0 2 v10 # 9 0 0 1 5 r0 # \u0000*/";

	/**************************** Requete AJOUTID ****************************/
	public static String EP_PREAJOUTID=
		"/*EP \u0001 0 2 2 4 # 1 1 1 2 r0 2 4 1 17 20 # 4 0 0 1 20 0 ?1 r2 # \u0000 1 1 1 IdGlobal # \u0000*/";

	public static String EP_AJOUTIDCHECK=
		"/*EP \u0004 0 5 5 5 # 1 4 4 5 r0 5 5 1 22 23 24 25 26 # 4 3 3 4 23 0 ?1 r2 # 4 2 2 3 24 0 ?2 r3 # 4 1 1 2 25 0 ?3 r4 # 4 0 0 1 26 0 ?4 r5 # \u0000 1 1 1 IdGlobal # \u0000*/";

	public static String EP_AJOUTIDSELECT=
		"/*EP \u0000 0 1 1 5 # 1 0 0 1 r0 1 5 1 22 # \u0000 1 1 1 IdGlobal # \u0000*/";

	public static String EP_AJOUTID=
		"/*EP \u0006 2 4 4 -1 1 ?2 # 2 5 5 -1 2 ?3 # 7 3 3 4 5 # 6 2 2 3 3 ?1 # 5 1 1 2 2 0 0 0 r1 1 r3 # 5 0 0 1 6 5 1 22 r4 23 ?2 24 ?3 25 ?4 26 ?5 27 ?6 # \u0000*/";

	/**************************** Requete TESTS ****************************/
	public static String EP_TESTUSERS=
		"/*EP \u0000 0 1 1 3 # 1 0 0 1 r0 6 3 1 11 12 13 14 15 16 # \u0000 6 1 1 IdGlobal 0 2 Name 0 3 ValeurPIN 0 4 ValeurFinger 0 5 BackupKey 1 6 VersionUser # \u0000*/";

	public static String EP_TESTWEBSITES=
		"/*EP \u0000 0 1 1 4 # 1 0 0 1 r0 5 4 1 17 18 19 20 21 # \u0000 5 1 1 IdGlobal 0 2 NomFieldLogin 0 3 NomFieldPass 0 4 URLPageForm 1 5 VersionSite # \u0000*/";

	public static String EP_TESTIDS=
		"/*EP \u0000 0 1 1 5 # 1 0 0 1 r0 6 5 1 22 23 24 25 26 27 # \u0000 6 1 1 IdGlobal 1 2 IdUser 1 3 IdSite 0 4 ValeurLogin 0 5 ValeurPass 1 6 VersionID # \u0000*/";

}