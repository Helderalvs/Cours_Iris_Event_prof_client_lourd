package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.IrisEvent;
import controleur.User;

public class VueGenerale extends JFrame implements ActionListener{

	private JPanel panelMenu = new JPanel(); 
	private JButton btProfil = new JButton("Profil");
	private JButton btClasses= new JButton("Classes"); 
	private JButton btProfesseurs = new JButton("Professeurs"); 
	private JButton btEtudiants = new JButton("Etudiants"); 
	private JButton btEnseignements = new JButton("Enseignements"); 
	private JButton btQuitter = new JButton("Quitter"); 
	
	//Les panels d'administration 
	private PanelProfil unPanelProfil ; 
	private PanelClasse unPanelClasse= new PanelClasse(); 
	private PanelProfesseur unPanelProfesseur= new PanelProfesseur(); 
	private PanelEtudiant unPanelEtudiant = new PanelEtudiant(); 
	private PanelEnseignement unPanelEnseignement= new PanelEnseignement(); 
	
	public   VueGenerale ( User unUser) {
		
		unPanelProfil = new PanelProfil(unUser);
		
		this.setTitle("Application Admin Scolarité IRIS");
		this.setResizable(false);
		this.setBounds(100, 100, 900, 600);
		this.getContentPane().setBackground(Color.gray);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//construction du panel Menu 
		this.panelMenu.setBounds(50, 10, 800, 30);
		this.panelMenu.setBackground(Color.gray);
		this.panelMenu.setLayout(new GridLayout(1, 6));
		this.panelMenu.add(this.btProfil);
		this.panelMenu.add(this.btClasses); 
		this.panelMenu.add(this.btProfesseurs); 
		this.panelMenu.add(this.btEtudiants);
		this.panelMenu.add(this.btEnseignements); 
		this.panelMenu.add(this.btQuitter); 
		this.add(this.panelMenu); 
		
		//rendre les boutons ecoutables 
		this.btProfil.addActionListener(this);
		this.btClasses.addActionListener(this);
		this.btProfesseurs.addActionListener(this);
		this.btEtudiants.addActionListener(this);
		this.btEnseignements.addActionListener(this);
		this.btQuitter.addActionListener(this);
		
		//ajout les panels dans la vue 
		this.add(this.unPanelProfil); 
		this.add(this.unPanelClasse); 
		this.add(this.unPanelProfesseur); 
		this.add(this.unPanelEtudiant); 
		this.add(this.unPanelEnseignement); 
		
		
		this.setVisible(true);
	}
	public void afficherPanel (int choix) {
		this.unPanelProfil.setVisible(false);
		this.unPanelClasse.setVisible(false);
		this.unPanelProfesseur.setVisible(false);
		this.unPanelEtudiant.setVisible(false);
		this.unPanelEnseignement.setVisible(false);
		switch (choix) {
		case 1 : this.unPanelProfil.setVisible(true);break;
		case 2 : this.unPanelClasse.setVisible(true);break;
		case 3 : this.unPanelProfesseur.setVisible(true);break;
		case 4 : this.unPanelEtudiant.setVisible(true);break;
		case 5 : this.unPanelEnseignement.setVisible(true);break;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btQuitter) {
			 IrisEvent.rendreVisibleVueGenerale(false, null);
			 IrisEvent.rendreVisibleVueConnexion(true);
		 }
		 else if (e.getSource() == this.btProfil) {
			 this.afficherPanel(1);
		 }
		 else if (e.getSource() == this.btClasses) {
			 this.afficherPanel(2);
		 }
		 else if (e.getSource() == this.btProfesseurs) {
			 this.afficherPanel(3);
		 }
		 else if (e.getSource() == this.btEtudiants) {
			 this.afficherPanel(4);
		 }
		 else if (e.getSource() == this.btEnseignements) {
			 this.afficherPanel(5);
		 }
		
	}

}







