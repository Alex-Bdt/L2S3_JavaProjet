package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import metier.Capacite;
import metier.Item;
import metier.Joueurenregistre;

public class JoueurenregistreDAO extends DAO{

	ResultSet rs;
	public JoueurenregistreDAO() {};

	public ArrayList<Joueurenregistre> selection() {
		ArrayList<Joueurenregistre> Joueur_list = new ArrayList<>();
		String requete = "SELECT * FROM joueur";

		try (PreparedStatement ps = connect.prepareStatement(requete)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) { 
					int id = rs.getInt("id");
					int roundmax = rs.getInt("roundmax");
					Date datesave = rs.getDate("datesave");

					Joueurenregistre joueur = new Joueurenregistre(id, roundmax, datesave);
					Joueur_list.add(joueur); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Joueur_list;
	}

	public void NewJouer(int idJoueur) {
		String requete = "INSERT INTO `joueur` (`id`, `roundMax`, `dateSave`) VALUES (NULL, 0, NOW())";

	    try (PreparedStatement ps = connect.prepareStatement(requete)) {

	        int rowsInserted = ps.executeUpdate();

	        // Vérification de l'insertion
	        if (rowsInserted > 0) {
	            System.out.println("Insertion réussie dans la table joueur !");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}

}
