package application;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Menu_commencement extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane topPane = new Pane();
			topPane.setPrefHeight(800);
			topPane.setMaxWidth(600);

			Image backgroundImage = new Image(getClass().getResource("Ressources/image_commencementt.jpg").toExternalForm());
			ImageView backgroundImageView = new ImageView(backgroundImage);
			backgroundImageView.setFitWidth(600);
			backgroundImageView.setFitHeight(700);
			backgroundImageView.setPreserveRatio(false);
			Color White=Color.WHITE;

			Text text = new Text( "DUNGEON MASTER \n\n"+
					"Bienvenue, aventurier intrépide, dans ce monde mystérieux \n"+
					"Autrefois, humains et monstres vivaient en paix. Mais un pouvoir obscur a corrompu les créatures, les transformant en ennemis redoutables. Les donjons, pleins de dangers et de secrets, se mirent à apparaitre partout autour du globe.\r\n"
					+ "\r\n"
					+ "Tu ne te souviens peut-être pas mais tu es l’un des rares capables de changer le cours des choses. Arrivé en ce monde armé de ton courage, tu peux affronter les monstres, ou les capturer pour en faire tes alliés. Chaque choix te rapproche de ton objectif : explorer, survivre, et découvrir la vérité derrière cette corruption.\r\n"
					+ "\r\n"
					+ "Tu ne te souviens point de ton nom... \nAlors ce sera AZ..."
					+ "\r\n\n"
					+ "Ton aventure commence ici. Entre mon enfant et fais tes preuves.");
			text.setFill(White);
			text.setStyle("-fx-font-size: 20px; -fx-font-family: 'Georgia'; -fx-font-weight: bold;");
			text.setTextAlignment(TextAlignment.CENTER);

			// Conteneur pour le texte avec centrage
			TextFlow textFlow = new TextFlow(text);
			textFlow.setMaxWidth(500); 
			textFlow.setLayoutX(50); 
			textFlow.setLayoutY(600); 

			// Animation du texte vers le haut
			TranslateTransition textAnimation = new TranslateTransition(Duration.seconds(10), textFlow);
			textAnimation.setFromY(300); // Position de départ
			textAnimation.setToY(-550); // Position finale 
			textAnimation.setCycleCount(1); // Une seule fois
			textAnimation.play();

			// Ajouter un bouton
			Button startButton = new Button("COMMENCER \n L'AVENTURE");
			startButton.setPrefSize(200, 100);
			startButton.setStyle("-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: white; " +"-fx-background-color: linear-gradient(#78866B, #717A81 ); " +"-fx-border-color: #78866B; " +"-fx-border-width: 4px; " + "-fx-border-radius: 5px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.9), 10, 0.8, 2, 2);");

			String hoverStyle = "-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: white; " +"-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " +"-fx-border-color: #5A9BD6; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.7, 2, 2);";
			String baseStyle = "-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: black; " +"-fx-background-color: linear-gradient(#78866B, #717A81 ); " +"-fx-border-color: #78866B; " +"-fx-border-width: 4px; " +"-fx-border-radius: 5px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.9), 10, 0.8, 2, 2);";

			startButton.setOnMouseEntered(e -> startButton.setStyle(hoverStyle));
			startButton.setOnMouseExited(e -> startButton.setStyle(baseStyle));
			startButton.setLayoutX(200); // Centrer horizontalement
			startButton.setLayoutY(650); // Positionner en bas de l'écran

			// Action pour le bouton
			startButton.setOnAction(e -> {
				try {
					Main.Commencement();
					Menu_combat Mainapp = new Menu_combat();
					Mainapp.start(primaryStage);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});

			// Ajouter les éléments au Pane
			topPane.getChildren().addAll(backgroundImageView, textFlow, startButton);

			// Créer la scène et l'afficher
			Scene scene = new Scene(topPane, 600, 700);
			primaryStage.setTitle("Jeu Java - Commencement");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
