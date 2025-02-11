package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.Capacite;
import metier.Monstre;

public class MonstreDAO extends DAO {
	ResultSet rs;
	public MonstreDAO() {};

	public Monstre read(String nom) {
		String requete = "SELECT * FROM monstre WHERE nomMonstre = ?";

		try (PreparedStatement ps = connect.prepareStatement(requete)) {

			ps.setString(1, nom);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {

					String n = rs.getString("nomMonstre");
					int PV = rs.getInt("PV"); 
					int PE = rs.getInt("PE"); 
					double attaque = rs.getDouble("attaque");
					double defense = rs.getDouble("defense");
					double vitesse = rs.getDouble("vitesse");
					int tauxCapture = rs.getInt("tauxCapture");
					String description = rs.getString("description");
					double special = rs.getDouble("special");

					return new Monstre(n, PV, PE, attaque,  special,defense, vitesse, tauxCapture, description);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}
		return null;
	}
	
	
	public Capacite ApprendreCapacites(Monstre monstre) {
	    String existenceQuery = "SELECT COUNT(*) FROM apprend WHERE nomMonstre = ? AND niveau = ?";
	    
	    String query = "SELECT a.nomCapacite FROM apprend a " +
	                   "INNER JOIN capacite c ON a.nomCapacite = c.nomCapacite " +
	                   "WHERE a.nomMonstre = ? AND a.niveau = ?";

	    try (PreparedStatement checkPs = connect.prepareStatement(existenceQuery)) {
	        checkPs.setString(1, monstre.getNom());
	        checkPs.setInt(2, monstre.getNiveau());
	        ResultSet checkRs = checkPs.executeQuery();

	      
	        if (checkRs.next() && checkRs.getInt(1) > 0) {
	            try (PreparedStatement ps = connect.prepareStatement(query)) {
	                ps.setString(1, monstre.getNom());
	                ps.setInt(2, monstre.getNiveau()); // Filtre SQL pour le niveau exact
	                ResultSet rs = ps.executeQuery();

	                rs.next();
	                System.out.println(rs.getString("nomCapacite"));
	                CapaciteDAO c = new CapaciteDAO();
	                Capacite resultat = null;
	                
	                resultat = c.read(rs.getString("nomCapacite"));

	                return resultat;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	    return null;
	}
	
	public ArrayList<Monstre> DifficulteMonstre(int inftaux, int suptaux) {
        ArrayList<Monstre> monstre_difficulte = new ArrayList<>();
        String requete = "SELECT * FROM monstre WHERE tauxCapture BETWEEN ? AND ?";

        try (PreparedStatement ps = connect.prepareStatement(requete)) {
            ps.setInt(1, inftaux);
            ps.setInt(2, suptaux);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String n = rs.getString("nomMonstre");
                    int PV = rs.getInt("PV");
                    int PE = rs.getInt("PE");
                    double attaque = rs.getDouble("attaque");
                    double defense = rs.getDouble("defense");
                    double vitesse = rs.getDouble("vitesse");
                    int tauxCapture = rs.getInt("tauxCapture");
                    String description = rs.getString("description");
                    double special = rs.getDouble("special");

                    Monstre monstre = new Monstre(n, PV, PE, attaque, special, defense, vitesse, tauxCapture, description);
                    monstre_difficulte.add(monstre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monstre_difficulte; 
    }
	
	
	
	
	
	


	

}
