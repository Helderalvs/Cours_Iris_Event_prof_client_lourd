package modele;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.*;

public class Modele {
	private static BDD uneBDD = new BDD("root", "", "localhost","scolarite_jv_iris_24");

	public static User selectWhereUser (String email, String mdp) {
		User unUser = null;
		String requete ="select * from user where email='"+email+"' and mdp='"+mdp+"' ;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if (unResultat.next()) {
				unUser = new User(unResultat.getInt("iduser"), unResultat.getString("nom"),
						unResultat.getString("prenom"), unResultat.getString("email"),
						unResultat.getString("mdp"), unResultat.getString("role"));
			}
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
		return unUser;
	}
	public static void updateUser(User unUser) {
		String requete="update user set nom='"
				+unUser.getNom()+"',prenom = '"
				+unUser.getPrenom()+"',email = '"
				+unUser.getEmail()+"',role = '"
				+unUser.getRole()+"',mdp = '"
				+unUser.getMdp()+"' where iduser = "
				+unUser.getIduser() +";";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();

			unStat.execute(requete);

			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}

	}
	public static void insertClasse(Classe uneClasse) {
		String requete ="insert into classe values(null, '"
				+ uneClasse.getNom()+"','"
				+uneClasse.getSalle()+"','"
				+uneClasse.getDiplome()+"' ) ;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
	}
	public static void deleteClasse(int idclasse) {
		String requete ="delete from classe where idclasse = "+idclasse+";";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
	}
	public static void updateClasse(Classe uneClasse) {
		String requete ="update classe set nom='" + uneClasse.getNom()
				+"' , salle ='" + uneClasse.getSalle() +"' , diplome='"
				+ uneClasse.getDiplome()+"'   where idclasse ="+uneClasse.getIdclasse();
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
	}
	public static ArrayList<Classe> selectAllClasses (String filtre){
		String requete ="";
		if (filtre.equals("")) {
			requete = "select * from classe ; ";
		}else {
			requete = "select * from classe where nom like '%"
					+ filtre +"%' or salle like '%" + filtre
					+ "%' or diplome = '%" + filtre +"%' ;";
		}

		ArrayList<Classe> lesClasses = new ArrayList<Classe>();
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Classe uneClasse = new Classe (
						desRes.getInt("idclasse"), desRes.getString("nom"),
						desRes.getString("salle"), desRes.getString("diplome")
				);
				lesClasses.add(uneClasse);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
		return lesClasses;
	}
	public static void insertEtudiant(Etudiant unEtudiant) {
		String requete ="insert into etudiant values(null, '"
				+ unEtudiant.getNom()+"','"
				+unEtudiant.getPrenom()+"','"
				+unEtudiant.getEmail()+"','"
				+unEtudiant.getDateNais()+"','"
				+unEtudiant.getIdclasse()+"' ) ;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}

	}
	public static Classe selectWhereClasse(String nom, String salle, String diplome) {
		Classe uneClasse = null;
		String requete ="select * from classe where nom ='" + nom
				+ "' and salle='" + salle + "' and diplome='"+diplome+"';";

		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				uneClasse = new Classe (
						desRes.getInt("idclasse"), desRes.getString("nom"),
						desRes.getString("salle"), desRes.getString("diplome")
				);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
		return uneClasse ;
	}
	public static ArrayList<Professeur> selectAllProfesseurs(String filtre) {

		String requete ="";
		if (filtre.equals("")) {
			requete = "select * from professeur ; ";
		}else {
			requete = "select * from professeur where nom like '%"
					+ filtre +"%' or prenom like '%" + filtre +"%' or email like '%" + filtre
					+ "%' or diplome = '%" + filtre +"%' ;";
		}

		ArrayList<Professeur> lesProfesseurs = new ArrayList<Professeur>();
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			while (desRes.next()) {
				Professeur unProfesseur = new Professeur (
						desRes.getInt("idprofesseur"), desRes.getString("nom"),
						desRes.getString("prenom"), desRes.getString("email"), desRes.getString("diplome")
				);
				lesProfesseurs.add(unProfesseur);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
		return lesProfesseurs;
	}
	public static Professeur selectWhereProfesseur(String nom, String prenom, String email, String diplome) {
		Professeur unProfesseur = null;
		String requete ="select * from professeur where nom ='" + nom
				+ "' and prenom='" + prenom + "' and email='" + email+ "' and diplome='"+diplome+"';";

		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			if (desRes.next()) {
				unProfesseur = new Professeur (
						desRes.getInt("idprofesseur"), desRes.getString("nom"),
						desRes.getString("prenom"),  desRes.getString("email"), desRes.getString("diplome")
				);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
		return unProfesseur ;
	}
	public static void deleteProfesseur(int idprofesseur) {
		String requete ="delete from professeur where idprofesseur = "+idprofesseur+";";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}
	}
	public static void insertProfesseur(Professeur unProfesseur) {
		String requete ="insert into professeur values(null, '"
				+ unProfesseur.getNom()+"','"
				+unProfesseur.getPrenom()+"','"
				+unProfesseur.getEmail()+"','"

				+unProfesseur.getDiplome()+"' ) ;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}

	}

	public static void updateProfesseur(Professeur unProfesseur) {
		String requete="update professeur set nom='"
				+unProfesseur.getNom()+"',prenom = '"
				+unProfesseur.getPrenom()+"',email = '"
				+unProfesseur.getEmail()+"',diplome = '"
				+unProfesseur.getDiplome()+"' where idprofesseur = "
				+unProfesseur.getIdprofesseur() +";";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();

			unStat.execute(requete);

			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}

	}

	public static void insertInitiale(Initial unEtudiant) {
		// todo tous modifier pour moi c'esst materiel rando dans mon fichier client lourd
		String requete ="insert into etudiant values(null, '"
				+ unEtudiant.getNom()+"','"
				+unEtudiant.getPrenom()+"','"
				+unEtudiant.getEmail()+"','"
				+unEtudiant.getDateNais()+"','"
				+unEtudiant.getIdclasse()+"' ) ;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}

	}

	public static void insertAlternant(Alternant unEtudiant) {
		// todo tous modifier pour moi c'esst materiel rando dans mon fichier client lourd
		String requete ="insert into etudiant values(null, '"
				+ unEtudiant.getNom()+"','"
				+unEtudiant.getPrenom()+"','"
				+unEtudiant.getEmail()+"','"
				+unEtudiant.getDateNais()+"','"
				+unEtudiant.getIdclasse()+"' ) ;";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		}
		catch (SQLException exp)
		{
			System.out.println("Erreur de requete : " + requete );
		}

	}


}