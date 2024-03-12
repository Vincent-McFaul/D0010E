package labb6.state;
import java.util.ArrayList;
import java.util.NoSuchElementException;
/**
 * En enkel FIFO (First In First Out) köimplementation.
 * Använder en ArrayList för att lagra element.
 * 
 * @author William Hägg, Axel Nordelöf, Vincent McFaul and Herman Ghafouri
 */
public class FIFO implements Queue {

	private int maxLenght = 0;
	private ArrayList<Object> queue = new ArrayList<Object>();

	@Override
	public void add(Object arg0) {
		this.queue.add(arg0);
		if (this.maxLenght < this.queue.size()) {
			this.maxLenght = this.queue.size();
		}
	}

	@Override
	public Object first() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return this.queue.get(0);
	}

	@Override
	public boolean isEmpty() {
		return this.queue.size() == 0;
	}

	@Override
	public int maxSize() {
		return this.maxLenght;
	}

	@Override
	public void removeFirst() throws NoSuchElementException {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		this.queue.remove(0);
	}

	@Override
	public int size() {
		return this.queue.size();
	}

	public String toString() {
		String str = "Queue: ";
		for (Object elem : queue) {
			str += "(" + String.valueOf(elem) + ") ";
		}
		return str;
	}

	public boolean equals(Object f) throws ClassCastException {
		if (!(this.getClass() == f.getClass())) {
			throw new ClassCastException();
		}
		FIFO FIFO_f = (FIFO) f;
		boolean equal = true; // set true for empty cases
		if (FIFO_f.size() == queue.size()) {
			for (int i = 0; i < queue.size(); i++) {
				if ((FIFO_f.queue.get(i) != null && this.queue.get(i) == null)
						|| (FIFO_f.queue.get(i) == null && this.queue.get(i) != null)) {
					equal = false;
					break;
				}
				if (FIFO_f.queue.get(i) == null && this.queue.get(i) == null) {
					equal = true;
					continue;
				}
				if (this.queue.get(i).equals(FIFO_f.queue.get(i))); {
					equal = true;
					continue;
				}
			}
			return equal;
		}
		return false;
	}

}