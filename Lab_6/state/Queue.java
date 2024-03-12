package labb6.state;
/**
 * Ett gränssnitt som representerar en kö.
 * Innehåller metoder för att hantera en kö, såsom att lägga till och ta bort element, samt kontrollera köns storlek och om den är tom.
 * @author William Hägg, Axel Nordelöf, Vincent McFaul and Herman Ghafouri
 */
public abstract interface Queue {
	  
	  // Method descriptor #4 (Ljava/lang/Object;)V
	  public abstract void add(java.lang.Object arg0);
	  
	  // Method descriptor #6 ()V
	  public abstract void removeFirst() throws java.util.NoSuchElementException;
	  
	  // Method descriptor #10 ()Ljava/lang/Object;
	  public abstract java.lang.Object first() throws java.util.NoSuchElementException;
	  
	  // Method descriptor #12 ()I
	  public abstract int maxSize();
	  
	  // Method descriptor #14 ()Z
	  public abstract boolean isEmpty();
	  
	  // Method descriptor #12 ()I
	  public abstract int size();
	  
	  // Method descriptor #17 ()Ljava/lang/String;
	  public abstract java.lang.String toString();
	  
	  // Method descriptor #19 (Ljava/lang/Object;)Z
	  public abstract boolean equals(java.lang.Object arg0);
	  
	  // Method descriptor #12 ()I
	  public abstract int hashCode();
	}