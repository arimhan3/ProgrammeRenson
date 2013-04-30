package jpanelAdmin;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.*;
import bdd.*;
import divers.*;
import principal.*;

public class CompteAdmin extends BasePanel {
	private JLabel bloc,nom,prenom,mail,tel1,tel2,rue,cp,ville,dateNaiss, dateVisite, listAff, etat, mdp, com;
	private JTextField nomIN,prenomIN,mailIN,tel1IN,tel2IN,rueIN,cpIN,villeIN, dateVisiteIN, etatIN;
	private JButton enreg, deco, mdpIN;
	private Calendrier dateNaissIN;
	private JComboBox listeClub;
	private JTextArea commIN;
	public CompteAdmin(JFrame f)
	{
		super(f,new GridBagLayout());
		//JLabel
		nom = new JLabel("nom");
		prenom = new JLabel("prenom");
		mail = new JLabel("mail");
		tel1 = new JLabel("tel1");
		tel2 = new JLabel("tel2");
		rue = new JLabel("rur");
		cp = new JLabel("cp");
		ville = new JLabel("ville");
		dateNaiss = new JLabel("dateNaiss");
		dateVisite = new JLabel("dateVisite");
		listAff = new JLabel("listeAFF");
		etat = new JLabel("etat");
		mdp = new JLabel("mdp");
		com = new JLabel("com");
		bloc = new JLabel();
		bloc.setPreferredSize(new Dimension(20,30));
		nom.setPreferredSize(new Dimension(150,30));
		prenom.setPreferredSize(new Dimension(150,30));
		mail.setPreferredSize(new Dimension(150,30));
		tel1.setPreferredSize(new Dimension(150,30));
		tel2.setPreferredSize(new Dimension(150,30));
		rue.setPreferredSize(new Dimension(150,30));
		cp.setPreferredSize(new Dimension(150,30));
		ville.setPreferredSize(new Dimension(150,30));
		dateNaiss.setPreferredSize(new Dimension(150,30));
		dateVisite.setPreferredSize(new Dimension(150,30));
		listAff.setPreferredSize(new Dimension(150,30));
		etat.setPreferredSize(new Dimension(150,30));
		mdp.setPreferredSize(new Dimension(150,30));
		com.setPreferredSize(new Dimension(150,30));
		//JButton
		enreg = new JButton("enreg");
		deco = new JButton("deco");
		mdpIN = new JButton("changer mdp");
		enreg.setPreferredSize(new Dimension(130,30));
		deco.setPreferredSize(new Dimension(130,30));
		mdpIN.setPreferredSize(new Dimension(130,30));
		//JtextField
		nomIN = new JTextField();
		prenomIN = new JTextField();
		mailIN = new JTextField();
		tel1IN = new JTextField();
		tel2IN = new JTextField();
		rueIN = new JTextField();
		cpIN = new JTextField();
		villeIN = new JTextField();
		dateVisiteIN = new JTextField();
		dateVisiteIN.setEditable(false);
		etatIN = new JTextField();
		nomIN.setPreferredSize(new Dimension(200,30));
		prenomIN.setPreferredSize(new Dimension(200,30));
		mailIN.setPreferredSize(new Dimension(200,30));
		tel1IN.setPreferredSize(new Dimension(200,30));
		tel2IN.setPreferredSize(new Dimension(200,30));
		rueIN.setPreferredSize(new Dimension(200,30));
		cpIN.setPreferredSize(new Dimension(200,30));
		villeIN.setPreferredSize(new Dimension(200,30));
		dateVisiteIN.setPreferredSize(new Dimension(200,30));
		etatIN.setPreferredSize(new Dimension(200,30));
		//Jcombobox
		listeClub = new JComboBox();
		listeClub.setPreferredSize(new Dimension(200,30));
		//JtextArea
		commIN = new JTextArea();
		commIN.setPreferredSize(new Dimension(200,100));
		//Calendrier
		dateNaissIN = new Calendrier();
		//Ajout des Listener
		deco.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						((Fenetre)parent).decoBDD();				
					}					
				});
		enreg.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						enr();	
					}					
				});
		mdpIN.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						new ChangePass(parent);			
					}					
				});
		//Ajout au GridBagLayout
		gbc.anchor = GridBagConstraints.EAST;
		add(nom,gbc);
		gbc.gridx = 1;
		add(nomIN,gbc);
		gbc.gridx = 2;
		add(bloc,gbc);
		gbc.gridx = 3;
		add(listAff,gbc);
		gbc.gridx = 4;
		add(listeClub,gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		add(prenom,gbc);
		gbc.gridx = 1;
		add(prenomIN,gbc);
		gbc.gridx = 3;
		add(etat,gbc);
		gbc.gridx = 4;
		add(etatIN,gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(mail,gbc);
		gbc.gridx = 1;
		add(mailIN,gbc);
		gbc.gridx = 3;
		add(mdp,gbc);
		gbc.gridx = 4;
		add(mdpIN,gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		add(tel1,gbc);
		gbc.gridx = 1;
		add(tel1IN,gbc);
		gbc.gridx = 3;
		add(com,gbc);	
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(tel2,gbc);
		gbc.gridx = 1;
		add(tel2IN,gbc);
		gbc.gridx = 4;
		gbc.gridheight = 3;
		gbc.gridwidth = 1;
		add(commIN,gbc);
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(rue,gbc);
		gbc.gridx = 1;
		add(rueIN,gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		add(cp,gbc);
		gbc.gridx = 1;
		add(cpIN,gbc);
		gbc.gridx = 0;
		gbc.gridy = 7;
		add(ville,gbc);
		gbc.gridx = 1;
		add(villeIN,gbc);
		gbc.gridx = 0;
		gbc.gridy = 8;
		add(dateNaiss,gbc);
		gbc.gridx = 1;
		add(dateNaissIN,gbc);
		gbc.gridx = 4;
		add(enreg,gbc);
		gbc.gridx = 0;
		gbc.gridy = 9;
		add(dateVisite,gbc);
		gbc.gridx = 1;
		add(dateVisiteIN,gbc);
		gbc.gridx = 4;
		add(deco,gbc);
		actual();
	}
	public void actual() 
	{
		//requete SQL
		String reqSQL = "SELECT * FROM membres WHERE nom=";
		reqSQL += ((Fenetre)parent).getUser();
		try 
		{
			TableModel model = AccessBDGen.creerTableModel(((Fenetre)parent).getBDD(),reqSQL);
		} 
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(this,e,"Erreur" ,JOptionPane.ERROR_MESSAGE);
		}
		//remplir les JtextField
		//remplir la Jcombobox	
		//remplir le Calendrier
	}
	public void enr()
	{
		String reqSQL = "UPDATE ";
		
		try
		{
			AccessBDGen.executerInstruction(((Fenetre)parent).getBDD(), reqSQL);
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(this,e,"Erreur" ,JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
