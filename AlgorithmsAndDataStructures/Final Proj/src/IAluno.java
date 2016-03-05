import dataStructures.*;
import java.io.Serializable;

public interface IAluno extends Serializable{
	
	public String getNome();
	
	public String getCurso();
	
	public boolean isDiplomado();
	
	public void setDiplomado();
	
	public float getCreditos();
	
	public Iterator<Entry<Disciplina,Integer>> getAprovacoes();
	
	public void aprovacao(Disciplina X, int nota);
	
	public void melhoria(Disciplina X, int nota);
	
	public int mediaAluno();
	
	public int DisciplinasObrigatoriasFeitas();

}
