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

    @Override
    public void start(Stage lava)  {
        Tron game = new Tron();

        lava.setTitle( "Tron" );

        //Mõlema ussi esimese ruudu tegemine
        liikumine(20,20,570,570);

        //Kontrollimine, kas klaviatuuril on vajutatud kummagi ussi liikumiseks mõeldud nuppu
        scene.setOnKeyPressed(keyEvent -> {
            //Esimese ussi liikumine
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
            //Teise ussi liikumine
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
            //Kontrollimine, et kas on võimalik ussil edasi liikuda ja kui on siis tema liigutamine
            game.oota();
            liikumine(game.getX1(), game.getY1(), game.getX2(), game.getY2());
        });

        //Lava suuruse muutmise võimatuks tegemine ja lava kuvamine
        lava.setResizable(false);
        lava.setScene(scene);
        lava.show();
    }

    public void liikumine(int x1, int y1, int x2, int y2) {
        Tron mäng = new Tron();

        //Esimese ussi keha joonistamine
        Rectangle rk1 = new Rectangle(x1,y1,10,10);
        rk1.setFill(Color.GREEN);
        //Teise ussi keha joonistamine
        Rectangle rk2 = new Rectangle(x2,y2,10,10);
        rk2.setFill(Color.RED);

        //Usside kordinaatide lisamine massiivi
        mäng.lisamine(x1, y1, x2, y2);

        //Juurele ristkülikute lisamine, et need oleks laval nähtavad
        juur.getChildren().addAll(rk1, rk2);
    }
}
