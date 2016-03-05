import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in=new Scanner(System.in);
	    int saldo;
	    SafeBankAccount b;
	    int lev;
	    
	    
	    in=new Scanner(System.in);
		System.out.print("Introduza o saldo da conta a criar:");
		saldo = in.nextInt();
		b = new SafeBankAccount(saldo);
		System.out.println("Saldo da conta: "+b.getBalance());
		System.out.print("Introduza o valor a levantar:");
		lev=in.nextInt();
		if(lev> b.getBalance())
			System.out.println("Saldo inferior. Aplicar multa");
		b.withdraw(lev);
		System.out.println("Saldo da conta:"+b.getBalance());
		
			
			
	}

}