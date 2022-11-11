public class Celda
{
    private int posX, posY;
    private boolean mina;
    private int numMinasAlrededor;
    private boolean abierta;
    
    public Celda(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    
    public boolean esMina(){
        return mina;
    }
    
    public void setMina(boolean s){
        mina = s;
    }
    
    public int getNumMinasAlrededor(){
        return numMinasAlrededor;
    }
    
    public void incrementarNumMinasAlrededor(){
        this.numMinasAlrededor++;
    }
    
    public boolean getAbierta(){
        return abierta;
    }
    
    public void setAbierta(boolean a){
        abierta = a;
    }
}
