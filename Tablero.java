import java.util.ArrayList;

public class Tablero
{
    private Celda[][] celdas;
    private int numMinas;

    public Tablero(int size, int numMinas){
        celdas = new Celda[size][size];
        this.numMinas = numMinas;
    }

    public boolean celdaAbierta(int x, int y){
        if(celdas[x][y].getAbierta()){
            return true;
        }
        return false;
    }
    public void crearCeldasDelTablero(){
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
            if(!celdaEsMina(posTmpFila, posTmpColumna)){
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
                if(celdaEsMina(i,j)){
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

    public boolean celdaEsMina(int posX, int posY){
        if(celdas[posX][posY].esMina()){
            return true;
        }
        return false;
    }
    public void abrirTodasLasMinas(){
        ArrayList<Celda> celdasConMinas = celdas_minas();
        for(Celda celdaAct : celdasConMinas){
            celdaAct.setAbierta(true);
        }
    }

    public void imprimirTablero() {
        for (int i=0; i<celdas.length; i++){
            for(int j=0; j<celdas[0].length; j++){
                if(celdaAbierta(i,j)){
                    if(celdaEsMina(i,j)){
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

    public void celdaEsCampo(int posX, int posY) {
        if (celdas[posX][posY].getNumMinasAlrededor() == 0) {
            celdas[posX][posY].setAbierta(true);
            ArrayList<Celda> celdasAlrededor = celdasAlrededor(posX, posY);
            for (Celda celda : celdasAlrededor) {
                if (!celda.getAbierta()) {
                    celda.setAbierta(true);
                    celdaEsCampo(celda.getPosX(), celda.getPosY());
                }
            }
        }else{
            celdas[posX][posY].setAbierta(true);
        }
    }
}
