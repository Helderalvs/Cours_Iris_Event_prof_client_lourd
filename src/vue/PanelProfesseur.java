package vue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import controleur.Classe;
import controleur.Controleur;
import controleur.Professeur;
import controleur.Tableau;
public class PanelProfesseur extends PanelPrincipal implements ActionListener{
	private JTextField txtNom = new  JTextField();
	private JTextField txtPrenom = new  JTextField();
	private JTextField txtDiplome = new  JTextField();
	private JTextField txtEmail = new  JTextField();

	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");

	private JPanel panelForm = new JPanel();


	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");

	private JTable tableProfesseurs ;
	private JScrollPane uneScroll ;
	private Tableau unTableau ;

	private JLabel nbProfesseurs = new JLabel();
	private int nb = 0;

	public PanelProfesseur () {
		super ("Gestion des Professeurs");

		//construire le panel formulaire : saisie de la classe.
		this.panelForm.setBounds(20, 80, 250, 200);
		this.panelForm.setBackground(Color.gray);
		this.panelForm.setLayout(new GridLayout(5,2));
		this.panelForm.add(new JLabel("Nom Prof :"));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("Prénom Prof :"));
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("Diplôme Préparé :"));
		this.panelForm.add(this.txtDiplome);
		this.panelForm.add(new JLabel("Email Contact :"));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		//on ajoute le formulaire à la fenetre
		this.add(this.panelForm);

		//construction du panel filtre
		this.panelFiltre.setBounds(360, 80, 460, 30);
		this.panelFiltre.setBackground(Color.gray);
		this.panelFiltre.setLayout(new GridLayout(1,3));
		this.panelFiltre.add(new JLabel("Filtrer par :"));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer);
		this.add(this.panelFiltre);
		//construction de la table
		String entetes [] = {"ID Prof", "Nom Prof","Prenom Prof", "Email", "Diplôme"};
		this.unTableau= new Tableau (this.obtenirDonnees(""), entetes);
		this.tableProfesseurs = new JTable(this.unTableau) ;
		this.uneScroll = new JScrollPane(this.tableProfesseurs);
		this.uneScroll.setBounds(360, 130, 460, 200);
		this.add(this.uneScroll);

		//interdire l'ordre des colonnes
		this.tableProfesseurs.getTableHeader().setReorderingAllowed(false);

		//construction du Label Nb
		this.nbProfesseurs.setBounds(400, 380, 250, 20);
		this.add(this.nbProfesseurs);
		this.nb = this.unTableau.getRowCount();
		this.nbProfesseurs.setText("Nombre de classes : " + nb);

		//suppression et modification
		this.tableProfesseurs.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne, idclasse ;
				if (e.getClickCount() >=2 ) {
					numLigne = tableProfesseurs.getSelectedRow();
					idclasse = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());

					int reponse = JOptionPane.showConfirmDialog(null,  "Voulez-vous supprimer la classe",
							"Suppression Classe", JOptionPane.YES_NO_OPTION);
					if (reponse == 0) {
						//supprimer dans la base
						Controleur.deleteClasse(idclasse);
						//actualiser l'affichage
						unTableau.supprimerLigne(numLigne);
						btEnregistrer.setText("Enregistrer");
					}
				}
				else if (e.getClickCount() == 1 ) {
					//remplir les champ du formulaire pour une modification
					numLigne = tableProfesseurs.getSelectedRow();
					txtNom.setText( unTableau.getValueAt(numLigne, 1).toString());
					txtPrenom.setText( unTableau.getValueAt(numLigne, 2).toString());
					txtEmail.setText( unTableau.getValueAt(numLigne, 3).toString());
					txtDiplome.setText( unTableau.getValueAt(numLigne, 4).toString());
					btEnregistrer.setText("Modifier");
				}
			}
		});

		//rendre les boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);
	}
	public Object [][] obtenirDonnees (String filtre){
		//transformer l'ArrayList en une matrice [][]
		ArrayList<Professeur> lesProfs = Controleur.selectAllProfesseurs(filtre);
		Object [][] matrice = new Object [lesProfs.size()][5];
		int i = 0;
		for (Professeur unProf : lesProfs) {
			matrice [i][0] = unProf.getIdprofesseur();
			matrice [i][1] = unProf.getNom();
			matrice [i][2] = unProf.getPrenom();
			matrice [i][3] = unProf.getEmail();
			matrice [i][4] = unProf.getDiplome();
			i++;
		}
		return matrice;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtDiplome.setText("");
			this.btEnregistrer.setText("Enregistrer");
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String email = this.txtEmail.getText();
			String diplome = this.txtDiplome.getText();

			//instancier un Professeur
			Professeur unProfesseur = new Professeur (nom, prenom, email, diplome);

			//insertion dans le professeur dans la base de données
			Controleur.insertProfesseur (unProfesseur);

			//recuperation de l'id du professeur inseree auprès de Mysql
			unProfesseur = Controleur.selectWhereProfesseur(nom, prenom, email, diplome);

			//actualiser l'affichage dans le tableau
			Object ligne[]= {unProfesseur.getIdprofesseur(), nom, prenom,email, diplome};
			this.unTableau.ajouterLigne(ligne);
			this.nb = this.unTableau.getRowCount();
			this.nbProfesseurs.setText("Nombre de professeurs : " + nb);

			JOptionPane.showMessageDialog(this, "Insertion réussie du professeur.");
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtDiplome.setText("");
		}
		else if (e.getSource() == this.btFiltrer) {
			String filtre = this.txtFiltre.getText();

			//recuperation des données de la bdd avec le filtre
			Object matrice[][] = this.obtenirDonnees(filtre);

			//actualisation de l'affichage
			this.unTableau.setDonnes(matrice);
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier"))
		{
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String email = this.txtEmail.getText();
			String diplome = this.txtDiplome.getText();

			//instancier une classe
			int numLigne = tableProfesseurs.getSelectedRow();
			int idprofesseur = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			Professeur unProfesseur = new Professeur (idprofesseur, nom, prenom,email, diplome);

			//modification dans la base de données
			Controleur.updateProfesseur(unProfesseur);

			//recuperation de l'id du professeur inseree auprès de Mysql
			unProfesseur = Controleur.selectWhereProfesseur(nom, prenom, email, diplome);

			//actualiser l'affichage dans le tableau
			Object ligne[]= {unProfesseur.getIdprofesseur(), nom, prenom,email, diplome};
			this.unTableau.ajouterLigne(ligne);
			this.nb = this.unTableau.getRowCount();
			this.nbProfesseurs.setText("Nombre de professeurs : " + nb);

			JOptionPane.showMessageDialog(this, "modification réussie du professeur.");
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtDiplome.setText("");


		}

	}
}