/**
 * @author Pedro Miranda, nº 28289, Turno P5 TP1
 * @author Pedro Estrela, nº 28761, Turno P5 TP1
 *
 * Docente: Bernardo Luís da Silva Ferreira
 */
public class Item {

	private String ItemName;
	private String SellerName;
	private String ActionName;
	private int startprice;
	private BidList bids;

	Item (String aname, String iname, String sname, int sprice){
		ItemName = iname;
		SellerName = sname;
		startprice = sprice;
		ActionName = aname;
		bids = new BidList();
	}

	public String getActionName(){
		return ActionName;
	}

	public String getItemName() {
		return ItemName;
	}

	public String getSellerName() {
		return SellerName;
	}

	public int getStartprice() {
		return startprice;
	}

	public int getHigherBidPrice (){//devolve o valor da proposta mais alta feita ao item
		return bids.getActualBid();
	}

	public void makeBidToItem(Bid bid){//actualiza a proposta feita ao item
		bids.PlaceBid(bid);
	}

	public BidList getBids() {//devolve todas as propostas feitas ao item
		return bids;
	}

	public Customer getBuyer(){//devolve o cliente comprador
		return bids.getLastBid();
	}

	public double getAverageBids() {//devolve a media das propostas feitas ao item
		return bids.getAvaregeBids();
	}
}
