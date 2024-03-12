package labb6.event;

import labb6.state.StoreState;
/**
 * Representerar en betalningshandelse for en kund i en butik.
 *
 * <p>Denna klass hanterar nar en kund betalar for sina varor och tar hand om
 * att behandla kunden vid kassan och schemalagga nasta betalningshandelse.
 *
 * <p>Klassen arver fran `StoreEvent` och implementerar metoden `performEffect()`
 * for att hantera sjalva betalningshandelsen.
 * 
 * <p>Observera att betalningshandelsen paverkar butikens tillstand genom att
 * minska antalet aktuella kunder, oka antalet avslutade kunder, behandla nasta
 * kund i kassakon eller oka antalet lediga kassor beroende pa kons status.
 * 
 * <p>Observera ocksa att denna klass anvands for att generera betalningshandelser
 * i simuleringen av butikens kundflode.
 * 
 * @author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
 */
public class PaymentEvent extends StoreEvent {
	
	private StoreState storeState;
	private int ID;

    public PaymentEvent(double scheduledTime, StoreState state, boolean isEmergencyStop, EventQueue eventQueue, int ID) {
        // Betalningshandelsen har en specifik tid, koppling till ett tillstand (state), och en flagga for nodbroms.
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
    	
    	storeState.update(this);
        getStoreState().decreaseTotalCurrentCustomers();
        getStoreState().increaseFinishedCustomers();

        if (!getStoreState().isLineEmpty()) {
            // Om kassakon inte ar tom, behandla nasta kund i kon.
            processNextCustomerInQueue();
        } else {
            // Om kassakon ar tom, oka antalet lediga kassor med 1.
            getStoreState().increaseFreeRegisters();
        }
    }

    private void processNextCustomerInQueue() {
        // Hamta nasta kund i kassakon.
        // Ga till en ledig kassa for scanning/betalning.
        initiatePayment();
    }

    private void initiatePayment() {
        // Ga till en ledig kassa for scanning/betalning.
        

        // Generera en framtida betalningshandelse for kunden och lagg till den i kon.
    	//System.out.println("next paymentevent: " + (getScheduledTime() + storeState.getCustomerPayTime(ID)));
    	PaymentEvent paymentEvent = new PaymentEvent(getScheduledTime() + storeState.getCustomerPayTime(storeState.getFirstInLine()), getStoreState(), false, getEventQueue(), storeState.dequeueCustomerFromLine());
        getEventQueue().addEvent(paymentEvent);
        
    }
}
