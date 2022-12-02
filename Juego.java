import java.util.Scanner;

public abstract class Juego
{
    protected boolean perdio;
    private Scanner sc;
    
    public Juego(){
        perdio = false;
        sc = new Scanner(System.in);
    }
    
    public abstract void jugar();
}
