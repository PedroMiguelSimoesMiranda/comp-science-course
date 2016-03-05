package poo;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	//public static final String FILENAME="grades.txt";

	public static void main(String[] args)/*throws Exception*/ {
		//FileReader fr=new FileReader(FILENAME);
		//Scanner s=new Scanner(fr);
		Scanner s = new Scanner(System.in);
		GradeList f=new GradeList();
		while(s.hasNext()){
			s.nextInt();
			String n=s.nextLine();
			int grade=0;
			n=n.substring(1);
			if(!n.equals("I") && !n.equals("A") && !n.equals("R") && !n.equals("X") && !n.equals("D")){
				grade = Integer.parseInt(n);
				f.addGrade(grade);
				}
		}
		int i=20;
		while(i>=0){
			System.out.println(i+"\t"+f.tabFrequencias(i));
			i--;
		}
	}
}

