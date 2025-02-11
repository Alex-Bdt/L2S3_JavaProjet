package application;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.util.ArrayList;
import metier.Joueur;
import metier.Monstre;
import metier.Item;

public class ItemInterface {

	private static Joueur joueur;
	private Monstre cible = null;
	private static String baseStyleAttaque= "-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: white; " +"-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " +"-fx-border-color: #5A9BD6; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.7, 2, 2);",
			hoverStyleAttaque="-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: #FFA500; " +"-fx-background-color:#FF8C00; " +"-fx-border-color: #2C5F8A; " +"-fx-text-fill: #2C5F8A; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.9), 10, 0.8, 2, 2);",
			baseStyleAttaqueEnemy= "-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: white; " +"-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " +"-fx-border-color: #850606; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.7, 2, 2);",
			hoverStyleAttaqueEnemy="-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: #FFA500; " +"-fx-background-color:#FF8C00; " +"-fx-border-color: #850606; " +"-fx-text-fill: #2C5F8A; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.9), 10, 0.8, 2, 2);",
			styleAlerte = "-fx-font-size: 20px; " + "-fx-font-family: 'Georgia'; " + "-fx-font-weight: bold; " + "-fx-text-fill: white; " + "-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " + "-fx-border-color: #5A9BD6; " + "-fx-border-width: 4px; " + "-fx-border-radius: 10px; " + "-fx-background-radius: 10px; " + "-fx-padding: 10px; " + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.7, 2, 2);",
			styleBoutonOk = "-fx-font-size: 16px; " + "-fx-font-family: 'Georgia'; " + "-fx-font-weight: bold; " + "-fx-text-fill: white; " + "-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " + "-fx-border-color: #5A9BD6; " + "-fx-border-width: 2px; " + "-fx-border-radius: 10px; " + "-fx-background-radius: 10px; " + "-fx-padding: 10px; " + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.7, 2, 2);";
	private VBox container = new VBox();
	private static ArrayList<Item> inventaire = new ArrayList<Item>();

	public static void updateInventaire() {
		if (joueur.getInventaire() != null) {
			System.out.println(inventaire);
			inventaire = joueur.getInventaire();
		}
		
	}
	
	public ItemInterface(Joueur joueur) {
		ItemInterface.joueur = joueur;
	}
	
	public VBox createInterface() {
		String styleCellule = "-fx-font-size: 14px; " + "-fx-font-family: 'Georgia'; " + "-fx-text-fill: white; " + "-fx-padding: 5px; " + "-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " + "-fx-border-color: #5A9BD6; " + "-fx-border-width: 2px; " + "-fx-border-radius: 10px; ";
		String styleCelluleSelectionnee = "-fx-font-size: 14px; " + "-fx-font-family: 'Georgia'; " + "-fx-text-fill: white; " + "-fx-padding: 5px; " + "-fx-background-color: lightblue; " + "-fx-border-color: #5A9BD6; " + "-fx-border-width: 2px; " + "-fx-border-radius: 10px; ";


		container.setPadding(new Insets(7));
		container.setAlignment(Pos.CENTER_LEFT);
		container.getChildren().clear();
		

		VBox itemBox = new VBox(5);
        itemBox.setAlignment(Pos.CENTER_LEFT);
        itemBox.setStyle("-fx-font-size: 15px; " +"-fx-font-family: 'Georgia'; " +"-fx-text-fill: white; " +"-fx-padding: 5px; " +"-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A);"); // Fond coloré pour le VBox
        
        ScrollPane scrollPane = new ScrollPane(itemBox);
        scrollPane.setFitToWidth(true);
        scrollPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        scrollPane.setPrefHeight(280); 
        scrollPane.setPrefWidth(320);  

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        
		updateInventaire();

		for (Item item : inventaire) {
			Label cellule = new Label(item.getNom() + "\n" + item.getDescription());
			cellule.setStyle(styleCellule);
			cellule.setPrefWidth(330);

			cellule.setOnMouseClicked(event -> {
				itemBox.getChildren().forEach(node -> node.setStyle(styleCellule));
				cellule.setStyle(styleCelluleSelectionnee);


				Button bttcible1 = new Button(),
						bttcible2 = new Button(),
						bttcible3 = new Button(), 
						bttcible4 = new Button(),
						bttcible5 = new Button(),
						bttcible6 = new Button();
				Pane bottomPanedroite = Menu_combat.bottomPanedroite;


				bottomPanedroite.getChildren().clear();

				bttcible1.setStyle(baseStyleAttaque);
				bttcible1.setPrefSize(85, 55); // Changement de taille
				bttcible1.setOnMouseEntered(e -> { bttcible1.setStyle(hoverStyleAttaque); });
				bttcible1.setOnMouseExited(e -> { bttcible1.setStyle(baseStyleAttaque); });
				bttcible1.setLayoutX(50);
				bttcible1.setLayoutY(30);
				bottomPanedroite.getChildren().add(bttcible1);

				bttcible2.setStyle(baseStyleAttaque);
				bttcible2.setPrefSize(85, 55); // Changement de taille
				bttcible2.setOnMouseEntered(e -> { bttcible2.setStyle(hoverStyleAttaque); });
				bttcible2.setOnMouseExited(e -> { bttcible2.setStyle(baseStyleAttaque); });
				bttcible2.setLayoutX(50);
				bttcible2.setLayoutY(125);
				bottomPanedroite.getChildren().add(bttcible2);

				bttcible3.setStyle(baseStyleAttaque);
				bttcible3.setPrefSize(85, 55); // Changement de taille
				bttcible3.setOnMouseEntered(e -> { bttcible3.setStyle(hoverStyleAttaque); });
				bttcible3.setOnMouseExited(e -> { bttcible3.setStyle(baseStyleAttaque); });
				bttcible3.setLayoutX(50);
				bttcible3.setLayoutY(220);
				bottomPanedroite.getChildren().add(bttcible3);

				bttcible4.setStyle(baseStyleAttaqueEnemy);
				bttcible4.setPrefSize(85, 55); // Changement de taille
				bttcible4.setOnMouseEntered(e -> { bttcible4.setStyle(hoverStyleAttaqueEnemy); });
				bttcible4.setOnMouseExited(e -> { bttcible4.setStyle(baseStyleAttaqueEnemy); });
				bttcible4.setLayoutX(250);
				bttcible4.setLayoutY(30);
				bottomPanedroite.getChildren().add(bttcible4);

				bttcible5.setStyle(baseStyleAttaqueEnemy);
				bttcible5.setPrefSize(85, 55); // Changement de taille
				bttcible5.setOnMouseEntered(e -> { bttcible5.setStyle(hoverStyleAttaqueEnemy); });
				bttcible5.setOnMouseExited(e -> { bttcible5.setStyle(baseStyleAttaqueEnemy); });
				bttcible5.setLayoutX(250);
				bttcible5.setLayoutY(125);
				bottomPanedroite.getChildren().add(bttcible5);

				bttcible6.setStyle(baseStyleAttaqueEnemy);
				bttcible6.setPrefSize(85, 55); // Changement de taille
				bttcible6.setOnMouseEntered(e -> { bttcible6.setStyle(hoverStyleAttaqueEnemy); });
				bttcible6.setOnMouseExited(e -> { bttcible6.setStyle(baseStyleAttaqueEnemy); });
				bttcible6.setLayoutX(250);
				bttcible6.setLayoutY(220);
				bottomPanedroite.getChildren().add(bttcible6);



				bttcible1.setOnAction(ev -> {
					System.out.println("cible1");
					try {
						cible = Main.getAllies(0);
					} catch (Exception e) {
						cible = null;
					}
					if (cible != null) {
						bottomPanedroite.getChildren().clear();
						utilisationItem(item ,cible);
						System.out.println("Action OK");
						Main.acte = null;
						Main.cible = null;
						Main.ActionAllie();

					} else { System.out.println("Cible invalide");}
				});
				bttcible2.setOnAction(ev -> {
					System.out.println("cible2");
					try {
						cible = Main.getAllies(1);
					} catch (Exception e) {
						cible = null;
					}
					if (cible != null) {
						bottomPanedroite.getChildren().clear();
						utilisationItem(item ,cible);
						System.out.println("Action OK");
						Main.acte = null;
						Main.cible = null;
						Main.ActionAllie();
					} else { System.out.println("Cible invalide");}
				});
				bttcible3.setOnAction(ev -> {
					System.out.println("button3");
					try {
						cible = Main.getAllies(2);
					} catch (Exception e) {
						cible = null;
					}
					if (cible != null) {
						bottomPanedroite.getChildren().clear();
						utilisationItem(item ,cible);
						System.out.println("Action OK");
						Main.acte = null;
						Main.cible = null;
						Main.ActionAllie();
					} else { System.out.println("Cible invalide");}
				});
				bttcible4.setOnAction(ev -> {
					System.out.println("button4");
					try {
						cible = Main.getEnnemi(0);
					} catch (Exception e) {
						cible = null;
					}
					if (cible != null) {
						bottomPanedroite.getChildren().clear();
						utilisationItem(item ,cible);
						System.out.println("Action OK");
						Main.acte = null;
						Main.cible = null;
						Main.ActionAllie();
					} else { System.out.println("Cible invalide");}
				});
				bttcible5.setOnAction(ev -> {
					System.out.println("button5");
					try {
						cible = Main.getEnnemi(1);
					} catch (Exception e) {
						cible = null;
					}
					if (cible != null) {
						bottomPanedroite.getChildren().clear();
						utilisationItem(item ,cible);
						System.out.println("Action OK");
						Main.acte = null;
						Main.cible = null;
						Main.ActionAllie();
					} else { System.out.println("Cible invalide");}
				});
				bttcible6.setOnAction(ev -> {
					System.out.println("button6");
					try {
						cible = Main.getEnnemi(2);
					} catch (Exception e) {
						cible = null;
					}
					if (cible != null) {
						bottomPanedroite.getChildren().clear();
						utilisationItem(item ,cible);
						System.out.println("Action OK");
						Main.acte = null;
						Main.cible = null;
						Main.ActionAllie();
					} else { System.out.println("Cible invalide");}
				});

			});

			itemBox.getChildren().add(cellule);
		}

		container.getChildren().add(scrollPane);
		return container;
	}

	private void utilisationItem(Item item,Monstre cible) {
		if (cible != null) {
			if (item.getEfficacite() != null) {
				item.Effet(cible);
			}
			joueur.removeItem(item);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setGraphic(null);
			alert.setTitle("");
			alert.setHeaderText(null);
			alert.setContentText("Vous avez utilisé : \n" + item.getNom());
			alert.getDialogPane().setStyle(styleAlerte);
			alert.getDialogPane().lookup(".label").setStyle("-fx-text-fill: white;");
			Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
			if (okButton != null) {
				okButton.setStyle(styleBoutonOk);
			}
            Main.mettreAJourTexte(Main.monstreActuel.getNom() + " a utilisé l'objet " + item.getNom() + " sur " + cible.getNom());
			alert.showAndWait();
		} else { System.out.println("Impossible d'utiliser l'item"); }


	}

}
