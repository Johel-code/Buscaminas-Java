import java.util.Scanner;

public class BuscaminaNivelBasico extends Buscamina
{
    private Tablero tablero;
    private Scanner sc;
    private boolean perdio;

    public BuscaminaNivelBasico(){
        tablero = new Tablero(8,10);
        sc = new Scanner(System.in);
        perdio = false;
    }
    
    public void jugar(){
        tablero.crearCeldasDelTablero();
        tablero.imprimirTablero();
        System.out.println("Ingrese un coordenada para comenzar a jugar");
        while(!perdio){
            String cordenadas = sc.nextLine();
            String[] parts = cordenadas.split(",");
            int posX = Integer.parseInt(parts[0]);
            int posY = Integer.parseInt(parts[1]);
            if(!tablero.celdaAbierta(posX, posY)){
                seleccionarCelda(posX, posY);
                tablero.imprimirTablero();
            }else{
                System.out.println("Esta celda ya esta abierta");
            }
        }
    }

    private void seleccionarCelda(int posX, int posY){
        if(tablero.celdaEsMina(posX, posY)){
            perdio = true;
            System.out.println("Game Over");
            tablero.abrirTodasLasMinas();
        }else{
            tablero.celdaEsCampo(posX, posY);
        }
    }
}
