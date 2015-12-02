package fr.gsb.vues;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueAccueil extends JPanel{

	public VueAccueil() {
		super();
		this.creerInterface();
		// TODO Auto-generated constructor stub
	}
	
	private void creerInterface(){
		this.add(new JLabel("Accueil"));
	}
	

}
