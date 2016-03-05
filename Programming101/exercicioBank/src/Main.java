import java.util.Scanner;
public Class Main{

public static void main (String[]args){

	Scanner in = new Scanner (system.in);
	int Saldo;
	SafeBankAccount b;
	int lev;
	
	in=new Scanner(System.in);
	System.out.print ("Introduza o saldo da conta a criar:");
	Saldo = in.nextInt();
	b = new SafeBankAccount(saldo);
	system.out.println ("Saldo da Conta:" + b.getBalance());
	system.out.println ("Introduza o valor a levantar:" );
	lev=in.nextInt();
	if(lev>)