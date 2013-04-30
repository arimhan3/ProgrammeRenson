package divers;

import java.text.*;
import java.util.*;
import javax.swing.*;

public class Calendrier extends JPanel {
	private JComboBox<String>[] combos = new JComboBox[3];
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    final int ranges[][] = {{1,31},{1,12},{2012,2050}};
    public Calendrier() {
        super();
        combos[0] =  new JComboBox<String>();
        combos[1] =  new JComboBox<String>();
        combos[2] =  new JComboBox<String>();
        // Fill the combos
        for (int i = 0; i<combos.length; i++)
            for (int j = ranges[i][0]; j<=ranges[i][1]; j++) 
                combos[i].addItem((j<9?"0":"")+Integer.toString(j));

        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        for (JComboBox<String> c: combos) { 
            this.add(c);
        }

        // Set to today's date
        setDate(new Date());
    }

    // Date argument constructor
    public Calendrier(Date date) {
        this();
        setDate(date);
    }

    public void setDate(Date d) {
        String[] date = df.format(d).split("/");
        for (int i=0;i<combos.length; i++)
            combos[i].setSelectedItem(date[i]);
    }

    public String getDate() {
        String str = combos[2].getSelectedItem()+"-"+combos[1].getSelectedItem()+"-"+combos[0].getSelectedItem();
        return str;
    }
}
