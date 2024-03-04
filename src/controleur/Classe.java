package controleur;

public class Classe {
	private int idclasse; 
	private String nom, salle, diplome ;
	public Classe(int idclasse, String nom, String salle, String diplome) {
		super();
		this.idclasse = idclasse;
		this.nom = nom;
		this.salle = salle;
		this.diplome = diplome;
	} 
	public Classe( String nom, String salle, String diplome) {
		super();
		this.idclasse = 0;
		this.nom = nom;
		this.salle = salle;
		this.diplome = diplome;
	}
	public int getIdclasse() {
		return idclasse;
	}
	public void setIdclasse(int idclasse) {
		this.idclasse = idclasse;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSalle() {
		return salle;
	}
	public void setSalle(String salle) {
		this.salle = salle;
	}
	public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	} 
	
}
