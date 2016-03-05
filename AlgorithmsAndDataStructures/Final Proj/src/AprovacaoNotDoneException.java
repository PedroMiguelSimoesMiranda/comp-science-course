
public class AprovacaoNotDoneException extends RuntimeException
{                                     

    static final long serialVersionUID = 1L;


    public AprovacaoNotDoneException( )   
    {
        super("Inexistencia da aprovacao na disciplina referida.");
    }

    public AprovacaoNotDoneException( String message )
    {
        super(message);
    }

}
