
public class AlunoAlreadyExistsException extends RuntimeException
{                                     

    static final long serialVersionUID = 1L;


    public AlunoAlreadyExistsException( )   
    {
        super("Existencia do aluno referido.");
    }

    public AlunoAlreadyExistsException( String message )
    {
        super(message);
    }

}
