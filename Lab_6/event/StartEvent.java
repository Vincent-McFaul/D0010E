package labb6.event;

import labb6.view.StoreView;
import labb6.state.StoreState;
/**
 * Representerar en handelse som markerar starten pa simuleringen av en butik.
 *
 * <p>Denna klass hanterar specifika atgarder nar simuleringen startar,
 * sasom att oppna butiken och schemalagga den forsta ankomsthandelsen for en ny kund.
 * 
 * <p>Har ar ocksa dar en simulering av en butik borjar och schemalagger en ankomsthandelse
 * for en ny kund for att initiera kundflodet i butiken.
 * 
 * <p>Observera att denna handelse utloser oppningen av butiken och initierar den forsta
 * ankomsthandelsen for en ny kund.
 * 
 * @author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
 */
public class StartEvent extends StoreEvent {
	
	private StoreState storeState;
	private EventQueue eventQueue;

    public StartEvent(double time, StoreState storeState, EventQueue eventQueue) {
        // man kan valja tid som timulatorn ska starta vid
        super(time, storeState, false, eventQueue);
        this.storeState = storeState;
        this.eventQueue = eventQueue;
    }

    @Override
    public void performEffect() {
        // Lagg till specifika atgarder for StartEvent
        // For nuvarande, lagg till en ankomsthandelse for en ny kund till handelsekon.
    	storeState.update(this);
    	storeState.setTime(getScheduledTime());
    	storeState.openStore();
    	double arrivalTime = storeState.getTime() + storeState.getNextArrivalTime();
        ArrivalEvent arrivalEvent = new ArrivalEvent(arrivalTime, storeState, false, eventQueue, 0);
        eventQueue.addEvent(arrivalEvent);
    }
}
