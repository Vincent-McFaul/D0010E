package labb6.simulator;
/**
 * Automatiserar simuleringar for att optimera antalet kassor i en butik.
 *
 * <p>Denna klass innehaller metoder for att utfora simuleringar med varierande
 * antal kassor och returnera det optimala antalet kassor for att minimera antalet
 * missade kunder.
 *
 * @author William Hagg, Axel Nordelof, Vincent McFaul & Herman Ghafouri
 */
import java.util.Random;

//import labb6.simulator.*;
import labb6.event.EventQueue;
import labb6.event.ClosingEvent;
import labb6.event.StartEvent;
import labb6.event.StopEvent;
import labb6.state.State;
import labb6.state.StoreState;

public class Optimize {

	
/**@author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
 *
 *Klass som automatiskt kor simuleringar
 * 
 */
	// konstruktor
	
	public Optimize(double openTime, double closeTime, int maxCustomers, double P_min, double Pmax, 
			double K_min, double Kmax, double lambda, long seed) {	
	}
	
	public static void main(String[] args) {
		
		//Optimize opt = new Optimize(0, 10.00, 5, 2.0, 3.0, 0.5, 1.0, 1, 1234);
		
		//int opt = findMinCheckOutForSeed(0, 10.00, 5, 2.0, 3.0, 0.5, 1.0, 1, 1234);
		int opt = findMinCheckOutForSeed(0, 8.00, 7, 0.5, 1.0, 2.0, 3.0, 2, 1234);
		
		System.out.println("Optimal register amount: " + opt);
		
	}
	
/*
 * Metod som returnar simState, inga utskrifter 
 * @param openingTime opening Time
 * @param closingTime closing time
 * @param Checkouts total checkouts
 * @param maxCustomers maximum allowed customers
 * @param P_min minimum pick time
 * @param Pmax maximum pick time
 * @param K_min minimum pay time
 * @param Kmax maximum pay time
 * @param lambda arrival rate for costumers
 * @param seed given seed
 */
	
	public static StoreState DefaultRun(double openTime, double closeTime, int maxCheckouts, int maxCustomers, double P_min, double Pmax, 
			double K_min, double Kmax, double lambda, long seed) {
		
		StoreState simState = new StoreState(maxCustomers, lambda, P_min, Pmax, K_min, Kmax, closeTime, maxCheckouts, seed);

		EventQueue eventQueue = new EventQueue();
		
		Simulator simulator = new Simulator(simState, eventQueue);

		
		StartEvent startEvent = new StartEvent(openTime, simState, eventQueue);
		
		ClosingEvent closeEvent = new ClosingEvent(closeTime, simState, false, eventQueue);
		
		StopEvent stopEvent = new StopEvent(999, simState, eventQueue);
	
		
		eventQueue.addEvent(startEvent);
		
		eventQueue.addEvent(closeEvent);
		
		eventQueue.addEvent(stopEvent);
		
		simulator.run();
		
		return simState;
	}
	
	
	
	/*
	 * Metod som returnerar max antalet kunder med minst antal kassor
	 * @param openingTime opening Time
	 * @param closingTime closing time
	 * @param maxCustomers maximum allowed customers
	 * @param P_min minimum pick time
	 * @param Pmax maximum pick time
	 * @param K_min minimum pay time
	 * @param Kmax maximum pay time
	 * @param lambda arrival rate for costumers
	 * @param seed given seed
	 */
	public static int minCheckouts(double openTime, double closeTime, int maxCustomers, double P_min, double Pmax, 
			double K_min, double Kmax, double lambda, long seed) {
		
		StoreState state;
		int missed = 99999;
		int bestRegisterAmount = 0;

		for (int registers = maxCustomers; registers >= 1; registers--) {

			state = DefaultRun(openTime, closeTime, registers, maxCustomers, P_min, Pmax, K_min, Kmax, lambda ,seed);
			
			if (state.getMissedCustomers() > missed) {
				break;
			}
			
			missed = state.getMissedCustomers();
			bestRegisterAmount = registers;
		}

		// printStart();
		// System.out.println(" (" + missed + "): " + bestRegisterAmount);
		return bestRegisterAmount;	
	}
	
	/*
	 * metod som simulerar tills 100 simuleringar har korts utan att nagra parametrar forandras
	 * @param openingTime opening Time
	 * @param closingTime closing time
	 * @param maxCustomers maximum allowed customers
	 * @param P_min minimum pick time
	 * @param Pmax maximum pick time
	 * @param K_min minimum pay time
	 * @param Kmax maximum pay time
	 * @param lambda arrival rate for costumers
	 * @param f seed
	 * 
	 */
	public static int findMinCheckOutForSeed(double openTime, double closeTime, int maxCustomers, double P_min, double Pmax, 
			double K_min, double Kmax, double lambda, long seed) {
		
	      Random rand = new Random();
	      int counter = 0;
	      int bestRegisterAmount = 0;
	      
	      while (counter < 100) {
	         int newAmountOfRegisters = minCheckouts(openTime, closeTime, maxCustomers, P_min, Pmax, K_min, Kmax, lambda ,rand.nextLong());
	         
	         if(bestRegisterAmount != Math.max(bestRegisterAmount, newAmountOfRegisters)) {
	            counter = 0;
	         }
	         else {
	            counter += 1;
	         }
	         
	         bestRegisterAmount = Math.max(bestRegisterAmount, newAmountOfRegisters);
	      }
	      
	      return bestRegisterAmount;
	}
}

