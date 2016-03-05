import java.io.Serializable;

public interface IDisciplina extends Comparable<Disciplina>, Serializable{
	
	public String getName();
	
	public float getCreditos();
	
	public boolean isObrigatoria();

}
