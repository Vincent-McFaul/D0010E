package labb6.state;
/**
 * En klass som representerar en butik.
 * Innehåller attribut och metoder för att hantera antalet kassor i butiken.
 * @author William Hägg, Axel Nordelöf, Vincent McFaul and Herman Ghafouri
 */
public class Store {
	
	private int totalRegisters;
	private int freeRegisters;
	
	public Store(int registers) {
		totalRegisters = registers;
		freeRegisters = registers;
	}
	
	public void increaseFreeRegisters() {
		freeRegisters++;
	}
	
	public void decreaseFreeRegisters() {
		freeRegisters--;
	}
	
}
