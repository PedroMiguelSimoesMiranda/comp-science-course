
public class DiplomadosOnCursoDontExistException extends RuntimeException
{                                     

    static final long serialVersionUID = 1L;


    public DiplomadosOnCursoDontExistException( )   
    {
        super("Inexistencia de diplomados do curso referido.");
    }

    public DiplomadosOnCursoDontExistException( String message )
    {
        super(message);
    }

}
