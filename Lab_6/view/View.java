package labb6.view;
/**
 * Generell visningsklass.
 * 
 * @author William Hägg, Axel Nordelöf, Vincent McFaul and Herman Ghafouri
 */
import java.util.Observer;
import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class View implements Observer {
    public abstract void update(Observable o, Object arg);
}