package controleur;

import vue.VueConnexion;
import vue.VueGenerale;

public class IrisEvent {
	
	private static VueConnexion uneVueConnexion; 
	private static VueGenerale uneVueGenerale ; 
	
	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
	}
	
	public static void rendreVisibleVueConnexion (boolean action) {
		uneVueConnexion.setVisible(action);
	}
	public static void rendreVisibleVueGenerale(boolean action, User unUser) {
		if (action == true) {
			uneVueGenerale = new VueGenerale(unUser); 
			uneVueGenerale.setVisible(true);
		}else {
			uneVueGenerale.dispose();//tuer l'application
		}
	}

}
