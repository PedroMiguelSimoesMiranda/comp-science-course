
public class CursoAlreadyExistsException extends RuntimeException
{                                     

    static final long serialVersionUID = 1L;


    public CursoAlreadyExistsException( )   
    {
        super("Existencia do curso referido.");
    }

    public CursoAlreadyExistsException( String message )
    {
        super(message);
    }

}
