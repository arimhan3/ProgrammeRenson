package divers;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import principal.*;
import bdd.*;

public class ChangePass extends JDialog {
	private Container contain;
	private JFrame parent;
	private JLabel pass, verifPass;
	private JPasswordField passIN, verifPassIN;
	private JButton change;
	private JPanel panel;
	public ChangePass(JFrame f)
	{
		super(f, "Changer le MDP");
		parent = f;
		contain = getContentPane();
		setModal(true);
		panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));//bordure noire autour du JPanel
		panel.setLayout(new GridBagLayout());
		panel.setSize(400,200);
		setMinimumSize(new Dimension(400,200));
		//JLabel
		pass = new JLabel("Entrez le nouveau MDP:");
		verifPass = new JLabel("Rentrez le MDP:");
		pass.setPreferredSize(new Dimension(100,20));
		verifPass.setPreferredSize(new Dimension(100,20));
		//JButton
		change = new JButton("changer le MDP");
		//JPasswordField
		passIN = new JPasswordField();
		verifPassIN = new JPasswordField();
		passIN.setPreferredSize(new Dimension(100,20));
		verifPassIN.setPreferredSize(new Dimension(100,20));
		//ajout des listener
		addWindowListener(new WindowAdapter()
				{
					public void windowClosing( WindowEvent e)
					{ 
						dispose(); 
					} 
				});
		change.addActionListener(new ActionListener()
				{					
					public void actionPerformed(ActionEvent e) 
					{						
						changePass();
					}				
				});
		//ajout au panel
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0; // la grille commence en (0, 0)
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(10, 10, 10, 10);//marge entre les composants
		panel.add(pass , gbc);
		gbc.gridx = 1;
		panel.add(passIN , gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(verifPass , gbc);
		gbc.gridx = 1;
		panel.add(verifPassIN , gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;;
		panel.add(change , gbc);
		//ajout du panel dans le container
		add(panel);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void changePass()
	{
		if(new String(passIN.getPassword()).equals(new String(verifPassIN.getPassword())))
		{
			String instSQL = "UPDATE MDP FROM membres WHERE nom=";
			instSQL += ((Fenetre)parent).getUser();
			try
			{
				AccessBDGen.executerInstruction(((Fenetre)parent).getBDD(), instSQL);
				dispose();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(this,e,"Erreur de BDD" ,JOptionPane.ERROR_MESSAGE);				
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this,"Les MDP ne correspondent pas","Erreur" ,JOptionPane.ERROR_MESSAGE);
		}
	}
}
