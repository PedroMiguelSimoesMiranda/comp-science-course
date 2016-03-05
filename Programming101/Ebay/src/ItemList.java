/**
 * @author Pedro Miranda, nº 28289, Turno P5 TP1
 * @author Pedro Estrela, nº 28761, Turno P5 TP1
 *
 * Docente: Bernardo Luís da Silva Ferreira
 */
public class ItemList {

	private Item[] itemlist;
	private static final int MAX_ITEMS = 20;
	private int numberofitems;

	ItemList(){
		itemlist = new Item[MAX_ITEMS];
		numberofitems = 0;
	}

	private int searchItemIndex(String name){
		int i = 0;
		boolean find = false;

		while ((i<numberofitems) && (!find)) {

			if (itemlist[i].getItemName().compareTo(name) == 0)
				find = true;

			else
				i++;
		}

		if (find)
			return i;
		else
			return -1;

	}

	public int getNumberOfItems(){
		return numberofitems;
	}

	public Item[] getItems() {
		return itemlist;
	}

	public void addItem (Item item){
		if (searchItemIndex(item.getItemName()) == -1){

			if(numberofitems == itemlist.length){
				Item tmp[] = new Item[2 * itemlist.length];

				int i = 0;
				while (i < numberofitems ){
					tmp[i] = itemlist[i];
					i++;
				}
				itemlist = tmp;
			}
			itemlist[numberofitems++] = item;
		}
	}

	//organiza os item por ordem alfabetica apartir do algoritmo buble sort
	public void sortItemsAlphabetic(){

		for(int i = 1; i < numberofitems; i++)
			for(int j = numberofitems-1; j >= i; j--){
				if(itemlist[j-1].getItemName().compareTo(itemlist[j].getItemName()) > 0 ) {
					Item tmp = itemlist[j-1];
					itemlist[j-1] = itemlist[j];
					itemlist[j] = tmp;
				}
			}
	}

	//organiza os item por ordem alfabetica do comprador apartir do algoritmo buble sort
	public void sortItemsByBuyer(){

		for(int i = 1; i < numberofitems; i++)
			if(itemlist[i].getBuyer() != null){
				for(int j = numberofitems-1; j >= i; j--){
					if(itemlist[j-1].getBuyer().getName().compareTo(itemlist[j].getBuyer().getName()) > 0 ) {
						Item tmp = itemlist[j-1];
						itemlist[j-1] = itemlist[j];
						itemlist[j] = tmp;
					}
				}
			}
	}

	public Item ItemExists(String name){//verifica se o item existe dado o nome
		Item item = null;

		for (int i = 0; i < numberofitems; i++)
			if (itemlist[i].getItemName().equals(name))
				item = itemlist[i];

		return item;
	}

	public Item[] itemsSold() {//devolve a lista d items vendidos
		Item[] tmp = new Item[numberofitems];
		int counter = 0;

		for (int i = 0; i < numberofitems; i++) {
			if (itemlist[i].getBids().getNumberOfBids() != 0){
				tmp[counter++] = itemlist[i];
			}
		}
		return tmp;
	}

	public Item[] itemsNonSold() {//devolve a lista de items nao vendidos
		Item[] tmp = new Item[numberofitems];
		int counter = 0;

		for (int i = 0; i < numberofitems; i++) {
			if (itemlist[i].getBids().getNumberOfBids() == 0){
				tmp[counter++] = itemlist[i];
			}
		}
		return tmp;
	}
}



