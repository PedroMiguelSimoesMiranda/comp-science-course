/**
 * @author Pedro Miranda, nº 28289, Turno P5 TP1
 * @author Pedro Estrela, nº 28761, Turno P5 TP1
 *
 * Docente: Bernardo Luís da Silva Ferreira
 */
public class AuctionList {

	private Auction[] auctions;
	private static final int MAX_AUCTIONS = 20;
	private int numberofauctions;

	AuctionList(){
		auctions = new Auction[MAX_AUCTIONS];
		numberofauctions = 0;
	}

	private int searchAuctionIndex(String name){
		int i = 0;
		boolean find = false;
		while ((i<numberofauctions)&&(!find)) {
			if (auctions[i].getAuctionName().compareTo(name) == 0)
				find = true;
			else
				i++;
		}
		if (find)
			return i;
		else
			return -1;
	}

	public Auction AuctionExists(String name){//Verifica se o objecto leilao existe dado o nome dele
		Auction result = null;
		for (int i = 0; i < numberofauctions; i++) {
			if (auctions[i].getAuctionName().equals(name))
				result = auctions[i];
		}
		return result;
	}

	public void createAuction (Auction auction){//Adiciona um novo leilao
		if (searchAuctionIndex(auction.getAuctionName()) == -1){
			if(numberofauctions == auctions.length){
				Auction tmp[] = new Auction[2 * auctions.length];
				int i = 0;
				while (i < numberofauctions ){
					tmp[i] = auctions[i];
					i++;
				}
				auctions = tmp;
			}
			auctions[numberofauctions++] = auction;
		}
	}

	public int getNrOfAuctionsNotClose (){//Devolve o numero de leiloes nao fechados
		return AuctionsNotClose().length;
	}

	public Auction[] AuctionsNotClose() {//devolve o vector com os items nao fechados

		int index = 1;
		Auction[] result = new Auction[numberofauctions];

		for (int i = 0; i < result.length; i++) {
			if ((auctions[i].getStatus() != 0))//verifica o status do leilao
				result[index++] = auctions[i];//e adiciona-o ao vector
		}
		System.out.println("HERE " + result.length);
		return result;
	}
}
