/**
 * @author Pedro Miranda, nº 28289, Turno P5 TP1
 * @author Pedro Estrela, nº 28761, Turno P5 TP1
 *
 * Docente: Bernardo Luís da Silva Ferreira
 */
public class Auction {
	//Constantes relativas ao estado
	private static final int POR_INICIAR = 0;
	private static final int A_DECORRER = 1;
	private static final int TERMINADO = 2;
	//Variaveis
	private String AuctionName;
	private int status;
	private ItemList items;

	public Auction(String auctionName) {
		AuctionName = auctionName;
		status = POR_INICIAR;
		items = new ItemList();
	}
	//Construtor que vai ser chamado para gravar os leiloes no ficheiro
	public Auction(String name, int s, ItemList itms){
		AuctionName = name;
		status = s;
		items = itms;
	}

	public String getAuctionName() {
		return AuctionName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatusAdecorrer() {
		status = A_DECORRER;
	}

	public void setStatusTerminado() {
		status = TERMINADO;
	}

	public void addItem(Item item){
		items.addItem(item);
	}

	public ItemList getItemList() {
		return items;
	}

	public String getItemNamebyIndex (int index){
		return items.getItems()[index].getItemName();
	}

	public String getSellerNamebyIndex (int index){
		return items.getItems()[index].getSellerName();
	}

	public String getBuyerNamebyIndex (int index){
		String name = "";
		if (items.getItems()[index].getBuyer() != null)
			name = items.getItems()[index].getBuyer().getName();
		return name;
	}

	public int getBidPricebyIndex(int index){
		return items.getItems()[index].getHigherBidPrice();
	}

	public double getAverageBidsbyIndex (int index){
		return items.getItems()[index].getAverageBids();
	}
}
