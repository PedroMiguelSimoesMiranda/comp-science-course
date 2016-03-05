/**
 * @author Pedro Miranda, nº 28289, Turno P5 TP1
 * @author Pedro Estrela, nº 28761, Turno P5 TP1
 *
 * Docente: Bernardo Luís da Silva Ferreira
 */
public class CustomersList {

	private Customer[] customers;
	private static final int MAX_CUSTOMERS = 20;
	private int numberofcustomers;

	CustomersList(){
		customers = new Customer[MAX_CUSTOMERS];
		numberofcustomers = 0;
	}

	private int searchCustomerIndex(String name){
		int i = 0;
		boolean find = false;
		while ((i<numberofcustomers)&&(!find)) {
			if (customers[i].getName().compareTo(name) == 0)
				find = true;
			else
				i++;
		}
		if (find)
			return i;
		else
			return -1;
	}

	public Customer CustomerExists(String name){//Verifica se o cliente existe dado o seu nome
		Customer customer = null;
		for (int i = 0; i < numberofcustomers; i++) {
			if (customers[i].getName().equals(name))
				customer = customers[i];
		}
		return customer;
	}

	public int getNumberOfCustomers(){
		return numberofcustomers;
	}

	public void addCustomer (Customer c){
		if (searchCustomerIndex(c.getName()) == -1){
			if(numberofcustomers == customers.length){
				Customer tmp[] = new Customer[2 * customers.length];
				int i = 0;
				while (i < numberofcustomers ){
					tmp[i] = customers[i];
					i++;
				}
				customers = tmp;
			}
			customers[numberofcustomers++] = c;
		}
	}

	public Customer getCustomer(int index) {
		return customers[index];
	}

	public Customer[] getCustomerList() {
		return customers;
	}
}
