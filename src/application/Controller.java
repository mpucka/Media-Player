package application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

//hbox is an horizontal box
public class Controller extends HBox{
	
	//Objects needed in our controller
	Slider time = new Slider();
	Slider vol  = new Slider();
	
	Button playButton = new Button(" || ");
	
	Label volume = new Label ("Volume: ");
	
	MediaPlayer player;
	
	public  Controller (MediaPlayer play){
		player = play;
		
		//Alingning our controller box
		setAlignment(Pos.CENTER);
		setPadding (new Insets(5,10,5,10));
		
		//volume label appearance
		vol.setPrefWidth(70);
		vol.setMinWidth(30);
		vol.setValue(100);  //default value
		
		HBox.setHgrow(time,  Priority.ALWAYS);
		
		//Play button appearance
		playButton.setPrefWidth(30);
		
		//put the objects in the controller
		getChildren().add(playButton);
		getChildren().add(time);
		getChildren().add(volume);
		getChildren().add(vol);
		
		
		//giving functionalities to  our objects 
		playButton.setOnAction(new EventHandler <ActionEvent>(){
			public void handle(ActionEvent  e){
				Status status = player.getStatus();
				
				//Checking if the video is playing
				if(status==Status.PLAYING){
					//Checking if we at the end of the video
					//then replay the video from the start
					
					if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())){
						player.seek(player.getStartTime());
						player.play();
					}
					else {
						player.pause();
						playButton.setText(">");
					}
					
				}
				if (status == Status.PAUSED || status==Status.HALTED || status==Status.STOPPED){
					player.play();
					playButton.setText("||");
				}
			}
		});
		
		//palying time Slider
		player.currentTimeProperty().addListener(new InvalidationListener(){
			public void invalidated(Observable observable){
				updateValues();
			}			
		});
		
		//Time slider
		time.valueProperty().addListener(new InvalidationListener(){
			public void invalidated (Observable observable){
				if (time.isPressed()){
					player.seek(player.getMedia().getDuration().multiply(time.getValue()/100));
				}				
			}			
		});
		
		//Volume slider		
		vol.valueProperty().addListener(new InvalidationListener(){
			public void invalidated (Observable observable){
				if(vol.isPressed()){
					player.setVolume(vol.getValue()/100);
				}
			}
		});
	}
	
	//Subroutine to get the playing slide updated
	protected void updateValues(){
		Platform.runLater(new Runnable(){
			public void run(){
				time.setValue(player.getCurrentTime().toMillis()/player.getTotalDuration().toMillis()*100);
			}
			
		});
	}

}
