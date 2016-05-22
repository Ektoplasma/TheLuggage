package org.inria.dmsp;
public class QEP {
public static final String META =
	"TAB_DESC,6\n"+
	"0	LogDeleted	12\n"+
	"1	UpdateLog	512\n"+
	"2	QEP	512\n"+
	"3	Users	216\n"+
	"4	Websites	264\n"+
	"5	IDs	120\n"+
	"COL_DESC,28\n"+
	"0	0	TabId	4	1	0\n"+
	"1	0	TuplePos	4	1	4\n"+
	"2	0	Flag	4	1	8\n"+
	"3	1	TabId	4	1	0\n"+
	"4	1	TuplePos	4	1	4\n"+
	"5	1	TupleSize	4	1	8\n"+
	"6	1	CompleteTup	500	0	12\n"+
	"7	2	IdGlobal	4	1	0\n"+
	"8	2	QEPStr	460	0	4\n"+
	"9	2	SQLStr	24	9	464\n"+
	"10	2	ExplainStr	24	9	488\n"+
	"11	3	IdGlobal	4	1	0\n"+
	"12	3	Name	52	0	4\n"+
	"13	3	ValeurPIN	52	0	56\n"+
	"14	3	ValeurFinger	52	0	108\n"+
	"15	3	BackupKey	52	0	160\n"+
	"16	3	VersionUser	4	1	212\n"+
	"17	4	IdGlobal	4	1	0\n"+
	"18	4	NomFieldLogin	52	0	4\n"+
	"19	4	NomFieldPass	52	0	56\n"+
	"20	4	URLPageForm	152	0	108\n"+
	"21	4	VersionSite	4	1	260\n"+
	"22	5	IdGlobal	4	1	0\n"+
	"23	5	IdUser	4	1	4\n"+
	"24	5	IdSite	4	1	8\n"+
	"25	5	ValeurLogin	52	0	12\n"+
	"26	5	ValeurPass	52	0	64\n"+
	"27	5	VersionID	4	1	116\n"+
	"FK_DESC,2\n"+
	"5	23	3	11\n"+
	"5	24	4	17\n"+
	"SKT_DESC,1\n"+
	"0	5	IDs	8\n"+
	"SKT_COL_DESC,2\n"+
	"0	0	3	11	1\n"+
	"0	4	4	17	1\n"+
	"CI_DESC,4\n"+
	"0	2	2	7	1\n"+
	"1	3	3	11	1\n"+
	"2	4	4	17	1\n"+
	"3	5	5	22	1\n"+
	"";

/**************************** Requete AJOUTSITE ****************************/
public static String EP_AJOUTSITECHECK=
	"/*EP \u0003 0 4 4 4 # 1 3 3 4 r0 4 4 1 17 18 19 20 # 4 2 2 3 18 0 ?1 r2 # 4 1 1 2 19 0 ?2 r3 # 4 0 0 1 20 0 ?3 r4 # \u0000 1 1 1 IdGlobal # \u0000*/";

public static String EP_PREAJOUTSITE=
	"/*EP \u0000 0 1 1 4 # 1 0 0 1 r0 1 4 1 17 # \u0000 1 1 1 IdGlobal # \u0000*/";

public static String EP_AJOUTSITE=
	"/*EP \u0005 6 1 1 -1 2 ?1 # 5 0 0 1 5 4 1 17 r0 18 ?2 19 ?3 20 ?4 21 ?5 # \u0000*/";

/**************************** Requete CONNEXION ****************************/
public static String EP_CONNEXION=
	"/*EP \u0003 0 4 4 3 # 1 3 3 4 r0 5 3 1 11 15 12 14 13 # 4 2 2 3 12 0 ?1 r3 # 4 1 1 2 14 0 ?2 r4 # 4 0 0 1 13 0 ?3 r5 # \u0000 2 1 1 IdGlobal 0 2 BackupKey # \u0000*/";

/**************************** Requete INSCRIPTION ****************************/
public static String EP_INSCRIPTIONCHECK=
	"/*EP \u0001 0 2 2 3 # 1 1 1 2 r0 2 3 1 11 12 # 4 0 0 1 12 0 ?1 r2 # \u0000 1 1 1 IdGlobal # \u0000*/";

public static String EP_PREINSCRIPTION=
	"/*EP \u0000 0 1 1 3 # 1 0 0 1 r0 1 3 1 11 # \u0000 1 1 1 IdGlobal # \u0000*/";

public static String EP_INSCRIPTION=
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

public static String EP_REMPLIRFORM=
	"/*EP \u0002 0 5 5 5 # 1 4 4 5 r0 3 5 1 25 26 23 # 4 3 3 4 23 0 ?2 r3 # 1 2 2 3 r0 1 0 0 1 # 1 1 1 2 r5 2 4 1 18 19 # 4 0 0 1 17 0 ?1 r0 # \u0000 4 0 6 NomFieldLogin 0 7 NomFieldPass 0 1 ValeurLogin 0 2 ValeurPass # \u0000*/";

/**************************** Requete SUPPRID ****************************/
public static String EP_PRESUPPRID=
	"/*EP \u0001 0 2 2 4 # 1 1 1 2 r0 2 4 1 17 20 # 4 0 0 1 20 0 ?1 r2 # \u0000 1 1 1 IdGlobal # \u0000*/";

public static String EP_SUPPRID=
	"/*EP \u0002 0 5 5 5 # 1 4 4 5 r0 3 5 1 22 24 23 # 4 3 3 4 24 0 ?1 r2 # 4 2 2 3 23 0 ?2 r3 # 5 1 1 2 3 0 1 0 v15 1 r0 2 v10 # 9 0 0 1 5 r0 # \u0000*/";

/**************************** Requete SUPPRUSER ****************************/
public static String EP_PRESUPPRUSER=
	"/*EP \u0002 0 3 3 3 # 1 2 2 3 r0 3 3 1 11 12 13 # 4 1 1 2 12 0 ?1 r2 # 4 0 0 1 13 0 ?2 r3 # \u0000 1 1 1 IdGlobal # \u0000*/";

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

}

