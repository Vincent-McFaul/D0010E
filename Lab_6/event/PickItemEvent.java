package labb6.event;

import labb6.state.StoreState;
/**
 * Representerar en handelse dar en kund valjer varor i en butik.
 *
 * <p>Denna klass hanterar nar en kund valjer varor i butiken och tar hand om
 * att behandla kunden vid varuplockningen och schemalagga nasta betalningshandelse.
 *
 * <p>Klassen arver fran `StoreEvent` och implementerar metoden `performEffect()`
 * for att hantera sjalva plockhandelsen.
 * 
 * <p>Observera att plockhandelsen paverkar butikens tillstand genom att
 * oka ledig kassatid och kotid samt antalet personer i kon.
 * 
 * <p>Observera ocksa att denna klass anvands for att generera plockhandelser
 * i simuleringen av butikens kundflode.
 * 
 * @author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
 */
public class PickItemEvent extends StoreEvent {

	private StoreState storeState;
	private int ID;
	
    public PickItemEvent(double scheduledTime, StoreState state, boolean isEmergencyStop, EventQueue eventQueue, int ID) {
        // Plockhandelsen har en specifik tid, koppling till ett tillstand (state), och en flagga for nodbroms.
        super(scheduledTime, state, isEmergencyStop, eventQueue);
        this.storeState = state;
        this.ID = ID;
    }

    public StoreState getStoreState() {
		return storeState;
	}

	public int getID() {
		return ID;
	}

	@Override
    public void performEffect() {
		
		
		double freeRegTime = super.getScheduledTime() - storeState.getTime();
		storeState.increaseFreeRegisterTime(freeRegTime);
		double peopleInLineTime = super.getScheduledTime() - storeState.getTime();
		storeState.increaseLineTime(peopleInLineTime);
		
		
		storeState.setTime(getScheduledTime());
		
		getStoreState().update(this);
		
        if (!(getStoreState().getFreeRegisters() == 0)) {
            // Om det finns lediga kassor, ga direkt till en av dem for scanning/betalning.
            initiatePayment();
        } else {
            // Om alla kassor ar upptagna, stalls kunden i kassakon.
        	getStoreState().increaseTotalPeopleInLine();
            getStoreState().addCustomerToLine(ID);
        }
    }

    private void initiatePayment() {
        // Ga till en ledig kassa for scanning/betalning.
        getStoreState().decreaseFreeRegisters();

        // Generera en framtida betalningshandelse for kunden och lagg till den i kon.
        PaymentEvent paymentEvent = new PaymentEvent(storeState.getTime() + storeState.getCustomerPayTime(ID), storeState, false, getEventQueue(), ID);
        getEventQueue().addEvent(paymentEvent);
    }
}
