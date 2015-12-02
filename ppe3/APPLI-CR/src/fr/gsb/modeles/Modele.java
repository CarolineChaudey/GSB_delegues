package fr.gsb.modeles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

import fr.gsb.entites.Delegue;
import fr.gsb.entites.Praticien;
import fr.gsb.technique.Connexion;
import fr.gsb.technique.CriterePratHesi;
import fr.gsb.technique.Session;


public class Modele {
	
	private static Modele modele = null;
	private Connexion connexion = Connexion.getConnexion();
	private PreparedStatement dernAff = null;
	private PreparedStatement lesRoles = null;
	private PreparedStatement personnesRoles = null;
	private CriterePratHesi criterePH = null;
	
	
	private Modele(){
		super();
		System.out.println("Modele :: Modele()");
		this.creerRequetes();
	}
	
	public static Modele getModele(){
		System.out.println("Modele :: getModele()");
		if(modele == null){
			modele = new Modele();
		}
		
		return modele;
	}
	
	private void creerRequetes(){
		
		String requete1 = 
				"create view dernAff as ("
						+ " select VIS_MATRICULE, max(JJMMAA) as DATE"
						+ " from TRAVAILLER"
						+ " group by VIS_MATRICULE"
						+ ")"
					;
		
		String requete2 = 
				"create view delegues as ("
				+ " select t.VIS_MATRICULE, t.JJMMAA, t.REG_CODE, t.TRA_ROLE"
				+ " from TRAVAILLER as t"
				+ " inner join dernAff"
				+ " on dernAff.VIS_MATRICULE = t.VIS_MATRICULE"
				+ " where dernAff.DATE = t.JJMMAA"
				+ " and t.TRA_ROLE = ?"
				+ ")"
			;
		
		String requete3 = "select v.VIS_MATRICULE, VIS_NOM, VIS_PRENOM, REG_CODE"
				+ " from VISITEUR as v"
				+ " inner join delegues"
				+ " on delegues.VIS_MATRICULE = v.VIS_MATRICULE"
				+ " where v.MDP = ?"
				+ " and v.VIS_MATRICULE = ?"
			;
		
		try {
			dernAff = (PreparedStatement) connexion.getIdConnection().prepareStatement(requete1);
			lesRoles = (PreparedStatement) connexion.getIdConnection().prepareStatement(requete2);
			personnesRoles = (PreparedStatement) connexion.getIdConnection().prepareStatement(requete3);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
	public Delegue getDelegue(String login, String mdp){
		System.out.println("Modele :: getDelegue(String login, String mdp)");
		try {
			
			Statement stmt = (Statement) this.connexion.getIdConnection().createStatement();
			
			this.lesRoles.setString(1, "Délégué");
			this.personnesRoles.setString(1, mdp);
			this.personnesRoles.setString(2, login);
			
			String requeteFermeture1 = "drop view dernAff";
			String requeteFermeture2 = "drop view delegues";
			
			this.dernAff.execute();
			this.lesRoles.execute();
			ResultSet resultat = this.personnesRoles.executeQuery();
     
			int nb = 0 ;
			Delegue delegue = null;
			while (resultat.next()){
				nb++ ;
				//System.out.println(resultat.getString(1));
				String matricule = resultat.getString(1);
				//System.out.println(resultat.getString(2));
				String nom = resultat.getString(2);
				//System.out.println(resultat.getString(3));
				String prenom = resultat.getString(3);
				//System.out.println(resultat.getString(4));
				String numRegion = resultat.getString(4);
				delegue = new Delegue(matricule, nom, prenom, numRegion);
				//System.out.println(delegue.toString());
			}
			//System.out.println(nb);
			
			stmt.execute(requeteFermeture1);
			stmt.execute(requeteFermeture2);
			
			stmt.close();
			
			if(nb == 0){
				return null;
			}
			else{
				return delegue;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
	}
	
	public List<Praticien> getPraticien(){
		System.out.println("Modele :: getPraticien");
		
		List<Praticien> praticiensHesitants = new ArrayList<Praticien>();
		
		if(Session.getDelegue() == null){
			return praticiensHesitants;
		}
		
		String region = Session.getDelegue().getNumRegion();
		String requete = "select p.PRA_NOM, PRA_VILLE, PRA_CP, PRA_COEFNOTORIETE, derCR.COEFF_CONFIANCE, derCR.DATE_VISITE"
				+ " from PRATICIEN as p"
				+ " inner join (select r1.*"
				+ " 			from RAPPORT_VISITE as r1"
				+ " 			where r1.DATE_VISITE = (select max(r2.DATE_VISITE)"
				+ " 									from RAPPORT_VISITE as r2"
				+ "										where r1.PRA_NUM = r2.PRA_NUM"
				+ "										group by PRA_NUM)"
				+ "				) as derCR"
				+ " on derCR.PRA_NUM = p.PRA_NUM";
		
		
		// problème Languedoc-Roussillon divisé en Languedoc et Roussillon;
		switch (region) {
			case "AL" :
				requete = requete + " where substring(PRA_CP, 1,2) in (67, 68, 54, 55, 57, 88)";
				break;
			case "AQ" :
				requete = requete + " where substring(PRA_CP, 1,2) in (24, 33, 40, 47, 64)";
				break;
			case "AU" :
				requete = requete + " where substring(PRA_CP, 1,2) in (03, 15, 43, 63)";
				break;
			case "BG" :
				requete = requete + " where substring(PRA_CP, 1,2) in (22, 29, 35, 56)";
				break;
			case "BN" :
				requete = requete + " where substring(PRA_CP, 1,2) in (14, 50, 61)";
				break;
			case "BO" :
				requete = requete + " where substring(PRA_CP, 1,2) in (21, 58, 71, 89)";
				break;
			case "CA" :
				requete = requete + " where substring(PRA_CP, 1,2) in (08, 10, 51, 52)";
				break;
			case "CE" :
				requete = requete + " where substring(PRA_CP, 1,2) in (18, 28, 36, 37, 41, 45)";
				break;
			case "FC" :
				requete = requete + " where substring(PRA_CP, 1,2) in (25, 39, 70, 90)";
				break;
			case "HN" :
				requete = requete + " where substring(PRA_CP, 1,2) in (27, 76)";
				break;
			case "IF" :
				requete = requete + " where substring(PRA_CP, 1,2) in (75, 91, 92, 77, 93, 95, 94, 78)";
				break;
			case "LG" :
				requete = requete + " where substring(PRA_CP, 1,2) = ";
				break;
			case "LI" :
				requete = requete + " where substring(PRA_CP, 1,2) in (11, 30, 34, 48, 66)";
				break;
			case "MP" :
				requete = requete + " where substring(PRA_CP, 1,2) in (09, 12, 31, 32, 46, 65, 81, 82)";
				break;
			case "NP" :
				requete = requete + " where substring(PRA_CP, 1,2) in (59, 62)";
				break;
			case "PA" :
				requete = requete + " where substring(PRA_CP, 1,2) in (04, 05, 06, 13, 83, 84)";
				break;
			case "PC" :
				requete = requete + " where substring(PRA_CP, 1,2) in (16, 17, 79, 86)";
				break;
			case "PI" :
				requete = requete + " where substring(PRA_CP, 1,2) in (02, 60, 80)";
				break;
			case "PL" :
				requete = requete + " where substring(PRA_CP, 1,2) in (44, 49, 53, 72)";
				break;
			case "RA" :
				requete = requete + " where substring(PRA_CP, 1,2) in (01, 07, 26, 38, 42, 69, 73, 74)";
				break;
			case "RO" :
				requete = requete + " where substring(PRA_CP, 1,2) in (11, 30, 34, 48, 66)";
				break;
			case "VD" :
				requete = requete + " where substring(PRA_CP, 1,2) in (85)";
				break;
			default :
				break;
			
		}
		
		requete = requete + " and derCR.COEFF_CONFIANCE between 2 and 4";
		
		if(this.criterePH == CriterePratHesi.COEFF_CONFIANCE){
			requete = requete + " order by derCR.COEFF_CONFIANCE";
		}
		else if(this.criterePH == CriterePratHesi.COEFF_NOTORIETE){
			requete = requete + " order by PRA_COEFNOTORIETE desc";
		}
		// par défaut dernière visite
		else if (this.criterePH == CriterePratHesi.DER_VISITE){
			requete = requete + " order by derCR.DATE_VISITE";
		}
		else{
			return praticiensHesitants;
		}
		
		try {
			PreparedStatement stmt = (PreparedStatement) this.connexion.getIdConnection().prepareStatement(requete);
			ResultSet resultat = stmt.executeQuery(requete);
			while(resultat.next()){
//				System.out.println(resultat.getString(1));
				String nom = resultat.getString(1);
//				System.out.println(resultat.getString(2));
				String ville = resultat.getString(2);
//				System.out.println(resultat.getString(3));
				String cp = resultat.getString(3);
//				System.out.println(resultat.getString(4));
				float coeffNotoriete = resultat.getFloat(4);
//				System.out.println(resultat.getInt(5));
				int coeffConf = resultat.getInt(5);
//				System.out.println(resultat.getString(6));
				String derVisite = resultat.getString(6);
				Praticien praticien = new Praticien(nom, ville, cp, coeffNotoriete, coeffConf, derVisite);
				//System.out.println(praticien.toString());
				praticiensHesitants.add(praticien);
			}
			resultat.close();
			stmt.close();
			
			return praticiensHesitants;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
		
	}

	public CriterePratHesi getCriterePH() {
		return criterePH;
	}

	public void setCriterePH(CriterePratHesi criterePH) {
		this.criterePH = criterePH;
	}
	
	
	
}
