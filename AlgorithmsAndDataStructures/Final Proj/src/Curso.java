import dataStructures.*;

public class Curso implements ICurso {
	
	private static final long serialVersionUID = 1L;
	
	String nome;
	int creditos;
	OrderedDoublyLL <String, Disciplina > disciplinas;
	OrderedDoublyLL <KeyDip, Aluno> diplomados;
	int numDisciplinasObrigatorias;
	
	Curso(String nomeC, int creditosC, int numDisciplinasObrigatoriasC, Disciplina[] disciplinasObrigatorias, int numDisciplinasOpcionais, Disciplina[] disciplinasOpcionais){
		disciplinas = new OrderedDoublyLL <String, Disciplina >();
		diplomados = new OrderedDoublyLL <KeyDip, Aluno >();
		nome = nomeC;
		creditos = creditosC;
		numDisciplinasObrigatorias = numDisciplinasObrigatoriasC;
		
		int auxCreditos=0;
		for(int i=0;i<numDisciplinasObrigatoriasC;i++){
			if(disciplinas.find(disciplinasObrigatorias[i].nome.toLowerCase())!=null)
				throw new DisciplinasRepetidasException();
			disciplinas.insert(disciplinasObrigatorias[i].nome.toLowerCase(), disciplinasObrigatorias[i]);
			disciplinasObrigatorias[i].setObrigatoria();
			auxCreditos += disciplinasObrigatorias[i].creditos;
		}
		for(int i=0;i<numDisciplinasOpcionais;i++){
			if(disciplinas.find(disciplinasOpcionais[i].nome.toLowerCase())!=null)
				throw new DisciplinasRepetidasException();
			disciplinas.insert(disciplinasOpcionais[i].nome.toLowerCase(), disciplinasOpcionais[i]);
			auxCreditos += disciplinasOpcionais[i].creditos;
		}
		if(auxCreditos<creditos)
			throw new CreditosInsuficientesException();

	}


	public void addDiplomado(String nome, Aluno X) {
		diplomados.insert(new KeyDip(X.mediaAluno(),nome), X);
	}

	public int getCreditos() {
		return creditos;
	}

	public String getName() {
		return nome;
	}

	public Iterator<Entry<KeyDip, Aluno>> getDiplomados() {
		return diplomados.iterator();
	}
	
	public int numDisciplinasObrigatoriasCurso(){
		return numDisciplinasObrigatorias;
	}
	
	public Disciplina findDisciplina(String nomeDisciplina){
		return disciplinas.find(nomeDisciplina.toLowerCase());
	}

}
