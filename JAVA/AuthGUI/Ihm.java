import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.util.Arrays;
import java.lang.Thread.*;

public class Ihm {	

	private int IdUser=-1;
	private String BackupKey=null;
	private String nameCurrentUser=null;

	public static void main(String[] args){

		final JFrame firstFrame = new JFrame();
		JPanel firstPan = new JPanel();
		firstFrame.setTitle("The Luggage");
		/* Changer la taille de la fenetre */
    	firstFrame.setSize(400, 140);
    	firstFrame.setLocation(300,400);
    	firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	firstPan.setLayout(new BorderLayout());
		JPanel firstBasPan = new JPanel(new FlowLayout());
		JPanel firstNordPan = new JPanel(new FlowLayout());
		JPanel firstCentrePan = new JPanel(new FlowLayout());
		JButton btnLogin = new JButton("Sign in");
		JButton btnSupprUser = new JButton("Delete user");
		JButton btnRegister = new JButton("Register");
		JButton btnNotice = new JButton("Instructions");
		JButton btnBackup = new JButton("TCell Recovery");
		final JLabel connectState = new JLabel();
		connectState.setPreferredSize(new Dimension(140, 25));
		btnLogin.setPreferredSize(new Dimension(140, 25));
		btnSupprUser.setPreferredSize(new Dimension(140, 25));
		btnRegister.setPreferredSize(new Dimension(140, 25));
		btnNotice.setPreferredSize(new Dimension(140, 25));
		btnBackup.setPreferredSize(new Dimension(140, 25));
		firstNordPan.add(btnRegister);
		firstNordPan.add(btnLogin);
		firstCentrePan.add(btnSupprUser);
		firstCentrePan.add(btnBackup);
		firstBasPan.add(btnNotice);
		firstBasPan.add(connectState);
		firstPan.add(firstBasPan, BorderLayout.SOUTH);
		firstPan.add(firstNordPan, BorderLayout.NORTH);
		firstPan.add(firstCentrePan, BorderLayout.CENTER);
		firstFrame.setContentPane(firstPan);


		final JFrame fenetreFrame = new JFrame();
    	JPanel panneauPan = new JPanel();
		fenetreFrame.setTitle("Sign in");
		/* Changer la taille de la fenetre */
    	fenetreFrame.setSize(450, 130);
    	fenetreFrame.setLocation(300,400);
    	fenetreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	panneauPan.setLayout(new BorderLayout());
		JPanel basPan = new JPanel(new FlowLayout());
		JPanel nordPan = new JPanel(new FlowLayout());
		JPanel centrePan = new JPanel(new FlowLayout());
		JButton btnConnection = new JButton("Log in");
		JButton btnPrecedentFenetre = new JButton("Back");
		btnConnection.setPreferredSize(new Dimension(140, 25));
		btnPrecedentFenetre.setPreferredSize(new Dimension(140, 25));
		final JLabel isCorrect = new JLabel();
		basPan.add(btnPrecedentFenetre);
		basPan.add(btnConnection);
		basPan.add(isCorrect);
		final JTextField fieldUsername = new JTextField(10);
		JLabel authLogin = new JLabel("Login");
		authLogin.setPreferredSize(new Dimension(80, 15));
		final JPasswordField fieldPassword = new JPasswordField(10);
		JLabel authPass = new JLabel("Password");
		authPass.setPreferredSize(new Dimension(80, 15));
		nordPan.add(authLogin);
		nordPan.add(fieldUsername);
		centrePan.add(authPass);
		centrePan.add(fieldPassword);
		panneauPan.add(basPan, BorderLayout.SOUTH);
		panneauPan.add(nordPan, BorderLayout.NORTH);
		panneauPan.add(centrePan, BorderLayout.CENTER);
		fenetreFrame.setContentPane(panneauPan);


		/*final JFrame fingerprintFrame=new JFrame();
		fingerprintFrame.setTitle("Fingerprint");
		//Changer la taille de la fenetre 
		fingerprintFrame.setSize(500, 500);
		fingerprintFrame.setLocation(300,400);
    	fingerprintFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel picPan = new JPanel();
        picPan.setLayout(new BorderLayout());
        JPanel fingerBasPan = new JPanel(new FlowLayout());
		JLabel pic = new JLabel();
		ImageIcon myPicture = new ImageIcon("./pouce.gif");
		pic.setIcon(myPicture);
		pic.setHorizontalAlignment(JLabel.CENTER);
    	pic.setVerticalAlignment(JLabel.CENTER);
		picPan.add(pic);
		picPan.add(fingerBasPan, BorderLayout.SOUTH);
		fingerprintFrame.setContentPane(picPan);
		*/


				final JFrame preRecoveryFrame=new JFrame();
				JPanel recovPan = new JPanel();
				preRecoveryFrame.setTitle("TCell Recovery");

				GridBagLayout gbl_recovPan = new GridBagLayout();
	       		gbl_recovPan.columnWidths = new int[] {80, 100};
        		gbl_recovPan.rowHeights = new int[]{0, 0, 0, 50, 0};
        		gbl_recovPan.columnWeights = new double[]{0, 0, 0, 0, 0};
        		gbl_recovPan.rowWeights = new double[]{0, 0, 0, 0, 0};
        		recovPan.setLayout(gbl_recovPan);

        		JLabel lblNewLabel = new JLabel("Logfile");
        		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        		gbc_lblNewLabel.insets = new Insets(15, 0, 5, 0);
        		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        		gbc_lblNewLabel.gridx = 0;
        		gbc_lblNewLabel.gridy = 0;
        		recovPan.add(lblNewLabel, gbc_lblNewLabel);

        		final JTextField fieldFile = new JTextField();
        		GridBagConstraints gbc_fieldFile = new GridBagConstraints();
        		gbc_fieldFile.insets = new Insets(15, 0, 5, 0);
        		gbc_fieldFile.fill = GridBagConstraints.HORIZONTAL;
        		gbc_fieldFile.gridx = 1;
        		gbc_fieldFile.gridy = 0;
        		recovPan.add(fieldFile, gbc_fieldFile);
        		fieldFile.setColumns(5);

        		JLabel labelKey = new JLabel("Backup key");
        		GridBagConstraints gbc_labelKey = new GridBagConstraints();
        		gbc_labelKey.anchor = GridBagConstraints.WEST;
        		gbc_labelKey.insets = new Insets(5, 0, 5, 0);
        		gbc_labelKey.gridx = 0;
        		gbc_labelKey.gridy = 1;
        		recovPan.add(labelKey, gbc_labelKey);

        		final JPasswordField fieldKey = new JPasswordField();
        		GridBagConstraints gbc_fieldKey = new GridBagConstraints();
        		gbc_fieldKey.fill = GridBagConstraints.HORIZONTAL;
        		gbc_fieldKey.insets = new Insets(5, 0, 5, 0);
        		gbc_fieldKey.gridx = 1;
        		gbc_fieldKey.gridy = 1;
        		recovPan.add(fieldKey, gbc_fieldKey);
        		fieldKey.setColumns(5);

        		JLabel labelPass = new JLabel("Password");
        		GridBagConstraints gbc_labelPass = new GridBagConstraints();
        		gbc_labelPass.anchor = GridBagConstraints.WEST;
        		gbc_labelPass.insets = new Insets(5, 0, 10, 0);
        		gbc_labelPass.gridx = 0;
        		gbc_labelPass.gridy = 2;
        		recovPan.add(labelPass, gbc_labelPass);

        		final JPasswordField fieldPass = new JPasswordField();
        		GridBagConstraints gbc_fieldPass = new GridBagConstraints();
        		gbc_fieldPass.fill = GridBagConstraints.HORIZONTAL;
        		gbc_fieldPass.gridx = 1;
        		gbc_fieldPass.gridy = 2;
        		recovPan.add(fieldPass, gbc_fieldPass);
        		fieldPass.setColumns(5);

        		final JButton btnPrecedent = new JButton("Back");
				btnPrecedent.setPreferredSize(new Dimension(140, 25));
        		GridBagConstraints gbc_btnPrecedent = new GridBagConstraints();
        		gbc_btnPrecedent.anchor = GridBagConstraints.CENTER;
        		gbc_btnPrecedent.insets = new Insets(15, 0, 10, 5);
        		gbc_btnPrecedent.gridx = 0;
        		gbc_btnPrecedent.gridy = 3;
        		recovPan.add(btnPrecedent, gbc_btnPrecedent);

        	    final JButton btnRecover = new JButton("Start");
				btnRecover.setPreferredSize(new Dimension(140, 25));
        		GridBagConstraints gbc_btnRecover = new GridBagConstraints();
        		gbc_btnRecover.anchor = GridBagConstraints.CENTER;
        		gbc_btnRecover.insets = new Insets(15, 0, 10, 5);
        		gbc_btnRecover.gridx = 1;
        		gbc_btnRecover.gridy = 3;
        		recovPan.add(btnRecover, gbc_btnRecover);

        		final JLabel labelCorrect = new JLabel();
        		GridBagConstraints gbc_labelCorrect = new GridBagConstraints();
        		gbc_labelCorrect.insets = new Insets(15, 0, 10, 5);
        		gbc_labelCorrect.anchor = GridBagConstraints.CENTER;
        		gbc_labelCorrect.gridx = 2;
        		gbc_labelCorrect.gridy = 3;
        		recovPan.add(labelCorrect, gbc_labelCorrect);

        		/* Changer la taille de la fenetre */
				preRecoveryFrame.setSize(500, 200);
				preRecoveryFrame.setLocation(300,400);
    			preRecoveryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    preRecoveryFrame.setContentPane(recovPan);
				preRecoveryFrame.setVisible(false);


		/*final JFrame preRecoveryFrame = new JFrame();
    	JPanel preRecoveryPan = new JPanel();
		preRecoveryFrame.setTitle("TCell Recovery");
		 Changer la taille de la fenetre 
    	preRecoveryFrame.setSize(600, 130);
    	preRecoveryFrame.setLocation(300,400);
    	preRecoveryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	preRecoveryPan.setLayout(new BorderLayout());
		JPanel preRecoveryBasPan = new JPanel(new FlowLayout());
		JPanel preRecoveryNordPan = new JPanel(new FlowLayout());
		JPanel preRecoveryCentrePan = new JPanel(new FlowLayout());
		final JButton btnRecover = new JButton("Start");
		final JButton btnPrecedent = new JButton("Back");
		btnRecover.setPreferredSize(new Dimension(140, 25));
		btnPrecedent.setPreferredSize(new Dimension(140, 25));
		final JLabel labelCorrect = new JLabel();
		preRecoveryBasPan.add(btnPrecedent);
		preRecoveryBasPan.add(btnRecover);
		preRecoveryBasPan.add(labelCorrect);
		final JTextField fieldFile = new JTextField(10);
		JLabel authFile = new JLabel("Logfile");
		authFile.setPreferredSize(new Dimension(90, 15));
		final JPasswordField fieldKey = new JPasswordField(10);
		JLabel authKey = new JLabel("Backup key");
		JLabel passUser = new JLabel("Password");
		final JPasswordField fieldPass = new JPasswordField(10);
		authKey.setPreferredSize(new Dimension(90, 15));
		preRecoveryNordPan.add(authFile);
		preRecoveryNordPan.add(fieldFile);
		preRecoveryCentrePan.add(authKey);
		preRecoveryCentrePan.add(fieldKey);
		preRecoveryCentrePan.add(passUser);
		preRecoveryCentrePan.add(fieldPass);
		preRecoveryPan.add(preRecoveryBasPan, BorderLayout.SOUTH);
		preRecoveryPan.add(preRecoveryNordPan, BorderLayout.NORTH);
		preRecoveryPan.add(preRecoveryCentrePan, BorderLayout.CENTER);
		preRecoveryFrame.setContentPane(preRecoveryPan);*/

		/*final JFrame recoveryFrame = new JFrame();
		recoveryFrame.setTitle("TCell Recovery");
		// Changer la taille de la fenetre 
		recoveryFrame.setSize(400, 130);
		recoveryFrame.setLocation(300,400);
    	JPanel recoveryPan = new JPanel();
    	recoveryPan.setLayout(new BorderLayout());
    	JPanel recoveryBasPan = new JPanel(new FlowLayout());
    	JPanel recoveryNordPan = new JPanel(new FlowLayout());
    	JPanel recoveryCentrePan = new JPanel(new FlowLayout()); 
    	final JLabel wait=new JLabel("Please wait ...");
    	recoveryNordPan.add(wait);
    	recoveryPan.add(recoveryNordPan, BorderLayout.NORTH);
    	final JLabel labelQuit = new JLabel();
    	recoveryBasPan.add(labelQuit);
    	recoveryPan.add(recoveryBasPan, BorderLayout.SOUTH);
    	ImageIcon recoveryPicture = new ImageIcon("./attente.gif");
    	final JLabel recoveryPic=new JLabel();
		recoveryPic.setIcon(recoveryPicture);
    	recoveryPic.setHorizontalAlignment(JLabel.CENTER);
    	recoveryPic.setVerticalAlignment(JLabel.CENTER);
		recoveryPan.add(recoveryPic);
		recoveryPan.add(recoveryPic, BorderLayout.CENTER);
		recoveryFrame.setContentPane(recoveryPan);*/

		final JFrame deleteUserFrame = new JFrame();
		deleteUserFrame.setTitle("Delete user");
		/* Changer la taille de la fenetre */
		deleteUserFrame.setSize(500, 130);
		deleteUserFrame.setLocation(300,400);
    	deleteUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JPanel deleteUserPan = new JPanel();
    	deleteUserPan.setLayout(new BorderLayout());
    	JPanel deleteBasPan = new JPanel(new FlowLayout());
    	JPanel deleteNordPan = new JPanel(new FlowLayout());
    	JPanel deleteCentrePan = new JPanel(new FlowLayout());
    	JLabel nameToDelete = new JLabel("Name");
		nameToDelete.setPreferredSize(new Dimension(80, 15));
    	final JTextField fieldNameDelete = new JTextField(10);
    	JLabel passToDelete = new JLabel("Password");
		passToDelete.setPreferredSize(new Dimension(80, 15));
    	final JPasswordField fieldPassDelete = new JPasswordField(10);
    	final JLabel isGood = new JLabel();
    	JButton btnPrec = new JButton("Back");
    	JButton btnDelete = new JButton("Delete");
		btnPrec.setPreferredSize(new Dimension(140, 25));
		btnDelete.setPreferredSize(new Dimension(140, 25));
    	deleteNordPan.add(nameToDelete);
    	deleteNordPan.add(fieldNameDelete);
    	deleteCentrePan.add(passToDelete);
    	deleteCentrePan.add(fieldPassDelete);
    	deleteBasPan.add(btnPrec);
    	deleteBasPan.add(btnDelete);
    	deleteBasPan.add(isGood);
    	deleteUserPan.add(deleteNordPan, BorderLayout.NORTH);
    	deleteUserPan.add(deleteBasPan, BorderLayout.SOUTH);
    	deleteUserPan.add(deleteCentrePan, BorderLayout.CENTER);
		deleteUserFrame.setContentPane(deleteUserPan);

		final JFrame noticeFrame = new JFrame();
		noticeFrame.setTitle("Instructions");
		/* Changer la taille de la fenetre */
		noticeFrame.setSize(600, 150);
		noticeFrame.setLocation(300,400);
    	noticeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JPanel noticePan = new JPanel();
    	noticePan.setLayout(new BorderLayout());
    	JPanel noticeBasPan = new JPanel(new FlowLayout());
    	JPanel noticeCentrePan = new JPanel(new FlowLayout());
    	JButton btnNoticePrec = new JButton("Back");
    	final JLabel noticeText = new JLabel();
		btnNoticePrec.setPreferredSize(new Dimension(140, 25));
		noticeCentrePan.add(noticeText);
    	noticeBasPan.add(btnNoticePrec);
    	noticePan.add(noticeBasPan, BorderLayout.SOUTH);
    	noticePan.add(noticeCentrePan, BorderLayout.CENTER);
		noticeFrame.setContentPane(noticePan);

		final JFrame registryResultsFrame = new JFrame();
		registryResultsFrame.setTitle("Registration results");
		/* Changer la taille de la fenetre */
		registryResultsFrame.setSize(800, 105);
		registryResultsFrame.setLocation(300,400);
    	registryResultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JPanel registryResultsPan = new JPanel();
    	registryResultsPan.setLayout(new BorderLayout());
    	JPanel registryResultsCentrePan = new JPanel(new FlowLayout());
    	JPanel resultsBasPan = new JPanel(new FlowLayout());
    	final JLabel resultsText = new JLabel();
    	JButton registryResultsBack= new JButton("Back");
    	registryResultsBack.setPreferredSize(new Dimension(140, 25));
		registryResultsCentrePan.add(resultsText);
		resultsBasPan.add(registryResultsBack);
    	registryResultsPan.add(registryResultsCentrePan, BorderLayout.CENTER);
    	registryResultsPan.add(resultsBasPan, BorderLayout.SOUTH);
		registryResultsFrame.setContentPane(registryResultsPan);

		final JFrame recoveryResultsFrame = new JFrame();
		recoveryResultsFrame.setTitle("Recovery results");
		/* Changer la taille de la fenetre */
		recoveryResultsFrame.setSize(500, 105);
		recoveryResultsFrame.setLocation(300,400);
    	recoveryResultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JPanel recoveryResultsPan = new JPanel();
    	recoveryResultsPan.setLayout(new BorderLayout());
    	JPanel recoveryResultsCentrePan = new JPanel(new FlowLayout());
    	JPanel recoveryResultsBasPan = new JPanel(new FlowLayout());
    	final JLabel recoveryResultsText = new JLabel();
    	JButton recoveryResultsQuit= new JButton("Quit");
    	recoveryResultsQuit.setPreferredSize(new Dimension(140, 25));
		recoveryResultsCentrePan.add(recoveryResultsText);
		recoveryResultsBasPan.add(recoveryResultsQuit);
    	recoveryResultsPan.add(recoveryResultsCentrePan, BorderLayout.CENTER);
    	recoveryResultsPan.add(recoveryResultsBasPan, BorderLayout.SOUTH);
		recoveryResultsFrame.setContentPane(recoveryResultsPan);

		final JFrame connectResultsFrame = new JFrame();
		connectResultsFrame.setTitle("Connection results");
		/* Changer la taille de la fenetre */
		connectResultsFrame.setSize(500, 105);
		connectResultsFrame.setLocation(300,400);
    	connectResultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JPanel connectResultsPan = new JPanel();
    	connectResultsPan.setLayout(new BorderLayout());
    	JPanel connectResultsCentrePan = new JPanel(new FlowLayout());
    	JPanel connectResultsBasPan = new JPanel(new FlowLayout());
    	final JLabel connectResultsText = new JLabel();
    	JButton connectResultsQuit= new JButton("Quit");
    	connectResultsQuit.setPreferredSize(new Dimension(140, 25));
		connectResultsCentrePan.add(connectResultsText);
		connectResultsBasPan.add(connectResultsQuit);
    	connectResultsPan.add(connectResultsCentrePan, BorderLayout.CENTER);
    	connectResultsPan.add(connectResultsBasPan, BorderLayout.SOUTH);
		connectResultsFrame.setContentPane(connectResultsPan);

		final JFrame deconnectFrame = new JFrame();
		deconnectFrame.setTitle("You're now connected !");
		/* Changer la taille de la fenetre */
		deconnectFrame.setSize(400, 95);
		deconnectFrame.setLocation(300,400);
    	deconnectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JPanel decoPan = new JPanel();
    	decoPan.setLayout(new BorderLayout());
    	JPanel decoCentrePan = new JPanel(new FlowLayout());
    	JButton decoBtn = new JButton("Deconnection");
    	decoBtn.setPreferredSize(new Dimension(140, 50));
		decoCentrePan.add(decoBtn);
    	decoPan.add(decoCentrePan, BorderLayout.CENTER);
		deconnectFrame.setContentPane(decoPan);

		/******************************* Action listeners *******************************/

		btnRegister.addActionListener(new ActionListener() {
						
		public void actionPerformed(ActionEvent e) {

				final JFrame registerform=new JFrame();
				JPanel formPan = new JPanel();

				GridBagLayout gbl_formPan = new GridBagLayout();
	       		gbl_formPan.columnWidths = new int[] {80, 100};
        		gbl_formPan.rowHeights = new int[]{0, 0, 0, 50, 0};
        		gbl_formPan.columnWeights = new double[]{0, 0, 0, 0, 0};
        		gbl_formPan.rowWeights = new double[]{0, 0, 0, 0, 0};
        		formPan.setLayout(gbl_formPan);

        		JLabel lblNewLabel = new JLabel("Name");
        		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        		gbc_lblNewLabel.insets = new Insets(15, 0, 5, 0);
        		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        		gbc_lblNewLabel.gridx = 0;
        		gbc_lblNewLabel.gridy = 0;
        		formPan.add(lblNewLabel, gbc_lblNewLabel);

        		final JTextField textField = new JTextField();
        		GridBagConstraints gbc_textField = new GridBagConstraints();
        		gbc_textField.insets = new Insets(15, 0, 5, 0);
        		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        		gbc_textField.gridx = 1;
        		gbc_textField.gridy = 0;
        		formPan.add(textField, gbc_textField);
        		textField.setColumns(5);

        		JLabel lblNewLabel_1 = new JLabel("Password");
        		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
        		gbc_lblNewLabel_1.insets = new Insets(5, 0, 5, 0);
        		gbc_lblNewLabel_1.gridx = 0;
        		gbc_lblNewLabel_1.gridy = 1;
        		formPan.add(lblNewLabel_1, gbc_lblNewLabel_1);

        		final JPasswordField textField_1 = new JPasswordField();
        		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        		gbc_textField_1.insets = new Insets(5, 0, 5, 0);
        		gbc_textField_1.gridx = 1;
        		gbc_textField_1.gridy = 1;
        		formPan.add(textField_1, gbc_textField_1);
        		textField_1.setColumns(5);

        		JLabel lblNewLabel_2 = new JLabel("Retype pass");
        		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
        		gbc_lblNewLabel_2.insets = new Insets(5, 0, 10, 0);
        		gbc_lblNewLabel_2.gridx = 0;
        		gbc_lblNewLabel_2.gridy = 2;
        		formPan.add(lblNewLabel_2, gbc_lblNewLabel_2);

        		final JPasswordField textField_2 = new JPasswordField();
        		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
        		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
        		gbc_textField_2.gridx = 1;
        		gbc_textField_2.gridy = 2;
        		formPan.add(textField_2, gbc_textField_2);
        		textField_2.setColumns(5);

        		JButton btnBack = new JButton("Back");
				btnBack.setPreferredSize(new Dimension(140, 25));
        		GridBagConstraints gbc_btnBack = new GridBagConstraints();
        		gbc_btnBack.anchor = GridBagConstraints.CENTER;
        		gbc_btnBack.insets = new Insets(15, 0, 10, 5);
        		gbc_btnBack.gridx = 0;
        		gbc_btnBack.gridy = 3;
        		formPan.add(btnBack, gbc_btnBack);

        		btnBack.addActionListener(new ActionListener() {
						
					public void actionPerformed(ActionEvent e) {

						registerform.setVisible(false);
						firstFrame.setVisible(true);

					}
				});

        		final JLabel isMatching = new JLabel();
        		GridBagConstraints gbc_isMatching = new GridBagConstraints();
        		gbc_isMatching.anchor = GridBagConstraints.EAST;
        		gbc_isMatching.insets = new Insets(10, 0, 5, 0);
        		gbc_isMatching.gridx = 2;
        		gbc_isMatching.gridy = 3;
        		formPan.add(isMatching, gbc_isMatching);

        	    JButton btnSubmit = new JButton("Submit");
				btnSubmit.setPreferredSize(new Dimension(140, 25));
        		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
        		gbc_btnSubmit.anchor = GridBagConstraints.CENTER;
        		gbc_btnSubmit.insets = new Insets(15, 0, 10, 5);
        		gbc_btnSubmit.gridx = 1;
        		gbc_btnSubmit.gridy = 3;
        		formPan.add(btnSubmit, gbc_btnSubmit);

        		btnSubmit.addActionListener(new ActionListener() {
						
					public void actionPerformed(ActionEvent e) {

						String name=textField.getText();
						char[] pass1=textField_1.getPassword();
						char[] pass2=textField_2.getPassword();
						String backupKey = "azertyazertyazertyazertyazertyaz";
						int nameExist=0;

						if(!Arrays.equals(pass1,pass2)){
							isMatching.setText("<html><font color='red'>Passwords do not match</font></html>");
						}
						else if(pass1.length==0||pass2.length==0){
							isMatching.setText("<html><font color='red'>Empty password</font></html>");
						}
						else if(pass1.length<6){
							isMatching.setText("<html><font color='red'>Password too short</font></html>");
						}
						else if(name.length()==0){
							isMatching.setText("<html><font color='red'>Empty name</font></html>");
						}
						/*else if(Arrays.equals(pass1,pass2)){

							/*Recherche de l'existence d'un fichier fingerprint avec le name*/
								
								/* si verifFingerfile==1*/
									/*Creation d'un fichier fingerprint*/
									/*fingerprint=Ce qu'on lit dans le fichier qui vient d'etre crée*/
							
							/*Generation de la backupKey (dans la procédure finalement)*/

							/*
								TODO requete_INSCRIPTION (Inscription)
							

							if(nameExist==1){
								isMatching.setText("<html><font color='red'>Name already used</font></html>");
							}
							else {

								registerform.setVisible(false);
								resultsText.setText("<html><font color='green'>Registration success ! Please write on a paper your backup key : "+backupKey+"</font></html>");
								registryResultsFrame.setVisible(true);
							}
						}*/

					}
				});


				firstFrame.setVisible(false);

				registerform.setTitle("Register");
			    /*Changer la taille de la fenetre */
				registerform.setSize(500, 200);
				registerform.setLocation(300,400);
    			registerform.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    registerform.setContentPane(formPan);
				registerform.setVisible(true);

			}
		});

		btnConnection.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {
				String logUser=fieldUsername.getText();
				char[] logPass=fieldPassword.getPassword();

				int goodCred=0;

				if(logUser.length()==0){
					isCorrect.setText("<html><font color='red'>Empty login</font></html>");
				}
				else if(logPass.length==0){
					isCorrect.setText("<html><font color='red'>Empty password</font></html>");
				}
				else if(logPass.length<6){
					isCorrect.setText("<html><font color='red'>Bad password</font></html>");
				}
				else {

					/* Verification de l'existence du fichier fingerprint
					si il n'existe pas (verifFingerFile==1) -> connexion impossible
					sinon
					aller lire dans le fichier finger la valeurFinger*/

					/*
						TODO requete_CONNEXION (Connexion)
					*/

					/* si credentials n'existent pas : goodCred==1)
					sinon : goodCred==0
					*/

					if (goodCred==1){
						connectResultsText.setText("<html><font color='red'>Error ! Please relaunch the application ...</font></html>");

						connectResultsFrame.setVisible(true);
					}
					else if(goodCred==0){

    	      			fenetreFrame.setVisible(false);
    	      			connectState.setText("<html><font color='green'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Connected</font></html>");
    	      			/*setIdUser("ce qu'on a eu plus haut");
    	      			setBackUpKey("ce qu'on a eu plus haut"); --> PAS NECESSAIRE
    	      			setNameCurrentUser(logUser);*/
						deconnectFrame.setVisible(true);
						/*lancement thread qui recoit les requetes a traiter*/
            		}

				}

			}

		});

		btnLogin.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {

				firstFrame.setVisible(false);
				fenetreFrame.setVisible(true);
			}
		});

		btnPrecedentFenetre.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {

				fenetreFrame.setVisible(false);
				firstFrame.setVisible(true);
			}
		});


		btnBackup.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {
				firstFrame.setVisible(false);
				preRecoveryFrame.setVisible(true);

				btnPrecedent.addActionListener(new ActionListener(){
      				public void actionPerformed(ActionEvent event){
						preRecoveryFrame.setVisible(false);
      					firstFrame.setVisible(true);
        
      				}
    			});

				btnRecover.addActionListener(new ActionListener(){
      				public void actionPerformed(ActionEvent event){
      				    String fileLog=fieldFile.getText();
						char[] keyLog=fieldKey.getPassword();
						char[] passLog=fieldPass.getPassword();
						int backUpState=0;

						if(keyLog.length==0){
							labelCorrect.setText("<html><font color='red'>Empty key</font></html>");
						}
						else if(fileLog.length()==0){
							labelCorrect.setText("<html><font color='red'>Empty file</font></html>");
						}
						else if(keyLog.length<6){
							labelCorrect.setText("<html><font color='red'>Key too short</font></html>");
						}
						else if(passLog.length==0){
							labelCorrect.setText("<html><font color='red'>Empty password</font></html>");
						}
						else if(passLog.length<6){
							labelCorrect.setText("<html><font color='red'>Bad password</font></html>");
						}
						/*
						sinon si 'fileLog' n'existe pas (verifLogFile==1) : labelCorrect.setText("<html><font color='red'>File does not exist</font></html>");
						*/
						/*
						sinon si 'fileLog' est vide (verifLogFile==2) : labelCorrect.setText("<html><font color='red'>File is empty</font></html>");
						*/
						else {
							/*
								TODO recupération des logs(recupLog) + déchiffrement(decrypt avec keylog) + recovery bdd
          					*/

							/* si traitement fail : backUpState==1)
							sinon : backUpState==0
							
							if (backUpState==1){
    	      					recoveryResultsText.setText("<html><font color='red'>Backup error : please relaunch the recovery ...</font></html>");	          	
							}
							else if(backUpState==0){

    	      					recoveryResultsText.setText("<html><font color='green'>Backup done : please relaunch the application ...</font></html>");	          	

            				}

            				*/
   	      					recoveryResultsText.setText("<html><font color='green'>Backup done : please relaunch the application ...</font></html>");	          	

							preRecoveryFrame.setVisible(false);
							recoveryResultsFrame.setVisible(true);


						}
        
      				}
    			});  

			}
		});

		btnSupprUser.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {

				firstFrame.setVisible(false);
				deleteUserFrame.setVisible(true);
			}
		});

		btnPrec.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {

				deleteUserFrame.setVisible(false);
				firstFrame.setVisible(true);
			}
		});

		btnDelete.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {
      		    String nameDelete=fieldNameDelete.getText();
			    char[] passDelete=fieldPassDelete.getPassword();
			    int userExist=0;


			    if(passDelete.length==0){
			    	isGood.setText("<html><font color='red'>Empty pass</font></html>");
			    }
			    else if(passDelete.length<6){
			    	isGood.setText("<html><font color='red'>Bad password</font></html>");
			    }
			    else if(nameDelete.length()==0){
			    	isGood.setText("<html><font color='red'>Empty name</font></html>");
			    }
			    
			    /*
					TODO requete_SUPPRUSER (PreSupprUser)
			    */
			    	
				if(userExist==0){
			    	isGood.setText("<html><font color='green'>User '"+nameDelete+"' deleted</font></html>");
			    }
			    else if(userExist==1){
			    	isGood.setText("<html><font color='red'>User not found</font></html>");
  			    }
			    
			}
		});

		btnNotice.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {

				noticeText.setText("<html> <B><U> Register :</U></B> register a new account <br><B><U> Log in :</U></B> connection with an already existing account (fingerprint file needed) <br><B><U> Delete user :</U></B> delete an existing account <br><B><U> TCell Recovery :</U></B> use a backup key to restore your token</html>");
				firstFrame.setVisible(false);
				noticeFrame.setVisible(true);
			}
		});

		btnNoticePrec.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {

				noticeFrame.setVisible(false);
				firstFrame.setVisible(true);
			}
		});

		registryResultsBack.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {

				registryResultsFrame.setVisible(false);
				firstFrame.setVisible(true);
			}
		});

		recoveryResultsQuit.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {

				recoveryResultsFrame.setVisible(false);
				System.exit(0);
			}
		});

		connectResultsQuit.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {

				connectResultsFrame.setVisible(false);
				System.exit(0);
			}
		});

		decoBtn.addActionListener(new ActionListener() {
						
			public void actionPerformed(ActionEvent e) {

				deconnectFrame.setVisible(false);
				connectState.setText("<html><font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Not connected</font></html>");

				/*setIdUser(-1);
				setBackUpKey(null);
				setNameCurrentUser(null);*/

				firstFrame.setVisible(true);

				/*annulation thread qui recoit les requetes a traiter*/
			}
		});



		connectState.setText("<html><font color='red'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Not connected</font></html>");		
		firstFrame.setVisible(true);

	}

	public void setIdUser(int id){
		this.IdUser=id;
	}

	public void setBackUpKey(String bck){
		this.BackupKey=bck;
	}

	public void setNameCurrentUser(String name){
		this.nameCurrentUser=name;
	}

}
