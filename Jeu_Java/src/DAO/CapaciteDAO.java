package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import metier.Capacite;

public class CapaciteDAO extends DAO  {
	
	ResultSet rs;
	public CapaciteDAO() {};

	 public Capacite read(String nomcap) {
	        String requete = "SELECT * FROM capacite WHERE nomCapacite = ?";

	        try (PreparedStatement ps = connect.prepareStatement(requete)) {
	     
	            ps.setString(1, nomcap);

	      
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                	
	                    String nomC = rs.getString("nomCapacite");
	                    int puissance = rs.getInt("puissance"); 
	                    int precis = rs.getInt("precis"); 
	                    int cout = rs.getInt("cout"); 
	                    String effet = rs.getString("effet");	
	                    String description = rs.getString("description");
	                 

	                    
	                    return new Capacite(nomC, puissance, precis, cout,effet,description);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }

	        return null;
	    }

}

