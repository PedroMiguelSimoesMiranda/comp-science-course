
public class AprovacaoAlreadyDoneException extends RuntimeException
{                                     

    static final long serialVersionUID = 1L;


    public AprovacaoAlreadyDoneException( )   
    {
        super("Existencia da aprovacao na disciplina referida.");
    }

    public AprovacaoAlreadyDoneException( String message )
    {
        super(message);
    }

}
