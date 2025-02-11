package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import application.Main;
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
	                    int prix = rs.getInt("prix");
	                    String description = rs.getString("description");
	                    
	                    return new Item(iditem, nom, efficacite, prix, description);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }
 
	        return null;
	    }
	 
	 
	 public Item listItemaleatoire() {
		    ArrayList<Item> items = new ArrayList<>();
		    String requete = "SELECT * FROM item";

		    try (PreparedStatement ps = connect.prepareStatement(requete);
		         ResultSet rs = ps.executeQuery()) {

		        while (rs.next()) {
		            int id = rs.getInt("idItem");
		            String nom = rs.getString("nom");
		            String efficacite = rs.getString("efficacite");
		            int prix = rs.getInt("prix");
		            String description = rs.getString("description");

		            items.add(new Item(id, nom, efficacite, prix, description));
		        }

		        if (!items.isEmpty()) { 
		            return items.get(Main.random(0, items.size()-1)); 
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return null;
		}

}
