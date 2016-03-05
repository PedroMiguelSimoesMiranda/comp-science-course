
public class DisciplinasRepetidasException extends RuntimeException
{                                     

    static final long serialVersionUID = 1L;


    public DisciplinasRepetidasException( )   
    {
        super("Existencia de disciplinas repetidas.");
    }

    public DisciplinasRepetidasException( String message )
    {
        super(message);
    }

}
