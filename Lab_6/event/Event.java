package labb6.event;

import labb6.state.State;
/**
 * Representerar en handelse som paverkar simulatorns tillstand.
 *
 * <p>Denna klass hanterar schemalagd tid, referens till tillstand och om det ar en nodbromshandelse.
 * Handelser schemalaggs och koas for utforande.
 *
 * @author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
 */
public class Event {
    private double scheduledTime; // Utraknad tid da handelsen ska intraffa
    private State state; // Referens till tillstandet for att paverka det
    private boolean isEmergencyStop; // Flagga for att indikera om det ar en nodbroms-handelse
    private EventQueue eventQueue;

    // Konstruktor for en generell handelse
    public Event(double scheduledTime, State state, boolean isEmergencyStop, EventQueue eventQueue) {
        this.scheduledTime = scheduledTime;
        this.state = state;
        this.isEmergencyStop = isEmergencyStop;
        this.eventQueue = eventQueue;
    }

    // Metod som utfor handelsens effekt pa tillstandet
    public void performEffect() {
        if (isEmergencyStop) {
            state.emergencyStop();
        }
    }

    // Getter for den utraknade tiden
    public double getScheduledTime() {
        return scheduledTime;
    }
    
    public State getState() {
    	return state;
    }
    
    public EventQueue getEventQueue() {
    	return this.eventQueue;
    }
    
}
