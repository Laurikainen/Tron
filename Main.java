import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("proov");

        Tron game = new Tron();
        Scanner sc = new Scanner(System.in);
        //Mängijate informatsiooni küsimine
        System.out.println("Sisestage esimese mängija nimi.");
        String nimi1 = sc.nextLine();
        System.out.println("Sa oled trellid!");

        System.out.println("Sisestage teise mängija nimi.");
        String nimi2 = sc.nextLine();
        System.out.println("Sa oled tärnid!");

        //Algse mänguplatsi väljastamine
        System.out.println(game.manguala(nimi1, nimi2));

        System.out.println("Suunad: yles, alla, parem, vasak");
        System.out.println("Käsk edasi näitab mängulaua hetkeseisu");

        //Uss liigub kuni toimub kokkupõrge
        while (true){
            System.out.println("Sisestage käsk edasi või mängija nimi, tühik ja uus suund.");

            String sc2 = sc.nextLine();
            //Ussi liigutamine otse edasi ja seisu kuvamine
            if (sc2.equals("edasi")){
                String gameState = game.oota();
                System.out.println(gameState);
                if (gameState.length() < 100){
                    break;
                }
            }
            else{
                //Esimese ussi (#) liikumine
                if(sc2.substring(0, nimi1.length()).equals(nimi1)){
                    game.yks.setSuund(sc2.substring(nimi2.length() + 1, sc2.length()));
                }
                //Teise ussi (*) liikumine
                else if(sc2.substring(0, nimi2.length()).equals(nimi2)){
                    game.kaks.setSuund(sc2.substring(nimi2.length() + 1, sc2.length()));
                }
            }
        }
        sc.close();
    }
}
