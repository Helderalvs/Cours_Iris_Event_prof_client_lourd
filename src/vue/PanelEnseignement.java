package vue;

import javax.swing.*;
import java.awt.*;


public class PanelEnseignement extends PanelPrincipal {
	private JTextField txtMatiere = new JTextField();
	private JTextField txtCoeff = new JTextField();
	private JTextField txtNbHeures = new JTextField();
	private JTextField txtIdClasse = new JTextField();
	private JTextField txtIdProfesseur = new JTextField();


	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregister");

	private JPanel pannelFrom = new JPanel();

	public PanelEnseignement() {
		super("Gestion des Enseignements");
		{
			//construire le pannel formulaire : saisie de la classe.
			this.pannelFrom.setBounds(20, 80, 250, 200);
			this.pannelFrom.setBackground(Color.gray);
			this.pannelFrom.setLayout(new GridLayout(7, 2));
			this.pannelFrom.add(new JLabel("Nom de la matiere : "));
			this.pannelFrom.add(this.txtMatiere);
			this.pannelFrom.add(new JLabel("Le coefficient : "));
			this.pannelFrom.add(this.txtCoeff);
			this.pannelFrom.add(new JLabel("Le nombres d'heures :"));
			this.pannelFrom.add(this.txtNbHeures);
			this.pannelFrom.add(new JLabel("Id classe :"));
			this.pannelFrom.add(this.txtIdClasse);
			this.pannelFrom.add(new JLabel("Id Professeur :"));
			this.pannelFrom.add(this.txtIdProfesseur);
			this.pannelFrom.add(this.btAnnuler);
			this.pannelFrom.add(this.btEnregistrer);
			//on ajoute le formulaire à la fenetre
			this.add(this.pannelFrom);
		}
	}
}