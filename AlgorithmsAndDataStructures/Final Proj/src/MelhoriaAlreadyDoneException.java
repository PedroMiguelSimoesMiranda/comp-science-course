
public class MelhoriaAlreadyDoneException extends RuntimeException
{                                     

    static final long serialVersionUID = 1L;


    public MelhoriaAlreadyDoneException( )   
    {
        super("Existencia da melhoria na disciplina referida.");
    }

    public MelhoriaAlreadyDoneException( String message )
    {
        super(message);
    }

}
