package labb6.event;

import labb6.state.StoreState;
import labb6.view.StoreView;
/**
 * Representerar en handelse som markerar slutet av simuleringen.
 *
 * <p>Denna handelse anvands for att avsluta simuleringen av butikens
 * kundflode vid en specifik tidpunkt.
 *
 * <p>Klassen arver fran `Event` och implementerar metoden `performEffect()`
 * for att hantera sjalva stopphandelsen.
 * 
 * <p>Observera att stopphandelsen paverkar butikens tillstand genom att
 * uppdatera den sista tiden och stanga butiken om den inte redan ar stangd.
 * 
 * <p>Observera ocksa att denna klass anvands for att avsluta simuleringen
 * vid ett visst tidsskede.
 * 
 * @author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
 */
public class StopEvent extends Event {
	
	private StoreState storeState;

    public StopEvent(double scheduledTime, StoreState state, EventQueue eventQueue) {
        // Stopphandelsen har en specifik tid, koppling till ett tillstand (state), och en flagga for nodbroms.
        super(scheduledTime, state, true, eventQueue);
        this.storeState = state;
        
    }

    @Override
    public void performEffect() {
        // Avsluta simuleringen.
    	storeState.setLastTime(storeState.getTime());
    	storeState.setTime(getScheduledTime());
    	storeState.closeStore(); // stang affaren om den inte redan gjort det
        storeState.update(this);
        //System.exit(0);
    }
}
