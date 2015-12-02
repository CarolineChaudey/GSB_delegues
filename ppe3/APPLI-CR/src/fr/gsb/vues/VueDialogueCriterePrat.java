package fr.gsb.vues;

import java.awt.Container;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import fr.gsb.controleurs.ControleurCriterePrat;

public class VueDialogueCriterePrat extends JDialog{

	private VueFenetrePrincipale vue = null;
	private ControleurCriterePrat ctrl = null;
	
	private JRadioButton coeffConf = new JRadioButton("Par coefficient de confiance croissant");
	private JRadioButton derVisite = new JRadioButton("Par temps écoulé depuis la dernière visite (décroissant)");
	private JRadioButton notoriete = new JRadioButton("Par coefficient de notoriété décroissant");
	private JButton valider = new JButton("Valider");
	private JButton retour = new JButton("Retour");
	
	
	public VueDialogueCriterePrat(VueFenetrePrincipale vue){
		super();
		System.out.println("VueDialogueCriterePrat :: VueDialogueCriterePrat()");
		this.vue = vue;
		this.ctrl = new ControleurCriterePrat(this);
		this.setLocationRelativeTo(vue);
		this.setTitle("Critère de tri");
		this.setResizable(false);
		this.creerInterface();
		this.pack();
		this.setVisible(true);
	}
	
	private void creerInterface(){
		
		ButtonGroup groupeBoutons = new ButtonGroup() ;
		groupeBoutons.add(this.coeffConf) ;
		groupeBoutons.add(this.derVisite) ;
		groupeBoutons.add(this.notoriete) ;
		
		
		Container conteneur = this.getContentPane();
		
		Box boxPrincipale = Box.createVerticalBox();
		//Box boxRadio = Box.createHorizontalBox();
		Box boxBoutonsRadio = Box.createVerticalBox();
		Box boxBoutons = Box.createHorizontalBox();
		
		//JLabel titre = new JLabel("Choisissez votre critère de tri :");
		//boxRadio.add(titre);
		boxBoutonsRadio.add(this.coeffConf);
		this.coeffConf.setSelected(true);
		boxBoutonsRadio.add(this.derVisite);
		boxBoutonsRadio.add(this.notoriete);
		boxBoutonsRadio.setBorder(new TitledBorder("Choisissez votre critère de tri :"));
		//boxRadio.add(boxBoutonsRadio);
		
		boxBoutons.add(Box.createHorizontalGlue()) ;
		boxBoutons.add(this.retour);
		boxBoutons.add(Box.createHorizontalStrut(5)) ;
		boxBoutons.add(this.valider);
		boxBoutons.add(Box.createHorizontalGlue()) ;
		
		boxPrincipale.add(boxBoutonsRadio);
		boxPrincipale.add(boxBoutons);
		
		conteneur.add(boxPrincipale);
		
	}

	public VueFenetrePrincipale getVue() {
		return vue;
	}

	public JRadioButton getCoeffConf() {
		return coeffConf;
	}

	public JRadioButton getDerVisite() {
		return derVisite;
	}

	public JRadioButton getNotoriete() {
		return notoriete;
	}

	public JButton getValider() {
		return valider;
	}

	public JButton getRetour() {
		return retour;
	}
	
}
