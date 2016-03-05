import java.util.Scanner;


public class Main {

	
	public static void main(String[] args) {
		
		Scanner in=new Scanner (System.in);
		ContactBook cBook=new ContactBook();
		String comm="";
		
		System.out.print(">");
		comm=in.nextLine().toUpperCase();
		while(!comm.equals("QUIT")){
			if(comm.equals("ADDCONTACT")){
				
				
				
			}
		}
	private static void addContact(Scanner in, Contact cBook){
		
		String name="";
		String email="";
		int phone=0;
		Contact c=null;
		name=in.nextLine();
		phone=in.nextInt();
		in.nextLine();
		email=in.nextLine();
		if(cBook.getContact(name)==null){
			cBook.addContact(name,phone,email);
			System.out.println(ContactADDED);
		}
		else System.out.println("CONTACTEXIST");
			
	}
	
		
		

	}

}
