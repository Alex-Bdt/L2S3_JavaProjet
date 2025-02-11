package application;

import DAO.ItemDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import metier.Item;
import metier.Joueur;

public class ShopInterface {

	private static VBox item1Box;
	private static VBox item2Box;
	private static VBox item3Box;
	private static ItemDAO itemdao;
	
	private Joueur joueur = Main.joueur;

	public GridPane createInterface() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(50));
		grid.setHgap(20);
		grid.setVgap(20);

		itemdao=new ItemDAO();
		Item item1 = itemdao.listItemaleatoire();
		Item item2 = itemdao.listItemaleatoire();
		Item item3 = itemdao.listItemaleatoire();

		item1Box = createItemBox(item1);
		item2Box = createItemBox(item2);
		item3Box = createItemBox(item3);

		grid.add(item1Box, 0, 0);
		grid.add(item2Box, 1, 0);
		grid.add(item3Box, 2, 0);

		item1Box.setTranslateX(0);
		item1Box.setTranslateY(20);

		item2Box.setTranslateX(65);
		item2Box.setTranslateY(20);

		item3Box.setTranslateX(130);
		item3Box.setTranslateY(20);

		return grid;
	}

	private VBox createItemBox(Item item) {
		String baseStyle = "-fx-font-size: 10px; " + "-fx-font-family: 'Georgia'; " + "-fx-font-weight: bold; " + "-fx-text-fill: white; " + "-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " + "-fx-border-color: #5A9BD6; " + "-fx-border-width: 4px; " + "-fx-border-radius: 10px; " + "-fx-background-radius: 10px; " + "-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.7, 2, 2);";
		String hoverStyle= "-fx-font-size: 10px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: #FFA500; " +"-fx-background-color: linear-gradient(#FF8C00, #1E3A5F); " +"-fx-border-color: #FFA500; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.9), 10, 0.8, 2, 2);"; 
		
		VBox box = new VBox(8);
		box.setPrefWidth(100);
		box.setAlignment(Pos.CENTER);

		Image itemImage = new Image(getClass().getResource("Ressources/"+item.getNom()+".png").toExternalForm());
		ImageView imageView = new ImageView(itemImage);
		imageView.setFitWidth(90);
		imageView.setFitHeight(90);
		imageView.setPreserveRatio(true);

		StackPane imageContainer = new StackPane();
		imageContainer.getChildren().add(imageView);
		imageContainer.setStyle("-fx-border-color: #2C5F8A; " +"-fx-border-width: 10; " +"-fx-border-radius: 5;");

		Label nameLabel = new Label(item.getNom());
		nameLabel.setStyle("-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: white; " +"-fx-font-size: 10px; ");

		HBox priceBox = new HBox(0);
		priceBox.setAlignment(Pos.CENTER);

		Label prixLabel = new Label( "" + item.getPrix());
		prixLabel.setStyle("-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: white; " +"-fx-font-size: 20px; ");

		Image coinImage = new Image(getClass().getResource("Ressources/Coin.png").toExternalForm());
		ImageView coinImageView = new ImageView(coinImage);
		coinImageView.setFitWidth(20);
		coinImageView.setFitHeight(20);
		coinImageView.setPreserveRatio(true);

		priceBox.getChildren().addAll(prixLabel, coinImageView);

		Button actionButton = new Button("Acheter");
		actionButton.setStyle(baseStyle);
		box.getChildren().addAll(imageContainer, nameLabel, priceBox, actionButton);
		box.setPrefHeight(200);
		
		
		actionButton.setOnMouseEntered(e -> { actionButton.setStyle(hoverStyle);});
        actionButton.setOnMouseExited(e -> { actionButton.setStyle(baseStyle);});
		
		actionButton.setOnAction(event -> {
			int prix = item.getPrix();
			
			if (prix <= joueur.getArgent()) {
				System.out.println("Achat de l'item : " + item.getNom());
				joueur.addItem(item);
				joueur.setArgent(joueur.getArgent()-prix);
				//puis passer au prochain combat quand on a acheter 1 item
				Menu_combat.NewCombat();
				
			} else {
				System.out.println("Pas assez d'argent");
				
				// Un peu violent certe
				Menu_combat.NewCombat();
			}
			});
			
			

		

		return box;
	}



	public static VBox getItem1Box() {
		return item1Box;
	}

	public static VBox getItem2Box() {
		return item2Box;
	}

	public static VBox getItem3Box() {
		return item3Box;
	}
}
