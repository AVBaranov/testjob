package multithreading;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Run extends Application {

    Button startbutton;
    Button finishbutton;

    public static void main (String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        final Buffer buffer = new Buffer();
        final ReentrantLock lock = new ReentrantLock();
        final AtomicInteger flag = new AtomicInteger(2);
        Producer p1 = new Producer(buffer, 1, lock, flag);
        Producer p2 = new Producer(buffer, 2, lock, flag);

        primaryStage.setTitle("Multithreading title");
        startbutton = new Button();
        startbutton.setText("start thread");
        startbutton.setOnAction(e -> {
            p1.start();
            p2.start();
        });
        finishbutton = new Button();
        finishbutton.setText("finish thread");
        finishbutton.setOnAction(e -> {
            p1.interrupt();
            p2.interrupt();
        });
        FlowPane layout = new FlowPane();
        layout.getChildren().add(startbutton);
        layout.getChildren().add(finishbutton);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}


