
public class DisciplinaNotFoundException extends RuntimeException
{                                     

    static final long serialVersionUID = 1L;


    public DisciplinaNotFoundException( )   
    {
        super("Inexistencia da disciplina referida no curso do aluno.");
    }

    public DisciplinaNotFoundException( String message )
    {
        super(message);
    }

}
