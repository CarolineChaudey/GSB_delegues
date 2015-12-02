package fr.gsb.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.gsb.modeles.Modele;
import fr.gsb.technique.CriterePratHesi;
import fr.gsb.vues.VueDialogueCriterePrat;

public class ControleurCriterePrat implements ActionListener{
	
	private VueDialogueCriterePrat vue = null;
	
	public ControleurCriterePrat(VueDialogueCriterePrat vue){
		super();
		System.out.println("ControleurCriterePrat :: ControleurCriterePrat()");
		this.vue = vue;
		this.enregistrerEcouteur();
	}
	
	public void enregistrerEcouteur(){
		this.vue.getRetour().addActionListener(this);
		this.vue.getValider().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		
		if(source == this.vue.getRetour()){
			System.out.println("ControleurCriterePrat :: actionPerformed(ActionEvent e)");
			this.vue.dispose();
		}
		
		else if(source == this.vue.getValider()){
			System.out.println("ControleurCriterePrat :: actionPerformed(ActionEvent e)");
			if(this.vue.getCoeffConf().isSelected()){
				Modele.getModele().setCriterePH(CriterePratHesi.COEFF_CONFIANCE);
				//this.vue.getVue().getVuePraticiens().activer();
//				this.vue.getVue().changerVue("vuePraticiens");
//				this.vue.dispose();
				//Modele.getModele().getPraticien();
			}
			else if(this.vue.getDerVisite().isSelected()){
				Modele.getModele().setCriterePH(CriterePratHesi.DER_VISITE);
				//this.vue.getVue().getVuePraticiens().activer();
//				this.vue.getVue().changerVue("vuePraticiens");
//				this.vue.dispose();
				//Modele.getModele().getPraticien();
			}
			else if(this.vue.getNotoriete().isSelected()){
				Modele.getModele().setCriterePH(CriterePratHesi.COEFF_NOTORIETE);
				//this.vue.getVue().getVuePraticiens().activer();
//				this.vue.getVue().changerVue("vuePraticiens");
//				this.vue.dispose();
				//Modele.getModele().getPraticien();
			}
			this.vue.getVue().getVuePraticiens().getModeleLP().actualiser();
			this.vue.getVue().changerVue("vuePraticiens");
			this.vue.dispose();
		}
	}
	
}
