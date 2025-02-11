package metier;
//import java.util.ArrayList;

import java.util.ArrayList;

import application.ItemInterface;
import application.Main;

public class Joueur extends Monstre{

	public  static int id = 1;
	
	private int argent;
	private ArrayList<Item> inventaire = new ArrayList<Item>();  //A changer avec la partie gestion d'inventaire

	public Joueur(String nom, int pv, int pe, double attaque, double special, double defense, double vitesse, int tauxCapture, String description) {
		super(nom, pe, pe, vitesse, vitesse, vitesse, vitesse, tauxCapture, description);
		this.argent = 0;
	}

	public int getArgent() {
		return argent;
	}
	public void setArgent(int argent) {
		this.argent = argent;
	}
	public ArrayList<Item> getInventaire() {
		return inventaire;
	}
	public void setInventaire(ArrayList<Item> inventaire) {
		this.inventaire = inventaire;
	}
	public void addItem(Item item) {
		inventaire.add(item);
	}
	public void removeItem(Item itemToRemove) {
		inventaire.remove(itemToRemove); //retire bien que la première occuerence dans la liste !
	}

	@Override
	public String toString() {
		return nom + "   niveau: " + niveau + ", Experience actuelle=" + xpNow + "/" + next_niveau_xp
				+ "\nPV : " + pvNow + "/" + pvMax + "		PE :" + peNow + "/" + peMax
				+ "\n - Attaque: " + attaque
				+ ";\n - Special: " + special
				+ ";\n - Defense: " + defense
				+ ";\n - Vitesse: " + vitesse
				+ ";\n zennys: " + argent;
	}

	public void Capture(Monstre cible) {
		//prioritaire +3
		if (Main.dispoCapture()) {
			double x = ((1.0-(2.0/3.0)*(cible.pvNow/cible.pvMax))*cible.getTauxCapture());	//ratio des PV
			double resultat = (Math.pow(((65535.0*Math.pow((x/255.0), 0.25)+1.0)/65535.0), 4.0))*100;	//pourcentage final

			boolean reussite = false;
			for (int i = 1; i < 5; i++) { //test 4 fois la possibilité de capturer le monstre
				if (Main.random(1, 100) <= resultat) {	
					reussite = true;	//si il y a au moins une réussite, il est capturé
				}
			}
			if (reussite) {
				System.out.println("Capture réussie");
				Main.mettreAJourTexte("Capture réussite de " + cible.getNom());
				Main.suppList(cible);
			} else {
				System.out.println("Echec de la capture");
			}
		} else {
			System.out.println("Il y a encore trop d'ennemis sur le terrain");
		}
		
		
	}
	
	// Inventaire -> prioritaire lors de l'utilisation durant le tour
	
}
