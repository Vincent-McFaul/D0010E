package labb6.view;

import java.awt.EventQueue;
import java.util.Observable;

import labb6.event.ArrivalEvent;
import labb6.event.ClosingEvent;
import labb6.event.Event;
import labb6.event.PaymentEvent;
import labb6.event.PickItemEvent;
import labb6.event.StartEvent;
import labb6.event.StopEvent;
import labb6.state.StoreState;
/**
 * Visningsklass för att hantera och skriva ut information om butikens tillstånd och händelser.
 *
 * <p>Denna klass hanterar utskrift av information om butikens tillstånd och olika händelser som
 * sker under simuleringen. Den uppdateras när det sker förändringar i butikens tillstånd.
 * Klassen används för att visa resultat och statistik efter avslutad simulering.
 * 
 * <p>Klassen ärver från den abstrakta klassen `View`.
 * 
 * <p>Observera att denna klass använder systemutskrift för att visa informationen.
 * 
 * @author William Hägg, Axel Nordelöf, Vincent McFaul and Herman Ghafouri
 */
public class StoreView extends View {
	
	private StoreState state;
    private EventQueue eventQueue;
    
    public  StoreView(StoreState state){
        super();
        this.state = state;
        
        state.addObserver(this);
    }

    public void printStart()
    {
        System.out.println("PARAMETRAR");
        System.out.println("==========");
        System.out.println("Antal kassor, N...........: " + state.getTotalRegisters());
        System.out.println("Max som ryms, M...........: " + state.getMaxCustomers());
        System.out.println("Ankomsthastighet, lambda..: " + state.getLambda());
        System.out.println("Plocktider, [P_min..P_max]: " + "[" + state.getMinShoppingTime() + ".." +state.getMaxShoppingTime()+"]");
        System.out.println("Betaltider, [K_min..K_max]: " + "[" + state.getMinPayTime() + ".." +state.getMaxPayTime()+"]");
        System.out.println("Frö, f....................: " + state.getSeed());
        System.out.println("FÖRLOPP");
        System.out.println("=======");
        System.out.println("  Tid    Händelse    Kund    ?    led    ledT    I    $    :-(    köat    köT    köar    [Kassakö..]");
        System.out.println("0.00     Start");
    }

    @Override
    public void update(Observable o, Object arg)
    {
    	Event event = (Event) arg;
    	
    	
    	        
        //tidtagare
 		if (arg instanceof Event) {
			if (!(arg instanceof StopEvent)) {
 			System.out.print(Math.round(event.getScheduledTime() * 100.0) / 100.0 + "\t");
			}
 		}
 		
 		
 		if (event instanceof StartEvent) {
 			printStart();
 		}
 		
 		if(event instanceof ClosingEvent){
        	ClosingEvent CloseEvent = (ClosingEvent) arg;
 			System.out.print("Stänger\t\t" + "---  ");
 			System.out.print((CloseEvent.getStoreState().isOpen() ? "Ö" : "S" ) + "      ");
 			System.out.print(CloseEvent.getStoreState().getFreeRegisters() + "\t");
 			System.out.print(String.valueOf(String.format("%.2f", CloseEvent.getStoreState().getFreeRegisterTime())) + "    ");
 			System.out.print(CloseEvent.getStoreState().getTotalCurrentCustomers() + "    ");
 			System.out.print(CloseEvent.getStoreState().getFinishedCustomers() + "      ");
 			System.out.print(CloseEvent.getStoreState().getMissedCustomers() + "     ");
 			System.out.print(CloseEvent.getStoreState().getTotalPeopleInLine() + "\t");
 			System.out.print(String.valueOf(String.format("%.2f", CloseEvent.getStoreState().getLineTime())) + "    ");
 			System.out.print(CloseEvent.getStoreState().getLine().size() + "      ");
 			System.out.print(CloseEvent.getStoreState().getLine().toString() + "\t");
 			System.out.print("\n");
        }
 		
 		//typ av ändelse o vilken kund
 		if (arg instanceof ArrivalEvent) {
 			ArrivalEvent ArrEvent = (ArrivalEvent) arg;
 			System.out.print("Ankomst\t\t" + ArrEvent.getID() + "    ");
 			System.out.print((ArrEvent.getStoreState().isOpen() ? "Ö" : "S" ) + "      ");
 			System.out.print(ArrEvent.getStoreState().getFreeRegisters() + "\t");
 			System.out.print(String.valueOf(String.format("%.2f", ArrEvent.getStoreState().getFreeRegisterTime())) + "    ");
 			System.out.print(ArrEvent.getStoreState().getTotalCurrentCustomers() + "    ");
 			System.out.print(ArrEvent.getStoreState().getFinishedCustomers() + "      ");
 			System.out.print(ArrEvent.getStoreState().getMissedCustomers() + "     ");
 			System.out.print(ArrEvent.getStoreState().getTotalPeopleInLine() + "\t");
 			System.out.print(String.valueOf(String.format("%.2f", ArrEvent.getStoreState().getLineTime())) + "    ");
 			System.out.print(ArrEvent.getStoreState().getLine().size() + "      ");
 			System.out.print(ArrEvent.getStoreState().getLine().toString() + "\t");
 			System.out.print("\n");
 		} 
 		else if (arg instanceof PaymentEvent) {
 			PaymentEvent PayEvent = (PaymentEvent) arg;
 			System.out.print("Betalning\t" + PayEvent.getID() + "    ");
 			System.out.print((PayEvent.getStoreState().isOpen() ? "Ö" : "S" ) + "      ");
 			System.out.print(PayEvent.getStoreState().getFreeRegisters() + "\t");
 			System.out.print(String.valueOf(String.format("%.2f", PayEvent.getStoreState().getFreeRegisterTime())) + "    ");
 			System.out.print(PayEvent.getStoreState().getTotalCurrentCustomers() + "    ");
 			System.out.print(PayEvent.getStoreState().getFinishedCustomers() + "      ");
 			System.out.print(PayEvent.getStoreState().getMissedCustomers() + "     ");
 			System.out.print(PayEvent.getStoreState().getTotalPeopleInLine() + "\t");
 			System.out.print(String.valueOf(String.format("%.2f", PayEvent.getStoreState().getLineTime())) + "    ");
 			System.out.print(PayEvent.getStoreState().getLine().size() + "      ");
 			System.out.print(PayEvent.getStoreState().getLine().toString() + "");
 			System.out.print("\n");
 		} 
 		else if (arg instanceof PickItemEvent) {
 			PickItemEvent PickEvent = (PickItemEvent) arg;
 			System.out.print("Plock\t\t" + PickEvent.getID() + "    ");
 			System.out.print((PickEvent.getStoreState().isOpen() ? "Ö" : "S" ) + "      ");
 			System.out.print(PickEvent.getStoreState().getFreeRegisters() + "\t");
 			System.out.print(String.valueOf(String.format("%.2f", PickEvent.getStoreState().getFreeRegisterTime())) + "    ");
 			System.out.print(PickEvent.getStoreState().getTotalCurrentCustomers() + "    ");
 			System.out.print(PickEvent.getStoreState().getFinishedCustomers() + "      ");
 			System.out.print(PickEvent.getStoreState().getMissedCustomers() + "     ");
 			System.out.print(PickEvent.getStoreState().getTotalPeopleInLine() + "\t");
 			System.out.print(String.valueOf(String.format("%.2f", PickEvent.getStoreState().getLineTime())) + "    ");
 			System.out.print(PickEvent.getStoreState().getLine().size() + "      ");
 			System.out.print(PickEvent.getStoreState().getLine().toString() + "");
 			System.out.print("\n");
 		} 
 		
 		
 		//stop
        if(event instanceof StopEvent){
        	System.out.println(String.valueOf(String.format("%.2f", state.getTime())) + "  Stop");
        	printEnd();
        }

    }

    public void printEnd()
    {
        System.out.println("RESULTAT");
        System.out.println("========");
        System.out.println("1) Av " + (state.getFinishedCustomers()+state.getMissedCustomers()) + " kunder handlade " + state.getFinishedCustomers() + " medan " + state.getMissedCustomers() + " missades.");
        System.out.println("2) Total tid " + state.getTotalRegisters() + " kassor varit lediga: " + String.valueOf(String.format("%.2f", state.getFreeRegisterTime())) + " te.");
        double averageFreeTime = state.getFreeRegisterTime() / state.getTotalRegisters();
        double percentFreeTime = averageFreeTime * 100 / state.getLastTime();
        System.out.println("Genomsnittlig ledig kassatid: " + String.valueOf(String.format("%.2f", averageFreeTime) + " te.") + " (dvs " + String.valueOf(String.format("%.2f", percentFreeTime) + "% te.") + " av tiden från öppning tills sista kunden\n" + "betalat).");
        double averageTimeQueued = state.getLineTime() / state.getTotalPeopleInLine();
        System.out.println("3) Total tid " + state.getTotalPeopleInLine() + " kunder tvingats köa: " + String.valueOf(String.format("%.2f", state.getLineTime())) + " te.");
        System.out.println("Genomsnittlig kötid: " + String.valueOf(String.format("%.2f", averageTimeQueued)) + " te.");
    }
}
