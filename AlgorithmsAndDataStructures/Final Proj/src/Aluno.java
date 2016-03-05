import dataStructures.*;

public class Aluno implements IAluno {
	
	private static final long serialVersionUID = 1L;
	
	String nome, nomeCurso;
	boolean diplomado;
	float creditos;
	int media, nDisciplinasObrFeitas,nDisciplinasFeitas;
	Iterator<Entry<Disciplina, Integer>> ait;
	OrderedDoublyLL<Disciplina, Integer> aprovacoes;//OrderedDLL;

	Aluno(String nc, String na){
		nome = na;
		nomeCurso = nc;
		diplomado = false;
		creditos = 0;
		aprovacoes = new OrderedDoublyLL<Disciplina, Integer>();
	}

	public String getNome() {
		return nome;
	}
	
	public String getCurso() {
		return nomeCurso;
	}
	
	public boolean isDiplomado() {
		return diplomado;
	}
	
	public void setDiplomado() {
		diplomado = true;
	}
	
	public float getCreditos(){
		return creditos;
	}
	
	public void aprovacao(Disciplina X, int nota) {
		aprovacoes.insert(X, nota);
		creditos += X.getCreditos();
		media+=nota;
		nDisciplinasObrFeitas++;
		nDisciplinasFeitas++;
		
		ait = aprovacoes.iterator();
	}
	public void melhoria(Disciplina X, int nota) {
		int notaOld = aprovacoes.find(X).intValue();
		if(notaOld<nota){
			aprovacoes.insert(X, nota+100);
			media += nota-notaOld;}
		else
			aprovacoes.insert(X, notaOld+100);
		
		ait = aprovacoes.iterator();
	}

	public Iterator<Entry<Disciplina, Integer>> getAprovacoes() {
		return aprovacoes.iterator();
	}

	public int mediaAluno(){
		ait.rewind();
		float mult=0;
		while(ait.hasNext()){
			Entry<Disciplina,Integer> aux = ait.next();
			mult += aux.getKey().getCreditos() * (aux.getValue()%100);
		}
		
		return Math.round(mult/creditos);
	}
	
	public int DisciplinasObrigatoriasFeitas(){
		return nDisciplinasObrFeitas;
	}

}
