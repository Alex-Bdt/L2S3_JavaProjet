package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

import DAO.JoueurenregistreDAO;
import DAO.MonstreDAO;
import javafx.scene.control.Label;
import metier.Joueur;
import metier.Monstre;
import metier.Capacite;
import metier.Item;


public class Main {

	private static ArrayList<Monstre> allies = new ArrayList<Monstre>(); // ceux dont ont peut choisir l'action
	private static ArrayList<Monstre> ennemis = new ArrayList<Monstre>(); //liste d'ennemis = combattants - allies
	private static PriorityQueue<MonstreAction> actions = new PriorityQueue<>(
						Comparator.comparingDouble((MonstreAction a) -> -a.getMonstre().getVitesse()));
	
	public static boolean CombatEnCours = false;
	public static Capacite acte = null;
	public static Monstre cible = null ;
	public static int Round = 1, niveauVoulu = 1;
	private static int Compteur = 0, min = 200, max = 250; 
	public static Monstre monstreParDefaut = new Monstre("default",0,0,0,0,0,0,0,null);
	public static Monstre monstreActuel = monstreParDefaut; //profil par defaut
	public static Joueur joueur = new Joueur("AZ", 100, 100, 120, 120, 120, 120, 0, null);
	Label label = Menu_combat.action_text;
	
	public static Monstre getAllies(int num) {
		return allies.get(num);
	}
	public static int getAlliesSize() {
		return allies.size();
	}
	public static Monstre getEnnemi(int num) {
		return ennemis.get(num);
	}

	public static int random(int min, int max) { return ((int) (Math.random() * (max - min+1)) + min); }
	
	public static boolean dispoCapture() {
		if (ennemis.size() == 1) {return true;}	else {return false;}
	}
	
	public static void ajoutList(Monstre monstreToAdd, ArrayList<Monstre> liste) { liste.add(monstreToAdd); } //Ajoute un monstre à la liste des combattants
	
	public static void suppList(Monstre monstreToRemove) {	
		// Recherche le monstre par son nom
		// Puis on cherche a le supprimer d'allies ou d'ennemis
		Optional<Monstre> aRemove = allies.stream()
				.filter(p -> p.getNom().equals(monstreToRemove.getNom()))
				.findFirst();
		aRemove.ifPresent(allies::remove);
		
		if (CombatEnCours) { //durant un commbat en cours
			if (ennemis.contains(monstreToRemove)) { //si le monstre a supprimer est un ennemi
				Double tauxCapture = monstreToRemove.getTauxCapture();
				Double xpDonner = ((((0.0037*tauxCapture)*(0.0037*tauxCapture))*(-1.94)*tauxCapture+300)*monstreToRemove.getNiveau())/(3 * allies.size());
				System.out.println("Gain de " + xpDonner + " xp");
				for (Monstre i : allies) {
					i.gain_xp(xpDonner);
				}
			}
		}
		
		Optional<Monstre> eRemove = ennemis.stream()
				.filter(p -> p.getNom().equals(monstreToRemove.getNom()))
				.findFirst();
		eRemove.ifPresent(ennemis::remove);
		//si un ennemi est enlever alors c'est une mort, soit un gain d'xp pour les allies
	}
	
	public static void mettreAJourTexte(String nouveauTexte) {
        javafx.application.Platform.runLater(() -> Menu_combat.action_text.setText(nouveauTexte));
    }

	public static void Commencement() { 
		//alliées créées une seul fois au début et de nouveaux ennemis à chaque combat
		
		JoueurenregistreDAO JeDAO = new JoueurenregistreDAO();
		JeDAO.NewJouer((Joueur.id)+1);
		Joueur.id++;
		
		MonstreDAO m = new MonstreDAO();
		Monstre ash = m.read("Ash");
		Monstre madeline = m.read("Madeline");
		
		NouveauEnnemis(niveauVoulu);

		System.out.println("Début du jeu ...");
		ajoutList(joueur, allies); //ajout du joueur
		
		//ajout des alliees pour la partie
		ajoutList(madeline, allies); ajoutList(ash, allies);
		
		monstreActuel = allies.get(Compteur);
		CombatEnCours = true;
			
		Item item = new Item(0,"Potion", "BP2", 10, "Redonne 20PV");

		joueur.addItem(item); 	
		
	}	

	public static void NouveauEnnemis(int niveau) {
		//il faut des monstres differents	
		ArrayList<Monstre> ennemisPossibles = null;
		MonstreDAO m = new MonstreDAO();
		try {
			ennemisPossibles = m.DifficulteMonstre(min, max);
		} catch (Exception e) {
			System.out.println("ERREUR DE RECUPERATION");
			e.printStackTrace();
		}
		if (ennemisPossibles.size() < 3) {
			try {
				ennemisPossibles = m.DifficulteMonstre(min-50, max);
			} catch (Exception e) {
				System.out.println("ERREUR DE RECUPERATION");
				e.printStackTrace();
			}
		}

		while (ennemis.size() < 3) {
			int index = random(0, ennemisPossibles.size()-1);
			ajoutList(ennemisPossibles.get(index), ennemis);
			ennemisPossibles.remove(index);	
		}
		
		for (Monstre i : ennemis) {
			while (i.getNiveau() != niveau) { //risqué ?
				i.gain_xp(1); //gain de 1 point d'xp par 1 point d'xp
			}
		}
		
		System.out.println("\n" + ennemis);	
	}
	
	public static void ActionAllie() {
		actions.add(new MonstreAction(monstreActuel, acte, cible));
		if ((acte == null) && (cible == null)) {
		}
		else if ((acte == null) && (cible != null)) {
			 mettreAJourTexte("Tentative de Capture du Monstre");
		} else {
			 mettreAJourTexte(monstreActuel.getNom() + " utilise " + acte.getNom() + " sur " + cible.getNom());
		}
		
		
		if (allies.size() != 0) {
			Compteur = (Compteur+1) % allies.size();
		}
		monstreActuel = allies.get(Compteur);
		Menu_combat.updateBoutons();
		
		if (Compteur == 0) { //tout les choix sont faient, résolution du tour
			//choix de l'IA
			for (Monstre i : ennemis) {	
				acte = i.choisirCapacite(Main.random(1,4));
				while (acte == null) {
					acte = i.choisirCapacite(Main.random(1,4));
				}
				cible = allies.get(Main.random(0, allies.size()-1));
				actions.add(new MonstreAction(i,acte, cible));
			}
			acte = null; cible = null;
			
			//la résolution du tour
			while (!actions.isEmpty()) {
				
				MonstreAction action = actions.poll();
				
				if ( ennemis.contains(action.getMonstre()) || allies.contains(action.getMonstre())) {
					System.out.println(action.getMonstre().getNom() + " agit !");

					if ((action.getCapacite() == null) && (action.getCible() == null)) {
						//ne se passe rien vu que l'action est deja faite
					} else if ((action.getCapacite() == null) && (action.getCible() != null)) { // si c'est la capture
						action.getMonstre().addCoefVitesse((-10.0));
						if (action.getMonstre().getClass().getName() == "metier.Joueur") {
							Main.cible = Main.getEnnemi(0);
							((Joueur) action.getMonstre()).Capture(cible);
						}

					} else { // si c'est une attaque
						if (ennemis.contains(action.getCible()) || allies.contains(action.getCible())) {
							action.getMonstre().Attaquer(action.getCapacite(), action.getCible());
						} else { System.out.println("Aucune cible pour l'attaque de " + action.getMonstre().getNom());}
					} 
				} else { System.out.println(action.getMonstre().getNom() + " ne peut plus agir !"); }

			}
			
			// vérifie si il y a une équipe de morte ;\
			if (allies.size() == 0) {
				System.out.println("VOUS AVEZ PERDU");
				CombatEnCours = false;
				joueur.setArgent(0);
				//Fin de la game, quitte la partie en cours
				//sauvegarde le rang max, remet tout à 0 (argent et inventaire aussi)
				//-> met sur le menu de choix d'equipe pour lancer une nouvelle partie
				
			}
			if (ennemis.size() == 0) {
				System.out.println("VOUS AVEZ GAGNE");
				CombatEnCours = false;
				
				for (Monstre i : allies) { // clean tout les coefs qui ont etaient modifie durant le combat
					i.setCoefAttaque(1);
					i.setCoefDefense(1);
					i.setCoefSpecial(1);
					i.setCoefVitesse(1);
				}
				joueur.setArgent(joueur.getArgent() + random(4,9));	 //gagne un peu d'argent	
				if ((Round+1) % 10 == 0) {
					if (min > 0) {
						min =-20;
					} else { //max arrive à 0
						System.out.println("FIN DU JEU \n\nLE DONJON EST A VOUS DESORMAIS");
					}
				}					
				if ((Round+1) % 1 == 0) { //Marchant tout les 5 combats
					niveauVoulu ++;		  // Après chaue marchant, le niveau des ennemis augment de 1 (c'est très long au début)
					Menu_combat.sousMenuMarchand();
				} else { // sinon un nouveau combat
					CombatEnCours = true;
					Menu_combat.NewCombat();
				}
				
			}
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println("uwu\nMauvais fichier :)");


		
		
		
		/*
		//1. choix des équipiers parmis la liste dispo (monstre de base au début + monstres capturées par la suite


		//2. arrive en combat, pour chaque allier on demande l'action a effectuer
		// les boutons relies a une maniere de garder en memoire l'action a effectuer a la resolution
		
		//3. resolution du tour, chacun fait son action dans l'ordre de la liste combattants

		
		// a chanque monstre vaincu, les monstres allies gagner de l'experience -> niveau et new moves
		    //x = tauxCapture
		 	//calcul d'xp donnée: ((0.0037x)^2*(−1.94)x+300)*nieauMonstreVaincu)/(7 * allies.size())
		 	
		 	
		//et on recommence un nouveau tour si la liste allier ou ennemis est pas vide (la capture enlève)
			// while
			if ( allies.isEmpty())	//-> defaite
			if ( ennemis.isEmpty())	//-> victoire, combat suivant
		*/
	}
}

//Classe pour encapsuler une action de personnage
class MonstreAction {
	private final Monstre monstre;
	private final Capacite capacite;
	private final Monstre cible;

	public MonstreAction(Monstre monstre, Capacite capacite, Monstre cible) {
		this.monstre = monstre;
		this.capacite = capacite;
		this.cible = cible;
	}
	public Monstre getMonstre() {
		return monstre;
	}
	public Capacite getCapacite() {
		return capacite;
	}
	public Monstre getCible() {
		return cible;
	}
}