import dataStructures.*;

public class Universidade implements IUniversidade {
	
	private static final long serialVersionUID = 1L;
	
	OrderedDoublyLL<String, Curso> cursos;
	OrderedDoublyLL<String, Aluno> alunos;
	
	Universidade(){
		cursos = new OrderedDoublyLL<String, Curso>();
		alunos = new OrderedDoublyLL<String, Aluno>();
	}


	public void CC(int creditos, String nomeCurso, int numDisciplinasObrigatorias, Disciplina[] disciplinasObrigatorias, int numDisciplinasOpcionais, Disciplina[] disciplinasOpcionais) 
	throws CursoAlreadyExistsException, DisciplinasRepetidasException, CreditosInsuficientesException{
		if(cursos.find(nomeCurso.toLowerCase())!=null)
			throw new CursoAlreadyExistsException();
		
		Curso c = new Curso(nomeCurso, creditos, numDisciplinasObrigatorias, disciplinasObrigatorias, numDisciplinasOpcionais, disciplinasOpcionais);
		cursos.insert(nomeCurso.toLowerCase(), c);
	}

	public void IA(String nomeCurso, String nomeAluno)
	throws CursoDoesNotExistException, AlunoAlreadyExistsException{
		if(cursos.find(nomeCurso.toLowerCase())==null)
			throw new CursoDoesNotExistException();
		if(alunos.find(nomeAluno.toLowerCase())!=null)
			throw new AlunoAlreadyExistsException();
		Aluno a = new Aluno(cursos.find(nomeCurso.toLowerCase()).getName(),nomeAluno);
		alunos.insert(nomeAluno.toLowerCase(), a);
	}
	
	public void RA(String nomeAluno)
	throws AlunoDoesNotExistException{
		Aluno a = alunos.find(nomeAluno.toLowerCase());
		if(a==null)
			throw new AlunoDoesNotExistException();
		//remover se diplomado
		if(a.isDiplomado()){
			Curso c = cursos.find(a.getCurso().toLowerCase());
			c.removeDiplomado(a);
		}
		//remover aluno
		alunos.remove(nomeAluno.toLowerCase());
	}
	
	public void LA(String nomeAluno, int notaFinal, String nomeDisciplina)
	throws AlunoDoesNotExistException, AlunoIsInactiveException, DisciplinaNotFoundException, AprovacaoAlreadyDoneException{
		Aluno a = alunos.find(nomeAluno.toLowerCase());
		if(a==null)
			throw new AlunoDoesNotExistException();
		if(a.diplomado)
			throw new AlunoIsInactiveException();
		
		Disciplina x = cursos.find(a.getCurso().toLowerCase()).findDisciplina(nomeDisciplina.toLowerCase());
		if(x==null)
			throw new DisciplinaNotFoundException();
		if(a.aprovacoes.find(x)!=null)
			throw new AprovacaoAlreadyDoneException();
		
		a.aprovacao(x, notaFinal);
		//Verificas se fica diplomado
		Curso c = cursos.find(a.getCurso().toLowerCase());
		if(c.creditos <= a.getCreditos() && c.numDisciplinasObrigatoriasCurso()<=a.DisciplinasObrigatoriasFeitas()){
			a.setDiplomado();
			c.addDiplomado(nomeAluno.toLowerCase(), a);
		}
	}

	public void LM(String nomeAluno, int notaFinal, String nomeDisciplina)
	throws AlunoDoesNotExistException, AlunoIsInactiveException, DisciplinaNotFoundException, AprovacaoNotDoneException, MelhoriaAlreadyDoneException{
		Aluno a = alunos.find(nomeAluno.toLowerCase());
		if(a==null)
			throw new AlunoDoesNotExistException();
		if(a.diplomado)
			throw new AlunoIsInactiveException();
		Disciplina x = cursos.find(a.getCurso()).findDisciplina(nomeDisciplina.toLowerCase());
		if(cursos.find(a.nomeCurso.toLowerCase()).disciplinas.find(nomeDisciplina.toLowerCase())==null)
			throw new DisciplinaNotFoundException();
		if(a.aprovacoes.find(x)==null)
			throw new AprovacaoNotDoneException();
		if(a.aprovacoes.find(x).intValue() > 100)
			throw new MelhoriaAlreadyDoneException();
		
		a.melhoria(x, notaFinal);
	}

	public CurriculumAluno CA(String nomeAluno)
	throws AlunoDoesNotExistException {
		Aluno a = alunos.find(nomeAluno.toLowerCase());
		if(a==null)
			throw new AlunoDoesNotExistException();
		String status;
		if (a.isDiplomado())
			status = "Diplomado";
		else
			status = "Nao Diplomado";
		CurriculumAluno cc = new CurriculumAluno(a.getCurso(),status, a.getAprovacoes());
		return cc;
	}

	public Iterator<Entry<KeyDip, Aluno>> PD(String nomeCurso)
	throws CursoDoesNotExistException, DiplomadosOnCursoDontExistException {
		
		if(cursos.find(nomeCurso.toLowerCase())==null)
			throw new CursoDoesNotExistException();
		
		if(!cursos.find(nomeCurso.toLowerCase()).getDiplomados().hasNext())
			throw new DiplomadosOnCursoDontExistException();
		
		return cursos.find(nomeCurso.toLowerCase()).getDiplomados();
	}

}
