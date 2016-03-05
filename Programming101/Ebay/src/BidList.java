/**
 * @author Pedro Miranda, nº 28289, Turno P5 TP1
 * @author Pedro Estrela, nº 28761, Turno P5 TP1
 *
 * Docente: Bernardo Luís da Silva Ferreira
 */
public class BidList {

	private static final int MAX_BIDS = 20;

	private Bid[] bids;
	private int numberofbids;
	private int higherBidPrice;

	BidList(){
		bids = new Bid[MAX_BIDS];
		numberofbids = 0;
		higherBidPrice = 0;
	}

	private int searchBid (Bid bid){
		int i = 0;
		boolean find = false;
		while ((i < numberofbids) && (!find)) {
			if (bids[i].getItem().equals(bid.getItem()))
				find = true;
			else i++;
		}

		if (find)
			return i;
		else
			return -1;
	}

	public void PlaceBid (Bid bid){//Adicionar nova proposta

		if (searchBid(bid) == -1){
			if (numberofbids == bids.length){
				Bid tmp[] = new Bid[2 * bids.length];
				int i = 0;
				while (i < numberofbids){
					tmp[i] = bids[i];
					i++;
				}
				bids = tmp;
			}
		}
		bids[numberofbids++] = bid;

		//devolver ao cliente o seu dinheiro
		if(numberofbids > 1) {
			Bid oldbid = bids[numberofbids - 2];//proposta anterior
			int spendcredit = oldbid.getBidprice();//valor dela
			oldbid.getCustomer().creditCustomer(spendcredit);//actualizar o credito do cliente
		}

		if(bid.getBidprice() > higherBidPrice)//actualizar a proposta mais alta
			higherBidPrice = bid.getBidprice();

		if (bid.getBidprice() > bid.getCustomer().getHighBid())//actualizar a proposta mais alta no cliente
			bid.getCustomer().setHightBid(bid);

	}

	public double getAvaregeBids (){
		int sum = 0;
		double average = 0.0;
		if(numberofbids != 0) {
			for (int i=0; i<numberofbids; i++)
				sum += bids[i].getBidprice();
			average = sum/numberofbids;
		}
		else average = 0;
		return average;
	}

	public int getActualBid(){
		return higherBidPrice;
	}

	public Bid BidExists(int value){//verifica se a proposta existe dado o seu valor
		Bid bid = null;
		for (int i = 0; i < numberofbids; i++) {
			if (bids[i].getBidprice() == value)
				bid = bids[i];
		}
		return bid;
	}

	public Bid[] getBidList(){
		return bids;
	}

	public int getNumberOfBids(){
		return numberofbids;
	}

	public Customer getLastBid() {
		Customer resp = null;
		if(numberofbids > 0)
			resp = bids[numberofbids -1].getCustomer();//devolve a ultima proposta feita pelo cliente

		return resp;
	}
}
