import java.util.ArrayList;
import java.lang.Math;
/**
 * Clase de mundo que contiene todos los elementos necesarios 
 * para conquistar el mundo
 * @author Yesid Carrillo, Juan David Parroquiano 
 * @version 1.0 (Septiembre 3, 2021)
 */
public class World
{
    Rectangle world ;
    Rectangle world2 ;
    private ArrayList<Nation> nations = new ArrayList<Nation>();
    private ArrayList<Route> routes = new ArrayList<Route>();
    private ArrayList<Army> armies = new ArrayList<Army>();
    private boolean isVisible;
    private boolean complete;
    // ------------------------MiniCiclo 1-----------------------------------
    /**
     * Constructor de mundo
     * @param _length: int, valor del largo del mundo
     * @param _width: int, valor del ancho del mundo
     */
    public World()//int _length, int _width)
    {
        world = new Rectangle();
        world.changeColor("black");
        //world.changeSize(_length, _width);
        world.changeSize(320, 470);
        world2 = new Rectangle();
        world2.changeColor("white");
        //world2.changeSize(_length-10, _width-10);
        world2.changeSize(310, 460);
        world2.moveHorizontal(5);
        world2.moveVertical(5);
        complete = true;
    }
    
    /**
     * Metodo que adiciona una nacion
     * @param _color: String, color de la nacion
     * @param _x: int, posicion en x de la nacion
     * @param _y: int, posicion en y de la nacion
     * @param _armies: int, numero de ejercitos que tiene una nacion
     */
    public void addNation(String _color, int _x, int _y, int _armies)
    {
        Nation nation = new Nation(_color,_x,_y,_armies);
        nations.add(nation);
        complete = true;
    }
    
    /**
     * Metodo que adiciona una ruta
     * @param _locationA: String, color de la nacion 1
     * @param _locationB: String, color de la nacion 2
     * @param _cost: int, costo de utilizar una ruta
     */
    public void addRoute(String _locationA, String _locationB,int _cost)
    {
        if(!routes.contains(searchRoute(_locationA, _locationB)))
        {
            Nation nation1 = searchNation(_locationA);
            Nation nation2 = searchNation(_locationB);
            Route route = new Route(nation1,nation2,_cost);
            nation1.addRoute(route,routes,nation2);
            complete = true;
        }
        complete = false;            
    }   
    
    /**
     * Metodo que elimina una nación
     * @param _color: String, color de la nacion a eliminar
     */
    public void delNation(String _color)
    {
        Nation nationDeleted = searchNation(_color);
        nationDeleted.delNation(routes, armies);
        nations.remove(nationDeleted);
    }
    
    /**
     * Metodo para eliminar una ruta
     * @param _locationA: String, color de la nacion 1
     * @param _locationB: String, color de la nacion 2
     */
    public void delStreet(String _locationA, String _locationB){
        Route deletedRoute = searchRoute(_locationA, _locationB);
        deletedRoute.delRoute(routes);
    }
    
    /**
     * Metodo que muestra un ejercito en una nación 
     * @param _location: String, 
     */
    public void putArmy(String _location)
    {
        
        if (nations.contains(searchNation(_location)))
        {
            Army army = new Army(searchNation(_location).getnArmies(), searchNation(_location));
            armies.add(army);
            searchNation(_location).addArmy(army); //<--?
            complete = true;                
        }
        complete = false;
    }
    
    /**
     * Metodo para remover un ejercito
     * @param _location: String, 
     */
    public void removeArmy(String _location)
    {
        Nation minusArmy = searchNation(_location);
        //minusArmy.delArmy();
    }
    
    // ------------------------MiniCiclo 2-------------------
    
    public void moveArmyOneRoute(String _locationA, String _locationB)
    {
        if(searchNation(_locationA) != null && searchNation(_locationB) != null)
        {
            int nArmies = searchNation(_locationA).getnArmies();
            int newArmies = searchNation(_locationB).getnArmies() + nArmies;
            if( nArmies > 0)
            {
                searchNation(_locationA).setnArmies(0);
                searchNation(_locationB).setnArmies(newArmies);
            }
            
        }            
    }
    
    /*
    public String[] conqueredNations()
    {        
    }
    public int payments()
    {
    }
    public boolean conquer()
    {
    }
    */
    //----------------------MiniCiclo 3----------------------------
    
    /**
     * Metodo para hacer visible el mundo y sus elementos
     */
    public void makeVisible()
    {
        isVisible = true;
        world.makeVisibles();
        world2.makeVisibles();
        for(Nation n: nations)
            n.makeVisible();
        for(Route r: routes)
            r.makeVisible();        
        for(Army a: armies)
            a.makeVisible();
        complete = true;
    }
    
    /**
     * Metodo para hacer invisible el mundo y sus elementos
     */
    public void makeInvisible()
    {
        world.makeInvisibles();
        for(Nation n: nations)
            n.makeInvisible();
        for(Route r: routes)
            r.makeInvisible();
        for(Army a: armies)
            a.makeInvisible();
        isVisible = false;
        complete = true;
    }
    
    /**
     * Metodo para finalizar con el programa
     */
    public void finish()
    {
        System.exit(0);
    }
    
    /**
     * Metodo que retorna si la ultima accion se pudo realizar o no
     */
    public boolean ok()
    {
        return complete;
    }
    
    // METODOS AUXILIARES
    
    /**
     * Metodo para buscar si existe o no una nacion
     * @param _color: String,
     */
    private Nation searchNation(String _color)
    {
        for (Nation i: nations)
        {
            if (i.getColor().equals(_color)) 
                return i;
        }
        return null;
    }
    
    /**
     * Metodo para buscar si una ruta existe o no
     * @param _locationA: String, color de la nacion 1
     * @param _locationB: String, color de la nacion 2
     */
    private Route searchRoute(String _locationA, String _locationB)
    {
        for (Route i: routes)
        {
            if (i.searchRoute(_locationA, _locationB))
                return i;
        }
        return null;
    }
}

/** Retrospectiva Ciclo # 1
 * 1. ¿Cuáles fueron los mini-ciclos definidos? Justifíquenlos.
 *    Los mini-ciclos elegidos fueron:
 *    1. constructor World, addNation, addRoute, putArmy, delNation,
 *    delStreet, removeArmy
 *    2. removeArmyOneRoute, conqueredNations, payments, conquer
 *    3. makeInvisible(), makeVisible(), finish(), ok()
 *    
 * 2. ¿Cuál es el estado actual del laboratorio en términos de mini-ciclos? ¿por qué?
 * 
 *    En el estado actual del laboatorio logramos hacer la mayor parte del ciclo 1, el ciclo 2 no
 *    logramos implementarlo y el ciclo 3 logramos hacer la mayor parte del ciclo
 *    
 * 3. ¿Cuál fue el tiempo total invertido por cada uno de ustedes? (Horas/Hombre)
 * 
 *    El tiempo total invertido para la entrega del primer ciclo de laboratorio es:
 *    Yesid Carrillo 15 Horas
 *    Juan David Parroquiano 15 Horas
 *    
 * 4. ¿Cuál consideran fue el mayor logro? ¿Por qué?
 * 
 *    El mayor logro es hacer de una manera interactiva la interfaz para así lograr
 *    entender el problema de una mejor manera, entendiendo bien graficamente lo que está sucediendo 
 *    podemos codificar de una buena forma probando y resolviendo lo que se quiere.
 *    
 * 5. ¿Cuál consideran que fue el mayor problema técnico? ¿Qué hicieron para resolverlo?
 * 
 *    El mayor problema técnico para nosotros fue la utilizacion de una manera correcta Git, 
 *    pues hemos tenido algunos incovenientes al utilizar las herramientas que a su vez nos 
 *    provee tanto git como java para representar la situacion de manera grafica y resolver el problema 
 * 
 * 6. ¿Qué hicieron bien como equipo? ¿Qué se comprometen a hacer para mejorar los resultados?
 * 
 *     Cumplir con pequeñas reuniones diarias establecidas para avanzar con el proyecto. 
 *    
 * 7. Considerando las prácticas XP del laboratorio. ¿cuál fue la más útil? ¿por qué? 
 * 
 *    La practica más util fue la de Pair Programing ya que nos entendimos y nos complementamos
 *    muy bien  a la hora de la codificacion
 *    
 */
