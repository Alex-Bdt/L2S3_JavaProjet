package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import metier.Monstreacquis;

public class MonstreacquisDAO extends DAO {

    public MonstreacquisDAO() {}

    public ArrayList<Monstreacquis> selection() {
        ArrayList<Monstreacquis> Monstre_list = new ArrayList<>();
        String requete = "SELECT * FROM monstreacquis";

        try (PreparedStatement ps = connect.prepareStatement(requete)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) { 
                    int id = rs.getInt("idjoueur");
                    String nomMonstre = rs.getString("nomMonstre"); // Correction: Suppression des espaces
                    String move1 = rs.getString("move1");
                    String move2 = rs.getString("move2");
                    String move3 = rs.getString("move3");
                    String move4 = rs.getString("move4");

                    Monstreacquis Monstre = new Monstreacquis(id, nomMonstre, move1, move2, move3, move4);
                    Monstre_list.add(Monstre); 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Vérification si la liste est vide
        if (Monstre_list.isEmpty()) {
            System.out.println("Aucun monstre trouvé dans la table monstreacquis.");
        }

        return Monstre_list;
    }
}
