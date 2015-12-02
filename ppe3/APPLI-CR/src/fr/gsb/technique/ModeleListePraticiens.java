package fr.gsb.technique;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.gsb.entites.Praticien;
import fr.gsb.modeles.Modele;

public class ModeleListePraticiens extends AbstractTableModel {
	
	private String[] entete = {"nom", "ville", "code postal", "coeff. de notoriété", "coeff. de confiance", "dernier rdv"};
	private List<Praticien> praticiens = Modele.getModele().getPraticien();
	
	
	public ModeleListePraticiens() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getRowCount(){
		return this.praticiens.size();
	}
	
	public int getColumnCount(){
		return this.entete.length;
	}
	
	public String getColumnName(int columnIndex){
		return this.entete[columnIndex];
	}
	
	public Object getValueAt(int rowIndex, int columnIndex){
		Praticien praticien = this.praticiens.get(rowIndex);
		//Object resultat = null;
		switch (columnIndex){
			case 0 :
				return praticien.getNom();		
			case 1 :
				return praticien.getVille();			
			case 2 :
				return praticien.getCp();				
			case 3 :
				return praticien.getCoeffNotoriete();				
			case 4 :
				return praticien.getCoeffConfiance();				
			case 5 :
				return praticien.getDerVisite();
			default :
				return null;
				
		}
		//return resultat;
	}
	
	public void actualiser(){
		System.out.println("ModeleListePraticiens :: actualiser");
		this.praticiens = Modele.getModele().getPraticien();
		this.fireTableDataChanged();
	}

}
