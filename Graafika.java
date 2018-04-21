import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Graafika extends Application {

    @Override
    public void start(Stage lava) {
        lava.setTitle( "Tron" );

        Group juur = new Group();
        Scene scene = new Scene(juur);
        lava.setScene(scene);

        Canvas canvas = new Canvas(500, 500);
        juur.getChildren().add(canvas);

        //Canvase peale joonistamine
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.DARKBLUE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        Font font = Font.font( "Calibri", FontWeight.BOLD, 99 );
        gc.setFont(font);
        gc.fillText( "Tron", 200, 200 );
        gc.strokeText( "Tron", 190, 190 );

        //Alustamise ja lõpetamise nupud
        Button start = new Button("Start");
        start.setStyle("-fx-base: #0645aa;");
        start.setLayoutX(200);
        start.setLayoutY(200);
        Font font1 = Font.font( "Calibri", FontWeight.BOLD, 40 );
        start.setFont(font1);

        start.setOnAction(e -> uusMäng());


        Button exit = new Button("Exit");
        exit.setLayoutX(200);
        exit.setLayoutY(300);
        exit.setStyle("-fx-base: #0645aa;");
        Font font2 = Font.font( "Calibri", FontWeight.BOLD, 40 );
        exit.setFont(font2);


        exit.setOnAction(e -> Platform.exit());

        juur.getChildren().addAll(start, exit);
        //Image earth = new Image( "" );
        //gc.drawImage( earth, 180, 100 );

        lava.show();

    }

    private void uusMäng() {
    }


}
