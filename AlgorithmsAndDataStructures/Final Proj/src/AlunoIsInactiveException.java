
public class AlunoIsInactiveException extends RuntimeException
{                                     

    static final long serialVersionUID = 1L;


    public AlunoIsInactiveException( )   
    {
        super("Inactividade do aluno referido.");
    }

    public AlunoIsInactiveException( String message )
    {
        super(message);
    }

}
