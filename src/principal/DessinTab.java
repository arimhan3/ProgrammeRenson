package principal;

import java.awt.*;
import javax.swing.*;
import jpanelUser.*;
import jpanelAdmin.*;

public class DessinTab {
	public static void setWindowsUser(JFrame parent, JTabbedPane onglet)
	{
		//dessin de la fenetre User
		//ajout des onglets
		onglet.addTab("Compte",new CompteUser(parent));
		onglet.addTab("Calendrier",new CalendrierAdmin(parent));
		parent.getContentPane().add(onglet);
	}
	public static void setWindowsAdmin(JFrame parent,JTabbedPane onglet)
	{
		//dessin de la fenetre Admin
		//ajout des onglets
		onglet.add("Compte",new CompteAdmin(parent));
		onglet.add("Ajout de membre",new AddMembre(parent));
		onglet.add("Suppression de membre",new SuppMembre(parent));
		onglet.add("Ajout d'event",new AddEvent(parent));
		onglet.add("Suppression d'event",new SuppEvent(parent));
		onglet.add("Ajout d'un club",new AddClub(parent));
		onglet.add("Suppression d'un club",new SuppClub(parent));
		onglet.add("Calendrier",new CalendrierAdmin(parent));
		onglet.add("Info membres",new InfoMembre(parent));
		parent.getContentPane().add(onglet);
	}

}
