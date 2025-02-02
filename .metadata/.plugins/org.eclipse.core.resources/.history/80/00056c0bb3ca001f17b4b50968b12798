package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		}


		return null;
	}
	/**
	 public Monstre readJoueur(String nom) {
	        String requete = "SELECT * FROM monstre WHERE nom = ?";

	        try (PreparedStatement ps = connect.prepareStatement(requete)) {
	     
	            ps.setString(1, nom);

	      
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                	
	                    String nom = rs.getString("nom");
	                    double PV = rs.getDouble("PV"); 
	                    double PE = rs.getDouble("PE"); 
	                    double attaque = rs.getDouble("attaque");
	                    double defense = rs.getDouble("defense");
	                    double vitesse = rs.getDouble("vitesse");
	                    double tauxCapture = rs.getDouble("tauxCapture");
	                    String description = rs.getString("description");
	                    double special = rs.getDouble("special");

	                    
	                    return new Joueur(nom, PV, PE, attaque,  special,defense, vitesse, tauxCapture, description);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }

	       
	        return null;
	    }
	 
	 **/
	

}
