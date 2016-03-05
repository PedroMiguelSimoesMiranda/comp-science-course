package poo;

//import java.io.FileReader;
import java.util.Scanner;

public class Main {
	
//public static final String FILENAME="grades.txt";

	public static void main(String[] args) /*throws Exception*/ {
	//	FileReader fr=new FileReader(FILENAME);
		//Scanner in=new Scanner(fr);
		GradeList g = new GradeList();
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			int numb=in.nextInt();
			String n=in.nextLine();
			n=n.substring(1);
			int gr=0;
			if(!n.equals("I") && !n.equals("A") && !n.equals("R") && !n.equals("X") && !n.equals("D") &&  !n.equals("")){
				gr = Integer.parseInt(n);
				g.addGrade(numb,gr);	
			}	
			else if (n==null){
				g.addGrade(0,0);
			}
		}
		System.out.print(g.getHonor());
	}	
}


