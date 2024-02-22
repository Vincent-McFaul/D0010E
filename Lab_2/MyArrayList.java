import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

@SuppressWarnings("serial")
public class MyArrayList<E> implements Serializable, Cloneable, Iterable<E>,
		Collection<E>, List<E>, RandomAccess {

    	// ---------------------------------------------------------------

	public static void main(String[] args) {
		MyArrayList<String> strlist = new MyArrayList<String>();
		
		// testa metoder härifrån
		
	}

    	// ---------------------------------------------------------------
    
	private int size;
	private E[] arr;
	
	public MyArrayList(int initialCapacity) {
		if (initialCapacity > -1) {
            this.arr = (E[]) new Object[initialCapacity];
            this.size = 0;
        } 
		else {
            throw new IllegalArgumentException("Illegal Capacity: "+ initialCapacity);
        }
	}

	public MyArrayList() {
		this.arr = (E[]) new Object[10]; //10 is default size.
		this.size = 0;
	}

	// -- 1 --

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
		
	@Override
	public void clear() {
		size = 0; //removes the list elements, to be overwritten.
	}

	// -- 2 --

	public void ensureCapacity(int minCapacity) {
		if (minCapacity > this.arr.length) {
            E[] tempArray = (E[]) new Object[minCapacity];
            for(int i = 0; i < size; i++){ //copies the previous list, for add it gives a new null pos.
            	tempArray[i] = arr[i];
            }
            arr = tempArray;
        	}
        }

	public void trimToSize() { //is not needed to pass test?
		if(size != arr.length)
        {
            E[] trimArr = (E[]) new Object[size];
            for(int i = 0; i < size; i++){
            	trimArr[i] = arr[i];
            }
            arr = trimArr;
        }
	}
    
	// -- 3 --
    
	@Override
	public void add(int index, E element) {
		if(index > size || index <0){
            throw new IndexOutOfBoundsException();
        }
		size = size + 1;
		ensureCapacity(size + 1); //fixes so the list can accomodate elements and new.
        for(int i = index+1; i < arr.length; i++){
            arr[i] = arr[i-1];
        }
        arr[index] = element;
	}

	@Override
	public boolean add(E e) {
		add(size, e);
		return true;
	}

        // -- 4 --
    
	@Override
	public E get(int index) {
		if(index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
	}

	@Override
	public E set(int index, E element) {
		if (index < 0 || index >= size) {
	        throw new IndexOutOfBoundsException();
	    }
		E oldElement = get(index); //stores old element, to return for test.
		arr[index] = element; //replaces element at index pos.
		return oldElement;
	}

	// -- 5 --
	
	private void movePosLeft(int index)
    {
		for (int i = index; i < size-1; i++) {
			arr[i] = arr[i+1];
		}
		arr[size-1] = null; //minus one to compensate counter (+1) vs position
		size = size - 1;
    }
	
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		E removed = get(index); //stores element before removal, to return.
		movePosLeft(index);
		return removed; 
	}

	protected void removeRange(int fromIndex, int toIndex) {
		if (fromIndex < 0 || fromIndex >= size() || toIndex > size() || toIndex < fromIndex) {
			throw new IndexOutOfBoundsException();
		}
		if (fromIndex == toIndex) {
			return;
		}
		for (int i = toIndex-1; i >= fromIndex; i--) {
			movePosLeft(i);
		}
	}

	// -- 6 --

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < size(); i++) {
			if (o == null) {
				if (get(i) == null) { //if pos is empty, return null still.
					return i;
				}
			} 
			else {
				if (o.equals(get(i))) {
					return i;
				}
			}
		}
		return -1;
	}

	@Override
	public boolean remove(Object o) {
		if(indexOf(o) != -1){ //checks if index exists (a return other than -1).
            remove(indexOf(o));
            return true;
        }
        return false;
	}
    
	@Override
	public boolean contains(Object o) {
		if (indexOf(o) != -1){  //same as remove method above, but doesen't remove element.
            return true;
        }
        return false;
	}

	// -- 7 --

	@Override
	public Object clone() {
		MyArrayList<E> clonedArr = new MyArrayList<E>(arr.length);
		clonedArr.arr = arr;
        return clonedArr; // same pointers, hence shallow copy.
	}

	@Override
	public Object[] toArray() {
		Object[] newArr = new Object[size()]; 
		for (int i = 0; i < size(); i++) { //deep copy!
			newArr[i] = arr[i];
		}
		return newArr;
	}

	// --- Rör ej nedanstående kod -----------------------------------

	public MyArrayList(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	private class InternalIterator implements Iterator {
		int current = 0;

		@Override
		public boolean hasNext() {
			return current < size();
		}

		@Override
		public Object next() {
			return get(current++);

		}

	}

	@Override
	public Iterator<E> iterator() {
		return new InternalIterator();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void forEach(Consumer<? super E> action) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Spliterator<E> spliterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeIf(Predicate<? super E> filter) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sort(Comparator<? super E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

}