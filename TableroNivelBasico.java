import java.util.ArrayList;
import java.util.Scanner;

public class TableroNivelBasico
{
    private Celda[][] celdas;
    
    private int numMinas;
    private Scanner sc;
    
    private boolean perdio;
    
    public TableroNivelBasico(){
        sc = new Scanner(System.in);
        celdas = new Celda[8][8];
        numMinas = 10;
        perdio = false;
    }
    
    private void crearCeldasDelTablero(){
        for (int i=0; i<celdas.length; i++){
            for(int j=0; j<celdas[0].length; j++){
                celdas[i][j] = new Celda(i, j);
            }
        }
        generarMinas();
    }
    
    private void generarMinas(){
        int minasGeneradas=0;
        while (minasGeneradas != numMinas){
            int posTmpFila = (int) (Math.random()*8);
            int posTmpColumna = (int) (Math.random()*8);
            if(!celdas[posTmpFila][posTmpColumna].esMina()){
                celdas[posTmpFila][posTmpColumna].setMina(true);
                minasGeneradas++;
            }
        }
        actualizarNumMinasAlrededor();
    }
    
    private void actualizarNumMinasAlrededor(){
        ArrayList<Celda> celdasConMinas = celdas_minas();
        for(Celda celdaAct : celdasConMinas){
            int i = celdaAct.getPosX();
            int j = celdaAct.getPosY();
            ArrayList<Celda> celdasAlrededor = celdasAlrededor(i, j);
            incrementarValorDeNumMinasAlrededor(celdasAlrededor);
        }
    }

    private ArrayList<Celda> celdas_minas(){
        ArrayList<Celda> listaCeldasMinas = new ArrayList<>();
        for (int i=0; i<celdas.length; i++){
            for(int j=0; j<celdas[0].length; j++){
                if(celdas[i][j].esMina()){
                    listaCeldasMinas.add(celdas[i][j]);
                }
            }
        }
        return listaCeldasMinas;
    }

    private void incrementarValorDeNumMinasAlrededor(ArrayList<Celda> celdasAlrededor) {
        for(Celda celdaAct : celdasAlrededor){
            celdaAct.incrementarNumMinasAlrededor();
        }
    }

    private ArrayList<Celda> celdasAlrededor(int posX, int posY){
        ArrayList<Celda> listaCeldas = new ArrayList<>();
        for(int i=0; i<8; i++){
            int posActX = posX;
            int posActY = posY;
            switch(i){
                case 0: posActX--;break;            //Arriba
                case 1: posActX--;posActY++;break;  //Arriba Derecha
                case 2: posActY++;break;            //Derecha
                case 3: posActY++;posActX++;break;  //Derecha Abajo
                case 4: posActX++;break;            //Abajo
                case 5: posActX++;posActY--;break;  //Abajo Izquierda
                case 6: posActY--;break;            //Izquierda
                case 7: posActX--;posActY--;break;  //Izquierda Arriba
            }
            
            if(posActX>=0 && posActX<8 && posActY>=0 && posActY<8){
                listaCeldas.add(this.celdas[posActX][posActY]);
            }
        }
        return listaCeldas;
    }
    
    private void seleccionarCelda(int posX, int posY){
        if(celdas[posX][posY].esMina()){
            perdio = true;
            System.out.println("Game Over");
            ArrayList<Celda> celdasConMinas = celdas_minas();
            for(Celda celdaAct : celdasConMinas){
                celdaAct.setAbierta(true);
            }
        }else if (celdas[posX][posY].getNumMinasAlrededor() == 0){
            celdas[posX][posY].setAbierta(true);
            ArrayList<Celda> celdasAlrededor = celdasAlrededor(posX, posY);
            for(Celda celda: celdasAlrededor){
                if(!celda.getAbierta()){
                    celda.setAbierta(true);
                    seleccionarCelda(celda.getPosX(), celda.getPosY());
                }
            }
        }else{
            celdas[posX][posY].setAbierta(true);
        }
    }
    
    private void imprimirTablero() {
        for (int i=0; i<celdas.length; i++){
            for(int j=0; j<celdas[0].length; j++){
                if(celdas[i][j].getAbierta()){
                    if(celdas[i][j].esMina()){
                        System.out.print("*");
                    }else{
                        System.out.print(celdas[i][j].getNumMinasAlrededor());
                    }
                }else{
                    System.out.print("-");
                }
            }
            System.out.println("");
        }
    }

    private void jugar(){
        crearCeldasDelTablero();
        imprimirTablero();
        System.out.println("Ingrese un coordenada para comenzar a jugar");
        while(!perdio){
            String cordenadas = sc.nextLine();
            String[] parts = cordenadas.split(",");
            int posX = Integer.parseInt(parts[0]);
            int posY = Integer.parseInt(parts[1]);
            if(!celdas[posX][posY].getAbierta()){
                seleccionarCelda(posX, posY);
                imprimirTablero();
            }else{
                System.out.println("Esta celda ya esta abierta");
            }
        }  
    }
    
    public static void main(String[] args) {
        TableroNivelBasico tableroNivelBasico = new TableroNivelBasico();
        tableroNivelBasico.jugar();
    }
}