/**
 * @author Pedro Miranda, nº 28289, Turno P5 TP1
 * @author Pedro Estrela, nº 28761, Turno P5 TP1
 *
 * Docente: Bernardo Luís da Silva Ferreira
 */
public class Customer {

	private static final int DEFAULT_REP = 100;
	private static final int PEN_REP = 10;

	private String nameCustomer;
	private int money;
	private int rep;
	private int HighBid;

	Customer (String nCustomer, int m){
		nameCustomer = nCustomer;
		money = m;
		HighBid = 0;
		rep = DEFAULT_REP;
	}

	Customer (String nCustomer, int m, int r, int hbid){
		nameCustomer = nCustomer;
		money = m;
		rep = r;
		HighBid = hbid;
	}

	public String getName(){
		return nameCustomer;
	}

	public int getMoney(){
		return money;
	}

	public int getReputation(){
		return rep;
	}

	public void creditCustomer (int credit){
		money += credit;
	}

	public void subtractMoney (int credit){
		money -= credit;
	}

	public void penReputation (){
		rep -= PEN_REP;
	}

	public int getHighBid(){//devolve o valor da proposta mais alta feita pelo cliente
		return HighBid;
	}

	public void setHightBid(Bid bid){//actiliza a proposta mais alta feita pelo cliente
		HighBid = bid.getBidprice();
	}
}
