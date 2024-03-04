package vue;

import controleur.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

public class PanelEtudiant extends PanelPrincipal implements ActionListener {
	private JTextField txtNom = new JTextField();

	private JTextField txtPrenom = new JTextField();

	private JTextField txtEmail = new JTextField();

	private JTextField txtDateNais = new JTextField();

	private static JComboBox<String> txtIdClasse = new JComboBox<String>();

	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregister");

	private JPanel pannelFrom = new JPanel();

	//heritage sur le formulaire
	private static JComboBox<String> txtStatut = new JComboBox<String>();
	private JTextField txtEnretprise = new JTextField();
	private JTextField txtSalaire = new JTextField();
	private JTextField txtMontant = new JTextField();

	private JPanel panelInitial = new JPanel();

	private JPanel panelAlternant = new JPanel();

	public PanelEtudiant () {
		super ("Gestion des Etudiants");
		{
			//construire le pannel formulaire : saisie de la classe.
			this.pannelFrom.setBounds(20,80,250,200);
			this.pannelFrom.setBackground(Color.gray);
			this.pannelFrom.setLayout(new GridLayout(6,2));
			this.pannelFrom.add(new JLabel("Nom Etudiant : "));
			this.pannelFrom.add(this.txtNom);
			this.pannelFrom.add(new JLabel("Prenom Etudiant : "));
			this.pannelFrom.add(this.txtPrenom);
			this.pannelFrom.add(new JLabel("Email Etudiant :"));
			this.pannelFrom.add(this.txtEmail);
			this.pannelFrom.add(new JLabel("Date naissance Etudiant :"));
			this.pannelFrom.add(this.txtDateNais);
			this.pannelFrom.add(new JLabel("Id Classe :"));
			this.pannelFrom.add(this.txtIdClasse);

			this.pannelFrom.add(new JLabel("Statut :"));
			this.pannelFrom.add(this.txtStatut);
			this.txtStatut.addItem("Initial");
			this.txtStatut.addItem("Alternant");
			this.txtStatut.addActionListener(this);
			//on ajoute le formulaire à la fenetre
			this.add(this.pannelFrom);

			//appel méthode remplir id classe
			this.remplirCbxIdClasse();

			//ajout des deux pannaux

			this.panelInitial.setBounds(20,300,250,30);
			this.panelInitial.setBackground(Color.gray);
			this.panelInitial.setLayout(new GridLayout(1,2));
			this.panelInitial.add(new JLabel("Montant :"));
			this.panelInitial.add(this.txtMontant);
			this.add(this.panelInitial);
			this.panelInitial.setVisible(true);

			this.panelAlternant.setBounds(20,300,250,60);
			this.panelAlternant.setBackground(Color.gray);
			this.panelAlternant.setLayout(new GridLayout(2,2));
			this.panelAlternant.add(new JLabel("Entreprise :"));
			this.panelAlternant.add(this.txtEnretprise);
			this.panelAlternant.add(new JLabel("Salaire :"));
			this.panelAlternant.add(this.txtSalaire);
			this.add(this.panelAlternant);
			this.panelAlternant.setVisible(false);

			this.btAnnuler.setBounds(20,380,100,30);
			this.add(this.btAnnuler);
			this.btEnregistrer.setBounds(160,380,100,30);
			this.add(this.btEnregistrer);
		}
		}

	public static void remplirCbxIdClasse() {
		ArrayList<Classe> lesClasses = Controleur.selectAllClasses("");
		// supprimer toutes les données existantes dans le cambo Id Classe
		txtIdClasse.removeAllItems();
		// ajout des classes
		for (Classe uneClasse : lesClasses){
			txtIdClasse.addItem(uneClasse.getIdclasse()+"-"+uneClasse.getNom());
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btAnnuler){
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtDateNais.setText("");
		} else if (e.getSource() == this.btEnregistrer) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String email = this.txtEmail.getText();
			String dateNais = this.txtDateNais.getText();

			String chaine = this.txtIdClasse.getSelectedItem().toString();
			String tab[] = chaine.split("-");


			int idClasse = Integer.parseInt(tab[0]);
			float salaire=0,montant=0;
			String entreprise="";
			String statut = this.txtStatut.getSelectedItem().toString();
			Etudiant unEtudiant;
			if (statut.equals("Initial")){
				montant = Float.parseFloat((txtMontant.getText()));
				unEtudiant = new Initial(nom,prenom,email,dateNais,idClasse,montant);
				Controleur.insertInitiale((Initial) unEtudiant);
			} else{
				salaire = Float.parseFloat((txtMontant.getText()));
				entreprise = txtEnretprise.getText();
				unEtudiant = new Alternant(nom,prenom,email,dateNais,idClasse,entreprise,salaire);
				//Insertion des Etudiant dans la base de données
				Controleur.insertAlternant((Alternant) unEtudiant);
			}

			// Actualiser

			JOptionPane.showMessageDialog(this,"Insertion réussie du professeur");
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtDateNais.setText("");
		}
		if (e.getSource() == this.txtStatut){
			String statut = this.txtStatut.getSelectedItem().toString();
			if(statut.equals("Initial")){
				this.panelInitial.setVisible(true);
				this.panelAlternant.setVisible(false);
			} else {
				this.panelInitial.setVisible(false);
				this.panelAlternant.setVisible(true);
			}
		}
	}
}

