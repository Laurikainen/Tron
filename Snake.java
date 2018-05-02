import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Snake extends Application {

    Tron game;

    Group juur = new Group();
    Scene scene = new Scene(juur,600, 600);


    @Override
    public void start(Stage lava) throws FileNotFoundException {
        game = new Tron();
        game.setSuund();

        lava.setTitle( "Tron" );

        //Mõlema ussi esimese ruudu tegemine
        liikumine(570,570,20,20);

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

        });

        //Uuendab usside asukohta iga sekund
        runner(game, lava);

        lava.setScene(scene);
        lava.show();
    }

    public void liikumine(int x1, int y1, int x2, int y2) {

        //Esimese ussi keha joonistamine
        Rectangle rk1 = new Rectangle(x1,y1,10,10);
        rk1.setFill(Color.RED);
        //Teise ussi keha joonistamine
        Rectangle rk2 = new Rectangle(x2,y2,10,10);
        rk2.setFill(Color.GREEN);

        //Usside kordinaatide lisamine massiivi
        game.lisamine(x1, y1, x2, y2);

        //Juurele ristkülikute lisamine, et need oleks laval nähtavad
        juur.getChildren().addAll(rk1, rk2);
    }

    //Update after certain interval
    private void runner(Tron game, Stage lava) throws FileNotFoundException {
        game.oota(lava);
        if (game.running) {
            liikumine(game.getX1(), game.getY1(), game.getX2(), game.getY2());
            Thread thread = new Thread(() -> {
                //Delay time between movement steps
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Interrupted!: " + e);
                }
                Platform.runLater(() -> {
                    //Run this again later
                    try {
                        runner(game, lava);
                    } catch (FileNotFoundException e) {
                        System.out.println("Taustapilti ei leitud!");
                    }
                });
            });
            thread.start();
        }
    }

}
