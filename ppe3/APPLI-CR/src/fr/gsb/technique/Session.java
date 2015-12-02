package fr.gsb.technique;

import fr.gsb.entites.Delegue;


public class Session {

	private static Session session = null;
	private static Delegue delegue = null;
	
	
	private Session(){
		super();
		System.out.println("Session :: Session()");
	}
	
	public static Session getSession(){
		System.out.println("Session :: getSession()");
		return session;
	}
	
	public static void ouvrirSession(Delegue leDelegue){
		System.out.println("Session :: ouvrirSession(Delegue leDelegue)");
		session = new Session();
		delegue = leDelegue;
	}
	
	public static void fermerSession(){
		System.out.println("Session :: fermerSession()");
		delegue = null;
		session = null;
	}

	public static String afficherAttr(){
		String attr = "" + session + delegue;
		return attr;
	}
	
	public static Delegue getDelegue(){
		return delegue;
	}
	
}
