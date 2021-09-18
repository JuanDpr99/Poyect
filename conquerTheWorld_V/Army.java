import java.lang.Math.*;
import java.util.Random;
import java.util.ArrayList;
/**
 * Write a description of class Army here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Army
{
    private ArrayList<Circle> circles = new ArrayList<Circle>(); 
    private int nArmies ;
    private Nation nation;
    private boolean isVisible;
    
    public Army(int _armies, Nation _location)
    {
        this.nArmies = _armies;
        this.nation = _location;
        contructArmies();
    }
    
    public void contructArmies()
    {   
       int i = this.nArmies;
       while(i != 0)
        {
            double posiX = Math.random()*(nation.getxPosition()+46);
            double posiY = Math.random()*(nation.getyPosition()+86);
            if(posiX > nation.getxPosition() +11   && 
               posiY > nation.getyPosition()+51)
            {
                Circle army = new Circle((int)posiX, (int)posiY);
                circles.add(army);
                i--;
            }
       }
       isVisible = true;
    }
    
    /**
     * Metodo para eliminar armies
     * @_routes: ArrayList<Route>, 
     */
    public void delArmy(ArrayList<Army> _armies)
    {
        makeInvisible();
        _armies.remove(this);
    }
    
    public void makeVisible()
    {
        //erase();
        for(Circle c: circles)
        {
            c.makeVisible();
        }
        isVisible = true;
    }
   
    public void makeInvisible()
    {
        for(Circle c: circles)
        {
            c.makeInvisible();
        }
        isVisible = false;
        //draw();
    }
    
    /*private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }*/
    
    /**
     * Metodo para eliminar el Army
     */
    private void erase()
    {
        Canvas canvas = Canvas.getCanvas();
        canvas.erase(this);
    }
}
