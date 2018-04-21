import java.util.ArrayList;

public class Tron {
    //Kõiki mänguala ridu sisaldav list
    private ArrayList<String> read = new ArrayList<>();
    //Mängijad Player klassi jaoks
    public Player yks = new Player();
    public Player kaks = new Player();
    //Mängijate alguskoordinaadid
    private int x1 = 1;
    private int y1 = 1;
    private int x2 = 8;
    private int y2 = 8;
    //Mängijate nimed
    private String esimene;
    private String teine;
    //Algse mänguala tegemine, mis on 10x10 ja kus kummalgi ussil on kindlad algkoordinaadid
    public String manguala(String nimi1, String nimi2) {
        float rand = (float) Math.random();
        esimene = nimi1;
        teine = nimi2;
        //Kummalegi ussile liikumissuuna andmine
        if (rand > 0.5){
            yks.setSuund("alla");
            kaks.setSuund("yles");
        }
        else{
            yks.setSuund("parem");
            kaks.setSuund("vasak");
        }
        //Mänguala tegemine koos ussidega
        for (int i = 0; i<10; i++) {
            StringBuilder rida = new StringBuilder();
            for (int j=0; j<10; j++){
                if (i==x1 && j==y1) rida.append("#");
                else if (i==x2 && j==y2) rida.append("*");
                else rida.append("-");
            }
            read.add(rida.toString());
        }
        return string(read);
    }
    //Listi muutmine sõneks, et seda väljastada
    private String string(ArrayList<String> uss) {
        StringBuilder sone = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sone.append(uss.get(i)).append("\n");
        }
        return sone.toString();
    }
    //Abifunktsioon, mis asendab etteantud indexil input sümboli chr-ga.
    private String replaceIndex(String input, int index, String chr){
        return input.substring(0, index) + chr + input.substring(index+1, input.length());
    }

    public String oota(){ //Liigutab usse edasi suunas, kuhu nad vaatavad ja tagastab uue mänguvälja
        x1 += yks.getSpeedX();
        y1 += yks.getSpeedY();
        x2 += kaks.getSpeedX();
        y2 += kaks.getSpeedY();

        String seis = stop(x1, y1, x2, y2);
        if (!seis.equals("")){ //Kontrollime, kas uss tohib edasi liikuda
            return seis;
        }
        else { //Vana mänguvälja muutmine, et ta oleks liikumisega kooskõlas
            read.set(y1, replaceIndex(read.get(y1), x1, "#"));
            read.set(y2, replaceIndex(read.get(y2), x2, "*"));
            return string(read);
        }
    }

    private String stop(int x1, int y1, int x2, int y2){ //Usside kokkupõrkamise ja mängualast väljumise kontrollimine
        //Mängualas olemise kontroll
        if ((x1<0 || x1>9 || y1<0 || y1>9)&&(x2<0 || x2>9 || y2<0 || y2>9)) return "Jäite viiki";
        else if (x1<0 || x1>9 || y1<0 || y1>9) return teine + " võitis";
        else if (x2<0 || x2>9 || y2<0 || y2>9) return esimene +  " võitis";
        else if (x2==x1 && y1==y2) return "Jäite viiki";

        else { //Usside kokkupõrkamise kontroll
            for (int i = 0; i < 10; i++) {
                boolean uss1 = false;
                boolean uss2 = false;
                String uus = read.get(i);
                if (y1 == i) {
                    for (int j = 0; j < 10; j++) {
                        if (uus.charAt(x1) == '*' || uus.charAt(x1) == '#') uss1 = true;
                    }
                }
                if (y2 == i) {
                    for (int j = 0; j < 10; j++) {
                        if (uus.charAt(x2) == '*' || uus.charAt(x2) == '#') uss2 = true;
                    }
                }
                if (uss1 && uss2) return "Jäite viiki";
                else if (uss1) return teine + " võitis";
                else if (uss2) return esimene + " võitis";
            }
            return "";
        }
    }
}