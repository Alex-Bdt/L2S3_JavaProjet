package metier;

public class Monstreacquis {
	private int idjoueur;
	private String nomMonstre;
	private String move1;
	private String move2;
	private String move3;
	private String move4;
	
	public Monstreacquis(int idjoueur,String nomMonstre, String move1, String move2, String move3, String move4) {
		this.idjoueur=idjoueur;
		this.nomMonstre=nomMonstre;
		this.move1=move1;
		this.move2=move2;
		this.move3=move3;
		this.move4=move4;
	}

	public int getIdjoueur() {
		return idjoueur;
	}

	public void setIdjoueur(int idjoueur) {
		this.idjoueur = idjoueur;
	}

	public String getNomMonstre() {
		return nomMonstre;
	}

	public void setNomMonstre(String nomMonstre) {
		this.nomMonstre = nomMonstre;
	}

	public String getMove1() {
		return move1;
	}

	public void setMove1(String move1) {
		this.move1 = move1;
	}

	public String getMove2() {
		return move2;
	}

	public void setMove2(String move2) {
		this.move2 = move2;
	}

	public String getMove3() {
		return move3;
	}

	public void setMove3(String move3) {
		this.move3 = move3;
	}

	public String getMove4() {
		return move4;
	}

	public void setMove4(String move4) {
		this.move4 = move4;
	}
	

}
