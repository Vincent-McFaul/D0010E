package labb6.simulator;

import labb6.event.EventQueue;
import labb6.simulator.Simulator;
import labb6.view.StoreView;
import labb6.view.View;

import labb6.event.ClosingEvent;
import labb6.event.StartEvent;
import labb6.event.StopEvent;
import labb6.state.State;
import labb6.state.StoreState;
/**
 * Kor en simulering med angivna varden for en butik.
 * @author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
 * 
 * <p> openTime     oppningstid
 * <p> closeTime    stangningstid
 * <p> maxCheckouts maximalt antal kassor
 * <p> maxCustomers maximalt antal kunder
 * <p> P_min        minimal plocktid
 * <p> Pmax         maximal plocktid
 * <p> K_min        minimal shoppingtid
 * <p> Kmax         maximal shoppingtid
 * <p> lambda       ankomsthastighet for kunder
 * <p> seed         givet fro
 */
public class RunSim {
	
	private Simulator simulator;
	
	public RunSim(double openTime, double closeTime, int maxCheckouts, int maxCustomers, double P_min, double Pmax, 
			double K_min, double Kmax, double lambda, long seed) {
		
		
    //skapar en ny instans av StoreState
	
	StoreState simState = new StoreState(maxCustomers, lambda, P_min, Pmax, K_min, Kmax, closeTime, maxCheckouts, seed);
	
	//
	
	EventQueue eventQueue = new EventQueue();
	
	//
	
	State state = new State();
	
	//
	
	Simulator simulator = new Simulator(state, eventQueue);
	
	//
	
	StoreView view = new StoreView(simState);
	
	// 
	
	StartEvent startEvent = new StartEvent(openTime, simState, eventQueue);
	
	//
	
	ClosingEvent closeEvent = new ClosingEvent(closeTime, simState, false, eventQueue);
	
	//
	
	StopEvent stopEvent = new StopEvent(999, simState, eventQueue);
	
	//
	
	eventQueue.addEvent(startEvent);
	
	eventQueue.addEvent(closeEvent);
	
	eventQueue.addEvent(stopEvent);
	
	//
	
	simulator.run();
	}
	
	public static void main(String[] args) {
		//RunSim runSim1 = new RunSim(0, 10.00, 2, 5, 2.0, 3.0, 0.5, 1.0, 1.0, 1234);
		RunSim runSim2 = new RunSim(0, 8.00, 2, 7, 0.35, 0.6, 0.6, 0.9, 3.0, 13);
	}
			
		
	}
