import java.util.Scanner;

public abstract class Buscamina
{
    protected boolean perdio;
    private Scanner sc;
    
    public Buscamina(){
        perdio = false;
        sc = new Scanner(System.in);
    }
    
    public abstract void jugar();
}
