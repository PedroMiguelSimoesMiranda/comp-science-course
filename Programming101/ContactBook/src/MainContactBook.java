
public class MainContactBook {

	static public void main(String[] args){
		ContactBook book = new ContactBook();
		book.addContact("João Martins",210732281,"jm@gmail.com");
		book.addContact("Ana Cruz", 224842567,"ac@gmail.com");
		int res = book.searchIndex("João Martins");
	
	

}
