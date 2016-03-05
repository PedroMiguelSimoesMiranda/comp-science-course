import dataStructures.*;
import java.io.Serializable;

public interface IUniversidade extends Serializable{
	
	public void CC (int creditos, String nomeCurso, int nDisciplinasObrigatorias, Disciplina[] disciplinasObrigatorias, int nDisciplinasOpcionais, Disciplina[] disciplinasOpcionais)
		throws CursoAlreadyExistsException, DisciplinasRepetidasException, CreditosInsuficientesException;
	
	public void IA (String nomeCurso, String nomeAluno)
		throws CursoDoesNotExistException, AlunoAlreadyExistsException;
	
	public void RA (String nomeAluno)
		throws AlunoDoesNotExistException;
	
	public void LA (String nomeAluno, int notaFinal, String nomeDisciplina)
		throws AlunoDoesNotExistException, AlunoIsInactiveException, DisciplinaNotFoundException, AprovacaoAlreadyDoneException;
	
	public void LM (String nomeAluno, int notaFinal, String nomeDisciplina)
		throws AlunoDoesNotExistException, AlunoIsInactiveException, DisciplinaNotFoundException, AprovacaoNotDoneException, MelhoriaAlreadyDoneException;
	
	public CurriculumAluno CA (String nomeAluno)
		throws AlunoDoesNotExistException;
	
	public Iterator<Entry<KeyDip, Aluno>> PD (String nomeCurso)
		throws CursoDoesNotExistException, DiplomadosOnCursoDontExistException;

}
