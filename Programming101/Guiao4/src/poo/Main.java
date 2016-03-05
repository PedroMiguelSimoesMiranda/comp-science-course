package poo;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		int x=1;
		Scanner reader = new Scanner(System.in);
		while(reader.hasNextInt()){
			int s=reader.nextInt();
			int t=reader.nextInt();
			int n=reader.nextInt();
			DrawGrid grid= new DrawGrid(s,t,n);
			if(s!=0 && t!=0 && n!=0){
				System.out.println("Case " + x + ":");
				System.out.println(grid.draw());
				x++;
			}
			if(s==0 && t==0 && n==0)
					break;
		}
	}
}
