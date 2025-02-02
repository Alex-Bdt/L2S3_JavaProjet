package application;

import java.util.ArrayList;

import DAO.JoueurenregistreDAO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import metier.Joueurenregistre;

public class Menu_joueur extends Application {
    private static JoueurenregistreDAO jdao;

    @Override
    public void start(Stage primaryStage) {
        try {
            Pane rootPane = new Pane();
            rootPane.setPrefSize(600, 800);

            // Ajouter l'image de fond
            Image backgroundImage = new Image(getClass().getResource("Ressources/paysage_lac.jpg").toExternalForm());
            ImageView backgroundImageView = new ImageView(backgroundImage);
            backgroundImageView.setFitWidth(600);
            backgroundImageView.setFitHeight(800);
            backgroundImageView.setPreserveRatio(false);

            // Styles de base des cellules
            String styleCellule = "-fx-font-size: 15px; " +
                                  "-fx-font-family: 'Georgia'; " +
                                  "-fx-text-fill: white; " +
                                  "-fx-padding: 5px; " +
                                  "-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " +
                                  "-fx-border-color: #5A9BD6; " +
                                  "-fx-border-width: 2px; " +
                                  "-fx-border-radius: 10px; ";

            String styleCelluleSelectionnee = "-fx-font-size: 15px; " +
                                              "-fx-font-family: 'Georgia'; " +
                                              "-fx-text-fill: white; " +
                                              "-fx-padding: 5px; " +
                                              "-fx-background-color: lightblue; " +
                                              "-fx-border-color: #5A9BD6; " +
                                              "-fx-border-width: 2px; " +
                                              "-fx-border-radius: 10px; ";

            // Conteneur principal pour les joueurs
            VBox playerBox = new VBox(10);
            playerBox.setPadding(new Insets(20));
            playerBox.setAlignment(Pos.TOP_LEFT);

            // Charger les joueurs de la base de données
            ArrayList<Joueurenregistre> joueurList = jdao.selection();

            // Ajouter chaque joueur à la liste
            for (Joueurenregistre joueur : joueurList) {
                Label joueurLabel = new Label(
                    "ID: " + joueur.getId() + "\n" +
                    "Round max: " + joueur.getRoundmax() + "\n" +
                    "Date: " + joueur.getSave()
                );
                joueurLabel.setStyle(styleCellule);
                joueurLabel.setPrefWidth(500);
                joueurLabel.setOnMouseClicked(event -> {
                    joueurLabel.setStyle(styleCelluleSelectionnee);
                });

                playerBox.getChildren().add(joueurLabel);
            }

            
            ScrollPane scrollPane = new ScrollPane(playerBox);
            
            scrollPane.setFitToWidth(true);
            scrollPane.setPrefSize(560, 760);
            scrollPane.setLayoutX(20);
            scrollPane.setLayoutY(20);

           
            scrollPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            
            
            playerBox.setStyle("-fx-font-size: 15px; " +
                    "-fx-font-family: 'Georgia'; " +
                    "-fx-text-fill: white; " +
                    "-fx-padding: 5px; " +
                    "-fx-border-color: #5A9BD6; " +
                    "-fx-border-width: 2px; " +
                    "-fx-border-radius: 10px; "+
                    "-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A);"
                    ); // Fond coloré pour le VBox
           

            // Ajouter les éléments au rootPane
            rootPane.getChildren().addAll(backgroundImageView, scrollPane);

            // Créer et afficher la scène
            Scene scene = new Scene(rootPane, 600, 800);
            primaryStage.setTitle("Menu Joueurs");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Initialisation du DAO
        jdao = new JoueurenregistreDAO();
        launch(args);
    }
}
