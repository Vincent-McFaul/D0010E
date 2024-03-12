package labb6.event;

import labb6.state.Customer;
import labb6.state.StoreState;
/**
 * Representerar en ankomsthandelse for en kund till en butik.
 *
 * <p>Denna klass hanterar nar en kund anlander till butiken och tar hand om
 * att skapa en kund och planera nasta ankomsthandelse baserat pa butikens
 * nuvarande tillstand.
 *
 * <p>Klassen arver fran `StoreEvent` och implementerar metoden `performEffect()`
 * för att hantera sjalva ankomsthandelsen.
 * 
 * <p>Observera att ankomsthandelsen paverkar butikens tillstand och kundlistan
 * samt schemalagger nasta ankomsthandelse och eventuella efterföljande handelser
 * beroende pa butikens kapacitet och öppettider.
 * 
 * <p>Observera ocksa att denna klass anvands för att generera ankomsthandelser
 * i simuleringen av butikens kundflöde.
 * 
 * @author William Hagg, Axel Nordelöf, Vincent McFaul and Herman Ghafouri
 */
public class ArrivalEvent extends StoreEvent {
	
	private StoreState storeState;
	private int ID;

    public ArrivalEvent(double scheduledTime, StoreState state, boolean isEmergencyStop, EventQueue eventQueue, int ID) {
        
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
		//System.out.println("customer "+ID+ " with picktime " + nextPickTime);
		if (storeState.hasSpaceForCustomer()) {
			Customer customer = new Customer(ID, storeState.getNextPickTime(), storeState.getNextPayTime(), storeState.getTotalCurrentCustomers() >= storeState.getMaxCustomers());
			storeState.getCustomerIDs().add(customer);
			
		}
		else {
			Customer customer = new Customer(ID, 0, 0, storeState.getTotalCurrentCustomers() >= storeState.getMaxCustomers());
			storeState.getCustomerIDs().add(customer);
			
		}
		
		// sista arrival event ska inte öka free reg time
		if ((!(getEventQueue().getEvents().size() == 1) && storeState.isOpen()) || (!(getEventQueue().getEvents().size() == 1) && !(storeState.isOpen()))) {
			double freeRegTime = super.getScheduledTime() - storeState.getTime();
			storeState.increaseFreeRegisterTime(freeRegTime);
			double peopleInLineTime = super.getScheduledTime() - storeState.getTime();
			storeState.increaseLineTime(peopleInLineTime);
		}
		
		storeState.setTime(getScheduledTime());
		
		storeState.update(this);
		
        if (getStoreState().isOpen() && getStoreState().hasSpaceForCustomer()) {
            // Om snabbköpet ar öppet och det finns plats, slapp in kunden.
            // Detta innebar att en till kund ar inne i snabbköpet och kommer att handla varor.
        	
        	
        	getStoreState().increaseTotalCurrentCustomers();
        
        	//System.out.println("send from arrival "+customer.getCustomerID()+ " with time " + (storeState.getTime()+customer.getShoppingTime()));
            PickItemEvent pickItemEvent = new PickItemEvent(storeState.getTime()+storeState.getCustomerShoppingTime(ID), getStoreState(), false, getEventQueue(), ID);
            getEventQueue().addEvent(pickItemEvent);
            
            ArrivalEvent nextArrivalEvent = new ArrivalEvent(storeState.getTime() + generateInterarrivalTime(), getStoreState(), false, getEventQueue(), storeState.getCustomerIDs().size());
            getEventQueue().addEvent(nextArrivalEvent);
            
        } else if (getStoreState().isOpen() && !getStoreState().hasSpaceForCustomer()) {
            // Om det ar öppet men fullt sa avviker kunden utan att handla.
            // Detta innebar att vi har missat en kund.
        	getStoreState().increaseMissedCustomers();
        	
        	ArrivalEvent nextArrivalEvent = new ArrivalEvent(storeState.getTime() + generateInterarrivalTime(), getStoreState(), false, getEventQueue(), storeState.getCustomerIDs().size());
            getEventQueue().addEvent(nextArrivalEvent);
        }
        
    }

    private double generateInterarrivalTime() {
        // Generera tiden till nasta ankomsthandelse.
    	double ArrivalTime = getStoreState().getArrivalTime().next();

        return ArrivalTime; 
    }
}
