public abstract class Tablero {
    private Celda[][] celdas;
    private int numMinas;

    public Tablero(int sizeX, int sizeY, int numMinas){
       celdas = new Celda[sizeX][sizeY];
       this.numMinas = numMinas;
    }


}
