package fr.gsb.vues;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.gsb.controleurs.ControleurAuthentification;

public class VueAuthentification extends JDialog{
	
	private JTextField login = new JTextField(10);
	private JPasswordField mdp = new JPasswordField(10);
	private JButton bConnecter = new JButton("Se connecter");
	private JButton bAnnuler = new JButton("Annuler");
	
	private ControleurAuthentification controleurAuth = new ControleurAuthentification(this);
	private VueFenetrePrincipale vueParente = null;
	
	public VueAuthentification(VueFenetrePrincipale vueParente){
		super();
		System.out.println("VueAuthentification :: VueAuthentification(VueFenetrePrincipale vueParente)");
		this.setTitle("Connexion");
		this.setSize(new Dimension(300,130));
		this.setResizable(false);
		this.setLocationRelativeTo(vueParente);
		this.setVisible(true);
		this.creerInterface();
		
		this.vueParente = vueParente;
	}
	
	private void creerInterface(){
		JPanel panneau = new JPanel();
		
		Box boxEtiquettes = Box.createVerticalBox();
		boxEtiquettes.add(new JLabel("login : "));
		boxEtiquettes.add(new JLabel("Mot de passe : "));
		Box boxChamps = Box.createVerticalBox();
		boxChamps.add(this.login);
		boxChamps.add(this.mdp);
		Box boxSaisies = Box.createHorizontalBox();
		boxSaisies.add(boxEtiquettes);
		boxSaisies.add(boxChamps);
		
		Box boxBoutons = Box.createHorizontalBox();
		boxBoutons.add(this.bConnecter);
		boxBoutons.add(this.bAnnuler);
		
		Box boxPrinc = Box.createVerticalBox();
		boxPrinc.add(boxSaisies);
		boxPrinc.add(boxBoutons);
		
		panneau.add(boxPrinc);
		this.getContentPane().add(panneau);
	}
	
	public void initialiser(){
		this.login.setText("");
		this.mdp.setText("");
	}

	public JTextField getLogin() {
		return login;
	}

	public void setLogin(JTextField login) {
		this.login = login;
	}

	public JPasswordField getMdp() {
		return mdp;
	}

	public void setMdp(JPasswordField mdp) {
		this.mdp = mdp;
	}

	public JButton getbConnecter() {
		return bConnecter;
	}

	public void setbConnecter(JButton bConnecter) {
		this.bConnecter = bConnecter;
	}

	public JButton getbAnnuler() {
		return bAnnuler;
	}

	public void setbAnnuler(JButton bAnnuler) {
		this.bAnnuler = bAnnuler;
	}

	public ControleurAuthentification getControleurAuth() {
		return controleurAuth;
	}

	public VueFenetrePrincipale getVueParente() {
		return vueParente;
	}
	
}
