public class Player {
    //Need on x muut ja y muut, sellega on lihtsam uusi koordinaate arvutada
    private int speedX;
    private int speedY;

    public int getSpeedX() { return speedX; }
    public int getSpeedY() { return speedY; }

    //Muudab suunda vastavalt sisendile, kui mängija ei vaata vastassuunda
    //(ehk mängija ei saa ühe käsuga keerata 180 kraadi)
    public void setSuund(String suund) {
        if (suund.equals("parem") && speedX != -10){
            this.speedX = 10;
            this.speedY = 0;
        }
        else if (suund.equals("vasak") && speedX != 10){
            this.speedX = -10;
            this.speedY = 0;
        }
        else if (suund.equals("yles") && speedY != -10){
            this.speedX = 0;
            this.speedY = -10;
        }
        else if (suund.equals("alla") && speedY != 10){
            this.speedX = 0;
            this.speedY = 10;
        }
    }
}