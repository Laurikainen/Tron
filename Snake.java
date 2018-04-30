import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Snake extends Application {

    Group juur = new Group();
    Scene scene = new Scene(juur,600, 600);

    ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    public void onEnable(Tron game) {
        System.out.println("start");
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
                    try{
                        game.oota();
                        liikumine(game.getX1(), game.getY1(), game.getX2(), game.getY2());
                    }
                    catch (Exception e){
                        System.out.println(e.toString());
                        e.printStackTrace();
                    }

                },
                2, 2, TimeUnit.SECONDS);
        System.out.println("stop");
    }



    @Override
    public void start(Stage lava)  {
        Tron game = new Tron();
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

        // update snakes every second
        runner(game);


        //Lava suuruse muutmise võimatuks tegemine ja lava kuvamine
        lava.setResizable(false);
        lava.setScene(scene);
        lava.show();
    }

    public void liikumine(int x1, int y1, int x2, int y2) {
        Tron mäng = new Tron();

        //Esimese ussi keha joonistamine
        Rectangle rk1 = new Rectangle(x1,y1,10,10);
        rk1.setFill(Color.RED);
        //Teise ussi keha joonistamine
        Rectangle rk2 = new Rectangle(x2,y2,10,10);
        rk2.setFill(Color.GREEN);

        //Usside kordinaatide lisamine massiivi
        mäng.lisamine(x1, y1, x2, y2);

        //Juurele ristkülikute lisamine, et need oleks laval nähtavad
        juur.getChildren().addAll(rk1, rk2);
    }

    //Update after certain interval
    private void runner(Tron game){
        try {
            game.oota();
            liikumine(game.getX1(), game.getY1(), game.getX2(), game.getY2());
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException exc) {
                    throw new Error("Unexpected interruption", exc);
                }
                Platform.runLater(() -> {
                    runner(game);
                });
            });
            thread.setDaemon(true);
            thread.start();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
