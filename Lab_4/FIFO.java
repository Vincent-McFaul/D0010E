import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FIFO implements Queue {
	private ArrayList<Object> list = new ArrayList<Object>();
	private int maxSize; //se add-funktionen

	public int size() {
		if (isEmpty()) {
		return 0; }
		
		return list.size();
	}

	public int maxSize() {
		return maxSize;
	}

	public boolean isEmpty() {
		   return list.isEmpty();
	}

	//hämtar första elementet
	public Object first() throws NoSuchElementException {
		if (list.isEmpty()) {
			throw new NoSuchElementException("Tom List");

		} else {
			return list.get(0);
		}
	}

	public boolean equals(Object f) throws ClassCastException {
       	
        if (!(f instanceof FIFO)) {	//exception om objektet INTE är av typen FIFO
            throw new ClassCastException("Olika typer");
        } 
        
        else {
            
            FIFO jämförlist = (FIFO) f; //konverterar typ f
            											
            if (jämförlist.list.size() != list.size()) { // jämför längd
                return false;
            }
            
            if (jämförlist.maxSize != maxSize) {	//jämför maxSize
                return false;
            }

            for (int i = 0; i < list.size(); i++) {
                
                if ((list.get(i) == null || jämförlist.list.get(i) == null)) { // kollar om det finns ett null i listorna
                    if (!(list.get(i) == null && jämförlist.list.get(i) == null)) { //false om båda INTE är null
                        return false;
                    }
                } 
                else if (!(list.get(i).equals(jämförlist.list.get(i)))) { //false om de INTE är lika 
                    return false;
                }
            }
            
        return true; 
        }
    }

	//konverterar element till sträng
	public String toString() {
		String print = ("Queue: ");
		for (int i = 0; i < list.size(); i++) {
			print = print + "(" + String.valueOf(list.get(i)) + ") ";
		}
		return print;
	}
	
	//lägger till ett element i slutet av kön
	public void add(Object items) {
		list.add(items); 
		if (list.size() > maxSize()) { //uppdaterar den maximala storleken om nödvändigt
			maxSize++;
		}
	}

	public void removeFirst() throws NoSuchElementException {
		if (list.isEmpty()) {
			throw new NoSuchElementException("Tom lista");
		}
		list.remove(0);
	}
}