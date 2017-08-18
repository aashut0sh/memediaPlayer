/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memediamediaplayer;

import java.io.File;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

/**
 *
 * @author user
 */
public class FXMLDocumentController implements Initializable {
    
    private MediaPlayer mediaPlayer;
    
    @FXML
    private MediaView mediaView;
    
    @FXML
    private Slider slider;
    
    @FXML
    private Slider seekSlider;
    
    private String filePath;
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        //file selector
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a afile(*.mp4)", "*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        
        //set file path
        filePath = file.toURI().toString(); 
        
        
        //only execute if the filepath is not null
        if(filePath!=null)
        {   
            
            //select file
            Media media = new Media(filePath); 
            
            
            mediaPlayer = new MediaPlayer(media);      
            mediaView.setMediaPlayer(mediaPlayer);
            
                DoubleProperty width = mediaView.fitWidthProperty();
                DoubleProperty height = mediaView.fitHeightProperty();
                
                //set the height and width of the media relative to the player
                width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
                height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
                
                //set slider value
                slider.setValue(mediaPlayer.getVolume() * 100);
                slider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(slider.getValue()/100);
                }
            });
                mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                   
                }
            });
                mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    seekSlider.setValue(newValue.toSeconds());
                }
            });
                seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                }
            });
                mediaPlayer.play();
                
                
                
        }
    }
    
    //play video
    @FXML
    private void playVideo(ActionEvent event){
        
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }
    
    //pause video
    @FXML
    private void pauseVideo(ActionEvent event){
        
        mediaPlayer.pause();
    }
    
    
    //stop playing the video
    @FXML
    private void stopVideo(ActionEvent event){
        mediaPlayer.stop();
       
    
    }
    
    @FXML
    private void forwardVideo(ActionEvent event){
        
        mediaPlayer.setRate(1.5);
    }
    
    @FXML
    private void fastForwardVideo(ActionEvent event){
        mediaPlayer.setRate(3);
    
    }
    
    @FXML
    private void slowVideo(ActionEvent event){
           mediaPlayer.setRate(0.75);
    
    }
    
    @FXML
    private void slowerVideo(ActionEvent event){
        mediaPlayer.setRate(0.5);
    
    }
    
    @FXML
    private void exitPlayer(ActionEvent event){
        
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
