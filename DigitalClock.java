package sample;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class DigitalClock extends Label {


    public DigitalClock() {
        bindToTime();
    }


    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar time = Calendar.getInstance();
                                String timeStamp = new SimpleDateFormat("HH:mm:ss").format(time.getTime());
                                String dateStamp = new SimpleDateFormat("dd/MM/yyyy").format(time.getTime());

                                String display = dateStamp+"  "+timeStamp;
                                setText(display);
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }



}
