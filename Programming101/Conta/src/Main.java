import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in=new Scanner(System.in);
	    int saldo;
	    SafeBankAccount b;
		System.out.print("Introduza o saldo da conta a criar:");
		saldo = in.nextInt();
		b = new SafeBankAccount(saldo);
		System.out.println(b.getBalance());
	}

}
