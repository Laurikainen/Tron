public class Tron {

    //Kõiki mänguala ridu sisaldav list
    private Integer[][] ussid = new Integer[60][60];

    //Mängijad Player klassi jaoks
    Player yks = new Player();
    Player kaks = new Player();
    Snake uss = new Snake();

    //Mängijate alguskoordinaadid
    private int x1 = 20;
    private int y1 = 20;
    private int x2 = 570;
    private int y2 = 570;

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    //Usside koordinaatide massiivi lisamine
    public void lisamine(int x1, int y1, int x2, int y2) {
        ussid[x1/10][y1/10]=1;
        ussid[x2/10][y2/10]=2;
    }

    private void setSuund() {
        //Ussidele algussuuna andmine
        float rand = (float) Math.random();
        if (rand > 0.5) {
            yks.setSuund("alla");
            kaks.setSuund("yles");
        } else {
            yks.setSuund("parem");
            kaks.setSuund("vasak");
        }
    }

    public String oota() { //Liigutab usse edasi suunas, kuhu nad vaatavad
        x1 += yks.getSpeedX();
        y1 += yks.getSpeedY();
        x2 += kaks.getSpeedX();
        y2 += kaks.getSpeedY();

        String seis = stop(x1, y1, x2, y2);
        if (!seis.equals("")){ //Kontrollime, kas uss tohib edasi liikuda
            return seis;
        }
        return "";
    }

    private String stop(int x1, int y1, int x2, int y2){ //Usside kokkupõrkamise ja mängualast väljumise kontrollimine
        //Mängualas olemise kontroll
        if ((x1<0 || x1>590 || y1<0 || y1>590)&&(x2<0 || x2>590 || y2<0 || y2>590)) return "Jäite viiki";
        else if (x1<0 || x1>590 || y1<0 || y1>590) return "x1";
        else if (x2<0 || x2>590 || y2<0 || y2>590) return " x2";
        else if (x2==x1 && y1==y2) return "Jäite viiki";

        /*else { //Usside kokkupõrkamise kontroll
            if (ussid[y1/10][x1/10]==1 || ussid[y2/10][x2/10]==2) {
                return "error";
            }
            else if (ussid[y1/10][x1/10]==2 || ussid[y2/10][x2/10]==1) {
                return "error";
            }
        }*/
        return "";
    }
}