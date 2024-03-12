package labb6.state;
/**
 * Representerar en kund med unikt kund-ID och tidsatgang for shopping och betalning.
 *
 * <p>Denna klass hanterar information om kunder, inklusive deras identifikationsnummer,
 * tidsatgang for shopping och betalning.
 *
 * @author William Hagg, Axel Nordelof, Vincent McFaul and Herman Ghafouri
 */
public class Customer {
	
	private int customerID;
	private double shoppingTime;
	private double payTime;
	
	public Customer(int customerID, double shoppingTime, double payTime, boolean storeIsFull) {
		this.customerID = customerID;
		if (!storeIsFull) {
			// Far ingen tid i affaren om den ar full
			this.shoppingTime = shoppingTime;
			this.payTime = payTime;
		}
	}

	public int getCustomerID() {
		return customerID;
	}

	public double getShoppingTime() {
		return shoppingTime;
	}

	public double getPayTime() {
		return payTime;
	}

}
