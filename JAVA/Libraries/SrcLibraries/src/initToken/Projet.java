package initToken;

import test.jdbc.Tools;
import java.io.PrintWriter;
import java.util.Arrays;
import org.inria.database.QEPng;
import encryptPackage.Encrypt;
import logPackage.Log;
import hashPackage.Hash;

public class Projet extends Tools {
	
	private Projet projet;
	private String requestReturn="";
	private String requestAddOnReturn="";
	private String userName="";
	private String userBackupKey="";
	private String mdp="";
	private int IdUser=-1;
	private int recover=0;
	private String persist="";
	
	
	public void init(String args,String option) throws Exception{
		Projet projet = new Projet();
	    setProject(projet);
		projet.out = new PrintWriter(java.lang.System.out);
		String dbmsHost = null;
		//java.sql.PreparedStatement ps;
	
		if (args.length() != 0)
		{
			dbmsHost = args;
		}
		else
		{
			projet.out.println("Missing argument: e.g. COM3, /dev/ttyACM0, ...");
			if (projet.out != null)
				projet.out.close();
			return;	    	
		}

		// this not a performance test ==> output is produced:
		perf = 0;
		
		// initialize the driver:
		projet.init();
		
		// connect without authentication
		projet.openConnection(dbmsHost, null);
		
		if(option.compareTo("NotPersistent")==0){
		
			//ATTENTION : pas obligatoire mais préférable si l'éxécution du programme sur le token se termine brutalement			
			projet.Desinstall_DBMS_MetaData();    
		
			// load the DB schema
			String schema = Schema.META;
		
			// load the DB schema
			projet.Install_DBMS_MetaData(schema.getBytes(), 0);
			setPersist("N");
		}
		else if(option.compareTo("Persistent")==0){
			((org.inria.jdbc.Connection)projet.db).bypassInitialization();
			setPersist("P");
		}
		
		// load and install QEPs
		Class<?>[] executionPlans = new Class[] { QEP.class };
		QEPng.loadExecutionPlans( QEP_IDs.class, executionPlans );
		QEPng.installExecutionPlans( projet.db );
		System.out.println("Token initialisation done");
	}
	
	public void shutdown() throws Exception{
		projet=getProject();
		
		if(getPersist().compareTo("N")==0){
			projet.Desinstall_DBMS_MetaData();
			System.out.println("Desinstall MetaData in progress...");
		}
		else if(getPersist().compareTo("P")==0){
			projet.Save_DBMS_on_disk();
			System.out.println("Save MetaData in progress ...");
		}
		setPersist("");
		projet.Shutdown_DBMS();
		System.out.println("Shutdown DBMS");
		System.out.println("Token is now off");
	}

	public void procedureADDON(String encodedRequest) throws Exception{
		java.sql.PreparedStatement ps;
		projet=getProject();
		int IdSite = -1, VersionSite = -1;
		String ValeurLogin="", ValeurPass="", URLPageForm="", NomFieldLogin="", NomFieldPass="";
		Parser pars = new Parser();
		pars.ParseIn(encodedRequest);
		Encrypt en=new Encrypt();
		Log l = new Log();
		
		/*ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_AJOUTSITE);
		ps.setInt(1, 1);
		ps.setString(2, "username");
		ps.setString(3, "password");
		ps.setString(4, "mail.insa-cvl.fr");
		ps.setInt(5, 43);
		ps.executeUpdate();*/
		
		
		if(pars.getAction().equals("AjoutID"))
		{
			//Stockage des variables
			ValeurLogin=pars.getLogin();
			ValeurPass=pars.getMdp();
			URLPageForm=pars.getURL();
			System.out.println(ValeurLogin + " " + ValeurPass + " " + URLPageForm);
			System.out.println("Procedure \"AjoutID\" in progress...");
			
			ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_PREAJOUTID);
			ps.setString(1, URLPageForm);
			org.inria.jdbc.ResultSet resultSet_PREAJOUTID = (org.inria.jdbc.ResultSet) ps.executeQuery();
			
			while(resultSet_PREAJOUTID.next())
				IdSite = resultSet_PREAJOUTID.getInt(1);
			System.out.println("IdSite = " + IdSite);
			
			ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_AJOUTIDCHECK);
			ps.setInt(1, getIdUser());
			ps.setInt(2, IdSite);
			ps.setString(3, en.encrypt(ValeurLogin,getBackupKey()));
			ps.setString(4, en.encrypt(ValeurPass,getBackupKey()));
			org.inria.jdbc.ResultSet resultSet_AJOUTIDCHECK = (org.inria.jdbc.ResultSet) ps.executeQuery();
			
			resultSet_AJOUTIDCHECK.next();
			if(resultSet_AJOUTIDCHECK.getInt(1) != -1)
			{
				System.out.println("Username and Password already exist for this Website : " + URLPageForm);
				System.out.println("Username : " + ValeurLogin);
				setRqADDON(pars.ParseOutExist(false));
			}
			else
			{
				int Ids[] = new int [100], i = 0, maxIds = 0;
				System.out.println("AjoutSiteCheck OK");
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_AJOUTIDSELECT);
				org.inria.jdbc.ResultSet resultSet_AJOUTIDSELECT = (org.inria.jdbc.ResultSet) ps.executeQuery();
				
				while(resultSet_AJOUTIDSELECT.next())
				{
					Ids[i] = resultSet_AJOUTIDSELECT.getInt(1);
					i++;
				}
				Arrays.sort(Ids);
				maxIds = Ids[99];
				System.out.println("Max(Ids) = " + maxIds);
				
				maxIds+=1;
				System.out.println("\nmaxIds : "+maxIds);
				if(getRecover()==0){
					System.out.println("IdUser : "+getIdUser());
				}
				else {
					System.out.println("IdUser : 1");
				}
				System.out.println("IdSite : "+IdSite);
				System.out.println("ValeurLogin : "+ValeurLogin);
				System.out.println("ValeurPass : "+ValeurPass + "\n");
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_AJOUTID);
				ps.setInt(1, maxIds);
				if(getRecover()==0){
					ps.setInt(2, getIdUser());
				}
				else {
					ps.setInt(2, 1);
				}
				ps.setInt(3, IdSite);
				ps.setString(4, en.encrypt(ValeurLogin,getBackupKey()));
				ps.setString(5, en.encrypt(ValeurPass,getBackupKey()));
				ps.setInt(6, 1);
				ps.executeUpdate();
				System.out.println("AjoutID done");
				setRqADDON(pars.ParseOutExist(true));
				if(getRecover()==0){
					l.writeLogADDON(en.encrypt(encodedRequest, getBackupKey()), getName());
				}
			}
		}
		else if(pars.getAction().equals("CheckID")){
			
			URLPageForm = pars.getURL();
			ValeurLogin = pars.getLogin();
			ValeurPass = pars.getMdp();
			String ValeurLogin_old = "", ValeurPass_old = ""; 
			
			ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_PREMODIFID);
			ps.setString(1, URLPageForm);
			org.inria.jdbc.ResultSet resultSet_PREMODIFID = (org.inria.jdbc.ResultSet) ps.executeQuery();
			
			while(resultSet_PREMODIFID.next())
				IdSite = resultSet_PREMODIFID.getInt(1);
			
			ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_MODIFIDSELECT);
			ps.setInt(1, IdSite);
			ps.setInt(2, getIdUser());
			org.inria.jdbc.ResultSet resultSet_MODIFIDSELECT = (org.inria.jdbc.ResultSet) ps.executeQuery();
			
			while(resultSet_MODIFIDSELECT.next())
            {
                ValeurLogin_old = resultSet_MODIFIDSELECT.getString(2);
                ValeurPass_old = resultSet_MODIFIDSELECT.getString(3);
            }
            
            if(en.encrypt(ValeurLogin_old,getBackupKey()).equals(en.encrypt(ValeurLogin,getBackupKey())) && en.encrypt(ValeurPass_old,getBackupKey()).equals(en.encrypt(ValeurPass,getBackupKey())))
            {
                //pars.ParseOutExist(true);
                setRqADDON(pars.ParseOutExist(true));
            }
            else
            {
            	//pars.ParseOutExist(false);
            	setRqADDON(pars.ParseOutExist(false));
            }
		}
		else if(pars.getAction().equals("AjoutSite"))
		{
			URLPageForm = pars.getURL();
			NomFieldLogin = pars.getLogin();
			NomFieldPass = pars.getMdp();
			System.out.println("Procedure \"AjoutSite\" in progress...");
			
			ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_AJOUTSITECHECK);
			ps.setString(1, URLPageForm);
			org.inria.jdbc.ResultSet resultSet_AJOUTSITECHECK = (org.inria.jdbc.ResultSet) ps.executeQuery();
			
			resultSet_AJOUTSITECHECK.next();
			
			if(resultSet_AJOUTSITECHECK.getInt(1) != -1){
				System.out.println("ERROR : the site \"" + URLPageForm + "\" already have an entry");
				setRqADDON(pars.ParseOutExist(false));
			}
			else
			{
				System.out.println("AjoutSiteCheck OK");
				int Ids[] = new int[100], i = 0, maxIds = 0;
				
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_PREAJOUTSITE);
				org.inria.jdbc.ResultSet resultSet_PREAJOUTSITE = (org.inria.jdbc.ResultSet) ps.executeQuery();
				
				while(resultSet_PREAJOUTSITE.next())
				{
					Ids[i] = resultSet_PREAJOUTSITE.getInt(1);
					System.out.println("Ids["+i+"] = "+Ids[i]);
					i++;
				}
				Arrays.sort(Ids);
				maxIds = Ids[99];
				System.out.println("Max(Ids) = " + maxIds);
				maxIds+=1;
				
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_AJOUTSITE);
				ps.setInt(1, maxIds);
				ps.setString(2, NomFieldLogin);
				ps.setString(3, NomFieldPass);
				ps.setString(4, URLPageForm);
				ps.setInt(5, 0);
				ps.executeUpdate();
				System.out.println("AjoutSite done");
				setRqADDON(pars.ParseOutExist(true));
				if(getRecover()==0){
					l.writeLogADDON(en.encrypt(encodedRequest, getBackupKey()), getName());
				}
			}
		}
		else if(pars.getAction().equals("ModifForm"))
		{
			String NomFieldLogin_old = "" , NomFieldPass_old = "";
			NomFieldLogin = pars.getFieldLogin();
			NomFieldPass = pars.getFieldMdp();
			URLPageForm = pars.getURL();
			System.out.println("Procedure \"ModifForm\" in progress...");
			
			ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_PREMODIFFORM);
			ps.setString(1, URLPageForm);
			org.inria.jdbc.ResultSet resultSet_PREMODIFFORM = (org.inria.jdbc.ResultSet) ps.executeQuery();
			
			while(resultSet_PREMODIFFORM.next())
			{
				IdSite = resultSet_PREMODIFFORM.getInt(1);
				NomFieldLogin_old = resultSet_PREMODIFFORM.getString(2);
				NomFieldPass_old = resultSet_PREMODIFFORM.getString(3);
				VersionSite = resultSet_PREMODIFFORM.getInt(4);
			}
			if(NomFieldLogin_old.equals(NomFieldLogin) && NomFieldPass_old.equals(NomFieldPass)){
				System.out.println("Fields equals, no modification necessary");
				setRqADDON(pars.ParseOutExist(false));
			}
			else
			{
				VersionSite+=1;
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_MODIFFORMDELETE);
				ps.setInt(1, IdSite);
				ps.executeUpdate();
				System.out.println("Delete done");
				
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_MODIFFORM);
				ps.setInt(1, IdSite);
				ps.setString(2, NomFieldLogin);
				ps.setString(3, NomFieldPass);
				ps.setString(4, URLPageForm);
				ps.setInt(5, VersionSite);
				ps.executeUpdate();
				System.out.println("Insert done");
				setRqADDON(pars.ParseOutExist(true));
				if(getRecover()==0){
					l.writeLogADDON(en.encrypt(encodedRequest, getBackupKey()), getName());
				}
			}
			
		}
		else if(pars.getAction().equals("RemplirForm"))
		{
			String NomFieldLogin_old = "", NomFieldPass_old = "";
			NomFieldLogin = pars.getFieldLogin();
			NomFieldPass = pars.getFieldMdp();
			URLPageForm = pars.getURL();
			System.out.println("Procedure \"RemplirForm\" in progress...");
			
			ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_PREREMPLIRFORM);
			ps.setString(1, URLPageForm);
			org.inria.jdbc.ResultSet resultSet_PREREMPLIRFORM = (org.inria.jdbc.ResultSet) ps.executeQuery();
			while(resultSet_PREREMPLIRFORM.next())
			{
				IdSite = resultSet_PREREMPLIRFORM.getInt(1);
				NomFieldLogin_old = resultSet_PREREMPLIRFORM.getString(2);
				NomFieldPass_old = resultSet_PREREMPLIRFORM.getString(3);
				VersionSite = resultSet_PREREMPLIRFORM.getInt(4);
			}

			if(NomFieldLogin_old.equals("")||NomFieldPass_old.equals("")){
				System.out.println("Ajout du site en cours");
				int Ids[] = new int[100], i = 0, maxIds = 0;
				
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_PREAJOUTSITE);
				org.inria.jdbc.ResultSet resultSet_PREAJOUTSITE = (org.inria.jdbc.ResultSet) ps.executeQuery();
				
				while(resultSet_PREAJOUTSITE.next())
				{
					Ids[i] = resultSet_PREAJOUTSITE.getInt(1);
					i++;
				}
				Arrays.sort(Ids);
				maxIds = Ids[99];
				System.out.println("Max(Ids) = " + maxIds);
				maxIds+=1;
				
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_AJOUTSITE);
				ps.setInt(1, maxIds);
				ps.setString(2, NomFieldLogin);
				ps.setString(3, NomFieldPass);
				ps.setString(4, URLPageForm);
				ps.setInt(5, 0);
				ps.executeUpdate();
				
				if(getRecover()==0){
					l.writeLogADDON(en.encrypt(encodedRequest, getBackupKey()), getName());
				}
				System.out.println("\nmaxIds : "+maxIds);
				System.out.println("NomFieldLogin : "+NomFieldLogin);
				System.out.println("NomFieldPass : "+NomFieldPass);
				System.out.println("URLPageForm : "+URLPageForm+"\n");
				System.out.println("Ajout du site done");
			}
			else if(NomFieldLogin_old.equals(NomFieldLogin) && NomFieldPass_old.equals(NomFieldPass)){
				System.out.println("Website already exist ...");
				if(getRecover()==0){
					l.writeLogADDON(en.encrypt(encodedRequest, getBackupKey()), getName());
				}
			}
			else if(!NomFieldLogin_old.equals(NomFieldLogin) || !NomFieldPass_old.equals(NomFieldPass))
			{
				System.out.println("Name of login field and/or name of pass field are different, update in progress...");
				
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_REMPLIRFORMDELETE);
				ps.setInt(1, IdSite);
				ps.executeUpdate();
				
     			VersionSite+=1;
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_REMPLIRFORMINSERT);
				ps.setInt(1, IdSite);
				ps.setString(2, NomFieldLogin);
				ps.setString(3, NomFieldPass);
				ps.setString(4, URLPageForm);
				ps.setInt(5, VersionSite);
				if(getRecover()==0){
					l.writeLogADDON(en.encrypt(encodedRequest, getBackupKey()), getName());
				}
			}
			
			ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_REMPLIRFORMW);
			ps.setInt(1, IdSite);
			org.inria.jdbc.ResultSet resultSet_REMPLIRFORMW = (org.inria.jdbc.ResultSet) ps.executeQuery();
			while(resultSet_REMPLIRFORMW.next())
			{
				NomFieldLogin = resultSet_REMPLIRFORMW.getString(1);
				NomFieldPass = resultSet_REMPLIRFORMW.getString(2);
			}
			
			System.out.println("NomFieldLogin : "+NomFieldLogin);
			System.out.println("NomFieldPass : "+NomFieldPass);
			
			ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_REMPLIRFORMI);
			ps.setInt(1, getIdUser());
			org.inria.jdbc.ResultSet resultSet_REMPLIRFORMI = (org.inria.jdbc.ResultSet) ps.executeQuery();
			while(resultSet_REMPLIRFORMI.next())
			{

				if(resultSet_REMPLIRFORMI.getInt(1) == IdSite)
				{
					ValeurLogin = resultSet_REMPLIRFORMI.getString(2);
					ValeurPass = resultSet_REMPLIRFORMI.getString(3);
				}
			}
			String[] Logins = {en.decrypt(ValeurLogin,getBackupKey())};
			String[] Passs = {en.decrypt(ValeurPass,getBackupKey())};
			//pars.ParseOut(Logins, Passs);
			System.out.println("ValeurLogin : "+ValeurLogin);
			System.out.println("ValeurPass : "+ValeurPass);
			System.out.println("pars.ParseOut : "+pars.ParseOut(Logins,Passs));
			setRqADDON(pars.ParseOut(Logins,Passs));
			/*System.out.println("ValeurLogin = " + ValeurLogin + "\nValeurPass = " + ValeurPass);
			System.out.println("Logins[0] = " + Logins[0] + "\nPasss[0] = " + Passs[0]);
			System.out.println("Le Beau Retour : " + pars.ParseOut(Logins, Passs));*/
		}
		else if(pars.getAction().equals("SupprID"))
		{
			URLPageForm = pars.getURL();
			//System.out.println("URL : " + URLPageForm);
			System.out.println("Procedure \"SupprID\" in progress...");
			
			ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_PRESUPPRID);
			ps.setString(1, URLPageForm);
			org.inria.jdbc.ResultSet resultSet_PRESUPPRID= (org.inria.jdbc.ResultSet) ps.executeQuery();
			while(resultSet_PRESUPPRID.next())
			{	
				IdSite = resultSet_PRESUPPRID.getInt(1);
			}
			ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_SUPPRID);
			ps.setInt(1, IdSite);
			ps.setInt(2, getIdUser());
			ps.executeUpdate();
			System.out.println("Suppression done");	
			setRqADDON(pars.ParseOutExist(true));
			if(getRecover()==0){
				l.writeLogADDON(en.encrypt(encodedRequest, getBackupKey()), getName());
			}
		}
		else if(pars.getAction().equals("ModifID"))
        {
            System.out.println("ModifID in progress...");
                            
            int IdGlobal = -1, VersionID = -1;
            String ValeurLogin_old = "null", ValeurPass_old = "null";
            URLPageForm = pars.getURL();
            ValeurLogin = pars.getLogin();
            ValeurPass = pars.getMdp();
            
            ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_PREMODIFID);
            ps.setString(1, URLPageForm);
            org.inria.jdbc.ResultSet resultSet_PREMODIFID = (org.inria.jdbc.ResultSet) ps.executeQuery();
            
            while(resultSet_PREMODIFID.next())
            {
                IdSite = resultSet_PREMODIFID.getInt(1);
            }
            
            ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_MODIFIDSELECT);
            ps.setInt(1, IdSite);
            ps.setInt(2, getIdUser());
            org.inria.jdbc.ResultSet resultSet_MODIFIDSELECT = (org.inria.jdbc.ResultSet) ps.executeQuery();
            
            while(resultSet_MODIFIDSELECT.next())
            {
                IdGlobal = resultSet_MODIFIDSELECT.getInt(1);
                ValeurLogin_old = resultSet_MODIFIDSELECT.getString(2);
                ValeurPass_old = resultSet_MODIFIDSELECT.getString(3);
                VersionID = resultSet_MODIFIDSELECT.getInt(4);
            }
            
            if(en.encrypt(ValeurLogin_old,getBackupKey()).equals(en.encrypt(ValeurLogin,getBackupKey())) && en.encrypt(ValeurPass_old,getBackupKey()).equals(en.encrypt(ValeurPass,getBackupKey())))
            {
                System.out.println("Login and Password are the same, do not need modification");
                setRqADDON(pars.ParseOutExist(false));
            }
            else
            {
                VersionID+=1;
                
                ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_MODIFIDDELETE);
                ps.setInt(1, IdGlobal);
                ps.executeUpdate();
                System.out.println("Delete done, insert in progress...");
                
                ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_MODIFID);
                ps.setInt(1, IdGlobal);
                ps.setInt(2, getIdUser());
                ps.setInt(3, IdSite);
                ps.setString(4, en.encrypt(ValeurLogin,getBackupKey()));
                ps.setString(5, en.encrypt(ValeurPass,getBackupKey()));
                ps.setInt(6, VersionID);
                ps.executeUpdate();
                
                System.out.println("Update ID done : new IDs for " + ValeurLogin + " in the site " + URLPageForm +"\n");
                setRqADDON(pars.ParseOutExist(true));
                if(getRecover()==0){
                	l.writeLogADDON(en.encrypt(encodedRequest, getBackupKey()), getName());
                }
            }
        }
		else{
			System.out.println("Request unknown or malformed "+pars.getAction());
			setRqADDON(pars.ParseOutExist(false));
		}
	}
	

	public void procedureJAVA(String requete) throws Exception {
				
			//Déclarations
			//String requete = "Q:SELECT:PreSupprUser:Ruby;1234";
			java.sql.PreparedStatement ps;
			projet=getProject();
			Hash h=new Hash();
			int IdUser = -1;
			String[] split, vals;
			String sep1 = ":";
			String sep2 = ";";
			String ok="0", pasok="1";
			String Name="", ValeurPIN="", ValeurFinger="", BackupKey="", marche = pasok, retour="", ValeurPIN_hash="";
			//Pré-découpage
			split = requete.split(sep1, 4);


			if(split[2].equals("Connexion"))
			{
				
				vals=split[3].split(sep2,3);
				Name=vals[0];
				ValeurPIN=vals[1];
				ValeurFinger=vals[2];
				System.out.println("Procedure \"Connexion\" in progress...");
				
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_PRECONNEXION);
				ps.setString(1, Name);
				ps.setString(2, ValeurFinger);
				org.inria.jdbc.ResultSet resultSet_PRECONNEXION = (org.inria.jdbc.ResultSet) ps.executeQuery();
				while(resultSet_PRECONNEXION.next())
				{
					ValeurPIN_hash = resultSet_PRECONNEXION.getString(1);
				}
				if(!ValeurPIN_hash.equals("")){
					if(h.verifPass(ValeurPIN,ValeurPIN_hash)){
				
						ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_CONNEXION);
						ps.setString(1, Name);
						ps.setString(2, ValeurFinger);
						ps.setString(3, ValeurPIN_hash);
						org.inria.jdbc.ResultSet resultSet_CONNEXION = (org.inria.jdbc.ResultSet) ps.executeQuery();
				
						while(resultSet_CONNEXION.next())
						{
							marche = ok;
							IdUser = resultSet_CONNEXION.getInt(1);
							BackupKey = resultSet_CONNEXION.getString(2);
						}
					}
				}
					retour = marche + sep1 + split[2] + sep1 + IdUser + sep2 + BackupKey;
					setRequestReturn(retour);
					System.out.println("retour : " + retour);

					
			}
			
			else if(split[2].equals("Inscription"))
			{
				vals = split[3].split(sep2, 3);
				Name = vals[0];
				ValeurPIN = vals[1];
				ValeurFinger = vals[2];
				System.out.println("Procedure \"Inscription\" in progress...");
				
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_INSCRIPTION);
				ps.setString(1, Name);
				org.inria.jdbc.ResultSet resultSet_INSCRIPTION = (org.inria.jdbc.ResultSet) ps.executeQuery();
				
				while(resultSet_INSCRIPTION.next())
				{
					IdUser = resultSet_INSCRIPTION.getInt(1);
				}
				
				if(IdUser != -1)
				{
					marche=pasok;
					System.out.println("User name already exists");
				}
				else
				{
					int Ids[] = new int[100], i = 0, maxIds = 0;
					ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_PREINSCRIPTION);
					org.inria.jdbc.ResultSet resultSet_EP_PREINSCRIPTION = (org.inria.jdbc.ResultSet) ps.executeQuery();
					
					while(resultSet_EP_PREINSCRIPTION.next())
					{
						Ids[i] = resultSet_EP_PREINSCRIPTION.getInt(1);
						System.out.println("Ids["+i+"] = "+Ids[i]);
						i++;
					}
					Arrays.sort(Ids);
					maxIds = Ids[99];
					System.out.println("Max(Ids) = " + maxIds);
					maxIds+=1;
					
					//Génération de la BackupKey
					Encrypt e = new Encrypt();
					BackupKey = e.generateEncryptKey();
					
					ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_INSCRIPTIONINSERT);
					ps.setInt(1, maxIds);
					ps.setString(2, Name);
					ps.setString(3, ValeurPIN);
					ps.setString(4, ValeurFinger);
					if(getRecover()==0){
						ps.setString(5, e.encrypt(BackupKey,this.mdp));
						setMdp("");
					}
					else {
						ps.setString(5, e.encrypt(getBackupKey(),this.mdp));
						setMdp("");
					}
					ps.setInt(6, 0);
					ps.executeUpdate();
					System.out.println("Inscription done");
					marche=ok;
				}
				
				if(getRecover()==0){
					retour = marche + sep1 + split[2] + sep1 + BackupKey;
				}
				else{
					retour = marche + sep1 + split[2] + sep1 + getBackupKey();
				}
				
				setRequestReturn(retour);
				System.out.println("retour : " + retour);
			}
			else if(split[2].equals("PreSupprUser"))
			{
				vals = split[3].split(sep2, 2);
				Name = vals[0];
				ValeurPIN = vals[1];
				System.out.println("Procedure \"SupprUser\" in progress...");
				
				ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_PRESUPPRUSER);
				ps.setString(1, Name);
				//ps.setString(2, ValeurPIN);
				org.inria.jdbc.ResultSet resultSet_PRESUPPRUSER = (org.inria.jdbc.ResultSet) ps.executeQuery();
				while(resultSet_PRESUPPRUSER.next())
				{
					IdUser = resultSet_PRESUPPRUSER.getInt(1);
					ValeurPIN_hash=resultSet_PRESUPPRUSER.getString(2);
					//marche = ok;
				}
				if(IdUser != -1){
					if(h.verifPass(ValeurPIN, ValeurPIN_hash)){
						marche=ok;
					}
				}
				
				if(marche.equals(ok))
				{
					ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_SUPPRUSER_USER);
					ps.setInt(1, IdUser);
					ps.executeUpdate();
					ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_SUPPRUSER_IDS);
					ps.setInt(1, IdUser);
					ps.executeUpdate();
					System.out.println("Suppression done");
				}
				else
				{
					System.out.println("User \""+ Name + "\" unknown or bad PIN");
				}
				retour = marche + sep1 + split[2];
				setRequestReturn(retour);
				System.out.println("retour : " + retour);
			}
			else
				System.out.println("Request unknown or malformed : " + split[2]);
			
	}
	
	public void testUsers() throws Exception {
		java.sql.PreparedStatement ps;
		projet=getProject();
        ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_TESTUSERS);
        org.inria.jdbc.ResultSet resultSet_TESTUSERS = (org.inria.jdbc.ResultSet) ps.executeQuery();
        while(resultSet_TESTUSERS.next())
        {
            System.out.println("");
            System.out.println("IdUser       : " + resultSet_TESTUSERS.getInt(1));
            System.out.println("Name         : " + resultSet_TESTUSERS.getString(2));
            System.out.println("ValeurPIN    : " + resultSet_TESTUSERS.getString(3));
            System.out.println("ValeurFinger : " + resultSet_TESTUSERS.getString(4));
            System.out.println("BackupKey    : " + resultSet_TESTUSERS.getString(5));
            System.out.println("VersionUser  : " + resultSet_TESTUSERS.getInt(6));
            System.out.println("");
        }
	}
	
	public void testWebsites() throws Exception {
		java.sql.PreparedStatement ps;
		projet=getProject();
        ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_TESTWEBSITES);
        org.inria.jdbc.ResultSet resultSet_TESTWEBSITES = (org.inria.jdbc.ResultSet) ps.executeQuery();
        while(resultSet_TESTWEBSITES.next())
        {
            System.out.println("");
            System.out.println("IdSite        : " + resultSet_TESTWEBSITES.getInt(1));
            System.out.println("NomFieldLogin : " + resultSet_TESTWEBSITES.getString(2));
            System.out.println("NomFieldPass  : " + resultSet_TESTWEBSITES.getString(3));
            System.out.println("URLPageForm   : " + resultSet_TESTWEBSITES.getString(4));
            System.out.println("VersionSite   : " + resultSet_TESTWEBSITES.getInt(5));
            System.out.println("");
        }
	}
	
	public void testIDs() throws Exception {
		java.sql.PreparedStatement ps;
		projet=getProject();
        
        ps = ((org.inria.jdbc.Connection)projet.db).prepareStatement(QEP_IDs.EP_Project.EP_TESTIDS);
        org.inria.jdbc.ResultSet resultSet_TESTIDS = (org.inria.jdbc.ResultSet) ps.executeQuery();
        while(resultSet_TESTIDS.next())
        {
            System.out.println("");
            System.out.println("Id          : " + resultSet_TESTIDS.getInt(1));
            System.out.println("IdUser      : " + resultSet_TESTIDS.getInt(2));
            System.out.println("IdSite      : " + resultSet_TESTIDS.getInt(3));
            System.out.println("ValeurLogin : " + resultSet_TESTIDS.getString(4));
            System.out.println("ValeurPass  : " + resultSet_TESTIDS.getString(5));
            System.out.println("VersionID   : " + resultSet_TESTIDS.getInt(6));
            System.out.println("");
        }
	}

	public void setRequestReturn(String rr){
		this.requestReturn=rr;
	}

	public String getRequestReturn(){
		return this.requestReturn;
	}
	
	public void setProject(Projet p){
		this.projet=p;
	}
	
	public Projet getProject(){
		return this.projet;
	}
	
	public String getName(){
		return this.userName;
	}
	
	public void setName(String name){
		this.userName=name;
	}
	
	public void setBackupKey(String key){
		this.userBackupKey=key;
	}
	
	public String getBackupKey(){
		return this.userBackupKey;
	}
	
	public void setIdUser(int id){
		this.IdUser=id;
	}
	
	public int getIdUser(){
		return this.IdUser;
	}
	
	public void setRqADDON(String r){
		this.requestAddOnReturn=r;
	}
	
	public String getRqADDON(){
		return this.requestAddOnReturn;
	}
	
	public void setRecover(int x){
		this.recover=x;
	}
	
	public int getRecover(){
		return this.recover;
	}
	
	public void setMdp(String m){
		this.mdp=m;
	}
	
	public void setPersist(String p){
		this.persist=p;
	}
	
	public String getPersist(){
		return this.persist;
	}
}
