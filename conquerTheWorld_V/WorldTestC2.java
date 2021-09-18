

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WorldTestC2.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WorldTestC2
{
    private World w1, w2, w3, w4;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        w1 = new World(100,100);
        w2 = new World(50,70);
        w3 = new World(50,200);
        w4 = w1;// new World(100,100);
    }
    
    @Test
    public void segunCPdeberiaCrearElWorld() 
    {        
        assertFalse (w1.equals(new World(100, 100)));
        assertTrue (w2.equals(w2));
        assertFalse (w3.equals(new World(50,200)));
        assertTrue (w1.equals(w4));
    }
    
    @Test
    public void segunCPNoDeberiaCrearElWorld() 
    {        
        assertFalse (w1.equals(new World(120, 300)));
        assertFalse (w2.equals(w3));
        assertFalse (w3.equals(new World(50,200)));
        assertEquals (w1,w4);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
