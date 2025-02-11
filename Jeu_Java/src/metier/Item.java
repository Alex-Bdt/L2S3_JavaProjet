package metier;

import application.RPGInterface;

public class Item {
	private int idItem ;
	private String nom;
	private String efficacite;
	private int prix;
	private String description;
	
	public Item(int idItem,String nom,String efficacite,int prix,String description ) {
		
		this.idItem=idItem;
		this.nom=nom;
		this.efficacite=efficacite;
		this.prix=prix;
		this.description=description;
		
	}
	
	@Override
	public String toString() {
		return "Item [idItem=" + idItem + ", nom=" + nom + ", efficacite=" + efficacite + ", prix=" + prix
				+ ", description=" + description + "]";
	}

	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEfficacite() {
		return efficacite;
	}
	public void setEfficacite(String efficacite) {
		this.efficacite = efficacite;
	}
	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public void Effet(Monstre cible) {
		
		String[] tab = this.efficacite.split("");
		//tab[0] bonus ou malus : 'B' ou 'M'		'P' et 'E' sont tjr des bonus
		//tab[1] attaque, special, defense ou vitesse : 'P', 'E', 'A', 'S', 'D' ou 'V'
		//tab[2] coef de l'effet (entre 0,1 et 0,9) ou pour 'P' et 'E' nombre*10 (entre 10 et 90)
		String val1 = tab[1];
		
		if (val1.equals("P") || val1.equals("E")) {
			int ratio;
			try {
				ratio = Integer.parseInt(tab[2]+"0");
			} catch(Exception e) {
				System.out.println("Erreur dans la valeur de l'effet");
				ratio = 0;
			}
			if (val1.equals("P")) {
				cible.setPvNow(cible.getPvNow()+ratio);
				System.out.println("Le PV de "+ cible.nom + " augementent.");
				RPGInterface.changePV(cible);
			} else if (val1.equals("E")) {
				cible.setPeNow(cible.getPeNow()+ratio);
				System.out.println("Le PE de "+ cible.nom + " augementent.");
			}
			
		} else {
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
			if (val1.equals("A")) {
				cible.addCoefAttaque(coef);
				System.out.println("L'attaque de "+ cible.nom + " change.");
			} else if (val1.equals("S")) {
				cible.addCoefDefense(coef);
				System.out.println("Le special de "+ cible.nom + " change.");
			} else if (val1.equals("D")) {
				cible.addCoefSpecial(coef);
				System.out.println("La defense de "+ cible.nom + " change.");
			} else if (val1.equals("V")) {
				cible.addCoefVitesse(coef);
				System.out.println("La vitesse de "+ cible.nom + " change.");
			}
		}
	}
	
}
