package application;

import DAO.MonstreacquisDAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu_demmarage extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane topPane = new Pane();
			topPane.setPrefHeight(800);
			topPane.setMaxWidth(600);

			// Ajouter une image d'arrière-plan
			Image backgroundImage = new Image(getClass().getResource("Ressources/image_commencementt.jpg").toExternalForm());
			ImageView backgroundImageView = new ImageView(backgroundImage);
			backgroundImageView.setFitWidth(600);
			backgroundImageView.setFitHeight(800);
			backgroundImageView.setPreserveRatio(false);

			String hoverStyle = "-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: white; " +"-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " +"-fx-border-color: #5A9BD6; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.7, 2, 2);";
			String baseStyle = "-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: #1E3A5F; " +"-fx-background-color: #FF8C00; " +"-fx-border-color: #2C5F8A; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.9), 10, 0.8, 2, 2);";

			Label titre = new Label("DUNGEON MASTER");
			titre.setPrefSize(200, 90);
			titre.setStyle("-fx-font-size: 18px;" +  "-fx-font-family: 'Georgia';" +  "-fx-font-weight: bold;" + "-fx-text-fill: white;");
			
			Button jouerButton = new Button("Jouer");
			jouerButton.setPrefSize(200, 90);
			jouerButton.setStyle(baseStyle);
			jouerButton.setOnMouseEntered(e -> jouerButton.setStyle(hoverStyle));
			jouerButton.setOnMouseExited(e -> jouerButton.setStyle(baseStyle));
			jouerButton.setOnAction(e -> {
				try {

					Menu_commencement CommencementAPP = new Menu_commencement();
					CommencementAPP.start(primaryStage);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});

			Button sauvegardeButton = new Button("Sauvegarde");
			sauvegardeButton.setPrefSize(200, 90);
			sauvegardeButton.setStyle(baseStyle);
			sauvegardeButton.setOnMouseEntered(e -> sauvegardeButton.setStyle(hoverStyle));
			sauvegardeButton.setOnMouseExited(e -> sauvegardeButton.setStyle(baseStyle));
			sauvegardeButton.setOnAction(e -> {
				try {
					Menu_monstreacquis.mdao = new MonstreacquisDAO();
					Menu_monstreacquis AcquisAPP = new Menu_monstreacquis();
					AcquisAPP.start(primaryStage);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			});

			Button exitButton = new Button("Exit");
			exitButton.setPrefSize(200, 90);
			exitButton.setStyle(baseStyle);
			exitButton.setOnMouseEntered(e -> exitButton.setStyle(hoverStyle));
			exitButton.setOnMouseExited(e -> exitButton.setStyle(baseStyle));
			exitButton.setOnAction(e -> primaryStage.close());

			// Disposer les boutons dans une VBox
			VBox buttonBox = new VBox(35); // Espacement de 35px entre les boutons
			buttonBox.setLayoutX(200); // Centrer horizontalement
			buttonBox.setLayoutY(100); // Positionner verticalement
			buttonBox.getChildren().addAll(titre,jouerButton, sauvegardeButton, exitButton);

			// Ajouter l'image et les boutons au Pane principal

			topPane.getChildren().addAll(backgroundImageView, buttonBox);

			// Créer la scène et l'afficher
			Scene scene = new Scene(topPane, 600, 700);
			primaryStage.setTitle("Jeu Java");
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
