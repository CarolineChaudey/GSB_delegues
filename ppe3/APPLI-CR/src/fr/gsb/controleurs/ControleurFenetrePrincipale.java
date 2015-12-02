package fr.gsb.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import fr.gsb.modeles.Modele;
import fr.gsb.technique.Connexion;
import fr.gsb.technique.EtatTentativeConnexion;
import fr.gsb.technique.Session;
import fr.gsb.vues.VueAuthentification;
import fr.gsb.vues.VueFenetrePrincipale;
import fr.gsb.vues.VueDialogueCriterePrat;

public class ControleurFenetrePrincipale implements ActionListener{

	private VueFenetrePrincipale vue = null;
	//private VueAuthentification vueAuth = null;
	
	public ControleurFenetrePrincipale(VueFenetrePrincipale vue) {
		super();
		System.out.println("ControleurFenetrePrincipale :: ControleurFenetrePrincipale");
		this.vue = vue;
		this.enregistrerEcouteur();
	}
	
	private void enregistrerEcouteur(){
		System.out.println("ControleurFenetrePrincipale :: enregistrerEcouteur");
		
		this.vue.getItemConnecter().addActionListener(this);
		this.vue.getItemDeconnecter().addActionListener(this);
		this.vue.getItemQuitter().addActionListener(this);
		this.vue.getItemVisiteurs().addActionListener(this);
		this.vue.getItemHesitants().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("ControleurFenetrePrincipale :: actionPerformed");
		//System.out.println("trace1");
		Object source = e.getSource();
		//System.out.println("trace2");
		if(source == this.vue.getItemConnecter()){
			
			VueAuthentification vueAuth = new VueAuthentification(this.vue);
		}
		else if(source == this.vue.getItemDeconnecter()){
			String message = "Voulez-vous vraiment vous déconnecter ?";
			int reponse = JOptionPane.showConfirmDialog(null, message, "Déconnexion ?", JOptionPane.YES_NO_OPTION);
			if(reponse == JOptionPane.YES_OPTION){
				Session.fermerSession();
				this.vue.setMenuDeconnecte();
				this.vue.changerVue("vueAccueil");
			}
		}
		else if(source == this.vue.getItemQuitter()){
			System.out.println("Appuie sur quitter");
			
			if(Session.getSession() != null){
				System.out.println("La session n'est pas nulle");
				Session.fermerSession();
			}
			Connexion.fermerConnexion();
			System.exit(0);
			
		}
		else if(source == this.vue.getItemVisiteurs()){
			this.vue.changerVue("vueCR");
		}
		else if(source == this.vue.getItemHesitants()){
			new VueDialogueCriterePrat(this.vue);
			//Modele.getModele().getPraticien();
		}
	}
	
}
