import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StartExit extends Application {
    public String tekst = "Tron";

    //Start ja exit interface
    @Override
    public void start(Stage lava)  {
        lava.setTitle( "Tron" );

        Group juur = new Group();
        Scene scene = new Scene(juur, 600, 600);
        scene.setFill(Color.BLACK);
        VBox vbox = new VBox(15);
        BorderPane bp = new BorderPane();
        bp.setPrefSize(600,600);

        lava.setScene(scene);

        //Mängu pealkirja lisamine
        Label nimi = new Label(tekst);
        if (tekst.equals("Tron")) {
            nimi.setFont(Font.font("Algerian", FontWeight.BOLD, 99));
        }
        else{
            nimi.setFont(Font.font("Algerian", FontWeight.BOLD, 50));
        }
        nimi.setTextFill(Color.WHITE);

        //Alustamise nupu tegemine
        Button start = new Button("Start");
        start.setStyle("-fx-base: #0645aa;");
        Font font1 = Font.font( "Algerian", FontWeight.BOLD, 40 );
        start.setFont(font1);

        //Mis juhtub kui start nupule vajutada
        start.setOnAction(e -> {
            try {
                keyCodes(lava);
            } catch (FileNotFoundException e1) {
                throw new RuntimeException(e1);
            }
        });

        //Lõpetamise nupu tegemine
        Button exit = new Button("Exit");;
        exit.setStyle("-fx-base: #0645aa;");
        Font font2 = Font.font( "Algerian", FontWeight.BOLD, 40 );
        exit.setFont(font2);

        //Mis juhtub kui vajutada exit
        exit.setOnAction(e -> Platform.exit());

        //Vboxi lisamine, et saaks kõik nupud keskele
        vbox.getChildren().addAll(nimi, start, exit);
        vbox.setAlignment(Pos.CENTER);
        bp.setCenter(vbox);

        juur.getChildren().add(bp);

        lava.show();
    }


    //Start nupule vajutamise tagajärg
    private void keyCodes(Stage lava) throws FileNotFoundException {

        //Taustapildi lugemine
        Image image = new Image(new FileInputStream("pilt.jpg"));
        //Taustapildi lisamine
        ImageView imageView = new ImageView(image);
        Group juur = new Group(imageView);
        Scene scene = new Scene(juur, 600, 600);
        scene.setFill(Color.BLACK);

        //Mis juhtub kui vajutada enter või esc
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                Snake uus_mäng = new Snake();
                uus_mäng.start(lava);
            }
            else if (keyEvent.getCode() == KeyCode.ESCAPE) {
                StartExit uus_mäng = new StartExit();
                uus_mäng.start(lava);
            }
        });

        lava.setScene(scene);
    }
}