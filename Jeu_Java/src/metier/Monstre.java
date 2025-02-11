package metier;

import java.util.ArrayList;

import DAO.MonstreDAO;
import javafx.scene.layout.VBox;
import application.Main;
import application.RPGInterface;

public class Monstre{
	
	public String nom;
	
	// statistiques actuelles du monstre
	protected double attaque, special, defense, vitesse;
	// difference entre la stat maximum et la stat actuelles
	protected int pvMax, pvNow, peMax, peNow;
	
	// statistiques de Base de l'espece
	protected double attaqueB, specialB, defenseB, vitesseB;
	protected int pvB, peB;
	
	protected int niveau; 
	protected int tauxCapture;
	protected String description;
	// next_niveau_xp est le nombre de points d'xp qu'il faut pour passer au niveau suivant.
	protected double xpNow, next_niveau_xp;
	
	//Malus ou Bonus sur la statistique
	protected double coefAttaque = 1.0, coefSpecial = 1.0, coefDefense = 1.0, coefVitesse = 1.0;
	
	//Les attaques du monstre
	protected ArrayList<Capacite> moveSet = new ArrayList<Capacite>(); //contient les attaques quand elles seront definies
	protected Capacite move1 = new Capacite("Charge", 40, 100, 0, null, "Une petite attaque de base"); //Attaque par défaut.
	protected Capacite move2 = null, move3 = null, move4 = null;
	
	protected VBox MonstreVBox = null;
	
	public Monstre(String nom, int pvB, int peB, double attaqueB, double specialB, double defenseB, double vitesseB, int tauxCapture, String description) {
		this.nom = nom;
		niveau = 1;
		this.moveSet.add(move1);
		
		this.pvB = pvB;	this.peB = peB;
		this.attaqueB = attaqueB; this.specialB = specialB;
		this.defenseB = defenseB; this.vitesseB = vitesseB;
		// stat init = (2*baseStat)*(niveau/100)+S
		//	Si PV, PE alors S = niveau + 10
		//  Si autres S = 5
		this.pvMax = (int) ((2*pvB)*((double)niveau/100)+niveau+10);
		this.peMax = (int) ((2*peB)*((double)niveau/100)+niveau+10);
		this.attaque = Math.round(((2*attaqueB)*((double)niveau/100)+5.0)*100.0)/100.0;
		this.special = Math.round(((2*specialB)*((double)niveau/100)+5.0)*100.0)/100.0;
		this.defense = Math.round(((2*defenseB)*((double)niveau/100)+5.0)*100.0)/100.0;
		this.vitesse = Math.round(((2*vitesseB)*((double)niveau/100)+5.0)*100.0)/100.0;
		this.tauxCapture = tauxCapture;
		this.description = description;
		
		next_niveau_xp = (Math.pow((niveau+1),3))*0.8; // experience(niveau) = ((niveau)^3)*0.8
		pvNow = this.pvMax;
		peNow = this.peMax;
	}

	public String getNom() {
		return nom;
	}
	public int getPvMax() {
		return pvMax;
	}
	public int getPvNow() {
		return pvNow;
	}
	public void setPvNow(int pvNow) {
		this.pvNow = pvNow;
		
		if (this.pvNow < 0) {
			this.pvNow = 0;
		}
		if (this.pvNow > pvMax) {
			this.pvNow = pvMax;
		}
	}
	public int getPeMax() {
		return peMax;
	}
	public int getPeNow() {
		return peNow;
	}
	public void setPeNow(int peNow) {
		this.peNow = peNow;
		
		if (this.peNow < 0) { //pas sensé arriver mais au cas où
			this.peNow = 0;
		}
		if (this.peNow > peMax) {
			this.peNow = peMax;
		}
	}
	public double getVitesse() {
		return vitesse;
	}
	public double getTauxCapture() {
		return tauxCapture;
	}
	public double getCoefAttaque() {
		return coefAttaque;
	}
	public void setCoefAttaque(double coefAttaque) {
		this.coefAttaque = coefAttaque;
	}
	public void addCoefAttaque(double coefAttaque) {
		this.coefAttaque = this.coefAttaque + coefAttaque;
	}
	public double getCoefSpecial() {
		return coefSpecial;
	}
	public void setCoefSpecial(double coefSpecial) {
		this.coefSpecial = coefSpecial;
	}
	public void addCoefSpecial(double coefSpecial) {
		this.coefSpecial = this.coefSpecial + coefSpecial;
	}
	public double getCoefDefense() {
		return coefDefense;
	}
	public void setCoefDefense(double coefDefense) {
		this.coefDefense = coefDefense;
	}
	public void addCoefDefense(double coefDefense) {
		this.coefDefense = this.coefDefense + coefDefense;
	}
	public double getCoefVitesse() {
		return coefVitesse;
	}
	public void setCoefVitesse(double coefVitesse) {
		this.coefVitesse = coefVitesse;
	}
	public void addCoefVitesse(double coefVitesse) {
		this.coefVitesse = this.coefVitesse + coefVitesse;
	}	
	public String getDescription() {
		return description;
	}
	public int getNiveau() {
		return niveau;
	}
	public Capacite getMove1() {
		return move1;
	}
	public Capacite getMove2() {
		return move2;
	}
	public Capacite getMove3() {
		return move3;
	}
	public Capacite getMove4() {
		return move4;
	}
	public VBox getVBox() {
		return MonstreVBox;
	}
	public void setVBox(VBox box) {
		MonstreVBox = box;
	}
	
	
	public Capacite choisirCapacite(int choix) {
        switch (choix) {
            case 1: return move1;
            case 2: return move2;
            case 3: return move3;
            case 4: return move4;
            default: throw new IllegalArgumentException("Choix invalide pour une capacité.");
        }
    }
	
	// calcul des dommages :
	// pvPerdus =(int) ((((niveau*0.5+2)*(attaqueAttaquant*coefboost)*puissanceCapacite)/(defenseCible*coefBoost))/50)
	public void Attaquer(Capacite capacite, Monstre cible) {
		if (Main.random(1, 100) <= capacite.getPrecision()) { //chance de reussir l'attaque

			if (capacite.getCout()== 0) {	//Si la capacité est d'attaque
				int pvPerdus = (int) ((((this.niveau*0.5+2)*(this.attaque*this.coefAttaque)*capacite.getPuissance())/(cible.defense*cible.getCoefDefense()))/50);
				cible.setPvNow(cible.pvNow - pvPerdus); //gestion de la mort dans Combat
				RPGInterface.changePV(cible);
				System.out.println("L'ennemis a perdu " + pvPerdus);
				cible.Deces();
			} else {
				if (this.peNow >= capacite.getCout()) { // Si le monstre a assez d'énergie pour utiliser la capacite

					if (capacite.getPuissance() == 0) { //Si statut

						if (capacite.getEffet()!= null) { //petite vérif supplémentaire A VOIR
							capacite.Alteration(cible);
						} else {
							int pvPerdus = (int) ((((this.niveau*0.5+2)*(this.special*this.coefSpecial)*capacite.getPuissance())/(cible.defense*cible.getCoefDefense()))/50);
							cible.setPvNow(cible.pvNow-pvPerdus);
							RPGInterface.changePV(cible);
							this.setPeNow(this.peNow - capacite.getCout());	//mettre le cout d'energie de la capacite
							
							//RPGInterface.changePE(this); MODIFIER POUR AVOIR QUE LES ALLIES QUI CHANGENT
							
							System.out.println("L'ennemis a perdu " + pvPerdus);
							cible.Deces();
						}

					} else {
						System.out.println(this.nom + " n'a pas assez d'energie pour lancer son attaque");
					}
				}
			}	
		} else {System.out.println("L'attaque a echoue");}	
	}

	protected void Deces() {
		if (this.pvNow <= 0) {
			pvNow = 0; //pour l'affichage
			Main.suppList(this); //pour l'enlever de la liste des combattants
			System.out.println(this.getNom() + " est mort de deces");
			// il y a aussi l'affichage qui se change
		}
	}
	
	public void Defendre() {
		//prioritaire 
		this.addCoefSpecial(0.5);
		
		System.out.println(this.nom + " attend en position defensive");
	}

	public void gain_xp(double gain) {
		xpNow += gain;
		while (xpNow >= next_niveau_xp) {
			xpNow = xpNow - next_niveau_xp;
			niveau++;
			next_niveau_xp = (Math.pow((niveau+1),3))*0.8;; // experience(niveau) = ((niveau)^3)*0.8
			
			double mult = 1.0/50.0;
			pvMax = (int) ((2*pvB)*((double)niveau/100)+niveau+10);
			pvNow = pvMax;	//Regagne toutes ses pv au passage de niveau 
			peMax = (int) ((2*peB)*((double)niveau/100)+niveau+10);
			pvNow = peMax;	//Regagne aussi ses pe
			attaque += mult*attaqueB;
			special += mult*specialB;
			defense += mult*defenseB;
			vitesse += mult*vitesseB;
			
			System.out.println( nom + " est desormais niveau " + this.niveau + ", ses statistiques ont augmentees");
			
			//
			MonstreDAO m = new MonstreDAO();
			Capacite newCapacite = m.ApprendreCapacites(this);
			if (newCapacite != null) {
				new_move(newCapacite);
			}
		}
	}
	// capacite obtenu si il y en a une dans la base
	public boolean new_move(Capacite capacite) {
		System.out.println(nom + " apprend une nouvelle capacité : " + capacite.getNom());
		
		if (moveSet.size() <= 4) {	//si il n'a pas ses 4 attaques, on ajoute directement où l'on peut
			if (move2 == null) { move2 = capacite; moveSet.add(move2); return true;
			}
			if (move3 == null) { move3 = capacite; moveSet.add(move3); return true;
			}
			if (move4 == null) { move4 = capacite; moveSet.add(move4); return true;
			}
		} else {	//emplace une capacite par une nouvelle 
			boolean remplacer  = true;	// demander si on veut la capacite ou pas !!!!!!!!!!
			if (remplacer){ 
				//choix de la capacite a enlever
				//remplacer move-nieme par la nouvelle capacite
				return true;
			} else { return true; } // ne rien faire
		}
		return true;
	}
	
	
	public void InformationsRapides() {
		System.out.println(nom + ", niveau: " + niveau
				+ "\nPV : " + pvNow + "/" + pvMax + " et PE :" + peNow + "/" + peMax); 
	}
	
	public String toString() {
		return nom + "   niveau: " + niveau + ", Experience actuelle=" + xpNow + "/" + next_niveau_xp
				+ "\nPV : " + pvNow + "/" + pvMax + "		PE :" + peNow + "/" + peMax
				+ "\n - Attaque: " + attaque
				+ ";\n - Special: " + special
				+ ";\n - Defense: " + defense
				+ ";\n - Vitesse: " + vitesse + ";";
	}


}
