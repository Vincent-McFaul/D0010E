package labb6.simulator;

import labb6.event.EventQueue;

import labb6.state.State;

/**
 * @author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
*
* Generell klass som kor simuleringen
* 
*/


public class Simulator {
	private State state; 			// instansvariabler
	private EventQueue eventQueue;	
	
	
	public Simulator(State state, EventQueue queue) { // konstruktor for state queue parametrar
		this.state = state;
		this.eventQueue = queue;
	}
	
	
	
	public void run() {
		while (!eventQueue.isEmpty()) {
			if (state.getSimulationStop()) {
				break;
			}
			eventQueue.getNextEvent().performEffect();
		}
	}
	
	
}
