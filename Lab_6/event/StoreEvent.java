package labb6.event;

import labb6.state.StoreState;
/**
 * Representerar en generell butikshandelse.
 *
 * <p>Den har klassen ar en abstrakt representation av handelser som
 * intraffar i en butikssimulering. Det kan vara olika typer av handelser
 * som ankomst av kunder, stangning av butiken, betalning av kunder osv.
 *
 * <p>Den har klassen arver fran `Event` och implementerar metoden
 * `performEffect()` for att hantera effekterna av butikshandelser.
 *
 * <p>Klassen kan ocksa innehalla specifika egenskaper och metoder
 * for hantering av butikshandelser.
 * 
 * @author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
 */
public class StoreEvent extends Event {
    // Lagg till specifika egenskaper och metoder for butikshandelser har

    public StoreEvent(double scheduledTime, StoreState state, boolean isEmergencyStop, EventQueue eventQueue) {
        super(scheduledTime, state, isEmergencyStop, eventQueue);
        // Konstruktoren for StoreEvent kan ha ytterligare egenskaper och parametrar
    }

    @Override
    public void performEffect() {
        // overrid performEffect for butikshandelser
        super.performEffect(); // Anropa aven den generella performEffect-metoden fran Event
        // Lagg till specifika atgarder for butikshandelser
    }
    /*
    public int dequeueCustomer() {
        if (!getStoreState().isLineEmpty()) {
            return getStoreState().dequeueCustomerFromLine();// Antag att 'cashierQueue' ar en Queue som haller StoreEvent-objekt.
        } else {
            // Returnera null eller hantera det pa annat satt om kon ar tom.
            return;
        }
    }
    */
    public StoreState getStoreState() {
        return (StoreState) getState();
    }
    
}
