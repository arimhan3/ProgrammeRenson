package principal;

import java.awt.*;

import javax.swing.*;

public abstract class BasePanel extends JPanel {
	protected JFrame parent;
	protected JPanel himself;
	protected GridBagConstraints gbc;
	public BasePanel(JFrame f, LayoutManager layout)
	{
		super();
		parent = f;
		himself=this;
		//GBC utilie seulement si gridBagLayout
		gbc = new GridBagConstraints();
		gbc.gridx = gbc.gridy = 0; // la grille commence en (0, 0)
		gbc.gridheight = 1; // valeur par défaut - peut s'étendre sur une seule ligne.
		gbc.anchor = GridBagConstraints.LINE_START;//positionne par la gauche
		gbc.insets = new Insets(5, 5, 0, 0);//marge entre les composants
		
		setBounds(0,10,600,400);
		setLayout(layout);		
	}
	abstract public void actual();
}
