import java.util.Scanner;

public abstract class Juego
{
    private boolean perdio;
    private Scanner sc;
    private Tablero tablero;
    public Juego(Tablero tablero){
        perdio = false;
        sc = new Scanner(System.in);
        this.tablero = tablero;
    }
    
    public abstract void jugar();
}
