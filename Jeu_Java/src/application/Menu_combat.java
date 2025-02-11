package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import metier.Item;
import metier.Joueur;

public class Menu_combat extends Application {

	private static String baseStyleAttaque= "-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: white; " +"-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " +"-fx-border-color: #5A9BD6; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.7, 2, 2);",
			hoverStyleAttaque="-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: #FFA500; " +"-fx-background-color:#FF8C00; " +"-fx-border-color: #2C5F8A; " +"-fx-text-fill: #2C5F8A; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.9), 10, 0.8, 2, 2);",
			baseStyleAttaqueEnemy= "-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: white; " +"-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " +"-fx-border-color: #850606; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.7, 2, 2);",
			hoverStyleAttaqueEnemy="-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: #FFA500; " +"-fx-background-color:#FF8C00; " +"-fx-border-color: #850606; " +"-fx-text-fill: #2C5F8A; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.9), 10, 0.8, 2, 2);",
			baseStyle= "-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: white; " +"-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " +"-fx-border-color: #5A9BD6; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.7, 2, 2);",
			hoverStyle= "-fx-font-size: 20px; " +"-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: #FFA500; " +"-fx-background-color: linear-gradient(#FF8C00, #1E3A5F); " +"-fx-border-color: #FFA500; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.9), 10, 0.8, 2, 2);"; 

	public static Joueur joueur = Main.joueur;
	public static Pane topPane = new Pane(), bottomPanedroite = new Pane(), bottomPanegauche = new Pane();
	private static Button bttattaque1 = new Button(), bttattaque2 = new Button(), bttattaque3 = new Button(), bttattaque4 = new Button(), bttattaque5 = new Button(), bttattaque6 = new Button(),
		btnCombat = new Button("Combat"), btnDefendre = new Button("Défendre"), btnItems = new Button("Sac"), btnCapture = new Button("Capture"),
		btn1, btn2, btn3, btn4;
	private static HBox bottomHBox = new HBox();
	private static VBox root = new VBox();
	private static RPGInterface rpgInterface = new RPGInterface();
	private static ShopInterface shopInterface = new ShopInterface();
	private static ItemInterface itemInterface = new ItemInterface(joueur);
	public static Label action_text=new Label("");
	

	@Override
	public void start(Stage primaryStage) {
		try {
			topPane.setPrefHeight(500);
			Image backgroundImage = new Image(getClass().getResource("Ressources/paysage_orange.jpg").toExternalForm());
			ImageView backgroundImageView = new ImageView(backgroundImage);
			backgroundImageView.setFitWidth(600);
			backgroundImageView.setFitHeight(500);
			backgroundImageView.setPreserveRatio(false); // L'image prend toutte la place

			// Ajouter l'image au Pane
			topPane.getChildren().add(backgroundImageView);


			topPane.getChildren().add(rpgInterface.createInterface());

			bottomPanegauche.setPrefWidth(200);
			bottomPanegauche.setStyle("-fx-background-color: white;");




			// Style des boutons pour occuper toute la largeur       
			btnCombat.setMaxWidth(Double.MAX_VALUE);
			btnCombat.setMaxWidth(Double.MAX_VALUE);
			btnCombat.setStyle(baseStyle);
			btnCombat.setOnMouseEntered(e -> {
				btnCombat.setStyle(hoverStyle);
			});
			btnCombat.setOnMouseExited(e -> {
				btnCombat.setStyle(baseStyle);
			});

			btnDefendre.setMaxWidth(Double.MAX_VALUE);
			btnDefendre.setMaxWidth(Double.MAX_VALUE);
			btnDefendre.setStyle(baseStyle);
			btnDefendre.setOnMouseEntered(e -> {
				btnDefendre.setStyle(hoverStyle);
			});
			btnDefendre.setOnMouseExited(e -> {
				btnDefendre.setStyle(baseStyle);
			});

			btnItems.setMaxWidth(Double.MAX_VALUE);
			btnItems.setMaxWidth(Double.MAX_VALUE);
			btnItems.setStyle(baseStyle);
			btnItems.setOnMouseEntered(e -> {
				btnItems.setStyle(hoverStyle);
			});
			btnItems.setOnMouseExited(e -> {
				btnItems.setStyle(baseStyle);
			});

			btnCapture.setMaxWidth(Double.MAX_VALUE);
			btnCapture.setMaxWidth(Double.MAX_VALUE);
			btnCapture.setStyle(baseStyle);
			btnCapture.setOnMouseEntered(e -> {
				btnCapture.setStyle(hoverStyle);
			});
			btnCapture.setOnMouseExited(e -> {
				btnCapture.setStyle(baseStyle);
			});        

			if (Main.monstreActuel.getClass().getName() != "metier.Joueur") {
				btnItems.setDisable(true);
			} 
			if (Main.monstreActuel.getClass().getName() != "metier.Joueur") {
				btnCapture.setDisable(true);
			}

			sousMenuActions();

			Scene scene = new Scene(root, 600, 700);

			primaryStage.setTitle("Jeu Java");
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sousMenuActions() {

		bottomHBox.getChildren().clear();

		HBox action = new HBox(0);

        action.setStyle(baseStyle);
        action.setPrefWidth(190);
        action.setPrefHeight(100);
        action_text.setStyle("-fx-font-family: 'Georgia'; " + "-fx-font-weight: bold; " + "-fx-text-fill: white; " + "-fx-font-size: 15px; ");
        action_text.setWrapText(true);
        action.getChildren().addAll(action_text);
        action.setTranslateX(192);
        action.setTranslateY(330);
        topPane.getChildren().add(action);

		
		VBox buttonBox = new VBox(); 
		buttonBox.setPrefWidth(200);
		buttonBox.setPrefHeight(300); 
		// Ajustement des hauteurs des boutons pour occuper tout l'espace
		buttonBox.heightProperty().addListener((obs, oldHeight, newHeight) -> {
			double buttonHeight = newHeight.doubleValue() / 4; // Diviser l'espace total entre les 4 boutons
			btnCombat.setPrefHeight(buttonHeight);
			btnDefendre.setPrefHeight(buttonHeight);
			btnItems.setPrefHeight(buttonHeight);
			btnCapture.setPrefHeight(buttonHeight);
		});
		buttonBox.getChildren().addAll(btnCombat, btnDefendre, btnItems, btnCapture);
		bottomPanegauche.getChildren().add(buttonBox);

		bottomPanedroite.setPrefWidth(400);
		bottomPanedroite.setStyle("-fx-background-color: #2C5F8A;"+"-fx-border-color: #5A9BD6; " +"-fx-border-width: 3px; " + "-fx-border-radius: 8px; "  );

		bottomHBox.setPrefHeight(300);
		bottomHBox.getChildren().addAll(bottomPanegauche, bottomPanedroite);

		HBox afficher_round = new HBox(0);

		Label Rond_aff = new Label("Manche : "+ Main.Round);
		Rond_aff.setStyle("-fx-font-family: 'Georgia'; " +
				"-fx-font-weight: bold; " +
				"-fx-text-fill: white; " +
				"-fx-font-size: 20px; "+"-fx-background-color: linear-gradient(#1E3A5F, #2C5F8A); " +"-fx-border-color: #5A9BD6; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-padding: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.7, 2, 2);");

		afficher_round.getChildren().addAll(Rond_aff);
		afficher_round.setTranslateX(220);
		afficher_round.setTranslateY(5);

		topPane.getChildren().add(afficher_round);

		root.getChildren().clear();
		root.getChildren().addAll(topPane, bottomHBox);


		String nomMove1 = "";
		if (Main.monstreActuel.getMove1() != null) {
			nomMove1 = Main.monstreActuel.getMove1().getNom();}
		String nomMove2 = "";
		if (Main.monstreActuel.getMove2() != null) {
			nomMove2 = Main.monstreActuel.getMove2().getNom();}
		String nomMove3 = "";
		if (Main.monstreActuel.getMove3() != null) {
			nomMove3 = Main.monstreActuel.getMove3().getNom();}
		String nomMove4 = "";
		if (Main.monstreActuel.getMove4() != null) {
			nomMove4 = Main.monstreActuel.getMove4().getNom();}

		btn1 = new Button(nomMove1);
		btn2 = new Button(nomMove2);
		btn3 = new Button(nomMove3);
		btn4 = new Button(nomMove4);
		btnCombat.setOnAction(event -> {
			bottomPanedroite.getChildren().clear();

			updateMoves();

			btn1.setPrefSize(160, 90);
			btn1.setLayoutX(30);
			btn1.setLayoutY(40);
			btn1.setStyle(baseStyleAttaque);
			btn1.setOnMouseEntered(e -> { btn1.setStyle(hoverStyleAttaque);});
			btn1.setOnMouseExited(e -> { btn1.setStyle(baseStyleAttaque);});
			if (btn1.getText() == "") {
				btn1.setDisable(true);
			}
			bottomPanedroite.getChildren().add(btn1);

			btn2.setPrefSize(160, 90);
			btn2.setLayoutX(220);
			btn2.setLayoutY(40);
			btn2.setStyle(baseStyleAttaque);
			btn2.setOnMouseEntered(e -> { btn2.setStyle(hoverStyleAttaque);});
			btn2.setOnMouseExited(e -> { btn2.setStyle(baseStyleAttaque);});
			if (btn2.getText() == "") {
				btn2.setDisable(true);
			}
			bottomPanedroite.getChildren().add(btn2);

			btn3.setPrefSize(160, 90);
			btn3.setLayoutX(30);
			btn3.setLayoutY(180);
			btn3.setStyle(baseStyleAttaque);
			btn3.setOnMouseEntered(e -> { btn3.setStyle(hoverStyleAttaque); });
			btn3.setOnMouseExited(e -> {btn3.setStyle(baseStyleAttaque);});
			if (btn3.getText() == "") {
				btn3.setDisable(true);
			}
			bottomPanedroite.getChildren().add(btn3);


			btn4.setPrefSize(160, 90);
			btn4.setLayoutX(220);
			btn4.setLayoutY(180);
			btn4.setStyle(baseStyleAttaque);
			btn4.setOnMouseEntered(e -> { btn4.setStyle(hoverStyleAttaque);});
			btn4.setOnMouseExited(e -> { btn4.setStyle(baseStyleAttaque);});
			if (btn4.getText() == "") {
				btn4.setDisable(true);
			}
			bottomPanedroite.getChildren().add(btn4);

		});	

		btn1.setOnAction(event -> {
			Main.acte = Main.monstreActuel.getMove1();
			System.out.println(Main.acte.getNom());
			sousMenuAttaque();
		});
		btn2.setOnAction(event -> {
			Main.acte = Main.monstreActuel.getMove2();
			System.out.println(Main.acte.getNom());
			sousMenuAttaque();
		});
		btn3.setOnAction(event -> {
			Main.acte = Main.monstreActuel.getMove3();
			System.out.println(Main.acte.getNom());
			sousMenuAttaque();
		});
		btn4.setOnAction(event -> {
			Main.acte = Main.monstreActuel.getMove4();
			System.out.println(Main.acte.getNom());
			sousMenuAttaque();
		});
		
		btnDefendre.setOnAction(event -> {
			bottomPanedroite.getChildren().clear();
			Main.acte = null;
			Main.cible = null;
			Main.mettreAJourTexte(Main.monstreActuel.getNom() + " est en position defensive");
			Main.monstreActuel.Defendre();
			Main.ActionAllie();
		});
		
		btnItems.setOnAction(event -> {
            bottomPanedroite.getChildren().clear();
            System.out.println("Section d'un item");
            bottomPanedroite.getChildren().add(itemInterface.createInterface());
          
        });
		
		btnCapture.setOnAction(event -> {
			bottomPanedroite.getChildren().clear();  
			if (Main.dispoCapture()) {
				Main.monstreActuel.addCoefVitesse(10.0); // a enlever lors de la résolution
				Main.cible = Main.getEnnemi(0);
				Main.acte = null;
				Main.ActionAllie();
			} else {
				System.out.println("Capture impossible");
			}
		});


		bttattaque1.setOnAction(event -> {
			System.out.println("cible1");
			try {
				Main.cible = Main.getAllies(0);
			} catch (Exception e) {
				Main.cible = null;
			}
			if (Main.cible != null) {
				bottomPanedroite.getChildren().clear();
				System.out.println("Action OK");
				Main.ActionAllie();
			} else { System.out.println("Cible invalide");}
		});
		bttattaque2.setOnAction(event -> {
			System.out.println("cible2");
			try {
				Main.cible = Main.getAllies(1);
			} catch (Exception e) {
				Main.cible = null;
			}
			if (Main.cible != null) {
				bottomPanedroite.getChildren().clear();
				System.out.println("Action OK");
				Main.ActionAllie();
			} else { System.out.println("Cible invalide");}
		});
		bttattaque3.setOnAction(event -> {
			System.out.println("button3");
			try {
				Main.cible = Main.getAllies(2);
			} catch (Exception e) {
				Main.cible = null;
			}
			if (Main.cible != null) {
				bottomPanedroite.getChildren().clear();
				System.out.println("Action OK");
				Main.ActionAllie();
			} else { System.out.println("Cible invalide");}
		});
		bttattaque4.setOnAction(event -> {
			System.out.println("button4");
			try {
				Main.cible = Main.getEnnemi(0);
			} catch (Exception e) {
				Main.cible = null;
			}
			if (Main.cible != null) {
				bottomPanedroite.getChildren().clear();
				System.out.println("Action OK");
				Main.ActionAllie();
			} else { System.out.println("Cible invalide");}
		});
		bttattaque5.setOnAction(event -> {
			System.out.println("button5");
			try {
				Main.cible = Main.getEnnemi(1);
			} catch (Exception e) {
				Main.cible = null;
			}
			if (Main.cible != null) {
				bottomPanedroite.getChildren().clear();
				System.out.println("Action OK");
				Main.ActionAllie();
			} else { System.out.println("Cible invalide");}
		});
		bttattaque6.setOnAction(event -> {
			System.out.println("button6");
			try {
				Main.cible = Main.getEnnemi(2);
			} catch (Exception e) {
				Main.cible = null;
			}
			if (Main.cible != null) {
				bottomPanedroite.getChildren().clear();
				System.out.println("Action OK");
				Main.ActionAllie();
			} else { System.out.println("Cible invalide");}
		});
	}

	public static void sousMenuMarchand() {
		Main.Round ++; //le marchand compte comme un round effectue

		bottomHBox.getChildren().clear();

		HBox priceBox = new HBox(4);

		Label descriptionLabel = new Label(joueur.getArgent()+"");
		descriptionLabel.setStyle("-fx-font-family: 'Georgia'; " + "-fx-font-weight: bold; " + "-fx-text-fill: white; " + "-fx-font-size: 20px; ");

		Image coinImage = new Image(Menu_combat.class.getResource("Ressources/Coin.png").toExternalForm());
		ImageView coinImageView = new ImageView(coinImage);
		coinImageView.setFitWidth(20);
		coinImageView.setFitHeight(20);
		coinImageView.setPreserveRatio(true);

		priceBox.getChildren().addAll(descriptionLabel, coinImageView);
		priceBox.setTranslateX(20);
		priceBox.setTranslateY(10);
		bottomHBox.getChildren().add(priceBox);

		bottomHBox.setStyle( "-fx-font-family: 'Georgia'; " +"-fx-font-weight: bold; " +"-fx-text-fill: white; " +"-fx-background-color: linear-gradient(#FF8C00,#F2A603); " +"-fx-border-color: #2C5F8A; " +"-fx-border-width: 4px; " +"-fx-border-radius: 10px; " +"-fx-background-radius: 10px; " +"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.7), 8, 0.7, 2, 2);" );

		bottomHBox.setStyle("-fx-background-color:#202020;");
		bottomHBox.getChildren().addAll(shopInterface.createInterface());
		topPane.getChildren().clear();
		Image backgroundImage =  new Image(Menu_combat.class.getResource("Ressources/paysage_orange.jpg").toExternalForm());
		ImageView backgroundImageView = new ImageView(backgroundImage);
		backgroundImageView.setFitWidth(600);
		backgroundImageView.setFitHeight(500);
		backgroundImageView.setPreserveRatio(false); // L'image prend toutte la place

		if (Main.monstreActuel.getClass().getName() != "metier.Joueur") {
			btnItems.setDisable(true);
		} 
		if (Main.monstreActuel.getClass().getName() != "metier.Joueur") {
			btnCapture.setDisable(true);
		} 

		// Ajouter l'image au Pane
		topPane.getChildren().add(backgroundImageView);
	}

	public static void sousMenuAttaque() {
		bottomPanedroite.getChildren().clear();

		bttattaque1.setStyle(baseStyleAttaque);
		bttattaque1.setPrefSize(85, 55); // Changement de taille
		bttattaque1.setOnMouseEntered(e -> { bttattaque1.setStyle(hoverStyleAttaque); });
		bttattaque1.setOnMouseExited(e -> { bttattaque1.setStyle(baseStyleAttaque); });
		bttattaque1.setLayoutX(50);
		bttattaque1.setLayoutY(30);
		bottomPanedroite.getChildren().add(bttattaque1);

		bttattaque2.setStyle(baseStyleAttaque);
		bttattaque2.setPrefSize(85, 55); // Changement de taille
		bttattaque2.setOnMouseEntered(e -> { bttattaque2.setStyle(hoverStyleAttaque); });
		bttattaque2.setOnMouseExited(e -> { bttattaque2.setStyle(baseStyleAttaque); });
		bttattaque2.setLayoutX(50);
		bttattaque2.setLayoutY(125);
		bottomPanedroite.getChildren().add(bttattaque2);

		bttattaque3.setStyle(baseStyleAttaque);
		bttattaque3.setPrefSize(85, 55); // Changement de taille
		bttattaque3.setOnMouseEntered(e -> { bttattaque3.setStyle(hoverStyleAttaque); });
		bttattaque3.setOnMouseExited(e -> { bttattaque3.setStyle(baseStyleAttaque); });
		bttattaque3.setLayoutX(50);
		bttattaque3.setLayoutY(220);
		bottomPanedroite.getChildren().add(bttattaque3);

		bttattaque4.setStyle(baseStyleAttaqueEnemy);
		bttattaque4.setPrefSize(85, 55); // Changement de taille
		bttattaque4.setOnMouseEntered(e -> { bttattaque4.setStyle(hoverStyleAttaqueEnemy); });
		bttattaque4.setOnMouseExited(e -> { bttattaque4.setStyle(baseStyleAttaqueEnemy); });
		bttattaque4.setLayoutX(250);
		bttattaque4.setLayoutY(30);
		bottomPanedroite.getChildren().add(bttattaque4);

		bttattaque5.setStyle(baseStyleAttaqueEnemy);
		bttattaque5.setPrefSize(85, 55); // Changement de taille
		bttattaque5.setOnMouseEntered(e -> { bttattaque5.setStyle(hoverStyleAttaqueEnemy); });
		bttattaque5.setOnMouseExited(e -> { bttattaque5.setStyle(baseStyleAttaqueEnemy); });
		bttattaque5.setLayoutX(250);
		bttattaque5.setLayoutY(125);
		bottomPanedroite.getChildren().add(bttattaque5);

		bttattaque6.setStyle(baseStyleAttaqueEnemy);
		bttattaque6.setPrefSize(85, 55); // Changement de taille
		bttattaque6.setOnMouseEntered(e -> { bttattaque6.setStyle(hoverStyleAttaqueEnemy); });
		bttattaque6.setOnMouseExited(e -> { bttattaque6.setStyle(baseStyleAttaqueEnemy); });
		bttattaque6.setLayoutX(250);
		bttattaque6.setLayoutY(220);
		bottomPanedroite.getChildren().add(bttattaque6);
	}

	public static void updateItems() {
		//update la liste des items possedes
		
	}
	
	public static void updateBoutons() { 
		//uptade des boutons Items et Capture
		if (Main.monstreActuel.getClass().getName() != "metier.Joueur") {
			btnItems.setDisable(true);
		} 
		if (Main.monstreActuel.getClass().getName() == "metier.Joueur") {
			btnItems.setDisable(false);
		} 
		if (Main.monstreActuel.getClass().getName() != "metier.Joueur") {
			btnCapture.setDisable(true);
		}
		if (Main.monstreActuel.getClass().getName() == "metier.Joueur") {
			btnCapture.setDisable(false);
		}
	}
	public static void updateMoves() {
		//update des boutons d'attaques
		if (Main.monstreActuel.getMove1() != null) {
			btn1.setText(Main.monstreActuel.getMove1().getNom());
			btn1.setDisable(false);
		}
		if (Main.monstreActuel.getMove1() == null) {
			btn1.setText("");
			btn1.setDisable(true);
		}
		if (Main.monstreActuel.getMove2() != null) {
			btn2.setText(Main.monstreActuel.getMove2().getNom());
			btn2.setDisable(false);
		}
		if (Main.monstreActuel.getMove2() == null) {
			btn2.setText("");
			btn2.setDisable(true);
		}
		if (Main.monstreActuel.getMove3() != null) {
			btn3.setText(Main.monstreActuel.getMove3().getNom());
			btn3.setDisable(false);
		}
		if (Main.monstreActuel.getMove3() == null) {
			btn3.setText("");
			btn3.setDisable(true);
		}
		if (Main.monstreActuel.getMove4() != null) {
			btn4.setText(Main.monstreActuel.getMove4().getNom());
			btn4.setDisable(false);
		}
		if (Main.monstreActuel.getMove4() == null) {
			btn4.setText("");
			btn4.setDisable(true);
		}

	}

	public static void NewCombat() {
		Main.Round ++;
		Main.NouveauEnnemis(Main.niveauVoulu);
		topPane.getChildren().add(rpgInterface.createInterface());
		//recharger le bas aussi.
		sousMenuActions();
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
