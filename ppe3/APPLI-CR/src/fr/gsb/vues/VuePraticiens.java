package fr.gsb.vues;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.gsb.technique.ModeleListePraticiens;

public class VuePraticiens extends JPanel {
	
	private JTable tableau = new JTable();
	private ModeleListePraticiens modele = new ModeleListePraticiens();
	//private Box boiteGenerale = Box.createVerticalBox();
	//private Box boiteTableau = Box.createVerticalBox();

	public VuePraticiens() {
		super();
		System.out.println("VuePraticiens :: VuePraticiens()");
//		this.tableau.setModel(modele);
		this.creerInterface();
		// TODO Auto-generated constructor stub
	}
	
	private void creerInterface(){
		Box boiteGenerale = Box.createVerticalBox();
		Box boiteTitre = Box.createHorizontalBox();
		boiteTitre.add(new JLabel("Praticiens hésitants :"));
		
		Box boiteTableau = Box.createVerticalBox();
		this.tableau.setModel(this.modele);
		tableau.setRowHeight(30);	
		JScrollPane panneauAsc = new JScrollPane(tableau);
		panneauAsc.setPreferredSize(new Dimension(750,400));
		boiteTableau.add(panneauAsc);
		
		boiteGenerale.add(boiteTitre);
		boiteGenerale.add(boiteTableau);
		this.add(boiteGenerale);
	}

	public JTable getTableau() {
		return tableau;
	}

	public void setTableau(JTable tableau) {
		this.tableau = tableau;
	}

	public ModeleListePraticiens getModeleLP() {
		return modele;
	}

	public void setModeleLP(ModeleListePraticiens modele) {
		this.modele = modele;
	}
	
//	public void activer(){
//		System.out.println("VuePraticiens :: activer()");
//		this.modele = new ModeleListePraticiens();
//		modele.actualiser();
//		this.tableau.setModel(this.modele);
//		tableau.setRowHeight(30);
//		
//		JScrollPane panneauAsc = new JScrollPane(tableau);
//		panneauAsc.setPreferredSize(new Dimension(750,400));
//    	boiteTableau.add(panneauAsc);
//		this.boiteGenerale.add(boiteTableau);
//	}
//	
//
//	public void desactiver(){
//		System.out.println("VuePraticiens :: déactiver()");
//		this.modele = null;
//		//this.boiteTableau.
//	}

}
