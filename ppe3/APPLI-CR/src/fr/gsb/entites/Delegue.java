package fr.gsb.entites;

public class Delegue {

	private String matricule;
	private String nom;
	private String prenom;
	private String numRegion;
	
	
	public Delegue(String matricule, String nom, String prenom, String numRegion) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.numRegion = numRegion;
	}


	@Override
	public String toString() {
		return "Delegue [matricule=" + matricule + ", nom=" + nom + ", prenom="
				+ prenom + ", numRegion=" + numRegion + "]";
	}


	public String getMatricule() {
		return matricule;
	}


	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public String getNumRegion() {
		return numRegion;
	}
	
	
}
