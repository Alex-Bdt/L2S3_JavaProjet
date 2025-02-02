package application;

import java.util.ArrayList;

import DAO.MonstreacquisDAO;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import metier.Monstreacquis;

public class Menu_monstreacquis extends Application {
    public static MonstreacquisDAO mdao;

    @Override
    public void start(Stage primaryStage) {
        try {
            Pane rootPane = new Pane();
            rootPane.setPrefSize(600, 800);

            // Image de fond
            Image backgroundImage = new Image(getClass().getResource("Ressources/image_commencementt.jpg").toExternalForm());
            ImageView backgroundImageView = new ImageView(backgroundImage);
            backgroundImageView.setFitWidth(600);
            backgroundImageView.setFitHeight(800);
            backgroundImageView.setPreserveRatio(false);

            String styleCellule = "-fx-font-size: 15px; " +  "-fx-font-family: 'Georgia'; " +  "-fx-text-fill: white; " +  "-fx-padding: 5px; " +  "-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " +  "-fx-border-color: #5A9BD6; " +  "-fx-border-width: 2px; " +  "-fx-border-radius: 10px; ";
            String styleCelluleSelectionnee = "-fx-font-size: 25px; " +  "-fx-font-family: 'Georgia'; " +  "-fx-font-weight: bold; " +  "-fx-text-fill: white; " +  "-fx-padding: 5px; " +  "-fx-background-color: lightblue; ";

            VBox playerBox = new VBox(10);
            playerBox.setPadding(new Insets(20));
            playerBox.setAlignment(Pos.TOP_LEFT);

            // Liste des monstres acquis
            ArrayList<Monstreacquis> Monstre_List = mdao.selection();

            for (Monstreacquis Monstre : Monstre_List) {
                Label joueurLabel = new Label(
                   "              "+ Monstre.getNomMonstre() +"\n"+" "+Monstre.getMove1()+"  "+Monstre.getMove2()+"  "+Monstre.getMove3()+"  "+Monstre.getMove4()
                );

                joueurLabel.setStyle("-fx-font-size: 25px; " +"-fx-font-family: 'Georgia'; " +"-fx-text-fill: white; " +"-fx-font-weight: bold; " +"-fx-padding: 5px; ");
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

            // Appliquer les styles CSS
            scrollPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            playerBox.setStyle("-fx-font-size: 15px; " +"-fx-font-family: 'Georgia'; " +"-fx-text-fill: white; " +"-fx-padding: 5px; " +"-fx-border-color: #5A9BD6; " +"-fx-border-width: 2px; " +"-fx-border-radius: 10px; " +"-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A);");

            Button retourButton = new Button("Retour");
            retourButton.setStyle(styleCellule); // Appliquer le même style de cellule
            retourButton.setPrefWidth(500);
            retourButton.setLayoutX(50);
            retourButton.setLayoutY(700);

            // Action sur le bouton "Retour"
            retourButton.setOnAction(event -> {
                // Code pour revenir au menu de démarrage
                Menu_demmarage menuD = new Menu_demmarage(); // Assurez-vous que Menu_demarage est une classe qui gère le menu de démarrage
                menuD.start(new Stage());
                primaryStage.close(); // Ferme la fenêtre actuelle
            });

            rootPane.getChildren().addAll(backgroundImageView, scrollPane, retourButton);

            // Créer la scène
            Scene scene = new Scene(rootPane, 600, 800);
            primaryStage.setTitle("Menu Monstre");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Initialisation du DAO
        launch(args);
    }
}
