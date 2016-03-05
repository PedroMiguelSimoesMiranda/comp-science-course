import dataStructures.*;
import java.io.Serializable;

public class CurriculumAluno implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	int i;
	String s1, s2;
	Iterator<Entry<Disciplina, Integer>> it;
	
	CurriculumAluno(String r1, String r2, Iterator<Entry<Disciplina, Integer>> x){
		i=0;
		s1 = r1;
		s2 = r2;
		it = x;
	}
	
	public String next(){
		if(i==0){
			i++;
			return s1;}
		else if (i==1){
			i++;
			return s2;}
		else{
			if(!it.hasNext())
				return null;
			Entry<Disciplina, Integer> x = it.next();
			Disciplina d =(Disciplina) x.getKey();
			return d.getName()+" "+(double)d.getCreditos()+" "+x.getValue()%100;
		}
	}

}
