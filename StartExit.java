import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StartExit extends Application {

    @Override
    public void start(Stage lava)  {
        lava.setTitle( "Tron" );

        Group juur = new Group();
        Scene scene = new Scene(juur, 500, 500);
        scene.setFill(Color.BLACK);
        VBox vbox = new VBox(10);
        BorderPane bp = new BorderPane();
        bp.setPrefSize(500,500);

        lava.setScene(scene);

        Label nimi = new Label("Tron");
        nimi.setFont(Font.font("Algerian", FontWeight.BOLD, 99));
        nimi.setTextFill(Color.WHITE);

        //Alustamise ja lõpetamise nupud
        Button start = new Button("Start");
        start.setStyle("-fx-base: #0645aa;");
        start.setLayoutX(200);
        start.setLayoutY(200);
        Font font1 = Font.font( "Algerian", FontWeight.BOLD, 40 );
        start.setFont(font1);

        start.setOnAction(e -> {
            try {
                keyCodes(lava);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        Button exit = new Button("Exit");
        exit.setLayoutX(200);
        exit.setLayoutY(300);
        exit.setStyle("-fx-base: #0645aa;");
        Font font2 = Font.font( "Algerian", FontWeight.BOLD, 40 );
        exit.setFont(font2);

        exit.setOnAction(e -> Platform.exit());

        vbox.getChildren().addAll(nimi, start, exit);
        vbox.setAlignment(Pos.CENTER);


        bp.setCenter(vbox);
        juur.getChildren().add(bp);


        lava.show();

    }

    private void keyCodes(Stage lava) throws FileNotFoundException {

        //Pildi lugemine
        Image image = new Image(new FileInputStream("pilt.jpg"));

        //Pildi lisamine
        ImageView imageView = new ImageView(image);
        Group juur = new Group(imageView);
        Scene scene = new Scene(juur, 500, 500);
        scene.setFill(Color.BLACK);
        Text go = new Text("Vajuta entrit");
        go.setFill(Color.WHITE);

        juur.getChildren().addAll(go);
        lava.setScene(scene);

        }
}