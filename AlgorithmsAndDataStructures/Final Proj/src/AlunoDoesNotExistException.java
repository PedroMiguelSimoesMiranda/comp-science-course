
public class AlunoDoesNotExistException extends RuntimeException
{                                     

    static final long serialVersionUID = 1L;


    public AlunoDoesNotExistException( )   
    {
        super("Inexistencia do aluno referido.");
    }

    public AlunoDoesNotExistException( String message )
    {
        super(message);
    }

}
