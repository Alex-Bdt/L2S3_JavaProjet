package metier;

public class Capacite {
	
	private String nom;
	private int puissance;
	private int precision;
	private int cout;		//cout en PE
	private String effet;	//code correspondant a une modification d'un coef Monstre
	protected String description;
	
	public Capacite(String nom, int puissance, int precision, int cout, String effet, String description) {
		super();
		this.nom = nom;
		this.puissance = puissance;
		this.precision = precision;
		this.cout = cout;
		this.effet = effet;
		this.description = description;
	}

	public String getNom() {
		return nom;
	}
	public int getPuissance() {
		return puissance;
	}
	public int getPrecision() {
		return precision;
	}
	public int getCout() {
		return cout;
	}
	public String getEffet() {
		return effet;
	}
	public String getDescription() {
		return description;
	}	
	
	public void Alteration(Monstre cible) {
		String[] tab = this.effet.split("");
		//tab[0] bonus ou malus : 'B' ou 'M'
		//tab[1] attaque, special, defense ou vitesse : 'A', 'S', 'D' ou 'V'
		//tab[2] ratio de l'effet (entre 1 et 9)
		double ratio;
		double coef = 0.0;
		try {
			ratio = Double.parseDouble("0."+tab[2]);
		} catch(Exception e) {
			System.out.println("Erreur dans la valeur de l'effet");
			ratio = 0.0;
		}	
		if (tab[0].equals("B")){	//faire gaffe aux if
			coef = 0.0 + ratio;
		}
		if(tab[0].equals("M")) {
			coef = 0.0 - ratio;
		}
		if (tab[1].equals("A")) {
			cible.addCoefAttaque(coef);
			System.out.println("L'attaque de "+ cible.nom + " change.");
		} else if (tab[1].equals("S")) {
			cible.addCoefDefense(coef);
			System.out.println("Le special de "+ cible.nom + " change.");
		} else if (tab[1].equals("D")) {
			cible.addCoefSpecial(coef);
			System.out.println("La defense de "+ cible.nom + " change.");
		} else if (tab[1].equals("V")) {
			cible.addCoefVitesse(coef);
			System.out.println("La vitesse de "+ cible.nom + " change.");
		}
	
	}

	
	public String toString() {
		return nom + "\n puissance : " + puissance 
				+ "\n precision : " + precision
				+ "\n cout :" + cout ;	}
}
