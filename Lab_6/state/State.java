package labb6.state;
/**
 * En klass som representerar tillståndet hos en simulering.
 * Innehåller attribut och metoder för att hantera tiden och stoppa simuleringen vid behov.
 * @author William Hägg, Axel Nordelöf, Vincent McFaul and Herman Ghafouri
 */
@SuppressWarnings("deprecation")

public class State extends java.util.Observable {
	private double time = 0;
	private boolean simulationStop = false;
	
	public State() {
		
	}
	
	// -------------- "get" methods --------------
	public double getTime() {
		return this.time;
	}
	
	public boolean getSimulationStop() {
		return this.simulationStop;
	}
	
	// -------------- other methods --------------
	public void emergencyStop() {
		this.simulationStop = true;
	}
	
	public void deactivateEmergencyStop() {
		this.simulationStop = false;
	}  
	
	public void setTime(double newTime) {
		this.time = newTime;
	}
	
}
