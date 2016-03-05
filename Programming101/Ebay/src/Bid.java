/**
 * @author Pedro Miranda, n� 28289, Turno P5 TP1
 * @author Pedro Estrela, n� 28761, Turno P5 TP1
 *
 * Docente: Bernardo Lu�s da Silva Ferreira
 */
public class Bid {

	private int bidprice;
	private Item item;
	private Customer customer;

	public Bid(int bidprice, Item item, Customer customer) {

		this.bidprice = bidprice;
		this.item = item;
		this.customer = customer;
	}

	public int getBidprice() {
		return bidprice;
	}

	public Item getItem() {
		return item;
	}


	public Customer getCustomer() {
		return customer;
	}
}
