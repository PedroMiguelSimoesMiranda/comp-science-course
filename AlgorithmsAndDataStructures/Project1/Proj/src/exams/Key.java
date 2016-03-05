package exams;

public class Key implements IKey{
	private String epoca;
	private String disciplina;
	private String curso;
	private String topico;
	private int ano;
	private int num_pergunta;
	
	
public Key(String rEpoca, String rDisc, String rCurso,String rTopico, int rAno, int rNum_pergunta){
	epoca=rEpoca;
	disciplina=rDisc;
	curso=rCurso;
	topico=rTopico;
	ano=rAno;
	num_pergunta=rNum_pergunta;
}

public String getEpoca(){
	return epoca;
}
public String getDisciplina(){
	return disciplina;
}
public String getCurso(){
	return curso;
}
public String getTopico(){
	return topico;
}
public int getAno(){
	
	return ano;
}
public int getNum_pergunta(){
	return num_pergunta;
}

}

