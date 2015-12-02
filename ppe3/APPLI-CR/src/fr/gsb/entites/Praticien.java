package fr.gsb.entites;

public class Praticien {
	
	private String nom;
	private String ville;
	private String cp;
	private float coeffNotoriete;
	private int coeffConfiance;
	private String derVisite;
	
	
	public Praticien(String nom, String ville, String cp,float coeffNotoriete,
			int coeffConfiance, String derVisite) {
		super();
		this.nom = nom;
		this.ville = ville;
		this.cp = cp;
		this.coeffNotoriete = coeffNotoriete;
		this.coeffConfiance = coeffConfiance;
		this.derVisite = derVisite;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public float getCoeffNotoriete() {
		return coeffNotoriete;
	}


	public void setCoeffNotoriete(float coeffNotoriete) {
		this.coeffNotoriete = coeffNotoriete;
	}


	public int getCoeffConfiance() {
		return coeffConfiance;
	}


	public void setCoeffConfiance(int coeffConfiance) {
		this.coeffConfiance = coeffConfiance;
	}


	public String getDerVisite() {
		return derVisite;
	}


	public void setDerVisite(String derVisite) {
		this.derVisite = derVisite;
	}

	
	public String getCp() {
		return cp;
	}


	public void setCp(String cp) {
		this.cp = cp;
	}


	@Override
	public String toString() {
		return "Praticien [nom=" + nom + ", ville=" + ville
				+ ", coeffNotoriete=" + coeffNotoriete + ", coeffConfiance="
				+ coeffConfiance + ", derVisite=" + derVisite + "]";
	}
	
	
}
