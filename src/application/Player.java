package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Player extends BorderPane{
	
	//Declaring media controllers
	Media media;
	MediaPlayer player;
	MediaView   view;
	Pane pane;
	
	//Player Constructor to initialize the Player
	public Player (String file){
		media  = new Media  (file);
		player = new MediaPlayer (media);
		view   = new MediaView (player);
		pane   = new Pane ();
		Controller controller;
		
		//adding the view to the pane
		pane.getChildren().add(view);
		setCenter(pane);
		
		controller = new Controller (player);
		
		setBottom(controller);
		
		setStyle("-fx-background-color: red");
		
		player.play();
	}

}
