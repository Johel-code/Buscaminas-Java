import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CeldaTest
{
    private Celda celda;
    /**
     * Default constructor for test class CeldaTest
     */
    public CeldaTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        celda = new Celda(0,0);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    @Test
    public void getPosCelda(){
        int posX = celda.getPosX();
        int posY = celda.getPosY();
        assertEquals(0, posX);
        assertEquals(0, posY);
    }
    @Test
    public void esMina(){
        assertFalse(celda.esMina());
    }
    @Test
    public void NumMinasAlrededor(){
        assertEquals(0,celda.getNumMinasAlrededor());
    }
    @Test
    public void celdaAbierta(){
        assertFalse(celda.getAbierta());
    }
}
