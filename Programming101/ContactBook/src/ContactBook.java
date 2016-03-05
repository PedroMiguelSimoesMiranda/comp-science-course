public class ContactBook {

	public static final int MAX_CONTACTS = 50;
	private int counter;
	private Contact[] contacts;
	
	public ContactBook() {
		counter = 0;
		contacts= new Contact[MAX_CONTACTS];
		}
	
	public int getPhone(String name){
		int i=0;
		int number=0;
		boolean find = false;
		while((i <= counter) && !find )
			if((contacts[i].getName().equals(name)))
				number=contacts[i].getPhone();
			 else i++;
			return number;
}
	
	
	public String getEmail(String name){
		int i=0;
		String mail="";
		boolean h=false;
		while(i <= counter && !h )
			if((contacts[i].getEmail().equals(name)))
				mail=contacts[i].getEmail();
			else i++;
				return mail;
	}

	
	
	public void removeContact(String name){
		int index = searchIndex(name);
		if (index != -1) { 
		int i=index;
		while (i < counter-1) {
		contacts[i] = contacts[i+1];
		i++;
		}
		counter--;
		}}
	
	
	public void setPhone(String name, int phone){
		int i =searchIndex(name);	
		if(i != -1 )
			contacts[i].setPhone(phone);
		
		
		
		
	}
	
	public void setEmail(String name, String email){
		int i = searchIndex(name);	
					if(i != -1 )
			        contacts[i].setEmail(email);
		}
	

	
	public String listContacts(){
		String a=null;
		int i=0;
		while(i<=counter){
			a=contacts[i].toString();
			i++;
		}
	
	
		return a;
	}
		
	public Contact getContact(String name){
		Contact a = null;
		int i=0;
		boolean find = false; 
		while(i<=counter && !find){
			if(contacts[i].equals(name))
				a=contacts[i];		
		}
	return a;
	}
	
	
	
	private int searchIndex(String name){
		int i = 0; 
		boolean find = false; 
		while((i < counter) && (!find))
		if( contacts[i].getName().compareTo(name) == 0)
		find = true;
		else i++;
		if (find) return i;
		else return -1;
	}
	
	public void                       {
		if (searchIndex(name) == -1) { 
		if (counter == contacts.length) { 
		Contact tmp[] = new Contact[2*contacts.length];
		int i=0;
		while (i < counter) { tmp[i] = contacts[i]; i++; }
		contacts = tmp;
		}
		contacts[counter] = new Contact(name,phone,email);
		counter++;
		}
		
	
		
}}
