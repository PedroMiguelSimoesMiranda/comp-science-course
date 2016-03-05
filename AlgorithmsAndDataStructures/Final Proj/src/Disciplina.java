
public class Disciplina implements IDisciplina{

	private static final long serialVersionUID = 1L;
	
	float creditos;
	String nome;
	boolean obrigatoria;
	
	Disciplina(float c, String n){
		creditos = c;
		nome = n;
	}
	
	public float getCreditos() {
		return creditos;
	}

	public String getName() {
		return nome;
	}
	
	public boolean isObrigatoria(){
		return obrigatoria;
	}
	
	public void setObrigatoria(){
		obrigatoria = true;
	}

	public int compareTo(Disciplina arg) {
		/*if (arg==null)
			return -1;
		else if(this.getName().compareTo(arg.getName())<0)
			return -1;
		else if(this.getCreditos()==arg.getCreditos() && this.isObrigatoria()==arg.isObrigatoria())
			return 0;
		else
			return -1;*/
		return this.getName().compareTo(arg.getName());
	}
	
}
