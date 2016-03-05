import dataStructures.*;
import java.io.Serializable;

public interface ICurso extends Serializable{
	
	public String getName();
	
	public int getCreditos();
	
	public Iterator<Entry<KeyDip, Aluno>> getDiplomados();
	
	public void addDiplomado(String name, Aluno X);
	
	public int numDisciplinasObrigatoriasCurso();
	
	public Disciplina findDisciplina(String nomeDisciplina);

}
