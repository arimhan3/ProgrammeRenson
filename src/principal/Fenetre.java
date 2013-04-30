package principal;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import bdd.*;

public class Fenetre extends JFrame {
	
	private Container contain;
	private Connection bdd;
	private JPanel etat;
	private boolean admin;
	private JTabbedPane onglet;
	private String session;
	public Fenetre(String titre, int x, int y, int l, int h ) 
	{
		//Appel au constructeur de JFrame
		super(titre);
		//taille de la fenetre
		setBounds(x,y,l,h);
		setMinimumSize(new Dimension(l,h));
		//init des composants
		contain = getContentPane();//placer le container
		contain.setLayout(null);//creation du BorderLayout
		onglet = new JTabbedPane ();
		onglet.setBounds(0, 0,contain.getWidth(),contain.getHeight() );
		connexionBDD(this);
		//Listener d'event pour fermer la fenetre
		this.addWindowListener( 
					new WindowAdapter( )
					{ 	public void windowClosing( WindowEvent e)
						{ 
							System.exit(0); 
						} 
					} );
		//redimensionner les JPanel quand la fenetre change de taille
		this.addComponentListener(
				new ComponentAdapter()
				{
					public void componentResized(ComponentEvent e)
					{
						onglet.setSize(getWidth()-15,getHeight()-39);
					}       
				});
		//afficher a l'ecran
		setVisible(true);
	}
	public Connection getBDD()
	{
		return bdd;
	}
	public void connexionBDD(JFrame parent)
	{
		admin = new DialogConnect(parent).getAdmin();
		onglet.removeAll();
		if(admin)
		{
			DessinTab.setWindowsAdmin(parent, onglet);
		}
		else
		{
			DessinTab.setWindowsUser(parent, onglet);
		}
	}
	public void decoBDD() //Deconnection de la BDD active
	{
		if( bdd != null)
		{
			if( JOptionPane.showConfirmDialog(this, "Voulez-vous vous déconnecter ?", "Déconnexion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE)== 0)
			{
				try
				{
					AccessBDGen.deconnecter ( bdd );
					JOptionPane.showMessageDialog(this,"Deconnexion réussie","Victoire",JOptionPane.INFORMATION_MESSAGE);
					onglet.removeAll();
					this.connexionBDD(this);
				}
				catch(SQLException e)
				{
					JOptionPane.showMessageDialog(this,e,"Erreur de déconnexion",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Pas de connexion en cours","Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void setAdminON()
	{
		admin = true;
	}
	public void setBDD(Connection co)
	{
		bdd = co;
	}
	public String getUser() 
	{
		return session;
	}
	public void setUser(String s)
	{
		session = s;
	}
}
