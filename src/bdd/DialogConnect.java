package bdd;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import bdd.*;
import principal.*;

public class DialogConnect extends JDialog{
	private Connection bdd;
	private DialogConnect himself;
	private JFrame parent;
	private JLabel info, password, login ;
	private JTextField loginIN;
	private JPasswordField passwordIN;
	private JButton connection;
	private JPanel panel;
	private boolean admin;
	public DialogConnect(JFrame par)
	{
		super(par, "MAS: Management All Sports");
		himself = this;
		parent = par;
		admin = false;
		setModal(true);
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));//bordure noire autour du JPanel
		panel.setLayout(new GridBagLayout());
		panel.setSize(400,200);
		setMinimumSize(new Dimension(400,200));
		//Init des elements
		info = new JLabel("Entrez vos informations de connexion");
		login = new JLabel("Login :");
		password = new JLabel("Mot de passe :");
		passwordIN = new JPasswordField();
		passwordIN.setPreferredSize(new Dimension(100,20));
		loginIN = new JTextField();
		loginIN.setPreferredSize(new Dimension(100,20));
		//Login par defaut pour les test
		//
		passwordIN.setText("popol");
		loginIN.setText("claude");
		//
		//
		connection = new JButton("Connection");
		connection.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e)
					{
						connect();
					}
				});
		//Pour co lors de l'appui sur enter
		KeyAdapter enter = new KeyAdapter()
		{
			public void keyReleased(KeyEvent e)
			{
				if(e.getKeyCode()== KeyEvent.VK_ENTER)
				{
					connect();
				}
			}					
		};
		passwordIN.addKeyListener(enter);
		loginIN.addKeyListener(enter);
		addWindowListener(new WindowAdapter()
				{
					public void windowClosing( WindowEvent e)
					{ 
						System.exit(0); 
					} 
				});
		//contraintes du GridBag
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0; // la grille commence en (0, 0)
		gbc.gridwidth = GridBagConstraints.REMAINDER; // seul composant de sa colonne
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(10, 10, 10, 10);//marge entre les composants
		//ajout au panel
		panel.add(info , gbc);
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		panel.add(login,gbc);
		gbc.gridx = 1;
		panel.add(loginIN,gbc); 
		gbc.gridy = 2;
		gbc.gridx = 0;
		panel.add(password , gbc);
		gbc.gridx = 1;
		panel.add(passwordIN , gbc);
		gbc.gridy = 3;
		gbc.gridx=0;
		panel.add( connection, gbc);
		gbc.gridx = 1;
		add(panel);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private void connect()
	{
		try
		{
			bdd = AccessBDGen.connecter ( "Renson", " ", "popol");
			if(bdd !=null && !(loginIN.getText().isEmpty()))
			{
				String instSQL = "SELECT MDP,Admin FROM Membres WHERE [Nom]='";
				instSQL+= loginIN.getText();
				instSQL+= "'";
				TableModel model = AccessBDGen.creerTableModel(bdd, instSQL);
				if((model.getRowCount() != 0) && ((String) model.getValueAt(0,0)).equals(new String(passwordIN.getPassword())))
				{
					((Fenetre)parent).setBDD(bdd);
					if((boolean)(model.getValueAt(0,1)))
					{
						admin = true;
					}
					((Fenetre)parent).setUser(loginIN.getText());
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(himself,"Mot de passe et/ou identifiant incorrect","Erreur" ,JOptionPane.ERROR_MESSAGE);
					passwordIN.setText("");
					AccessBDGen.deconnecter(bdd);
				}
				
			}
			if(true)
			{
				((Fenetre) parent).setAdminON();
			}
		}
		catch(SQLException exc)
		{
			JOptionPane.showMessageDialog(himself,exc ,"Erreur" ,JOptionPane.ERROR_MESSAGE);
		}
	}
	public boolean getAdmin()
	{
		return admin;
	}

}
