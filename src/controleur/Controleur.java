package controleur;
import java.util.ArrayList;
import modele.Modele;
public class Controleur {

	public static User selectWhereUser (String email, String mdp) {
		return Modele.selectWhereUser(email, mdp);
	}
	public static void updateUser(User unUser) {

		Modele.updateUser(unUser);
	}
	public static void insertClasse(Classe uneClasse) {
		Modele.insertClasse(uneClasse);
	}
	public static void insertEtudiant(Etudiant unEtudiant) {
		Modele.insertEtudiant(unEtudiant);

	}

	public static ArrayList<Classe> selectAllClasses (String filtre){
		return Modele.selectAllClasses (filtre);
	}
	public static Classe selectWhereClasse(String nom, String salle, String diplome) {
		return Modele.selectWhereClasse (nom, salle, diplome);
	}

	public static void deleteClasse (int idclasse) {
		Modele.deleteClasse(idclasse);
	}

	public static void updateClasse (Classe uneClasse) {
		Modele.updateClasse(uneClasse);
	}

	/********************* Professeurs *********************/
	public static ArrayList<Professeur> selectAllProfesseurs (String filtre){
		return Modele.selectAllProfesseurs (filtre);
	}
	public static Professeur selectWhereProfesseur(String nom, String prenom, String email,  String diplome) {
		return Modele.selectWhereProfesseur (nom, prenom, email, diplome);
	}

	public static void deleteProfesseur (int idprofesseur) {
		Modele.deleteProfesseur(idprofesseur);
	}

	public static void updateProfesseur (Professeur unProfesseur) {
		Modele.updateProfesseur(unProfesseur);
	}
	public static void insertProfesseur (Professeur unProfesseur) {
		Modele.insertProfesseur(unProfesseur);
	}

	public static void insertInitiale (Initial unEtudiant) {
		Modele.insertInitiale(unEtudiant);
	}

	public static void insertAlternant (Alternant unEtudiant) {
		Modele.insertAlternant(unEtudiant);
	}


}