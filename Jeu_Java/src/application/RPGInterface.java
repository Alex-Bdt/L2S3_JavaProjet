package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import metier.Monstre;
import DAO.MonstreDAO;

public class RPGInterface {
	private static VBox character1Box;
	private static VBox character2Box;
	private static VBox character3Box;
	private static VBox enemy1Box;
	private static VBox enemy2Box;
	private static VBox enemy3Box;

	public GridPane createInterface() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(20));
		grid.setHgap(20);
		grid.setVgap(20);

		if (Main.getAlliesSize() >= 3) {
			character3Box = createCharacterBox(Main.getAllies(2));
		} else {
			character3Box = createCharacterBox(Main.monstreParDefaut);
		}
		if (Main.getAlliesSize() >= 2) {
			character2Box = createCharacterBox(Main.getAllies(1));
		} else {
			character2Box = createCharacterBox(Main.monstreParDefaut);
		}
		character1Box = createCharacterBox(Main.getAllies(0));



		VBox teamBox = new VBox(10, character1Box, character2Box, character3Box);
		teamBox.setAlignment(Pos.TOP_LEFT);

		enemy1Box = createEnemyBox(Main.getEnnemi(0));
		enemy2Box = createEnemyBox(Main.getEnnemi(1));
		enemy3Box = createEnemyBox(Main.getEnnemi(2));

		// Associe la box a son proprio
		if (Main.getAlliesSize() >= 3) {
			Main.getAllies(2).setVBox(character3Box);
		}
		if (Main.getAlliesSize() >= 2) {
			Main.getAllies(1).setVBox(character2Box);
		}
		Main.getAllies(0).setVBox(character1Box);
		
		Main.getEnnemi(0).setVBox(enemy1Box);
		Main.getEnnemi(1).setVBox(enemy2Box);
		Main.getEnnemi(2).setVBox(enemy3Box);


		VBox enemyBox = new VBox(20, enemy1Box, enemy2Box, enemy3Box);
		enemyBox.setTranslateX(200); // Ajuster la position horizontale
		enemyBox.setTranslateY(5); // Ajuster la position verticale

		grid.add(teamBox, 0, 0);
		grid.add(enemyBox, 1, 0);

		return grid;
	}

	private VBox createCharacterBox(Monstre monstre) {
		VBox boxPhoto = new VBox(5);
		boxPhoto.setPrefWidth(150);
		boxPhoto.setPrefHeight(90);

		ProgressBar pvBar = new ProgressBar(monstre.getPvMax());
		ProgressBar peBar = new ProgressBar(monstre.getPeMax());


		Image backgroundImage = new Image(getClass().getResource("Ressources/"+monstre.getNom()+".jpg").toExternalForm());
		ImageView backgroundImageView = new ImageView(backgroundImage);


		backgroundImageView.setFitWidth(boxPhoto.getPrefWidth());
		backgroundImageView.setFitHeight(boxPhoto.getPrefHeight());
		backgroundImageView.setPreserveRatio(false);

		// Utiliser un StackPane pour appliquer la bordure
		StackPane imageContainer = new StackPane();
		imageContainer.getChildren().add(backgroundImageView);


		imageContainer.setStyle(
				"-fx-border-color: #2C5F8A; " + 
						"-fx-border-width: 4; " +
				"-fx-border-radius: 5;"); //


		VBox info_monstre = new VBox(10);
		Label pvLabel = new Label("PV :");
		Label peLabel = new Label("PE :");
		pvLabel.setStyle(
				"-fx-text-fill: white; " + 
						"-fx-font-size: 10px; " + 
						"-fx-font-weight: bold;" 
				);
		peLabel.setStyle(
				"-fx-text-fill: white; " + 
						"-fx-font-size: 10px; " + 
						"-fx-font-weight: bold;" 
				);

		HBox pv_barre = new HBox(5);
		pv_barre.getChildren().addAll(pvLabel, pvBar); 
		HBox pe_barre = new HBox(5);
		pe_barre.getChildren().addAll(peLabel, peBar); 

		info_monstre.getChildren().addAll(pv_barre, pe_barre);
		info_monstre.setPrefWidth(150);
		info_monstre.setPrefHeight(90);

		boxPhoto.getChildren().addAll(imageContainer, info_monstre); // Ajouter le StackPane dans le VBox
		return boxPhoto;
	}

	private VBox createEnemyBox(Monstre monstre) {
		VBox boxPhoto = new VBox(5);
		boxPhoto.setPrefWidth(150);
		boxPhoto.setPrefHeight(90);

		ProgressBar pvBar = new ProgressBar(monstre.getPvMax());
		Image backgroundImage = new Image(getClass().getResource("Ressources/"+monstre.getNom()+".jpg").toExternalForm());
		ImageView backgroundImageView = new ImageView(backgroundImage);


		backgroundImageView.setFitWidth(boxPhoto.getPrefWidth());
		backgroundImageView.setFitHeight(boxPhoto.getPrefHeight());
		backgroundImageView.setPreserveRatio(false);

		// Utiliser un StackPane pour appliquer la bordure
		StackPane imageContainer = new StackPane();
		imageContainer.getChildren().add(backgroundImageView);


		imageContainer.setStyle("-fx-border-color: #850606; " +"-fx-border-width: 4; " +"-fx-border-radius: 5;"); 

		VBox info_monstre = new VBox(10);
		Label pvLabel = new Label("PV :");
		// Label MPLabel = new Label("MP :");
		pvLabel.setStyle("-fx-text-fill: white; " +"-fx-font-size: 10px; " +"-fx-font-weight: bold;");

		HBox pv_barre = new HBox(5);
		pv_barre.getChildren().addAll(pvLabel, pvBar); 

		info_monstre.getChildren().addAll(pv_barre);
		info_monstre.setPrefWidth(150);
		info_monstre.setPrefHeight(90);

		boxPhoto.getChildren().addAll(imageContainer, info_monstre); // Ajouter le StackPane dans le VBox
		return boxPhoto;
	}




	// Méthodes pour accéder ou modifier les personnages indépendamment
	public static VBox getCharacter1Box() {
		return character1Box;
	}
	public static VBox getCharacter2Box() {
		return character2Box;
	}
	public static VBox getCharacter3Box() {
		return character3Box;
	}

	// Méthodes pour accéder ou modifier les ennemis indépendamment
	public static VBox getEnemy1Box() {
		return enemy1Box;
	}
	public static VBox getEnemy2Box() {
		return enemy2Box;
	}
	public static VBox getEnemy3Box() {
		return enemy3Box;
	}

	public static void changePV(Monstre monstre) {
		VBox vbox = monstre.getVBox();

		ProgressBar progressBar = findPvProgressBar(vbox);
		if (progressBar != null) {

			System.out.println((double)monstre.getPvNow()/(double)monstre.getPvMax());
			progressBar.setProgress((double)monstre.getPvNow()/(double)monstre.getPvMax());
		} else {
			System.out.println("Aucune ProgressBar trouvée !");
		}

	}
	public static ProgressBar findPvProgressBar(Parent parent) {
		for (Node node : parent.getChildrenUnmodifiable()) {
			if (node instanceof ProgressBar) {
				return (ProgressBar) node;
			} else if (node instanceof Parent) {
				ProgressBar result = findPvProgressBar((Parent) node);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}

	public static void changePE(Monstre monstre) {
		// a faire
	}

}
