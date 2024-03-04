package controleur;

public class Initial extends Etudiant{

    private float montant;

    public Initial(int idetudiant, String nom, String prenom, String email, String dateNais, int idclasse, float montant) {
        super(idetudiant, nom, prenom, email, dateNais, idclasse);
        this.montant = montant;
    }
    public Initial(String nom, String prenom, String email, String dateNais, int idclasse, float montant) {
        super( nom, prenom, email, dateNais, idclasse);
        this.montant = montant;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
}
