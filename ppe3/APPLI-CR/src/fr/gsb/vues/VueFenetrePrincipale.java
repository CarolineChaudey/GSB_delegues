package fr.gsb.vues;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.gsb.controleurs.ControleurFenetrePrincipale;

public class VueFenetrePrincipale extends JFrame {

	private ControleurFenetrePrincipale controleur ;
	
	private VueAccueil vueAccueil = new VueAccueil();
	private VueCR vueCR = new VueCR();
	private VuePraticiens vuePraticiens = new VuePraticiens();
	private CardLayout clVues = new CardLayout(5,5);

	private JMenu menuFichier = new JMenu("Fichier");
	private JMenu menuCR = new JMenu("Comptes-rendus");
	private JMenu menuPraticien = new JMenu("Praticien");
	private JMenuItem itemConnecter = new JMenuItem("Connection");
	private JMenuItem itemDeconnecter = new JMenuItem("Déconnection");
	private JMenuItem itemQuitter = new JMenuItem("Quitter");
	private JMenuItem itemVisiteurs = new JMenuItem("Visiteurs");
	private JMenuItem itemHesitants = new JMenuItem("Hésitants");
	
	public VueFenetrePrincipale(){
		super();
		this.setTitle("Application CR");
		this.setSize(800, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.creerBarreMenu();
		this.setVisible(true);
		controleur = new ControleurFenetrePrincipale(this);
		this.setLayout();
		
	}
	
	private void creerBarreMenu(){
		JMenuBar barreMenus = new JMenuBar();
		
		this.menuFichier.add(itemConnecter);
		this.menuFichier.add(itemDeconnecter);
		this.menuFichier.add(itemQuitter);
		this.menuCR.add(itemVisiteurs);
		this.menuPraticien.add(itemHesitants);
		
		barreMenus.add(menuFichier);
		barreMenus.add(menuCR);
		barreMenus.add(menuPraticien);
		
		this.setMenuDeconnecte();
		
		this.setJMenuBar(barreMenus);
	}
	
	private void setLayout(){
		this.getContentPane().setLayout(clVues);
		this.add(this.vueAccueil, "vueAccueil");
		this.add(this.vueCR, "vueCR");
		this.add(this.vuePraticiens, "vuePraticiens");
	}
	
	public void setMenuConnecte(){
		this.itemConnecter.setEnabled(false);
		this.itemDeconnecter.setEnabled(true);
		this.menuCR.setEnabled(true);
		this.menuPraticien.setEnabled(true);
	}
	
	public void setMenuDeconnecte(){
		this.itemConnecter.setEnabled(true);
		this.itemDeconnecter.setEnabled(false);
		this.menuCR.setEnabled(false);
		this.menuPraticien.setEnabled(false);
	}

	public void changerVue(String vue){
		System.out.println("VueFenetrePrincipale :: changerVue");
		this.clVues.show(this.getContentPane(), vue);
	}
	
	public ControleurFenetrePrincipale getControleur() {
		return controleur;
	}

	public void setControleur(ControleurFenetrePrincipale controleur) {
		this.controleur = controleur;
	}

	public JMenu getMenuFichier() {
		return menuFichier;
	}

	public void setMenuFichier(JMenu menuFichier) {
		this.menuFichier = menuFichier;
	}

	public JMenu getMenuCR() {
		return menuCR;
	}

	public void setMenuCR(JMenu menuCR) {
		this.menuCR = menuCR;
	}

	public JMenu getMenuPraticien() {
		return menuPraticien;
	}

	public void setMenuPraticien(JMenu menuPraticien) {
		this.menuPraticien = menuPraticien;
	}

	public JMenuItem getItemConnecter() {
		return itemConnecter;
	}

	public void setItemConnecter(JMenuItem itemConnecter) {
		this.itemConnecter = itemConnecter;
	}

	public JMenuItem getItemDeconnecter() {
		return itemDeconnecter;
	}

	public void setItemDeconnecter(JMenuItem itemDeconnecter) {
		this.itemDeconnecter = itemDeconnecter;
	}

	public JMenuItem getItemQuitter() {
		return itemQuitter;
	}

	public void setItemQuitter(JMenuItem itemQuitter) {
		this.itemQuitter = itemQuitter;
	}

	public JMenuItem getItemVisiteurs() {
		return itemVisiteurs;
	}

	public void setItemVisiteurs(JMenuItem itemVisiteurs) {
		this.itemVisiteurs = itemVisiteurs;
	}

	public JMenuItem getItemHesitants() {
		return itemHesitants;
	}

	public void setItemHesitants(JMenuItem itemHesitants) {
		this.itemHesitants = itemHesitants;
	}

	public VueAccueil getVueAccueil() {
		return vueAccueil;
	}

	public void setVueAccueil(VueAccueil vueAccueil) {
		this.vueAccueil = vueAccueil;
	}

	public VueCR getVueCR() {
		return vueCR;
	}

	public void setVueCR(VueCR vueCR) {
		this.vueCR = vueCR;
	}

	public VuePraticiens getVuePraticiens() {
		return vuePraticiens;
	}

	public void setVuePraticiens(VuePraticiens vuePraticiens) {
		this.vuePraticiens = vuePraticiens;
	}

	public CardLayout getClVues() {
		return clVues;
	}

	public void setClVues(CardLayout clVues) {
		this.clVues = clVues;
	}
	
	
}
