package labb6.event;

import labb6.state.StoreState;
import labb6.event.ArrivalEvent;
/**
 * Representerar en stangningshandelse for en butik.
 *
 * <p>Denna klass hanterar nar butiken stanger for dagen och tar hand om att
 * avsluta alla pagaende aktiviteter i butiken.
 *
 * <p>Klassen arver fran `StoreEvent` och implementerar metoden `performEffect()`
 * for att hantera sjalva stangningshandelsen.
 * 
 * <p>Observera att stangningshandelsen paverkar butikens tillstand genom att stanga
 * butiken och uppdatera tidtagare samt oka ledig kassatid och kotid.
 * 
 * <p>Observera ocksa att denna klass anvands for att generera stangningshandelser
 * i simuleringen av butikens kundflode.
 * 
 * @author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
 */
public class ClosingEvent extends StoreEvent {

	private StoreState storeState;
	
    public ClosingEvent(double scheduledTime, StoreState state, boolean isEmergencyStop, EventQueue eventQueue) {
        // Stangningshandelsen har en specifik tid, koppling till ett tillstand (state), och en flagga for nodbroms.
        super(scheduledTime, state, isEmergencyStop, eventQueue);
        this.storeState = state;
    }

    @Override
    public void performEffect() {
        // Stang snabbkopet for dagen.
    	double freeRegTime = super.getScheduledTime() - storeState.getTime();
		storeState.increaseFreeRegisterTime(freeRegTime);
		double peopleInLineTime = super.getScheduledTime() - storeState.getTime();
		storeState.increaseLineTime(peopleInLineTime);
		
		storeState.setTime(getScheduledTime());
		
    	storeState.update(this);
        getStoreState().closeStore();

        
    }
}
