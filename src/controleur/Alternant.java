package controleur;

public class Alternant extends Etudiant{
    private String entreprise;
    private float salaire;

    public Alternant(int idetudiant, String nom, String prenom, String email, String dateNais, int idclasse, String entreprise, float salaire) {
        super(idetudiant, nom, prenom, email, dateNais, idclasse);
        this.entreprise = entreprise;
        this.salaire = salaire;
    }

    public Alternant(String nom, String prenom, String email, String dateNais, int idclasse, String entreprise, float salaire) {
        super(nom, prenom, email, dateNais, idclasse);
        this.entreprise = entreprise;
        this.salaire = salaire;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }
}
