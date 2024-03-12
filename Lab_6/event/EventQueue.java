package labb6.event;

import java.util.ArrayList;
import java.util.Comparator;
/**
 * Representerar en ko for handelser som ska utforas i tidsordning.
 *
 * <p>Denna klass hanterar en lista av handelser och tillhandahaller
 * metoder for att lagga till handelser, hamta nasta handelse, och
 * kontrollera om kon ar tom.
 *
 * @author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
 */
public class EventQueue {

    private ArrayList<Event> events;

    public EventQueue() {
        
        this.events = new ArrayList<Event>();
    }

    // Sorterar event tidsmassigt.
    public void addEvent(Event event) {
    	ArrayList<Event> newEventList = new ArrayList<Event>();
        
        int counter = 0;
        
        for(Event i : events) 
        {
           if(i.getScheduledTime() <= event.getScheduledTime()) 
           {
              newEventList.add(i);
              counter ++;
           }
        }
        
        newEventList.add(event);
        
        for(int i = counter; i < events.size(); i++) 
        {
           if(events.get(i).getScheduledTime() > event.getScheduledTime()) 
           {
              newEventList.add(events.get(i));
           }
        }
        
        events = newEventList;
     }
    
    public ArrayList<Event> getEvents() {
    	return events;
    }

    public Event getNextEvent() {
        Event event = events.get(0);
        events.remove(0);
        return event;
    }

    public boolean isEmpty() {
        return events.isEmpty();
    }
    
}

