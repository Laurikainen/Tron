import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Snake extends Application {

    Group juur = new Group();
    Scene scene = new Scene(juur,600, 600);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage lava)  {
        Tron game = new Tron();

        lava.setTitle( "Tron" );

        //ussi esimeses ruudu tegemine ja selle listi lisamine
        liikumine(20,20,570,570);

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.UP) {
                game.yks.setSuund("yles");
            }
            else if (keyEvent.getCode() == KeyCode.DOWN) {
                game.yks.setSuund("alla");
            }
            else if (keyEvent.getCode() == KeyCode.LEFT) {
                game.yks.setSuund("vasak");
            }
            else if (keyEvent.getCode() == KeyCode.RIGHT) {
                game.yks.setSuund("parem");
            }

            if (keyEvent.getCode() == KeyCode.W) {
                game.kaks.setSuund("yles");
            }
            else if (keyEvent.getCode() == KeyCode.S) {
                game.kaks.setSuund("alla");
            }
            else if (keyEvent.getCode() == KeyCode.A) {
                game.kaks.setSuund("vasak");
            }
            else if (keyEvent.getCode() == KeyCode.D) {
                game.kaks.setSuund("parem");
            }
            game.oota();
            liikumine(game.getX1(), game.getY1(), game.getX2(), game.getY2());
        });

        lava.setResizable(false);
        lava.setScene(scene);
        lava.show();
    }

    public void liikumine(int x1, int y1, int x2, int y2) {
        Tron mäng = new Tron();

        Rectangle rk1 = new Rectangle(x1,y1,10,10);
        rk1.setFill(Color.GREEN);

        Rectangle rk2 = new Rectangle(x2,y2,10,10);
        rk2.setFill(Color.RED);

        mäng.lisamine(x1, y1, x2, y2);

        juur.getChildren().addAll(rk1, rk2);
    }
}
