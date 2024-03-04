package vue;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel
{
	private JLabel lbTitre = new JLabel();
	
	public PanelPrincipal (String message) {
		this.setBackground(Color.gray);
		this.lbTitre.setText(message);
		
		this.lbTitre.setBounds(300, 20, 300, 20);
		this.add(this.lbTitre);
		
		this.setLayout(null);
		this.setBounds(10, 100, 860, 560);

		this.setVisible(false);
				
	}
}
