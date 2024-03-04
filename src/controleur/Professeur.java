package controleur;

public class Professeur {
	private int idprofesseur; 
	private String nom, prenom, email, diplome ;
	public Professeur(int idprofesseur, String nom, String prenom, String email, String diplome) {
		super();
		this.idprofesseur = idprofesseur;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.diplome = diplome;
	} 
	public Professeur( String nom, String prenom, String email, String diplome) {
		super();
		this.idprofesseur = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.diplome = diplome;
	}
	public int getIdprofesseur() {
		return idprofesseur;
	}
	public void setIdprofesseur(int idprofesseur) {
		this.idprofesseur = idprofesseur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	} 
	
	
}

 