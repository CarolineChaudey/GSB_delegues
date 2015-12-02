package fr.gsb.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import fr.gsb.entites.Delegue;
import fr.gsb.modeles.Modele;
import fr.gsb.technique.EtatTentativeConnexion;
import fr.gsb.technique.Session;
import fr.gsb.vues.VueAuthentification;

public class ControleurAuthentification implements ActionListener {

	private EtatTentativeConnexion etatTentativeConnexion = null;
	private VueAuthentification vue = null;
	private Modele modele = Modele.getModele();
	
	
	public ControleurAuthentification(VueAuthentification vue) {
		super();
		this.vue = vue;
		this.enregistrerEcouteur();
		// TODO Auto-generated constructor stub
	}
	
	private void enregistrerEcouteur(){
		this.vue.getbConnecter().addActionListener(this);
		this.vue.getbAnnuler().addActionListener(this);
	}
	
	public EtatTentativeConnexion getEtatConnexion(){
		return this.etatTentativeConnexion;
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("ControleurAuthentification :: actionPerformed");
		Object source = e.getSource();
		if(source == this.vue.getbConnecter()){
			
			String login = this.vue.getLogin().getText();
			char[] mdp = this.vue.getMdp().getPassword();
			String mdpString = new String(mdp);
			System.out.println(login);
			System.out.println(mdp);
			Delegue delegue = this.modele.getDelegue(login, mdpString);
			//System.out.println(delegue.toString());
			if(delegue == null){
				//System.out.println("Connexion échouée");
				JOptionPane.showMessageDialog(null, "Navré mais votre connexion a échoué.");
				this.etatTentativeConnexion = EtatTentativeConnexion.ECHEC;
			}
			else{
				Session.ouvrirSession(delegue);
				//System.out.println(Session.afficherAttr());
				System.out.println("Connexion réussie");
				this.etatTentativeConnexion = EtatTentativeConnexion.OK;
				
				this.vue.getVueParente().setMenuConnecte();
				String message = "Bienvenue " + Session.getDelegue().getPrenom() + " " + Session.getDelegue().getNom();
				JOptionPane.showMessageDialog(null, message);
				
				this.vue.dispose();
			}
		}
		else if(source == this.vue.getbAnnuler()){
			int reponse = JOptionPane.showConfirmDialog(null, "Abandonner la connexion ?", null, 2);
			if(reponse == 0){
				this.etatTentativeConnexion = EtatTentativeConnexion.ABANDON;
				this.vue.dispose();
			}
		}
	}
	
}
