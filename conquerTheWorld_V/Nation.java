
import java.util.ArrayList;
/**
 * Clase de nation donde se vera una representacion visual de la misma con 
 * colores diferentes en forma de rectangulo
 * 
 * @author Yesid Carrillo, Juan David Parroquiano 
 * @version 1.0 (Septiembre 3, 2021)
 */
public class Nation
{
    Rectangle nation ;
    private String color ;
    private boolean isVisible;
    private int xPosition, yPosition;
    private int width = 40;
    private int height = 40;
    private int nArmies;
    private ArrayList<Integer> cen = new ArrayList<Integer>();
    private ArrayList<Route> properRoutes = new ArrayList<Route>();
    private ArrayList<Army> armies = new ArrayList<Army>();
    private Rectangle view ;
    private Rectangle world;
    
    /**
     * Constructor de la nacion
     * @param _color: String, color de la nacion
     * @param _x: int, posicion en x de la nacion
     * @param _y: int, posicion en y de la nacion
     */
    
    public Nation(String _color, int _x, int _y, int _armies)
    {
        this.color = _color;
        this.xPosition = _x;
        this.yPosition = _y;
        this.nArmies = _armies;
        nation = new Rectangle();
        nation.changeColor(_color);
        nation.changeSize(width, height);
        nation.moveHorizontal(_x);
        nation.moveVertical(_y);
        isVisible = false;
    }
    
    /**
     * Constructor de nacion 2
     * @param color: String color de la nacion
     * @param _x: int, posicion en x de la nacion
     * @param _y: int, posicion en y de la nacion
     * @param _nArmies: int, numero de ejercitos
     * @param _world: World, dimensiones del mundo
     */
    
    public Nation(String color, int x, int y, int _nArmies, Rectangle _world){
        this.color = color;
        this.world = _world;
        xPosition = getPositionX(x);
        yPosition = getPositionY(y);
        this.nArmies = nArmies;
        view = new Rectangle(width,height);
        configureVisual();
    }  
    
    /**
     * Methodo que retorna la posición x de la nación 
     */
    public int getxPosition()
    {
        return this.xPosition;
    }
    
    /**
     * Methodo que retorna la posición y de la nación 
     */
    public int getyPosition()
    {
        return this.yPosition;
    }
    
    /**
     * Metodo para obtener el color de una nacion
     */
    public String getColor()
    {
        return this.color;
    }
    
    /**
     * Metodo para
     */
    public void configureVisual()
    {
        view.changeColor(color);
        view.changePosition(xPosition, yPosition);
    }
    
    /**
     * Metodo para hacer visible una nacion
     */
    public void makeVisible(){
        nation.makeVisibles();
    }
    
    /**
     * Metodo para hacer invisible una nacion
     */
    public void makeInvisible()
    {
        nation.makeInvisibles();
    }
    
    /**
     * Metodo para adicionar una ruta 
     * @param _route: Route, ruta
     * @param _routes: ArrayList<Route>, 
     * @param _nation2: Nation
     */
    public void addRoute(Route _route, ArrayList<Route> _routes,Nation _nation2)
    {
        _routes.add(_route);
        properRoutes.add(_route);
        _nation2.addRoute(_route);
    }
    
    /**
     * Metodo para adicionar una ruta
     * @param _routes: Route,
     */
    public void addRoute(Route _route)
    {
        if (!properRoutes.contains(_route))
            properRoutes.add(_route);
    }
    
    /**
     * Metodo para adicionar ejercitos
     */
    public void addArmy(Army _army)
    {
        nArmies ++;
        this.armies.add(_army);
    }
    
    /**
     * Metodo para eliminar ejercitos
     */
    public void delArmy(Army _army)
    {
        nArmies --;
        this.armies.remove(_army);
    }
    
    public int getnArmies()
    {
        return this.nArmies;
    }
    
    public void setnArmies(int _newArmies)
    {
        this.nArmies = _newArmies;
    }
    
    /**
     * Metodo para obtener el centro en la coordenada x de una nacion
     */
    public int getCenX()
    {
        return this.xPosition + (height/2);
    }
    
    /**
     * Metodo para obtener el centro en la coordenada y de una nacion
     */
    public int getCenY()
    {
        return this.yPosition + (width/2);
    }
    
    /**
     * Metodo para eliminar una nacion
     * @param _routes: ArrayList<Route>
     */
    public void delNation(ArrayList<Route> _routes, ArrayList<Army> _armies)
    {
        this.makeInvisible();
        for(Route i: properRoutes) 
            i.delRoute(_routes);
        _routes.removeAll(properRoutes);
        for(Army a: armies)
            a.delArmy(_armies);
        _armies.removeAll(armies);   
    }
    
    
    /**
     * Metodo para eliminar una nacion
     * @param _routes: ArrayList<Route>
     */
    /*public void delNationArmy(ArrayList<Army> _armies)
    {
        this.makeInvisible();
        for(Route i: properRoutes) 
            i.delRoute(_routes);
        _routes.removeAll(properRoutes);
    }*/
    
    /**
     * Metodo para obtener una posicion en Y
     * @param yPosition: int,
     */
     private int getPositionY(int yPosition)
     {
        int total = world.getHeight();
        if (yPosition == 0) 
            return total - yPosition + world.getYPosition() - height;
            
        return total - yPosition + world.getYPosition();
    }
    
    /**
     * Metodo para obtener una posicion en X
     * @param xPosition: int,
     */
    private int getPositionX(int xPosition){
        int total = world.getXPosition();
        if (xPosition == 0) 
            return total + xPosition;
            
        return total + xPosition-width;
    }
}
