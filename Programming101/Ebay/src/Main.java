/**
 * @author Pedro Miranda, nº 28289, Turno P5 TP1
 * @author Pedro Estrela, nº 28761, Turno P5 TP1
 *
 * Docente: Bernardo Luís da Silva Ferreira
 */
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	private static final String  ADDCUSTOMER = "AC";
	private static final String  CREDITCUSTOMER = "CC";
	private static final String  CREATEAUCTION = "CA";
	private static final String  ADDITEM = "AI";
	private static final String  OPENAUCTION = "OA";
	private static final String  TERMINATEAUCTION = "TA";
	private static final String  PLACEBID = "PB";
	private static final String  REPORTAUCTION = "RA";
	private static final String  REPORTCUSTOMERS = "RC";
	private static final String  QUIT = "Q";

	private static final String WRONGCOMM = "Invalid Command";
	private static final String COMMDONNE = "OK";
	private static final String CANNOTINSERT = "Cannot insert item in running or terminated Auction";

	private static final String FILENAME = "auctions.txt";

	public static void main(String[] args)throws Exception {
		Scanner in;
		AuctionList actlist = new AuctionList();
		BidList blist = new BidList();
		CustomersList cstlist = new CustomersList();
		ItemList itmlist = new ItemList();
		in = new Scanner(System.in);

		try{//so vai ler o ficheiro se ele existir
			readFile(FILENAME, actlist, blist, cstlist, itmlist);
		}catch(Exception e){}

		commandInterpreter(in, actlist, cstlist, itmlist, blist);
		writeFile(FILENAME, actlist, blist, cstlist, itmlist);
	}

	private static void commandInterpreter(Scanner in, AuctionList auction, CustomersList customer, ItemList item, BidList bid){
		System.out.print(">");
		String comm = getCommand(in);

		while (!comm.equals(QUIT)) { 
			if(comm.equals(ADDCUSTOMER)){
				addCustomer(in,customer);
			}

			else if(comm.equals(CREDITCUSTOMER)){
				creditCustomer(in,customer);
			}

			else if(comm.equals(CREATEAUCTION)){
				createAuction(in,auction);
			}

			else if(comm.equals(ADDITEM)){
				AddItem(in,auction);
			}

			else if(comm.equals(OPENAUCTION)){
				OpenAuction(in,auction);
			}

			else if(comm.equals(TERMINATEAUCTION)){
				TerminateAuction(in,auction);
			}

			else if(comm.equals(PLACEBID)){
				PlaceBid(in,bid, item,auction, customer);
			}

			else if(comm.equals(REPORTAUCTION)){
				ReportAuction(in,auction);
			}

			else if (comm.equals(REPORTCUSTOMERS)){
				ReportCustomers(in,customer);
			}
			else System.out.println(WRONGCOMM);

			System.out.print(">");
			comm = getCommand(in);
		}
	}

	public static String getCommand(Scanner cmdin){

		String cmd= "";
		cmd = cmdin.nextLine().toUpperCase();
		return cmd;
	}

	public static void addCustomer(Scanner in, CustomersList c){

		String customerName= in.nextLine();;
		int money=in.nextInt();
		in.nextLine();

		if(c.CustomerExists(customerName) == null){//se o cliente nao existir adiciona um novo
			Customer customer = new Customer(customerName,money);
			c.addCustomer(customer);
			System.out.println(COMMDONNE);
		}
		else System.out.println("Customer " + customerName + " already created");
	}

	public static void creditCustomer(Scanner in , CustomersList c){

		String customerName= in.nextLine();;
		int money=in.nextInt();
		in.nextLine();

		Customer customer = c.CustomerExists(customerName); 
		if( customer != null){//se o cliente existir
			System.out.println(COMMDONNE);	
			customer.creditCustomer(money);
		}
		else System.out.println("Customer " + customerName + " not registered");	
	}

	public static void createAuction(Scanner in, AuctionList a){
		String auctionName = in.nextLine();

		Auction auction = a.AuctionExists(auctionName);
		if( auction == null){//se o leilao nao existir
			System.out.println(COMMDONNE);
			a.createAuction(new Auction(auctionName));
		}
		else System.out.println("Auction " + auctionName + " already created");
	}

	public static void AddItem(Scanner in, AuctionList a){
		String auctionName=in.nextLine();
		String itemName=in.nextLine();
		String seller=in.nextLine();
		int initialPrice=in.nextInt();
		in.nextLine();

		Auction auction = a.AuctionExists(auctionName);

		if(auction != null) {//se o leilao existir
			if(auction.getStatus() == 0) {// se o status for por iniciar
				if(auction.getItemList().ItemExists(itemName) == null) {//se o item nao existir
					auction.addItem( new Item(auctionName,itemName,seller,initialPrice)); // vai adicionar
					System.out.println(COMMDONNE);
				}
				else System.out.println("Item " + itemName + " already inserted in " + auctionName);
			}
			else System.out.println(CANNOTINSERT);
		}
		else System.out.println("Auction " + auctionName + " not created");
	}

	public static void OpenAuction(Scanner in, AuctionList a){
		String auctionName = in.nextLine();	
		Auction auction = a.AuctionExists(auctionName); 

		if( auction != null && (auction.getStatus() == 0)) {//se o leilao existir e nao tiver sido iniciado
			auction.setStatusAdecorrer();
			System.out.println(COMMDONNE);
		}
		else System.out.println("Auction " + auctionName + " cannot be opened");
	}

	public static void TerminateAuction(Scanner in, AuctionList a){
		String auctionName = in.nextLine();	
		Auction auction = a.AuctionExists(auctionName); 

		if( auction != null && auction.getStatus() == 1){//se o leilao existir e tiver sido iniciado
			auction.setStatusTerminado();
			System.out.println(COMMDONNE);
		}
		else System.out.println("Auction " + auctionName + " not open");
	}

	public static void PlaceBid(Scanner in, BidList b, ItemList i, AuctionList a, CustomersList l){
		String auctionName = in.nextLine();
		String itemName = in.nextLine();
		String customerName = in.nextLine();
		int value = in.nextInt();
		in.nextLine();

		Auction auction = a.AuctionExists(auctionName);

		if(auction != null && auction.getStatus() == 1) {//se o leilao existir e tiver iniciado
			Item item = auction.getItemList().ItemExists(itemName);//da a lista d items e procura o item
			if(item != null) {//se o item nao existir
				Customer customer = l.CustomerExists(customerName);//verifica se o cliente existe
				if(customer != null) {//se o cliente tiver registado
					if(customer.getMoney() >= value) {//se o dinheiro do cliente der para fazer a proposta
						if(customer.getReputation() >= 50) {//se tiver a reputação necessaria
							if(value > item.getHigherBidPrice()) {//se a proposta actual for maior que a anterior
								Bid bid = new Bid(value, item, customer);
								item.makeBidToItem(bid);//faz a proposta
								customer.subtractMoney(value);//actualiza o valor do cliente
								System.out.println(COMMDONNE);	
							}
							else System.out.println("Customer " + customerName + " does not cover the current bidding of " + item.getHigherBidPrice() + " euros");
						}
						else System.out.println("Customer " + customerName + " does not have enough credibility");
					}
					else {
						customer.penReputation();//o cliente perde reputação
						System.out.println("Customer " + customerName + " does not have enough credit");
					}
				}
				else System.out.println("Customer " + customerName + " not registered");
			}
			else System.out.println("Item " + itemName + " not for sale in this auction");
		}
		else System.out.println("Auction " + auctionName + " not created or open"); 

	}

	public static void ReportAuction(Scanner in, AuctionList a){
		String auctionName = in.nextLine();
		Auction auction = a.AuctionExists(auctionName);

		if ((auction != null) && (auction.getStatus() == 2)){//se o leilao existir e tiver terminado

			ItemList i = auction.getItemList();//obter lista de items
			Item[] itemsSold = i.itemsSold();//obter a lista de items vendidos no leilao
			i.sortItemsByBuyer();//Organizar a lista por ordem alfabetica do comprador

			for (int j = 0; j < itemsSold.length; j++) {//para cada item vendido
				if(itemsSold[j] != null)
					System.out.println(auction.getItemNamebyIndex(j) + " " + auction.getSellerNamebyIndex(j) + " " + auction.getBuyerNamebyIndex(j) + " " + auction.getBidPricebyIndex(j) + " " + auction.getAverageBidsbyIndex(j) + " ");
				//escrever os dados necessarios
			}

			Item[] itemsNonSold = i.itemsNonSold(); //obter lista d items nao vendidos
			i.sortItemsAlphabetic();//organizar esses items por ordem alfabetica do item

			for (int k = 0; k < itemsNonSold.length; k++) {//para cada um deles
				if (itemsNonSold[k] != null) 
					System.out.println(auction.getItemNamebyIndex(k) + " " + auction.getSellerNamebyIndex(k));
				//escrever os dados necessarios
			}
		}
		else System.out.println("Auction " + auctionName +" not created or not closed");
	}

	public static void ReportCustomers(Scanner in, CustomersList c){
		Customer[] customers = c.getCustomerList();//lista d clientes

		for (int i = 0; i < c.getNumberOfCustomers(); i++) {//para cada cliente
			System.out.println(customers[i].getName() + " " + customers[i].getMoney() + " " + customers[i].getReputation() + " " + customers[i].getHighBid());
		}
	}

	public static void readFile(String file,AuctionList actlists, BidList bdlists, CustomersList cstLists, ItemList itmlists) throws Exception{

		FileReader f = new FileReader(file);
		Scanner s = new Scanner(f);
		int nrcustomers = s.nextInt();//ler numero de clientes

		for (int i = 0; i < nrcustomers; i++) {//para da um deles
			cstLists.addCustomer(new Customer(s.next(), s.nextInt(), s.nextInt(), s.nextInt()));
		}

		int nrauctions = s.nextInt();//ler numero de leiloes
		for (int i = 0; i < nrauctions; i++) {//para cada um

			String name = s.next();//ler nome
			int status = s.nextInt();//sattus
			ItemList itemList = new ItemList();//obter a sua lista de items

			int numberItems = s.nextInt();//ler numero de items no leilao
			for (int j = 0; j < numberItems; j++) {//para cada um deles
				Item item = new Item(name, s.next(), s.next(), s.nextInt());//ler o item

				int nrbids = s.nextInt();//ler numero de propostas
				for (int k = 0; k < nrbids; k++) {//para cada uma delas
					Customer customer = cstLists.CustomerExists(s.next());//saber kem fez a proposta
					Bid bid = new Bid(s.nextInt(),item,customer);//ler a proposta
					item.makeBidToItem(bid);//associa-la com o item
				}
				itemList.addItem(item);
			}
			actlists.createAuction(new Auction(name, status, itemList));
		}
	}

	public static void writeFile(String file, AuctionList actlists, BidList bdlists, CustomersList cstLists, ItemList itmlists) throws Exception{
		PrintWriter pw = new PrintWriter(file);

		int nrclients = cstLists.getNumberOfCustomers();//obter numero de clientes
		pw.println(nrclients);

		if(nrclients > 0){//se houver clientes
			for (int i = 0; i < nrclients; i++) {//para cada um deles
				Customer customer = cstLists.getCustomer(i);
				pw.println(customer.getName() + " " + customer.getMoney() + " " + customer.getReputation() + " " + customer.getHighBid());
			}
		}

		int nraucts = actlists.getNrOfAuctionsNotClose();//obter numero de leiloes nao fexados
		pw.println(nraucts);//escrever numero de leiloes

		if (nraucts > 0){//se houver leiloes
			Auction[] temp = actlists.AuctionsNotClose();//obter lista desses leiloes

			for (int i = 0; i < nraucts; i++) {//e para cada um deles
				pw.println(temp[i].getAuctionName() + " " + temp[i].getStatus());//escrever a informaçao


				ItemList itemlist = temp[i].getItemList();//obter lista de items dos leiloes nao fexados
				int nritems = itemlist.getNumberOfItems();//numero de items

				if(itemlist != null)
					pw.println(nritems);//escrever numero de items
				System.out.println("HERE0" +nritems);

				if(nritems > 0){//se houver items
					for (int j = 0; j < nritems; j++) {//para cada um deles

						Item item = itemlist.getItems()[j];//obter items
						pw.println(item.getItemName() + " "+ item.getSellerName()+" "+ item.getStartprice());
						//escrever a informaçao necessaria

						int nrbids = bdlists.getNumberOfBids();//obter numero de propostas
						BidList bidList = item.getBids();//obter lista de propostas
						//pw.println(nrbids);
						if(nrbids > 0){//se houver propostas para os items
							for (int k = 0; k < nrbids; k++)//para cada uma delas
								pw.println(bidList.getBidList()[k].getCustomer().getName() + " " + bidList.getBidList()[k].getBidprice());					
							//escrever informaçao necessaria
						}
					}
				}
			}
		}
		pw.close();
	}
}
