package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import controleur.Controleur;
import controleur.IrisEvent;
import controleur.User;

public class VueConnexion extends JFrame implements ActionListener, KeyListener
{
	private JPanel panelForm = new JPanel (); 
	private JTextField txtEmail = new JTextField("a@gmail.com"); 
	private JPasswordField txtMdp = new JPasswordField("123"); 
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btSeConnecter = new JButton("Se Connecter"); 
	
	public VueConnexion() {
		this.setTitle("Application Admin Scolarité IRIS");
		this.setResizable(false);
		this.setBounds(100, 100, 600, 300);
		this.getContentPane().setBackground(Color.gray);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//placement du logo 
		ImageIcon uneImage = new ImageIcon("src/images/logo.png"); 
		JLabel leLogo = new JLabel(uneImage); 
		leLogo.setBounds(20, 20,240, 240);
		this.add(leLogo); 
		
		//construction du panel form 
		this.panelForm.setBounds(300, 70, 240, 150);
		this.panelForm.setBackground(Color.gray);
		this.panelForm.setLayout(new GridLayout(3,2)); //3lignes - 2 colonnes 
		this.panelForm.add(new JLabel("Email : ")); 
		this.panelForm.add(this.txtEmail); 
		this.panelForm.add(new JLabel("MDP :")); 
		this.panelForm.add(this.txtMdp); 
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btSeConnecter); 
		this.add(this.panelForm);
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		//rendre les txt ecoutables 
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btAnnuler) {
			 this.txtEmail.setText("");
			 this.txtMdp.setText("");
		 }else if (e.getSource() == this.btSeConnecter) {
			this.traitement ();
		 }
	}

	public void traitement () {
		 String email = this.txtEmail.getText(); 
		 String mdp = new String (this.txtMdp.getPassword()); 
		 
		 //on vérifie la sécurité des données 
		 
		 //on vérifie dans la base de données 
		 User unUser = Controleur.selectWhereUser(email, mdp); 
		 if (unUser == null) {
			 JOptionPane.showMessageDialog(this,
					 "Veuillez vérifier vos identifiants");
		 }else {
			 JOptionPane.showMessageDialog(this,
					 "Bienvenue "+unUser.getNom()
					 +"  "+unUser.getPrenom()); 
			 //on ouvre la vue générale 
			 IrisEvent.rendreVisibleVueConnexion(false);
			 IrisEvent.rendreVisibleVueGenerale(true, unUser);
		 }
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.traitement (); 
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}







