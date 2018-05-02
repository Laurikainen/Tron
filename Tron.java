import javafx.application.Platform;
import javafx.stage.Stage;

public class Tron {
    public boolean running = true;

    //Kõiki mänguala ridu sisaldav list
    private int[][] ussid = new int[60][60];

    //Mängijad Player klassi jaoks
    Player yks = new Player();
    Player kaks = new Player();
    Snake uss = new Snake();

    //Mängijate alguskoordinaadid
    private int x1 = 570;
    private int y1 = 570;
    private int x2 = 20;
    private int y2 = 20;

    //get meetodid, et klassis snake saaks koordinaatide väärtuseid
    public int getX1() { return x1; }
    public int getY1() { return y1; }
    public int getX2() { return x2; }
    public int getY2() { return y2; }

    //Usside koordinaatide massiivi lisamine
    public void lisamine(int x1, int y1, int x2, int y2) {
        ussid[x1/10][y1/10]=1;
        ussid[x2/10][y2/10]=1;
    }

    //Ussidele algussuuna andmine
    //Hetkel pole kuskil kasutuses
    public void setSuund() {

        float rand = (float) Math.random();
        if (rand > 0.5) {
            yks.setSuund("yles");
            kaks.setSuund("alla");
        } else {
            yks.setSuund("vasak");
            kaks.setSuund("parem");
        }
    }


    //Annab ussidele uued koordinaadid ja kontrollib, kas ussil on võimalik edasi liikuda
    public String oota(Stage lava) {

        x1 += yks.getSpeedX();
        y1 += yks.getSpeedY();
        x2 += kaks.getSpeedX();
        y2 += kaks.getSpeedY();


        //Mängualas olemise kontroll
        if ((x1<0 || x1>590 || y1<0 || y1>590)&&(x2<0 || x2>590 || y2<0 || y2>590)) newGame("Viik!", lava);
        else if (x1<0 || x1>590 || y1<0 || y1>590) newGame("Rohelise võit!", lava);
        else if (x2<0 || x2>590 || y2<0 || y2>590) newGame("Punase võit!", lava);
        else if (x2==x1 && y1==y2) newGame("Viik!", lava);


        else { //Usside kokkupõrkamise kontroll
            if (ussid[x1 / 10][y1 / 10] == 1) {
                newGame("Rohelise võit!", lava);
                //throw new RuntimeException("Rohelise v6it!");
            } else if (ussid[x2 / 10][y2 / 10] == 1) {
                newGame("Punase võit!", lava);
            }
        }


        return "";
    }

    private void newGame(String voitja, Stage lava){
        running = false;
        StartExit nextGame = new StartExit();
        nextGame.setTekst(voitja);
        nextGame.start(lava);
    }
}