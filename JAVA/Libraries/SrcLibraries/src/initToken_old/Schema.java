package initToken;

public class Schema {
	public static final String META =
			"TAB_DESC,6\n"+
			"0	LogDeleted	12\n"+
			"1	UpdateLog	512\n"+
			"2	QEP	512\n"+
			"3	Users	356\n"+
			"4	Websites	354\n"+
			"5	IDs	180\n"+
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
			"13	3	ValeurPIN	132	0	56\n"+
			"14	3	ValeurFinger	82	0	188\n"+
			"15	3	BackupKey	82	0	270\n"+
			"16	3	VersionUser	4	1	352\n"+
			"17	4	IdGlobal	4	1	0\n"+
			"18	4	NomFieldLogin	82	0	4\n"+
			"19	4	NomFieldPass	82	0	86\n"+
			"20	4	URLPageForm	182	0	168\n"+
			"21	4	VersionSite	4	1	350\n"+
			"22	5	IdGlobal	4	1	0\n"+
			"23	5	IdUser	4	1	4\n"+
			"24	5	IdSite	4	1	8\n"+
			"25	5	ValeurLogin	82	0	12\n"+
			"26	5	ValeurPass	82	0	94\n"+
			"27	5	VersionID	4	1	176\n"+
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
}

