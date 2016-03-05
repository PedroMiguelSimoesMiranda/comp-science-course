
public class CursoDoesNotExistException extends RuntimeException
{                                     

    static final long serialVersionUID = 1L;


    public CursoDoesNotExistException( )   
    {
        super("Inexistencia do curso referido.");
    }

    public CursoDoesNotExistException( String message )
    {
        super(message);
    }

}