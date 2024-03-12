package labb6.state;

import labb6.event.Event;
import java.util.ArrayList;
import labb6.ExponentialRandomStream;
import labb6.UniformRandomStream;
import labb6.event.StoreEvent;
/**
 * En klass som representerar tillstandet hos en butik.
 * Innehaller attribut och metoder for att hantera butikens tillstand, inklusive ankomsttider, kotider och betalningstider for kunder.
 * @author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
 */
public class StoreState extends State {
	
	private boolean isOpen;
	private int maxCustomers;
	private ExponentialRandomStream arrivalTime;
	private UniformRandomStream shoppingTime;
	private UniformRandomStream payTime;
	private ArrayList<Customer> CustomerIDs = new ArrayList<Customer>();
	private double closingTime;
	private double minPayTime;
	private double maxPayTime;
	private double minShoppingTime;
	private double maxShoppingTime;
	private int missedCustomers;
	private int finishedCustomers;
	private int totalCurrentCustomers;
	private int freeRegisters;
	private int totalRegisters;
	private double freeRegisterTime;
	private double lineTime;
	private double lambda; // snittantal kunder som anlander per tidsenhet
	private FIFO line = new FIFO();
	private int CurrentCustomerID;
	private long seed;
	private int totalPeopleInLine;
	private double LastTime;
	


	


	public StoreState(int maxCustomers, double lambda, double minPayTime, double maxPayTime, 
					double minShoppingTime, double maxShoppingTime, double closingTime, int totalRegisters,
					long seed) {
		
		this.isOpen = true;
		this.maxCustomers = maxCustomers;
		this.minPayTime = minPayTime;
		this.maxPayTime = maxPayTime;
		this.minShoppingTime = minShoppingTime;
		this.maxShoppingTime = maxShoppingTime;
		this.closingTime = closingTime;
		this.lambda = lambda;
		this.totalRegisters = totalRegisters;
		this.freeRegisters = totalRegisters; // vid borjan ar alla snabbkassor lediga.
		this.arrivalTime = new ExponentialRandomStream(lambda,seed);
		this.payTime = new UniformRandomStream(minPayTime, maxPayTime, seed);
		this.shoppingTime = new UniformRandomStream(minShoppingTime,maxShoppingTime,seed);
		this.seed = seed;
		
		this.finishedCustomers = 0;
		this.totalCurrentCustomers = 0;
		this.freeRegisterTime = 0;
		this.missedCustomers = 0;
	}


	// -------------- "get" & "set" methods --------------

	public double getLastTime() {
		return LastTime;
	}
	
	public void setLastTime(double lastTime) {
		LastTime = lastTime;
	}
	
	public int getTotalPeopleInLine() {
		return totalPeopleInLine;
	}
	
	public boolean isOpen() {
		return isOpen;
	}


	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}


	public int getMaxCustomers() {
		return maxCustomers;
	}


	public void setMaxCustomers(int maxCustomers) {
		this.maxCustomers = maxCustomers;
	}


	public ExponentialRandomStream getArrivalTime() {
		return arrivalTime;
	}
	
	
	public void setArrivalTime(ExponentialRandomStream arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public UniformRandomStream getShoppingTime() {
		return shoppingTime;
	}


	public void setShoppingTime(UniformRandomStream shoppingTime) {
		this.shoppingTime = shoppingTime;
	}


	public UniformRandomStream getPayTime() {
		return payTime;
	}


	public void setPayTime(UniformRandomStream payTime) {
		this.payTime = payTime;
	}


	public ArrayList<Customer> getCustomerIDs() {
		return CustomerIDs;
	}


	public void setCustomerIDs(ArrayList<Customer> customerIDs) {
		CustomerIDs = customerIDs;
	}


	public double getClosingTime() {
		return closingTime;
	}


	public void setClosingTime(double closingTime) {
		this.closingTime = closingTime;
	}


	public double getMinPayTime() {
		return minPayTime;
	}


	public void setMinPayTime(double minPayTime) {
		this.minPayTime = minPayTime;
	}


	public double getMaxPayTime() {
		return maxPayTime;
	}


	public void setMaxPayTime(double maxPayTime) {
		this.maxPayTime = maxPayTime;
	}


	public double getMinShoppingTime() {
		return minShoppingTime;
	}


	public void setMinShoppingTime(double minShoppingTime) {
		this.minShoppingTime = minShoppingTime;
	}


	public double getMaxShoppingTime() {
		return maxShoppingTime;
	}


	public void setMaxShoppingTime(double maxShoppingTime) {
		this.maxShoppingTime = maxShoppingTime;
	}


	public int getMissedCustomers() {
		return missedCustomers;
	}


	public void setMissedCustomers(int missedCustomers) {
		this.missedCustomers = missedCustomers;
	}


	public int getFinishedCustomers() {
		return finishedCustomers;
	}


	public void setFinishedCustomers(int finishedCustomers) {
		this.finishedCustomers = finishedCustomers;
	}


	public int getTotalCurrentCustomers() {
		return totalCurrentCustomers;
	}


	public void setTotalCurrentCustomers(int totalCurrentCustomers) {
		this.totalCurrentCustomers = totalCurrentCustomers;
	}


	public int getFreeRegisters() {
		return freeRegisters;
	}


	public void setFreeRegisters(int freeRegisters) {
		this.freeRegisters = freeRegisters;
	}


	public int getTotalRegisters() {
		return totalRegisters;
	}


	public void setTotalRegisters(int totalRegisters) {
		this.totalRegisters = totalRegisters;
	}


	public double getFreeRegisterTime() {
		return freeRegisterTime;
	}


	public void setFreeRegisterTime(double freeRegisterTime) {
		this.freeRegisterTime = freeRegisterTime;
	}


	public double getLambda() {
		return lambda;
	}


	public void setLambda(double lambda) {
		this.lambda = lambda;
	}


	public FIFO getLine() {
		return line;
	}


	public void setLine(FIFO line) {
		this.line = line;
	}
	
	public int getCurrentCustomerID() {
		return CurrentCustomerID;
	}
	
	public void getCurrentCustomerID(int CustomerID) {
		this.CurrentCustomerID = CustomerID;
	}
	
	public double getLineTime() {
		return lineTime;
	}

	public void setLineTime(double lineTime) {
		this.lineTime = lineTime;
	}
	
	public long getSeed() {
		return seed;
	}

	
	// -------------- Increase/Decrease methods --------------
	
	public void increaseTotalCurrentCustomers() {
		totalCurrentCustomers++;
	}
	
	public void decreaseTotalCurrentCustomers() {
		totalCurrentCustomers--;
	}
	
	public void increaseFinishedCustomers() {
		finishedCustomers++;
	}
	
	public void increaseMissedCustomers() {
		missedCustomers++;
	}
	
	public void increaseFreeRegisterTime(double time) {
		freeRegisterTime += (time * freeRegisters);
	}
	
	public void increaseLineTime(double time) {
		lineTime += (time * line.size());
	}
	
	public void increaseFreeRegisters() {
		freeRegisters++;
	}
	
	public void decreaseFreeRegisters() {
		freeRegisters--;
	}
	
	public void increaseTotalPeopleInLine() {
		totalPeopleInLine++;
	}
	
	
	// -------------- Other methods --------------
	
	public void addCustomerToLine(int customerID) {
		this.line.add(customerID);
	}
	
	public int dequeueCustomerFromLine() {
		Object firstCustomerID = this.line.first();
		this.line.removeFirst();
		return (int) firstCustomerID;
	}
	
	public int getFirstInLine() {
		return (int) line.first();
	}
	
	public void removeFirstInLine() {
		line.removeFirst();
	}
	
	public boolean isLineEmpty() {
		return line.isEmpty();
	}
	
	public void openStore() {
		this.isOpen = true;
	}
	
	public void closeStore() {
		this.isOpen = false;
	}
	
	public boolean hasSpaceForCustomer() {
		if (this.maxCustomers > this.totalCurrentCustomers) {
			return true;
		}
		return false;
	}
	
	public double getNextArrivalTime() {
		return arrivalTime.next();
	}
	
	public double getNextPickTime() {
		return shoppingTime.next();
	}
	
	public double getNextPayTime() {
		return payTime.next();
	}
	
	public int getCustomerID(int i){
		return CustomerIDs.get(i).getCustomerID();
	}

	public double getCustomerShoppingTime(int i) {
		return CustomerIDs.get(i).getShoppingTime();
	}

	public double getCustomerPayTime(int i) {
		return CustomerIDs.get(i).getPayTime();
	}
	
	public void update(Event event) {
		setChanged();
		notifyObservers(event);
	}


	
	
}
