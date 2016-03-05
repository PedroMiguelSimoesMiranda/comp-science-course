package poo;
//import java.io.FileReader;
import java.util.Scanner;
public class Main {

	//private static final String FILENAME = "grades.txt";
	
		public static void main(String[] args)/* throws Exception */  {
		//	FileReader f= new FileReader(FILENAME);
		//	Scanner in= new Scanner(f);
			Scanner in= new Scanner(System.in);
			Grade g = new Grade();
			while(in.hasNext()){
				in.nextInt();
				String n=in.nextLine();
				int grade=0;
				n=n.substring(1);
				if(!n.equals("I") && !n.equals("A") && !n.equals("R") && !n.equals("X") && !n.equals("D")){
					grade=Integer.parseInt(n);
					g.addGrade(grade);	
				}	
			}
		System.out.println(g.getModa());
		System.out.printf("%1.2f\n",g.media());
		System.out.printf("%1.2f\n",g.desvioPadrao());	
		}
	}

