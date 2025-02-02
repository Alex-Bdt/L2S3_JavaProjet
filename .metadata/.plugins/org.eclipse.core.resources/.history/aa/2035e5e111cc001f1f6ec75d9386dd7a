package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import metier.Item;

public class ItemDAO extends DAO{
	ResultSet rs;
	public ItemDAO() {};

	 public Item read(int idItem ) {
	        String requete = "SELECT * FROM item WHERE idItem = ?";

	        try (PreparedStatement ps = connect.prepareStatement(requete)) {
	     
	            ps.setInt(1, idItem);

	      
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                	int iditem = rs.getInt("idItem"); 
	                    String nom = rs.getString("nom");
	                    String efficacite = rs.getString("efficacite");	
	                    String description = rs.getString("description");
	                 

	                    
	                    return new Item(iditem, nom, efficacite,description);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }

	       
	        return null;
	    }

}
