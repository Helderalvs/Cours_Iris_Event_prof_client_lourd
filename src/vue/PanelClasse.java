package vue;

import controleur.Classe;
import controleur.Controleur;
import controleur.Tableau;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class PanelClasse extends PanelPrincipal implements ActionListener {

	private JTextField txtNom = new JTextField();
	private JTextField txtSalle = new JTextField();
	private JTextField txtDiplome = new JTextField();

	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");

	private JPanel pannelFrom = new JPanel();

	private JTable tableClasses;
	private JScrollPane uneScroll;

	private Tableau unTableau;
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton("Filtrer");

	private JLabel nbClasses = new JLabel();

	private int nb = 0;
	public PanelClasse() {
		super("Gestion des classes");
		{
			//construire le pannel formulaire : saisie de la classe.
			this.pannelFrom.setBounds(20,80,250,200);
			this.pannelFrom.setBackground(Color.gray);
			this.pannelFrom.setLayout(new GridLayout(4,2));
			this.pannelFrom.add(new JLabel("Nom Classe : "));
			this.pannelFrom.add(this.txtNom);
			this.pannelFrom.add(new JLabel("Salle Classe : "));
			this.pannelFrom.add(this.txtSalle);
			this.pannelFrom.add(new JLabel("Diplome Préparé :"));
			this.pannelFrom.add(this.txtDiplome);
			this.pannelFrom.add(this.btAnnuler);
			this.pannelFrom.add(this.btEnregistrer);
			//on ajoute le formulaire à la fenetre
			this.add(this.pannelFrom);

			// contruction du panel filtre
			this.panelFiltre.setBounds(360,80,460,30);
			this.panelFiltre.setBackground(Color.gray);
			this.panelFiltre.setLayout(new GridLayout(1,2));
			this.panelFiltre.add(new JLabel("Filtrer par :"));
			this.panelFiltre.add(this.txtFiltre);
			this.panelFiltre.add(this.btFiltrer);
			this.add(this.panelFiltre);

			// contruction de la table
			String entetes [] = {"Id classe" , "Nom Classe" , "Salle de cours" , "Diplôme"};
			this.unTableau = new Tableau(this.obtenirDonnes(""),entetes);
			this.tableClasses = new JTable(this.unTableau);
			this.uneScroll = new JScrollPane(this.tableClasses);
			this.uneScroll.setBounds(360,130,460,200);
			this.add(this.uneScroll);

			// interdire l'ordre des colonnes
			this.tableClasses.getTableHeader().setReorderingAllowed(false);

			//contrusction du label Nb
			this.nbClasses.setBounds(400,380,250,20);
			this.add(this.nbClasses);
			this.nb = this.unTableau.getRowCount();
			this.nbClasses.setText("Nombre de classes : " + nb );


			//supprestion et modification
			this.tableClasses.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int numLigne, idclasse;
					if(e.getClickCount() >=2){
						numLigne = tableClasses.getSelectedRow();
						idclasse = Integer.parseInt(unTableau.getValueAt(numLigne,0).toString());

						int reponse = JOptionPane.showConfirmDialog(null, "Voluez-vous supprimer la classe",
							"Suppresion Classe",JOptionPane.YES_NO_OPTION);
						if (reponse == 0){
							//suprimer dans la base
							Controleur.deleteClasse(idclasse);
							//actualiser l'affichage
							unTableau.supprimerLigne(numLigne);
							// actualisation du cambo du panel etudiant
							PanelEtudiant.remplirCbxIdClasse();
							btEnregistrer.setText("Enregistrer");
						}
					} else if (e.getClickCount() == 1) {
						// remplir les champs du formulaire pour une modification
						numLigne = tableClasses.getSelectedRow();
						txtNom.setText(unTableau.getValueAt(numLigne,1).toString());
						txtSalle.setText(unTableau.getValueAt(numLigne,1).toString());
						txtDiplome.setText(unTableau.getValueAt(numLigne,1).toString());
						btEnregistrer.setText("Modifier");
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}
			});

			//rendre les boutons ecoutables
			this.btAnnuler.addActionListener(this);
			this.btEnregistrer.addActionListener(this);
			this.btFiltrer.addActionListener(this);
		}
	}

	public Object [] [] obtenirDonnes(String filtre){
		// transformer l'arrayList en une matrice []
		ArrayList<Classe> lesClasses = Controleur.selectAllClasses(filtre);
		Object [] [] matrice = new Object[lesClasses.size()][4];
		int i = 0;
		for (Classe uneClasse : lesClasses){
			matrice [i][0] = uneClasse.getIdclasse();
			matrice [i][1] = uneClasse.getNom();
			matrice [i][2] = uneClasse.getSalle();
			matrice [i][3] = uneClasse.getDiplome();
			i++;
		}
		return  matrice;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btAnnuler){
			this.txtNom.setText("");
			this.txtSalle.setText("");
			this.txtDiplome.setText("");
			this.btEnregistrer.setText("Enregistrer");
		} else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			String nom = this.txtNom.getText();
			String salle = this.txtSalle.getText();
			String diplome = this.txtDiplome.getText();

			//Instanciation d'une classe
			Classe uneClasse = new Classe(nom, salle, diplome);

			//Insertion dans la classe dans la base de données
			Controleur.insertClasse(uneClasse);

			//recuperation de l'id de la classe insere auprès de Mysql
			uneClasse = Controleur.selectWhereClasse(nom,salle,diplome);

			// Actualiser l'affichage dans le tableau
			Object ligne[]={uneClasse.getIdclasse(),nom,salle,diplome};
			this.unTableau.ajouterLigne(ligne);
			this.nb = this.unTableau.getRowCount();
			this.nbClasses.setText("Nombre de classes : " + nb );

			// actualisation du cambo du panel etudiant
			PanelEtudiant.remplirCbxIdClasse();


			JOptionPane.showMessageDialog(this,"Insertion réussie de la classe");
			this.txtNom.setText("");
			this.txtSalle.setText("");
			this.txtDiplome.setText("");
		}
		else if(e.getSource() == this.btFiltrer){
			String filtre = this.txtFiltre.getText();
			//recuperation des donnes de la bdd avec le filtre
			Object matrice [][] = this.obtenirDonnes(filtre);

			//actualisation de l'affichage
			this.unTableau.setDonnes(matrice);
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String nom = this.txtNom.getText();
			String salle = this.txtSalle.getText();
			String diplome = this.txtDiplome.getText();

			//Instanciation d'une classe
			int numLigne = tableClasses.getSelectedRow();
			int idclasse = Integer.parseInt(unTableau.getValueAt(numLigne,0).toString());
			Classe uneClasse = new Classe(idclasse,nom, salle, diplome);

			//Insertion dans la classe dans la base de données
			Controleur.updateClasse(uneClasse);

			// Actualiser l'affichage dans le tableau
			Object ligne[]={uneClasse.getIdclasse(),nom,salle,diplome};
			this.unTableau.modifierLigne(numLigne,ligne);


			JOptionPane.showMessageDialog(this,"Modification réussie de la classe");
			this.txtNom.setText("");
			this.txtSalle.setText("");
			this.txtDiplome.setText("");
			this.btEnregistrer.setText("Enregistrer");

			// actualisation du cambo du panel etudiant
			PanelEtudiant.remplirCbxIdClasse();
		}
	}
}