package application;
	
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	Player player;
	FileChooser fileChooser;
	
	public void start(Stage primaryStage) {
		
		//Creating the menu Bar
		MenuItem open = new MenuItem("Open");
		Menu file     = new Menu ("File");
		MenuBar   menu   = new MenuBar();
		
		//Adding the menu bar
		file.getItems().add(open);
		menu.getMenus().add(file);
		
		//initializing the fileChooser
		fileChooser = new FileChooser();
		
		//Adding functionality to the menu
		open.setOnAction(new EventHandler <ActionEvent>(){
			public void handle(ActionEvent e){
				player.player.pause();
				File file = fileChooser.showOpenDialog(primaryStage);
				
				if (file!=null){
					try {
						player = new Player(file.toURI().toURL().toExternalForm());
						Scene scene = new Scene (player, 720, 600, Color.CHOCOLATE);
						primaryStage.setScene(scene);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
		player = new Player("file:///C:/Users/username/videos/videoname.mp4");
		player.setTop(menu);
		Scene scene   = new Scene (player, 720, 600, Color.CHOCOLATE);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}