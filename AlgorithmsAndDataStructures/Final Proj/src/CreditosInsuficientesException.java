
public class CreditosInsuficientesException extends RuntimeException
{                                     

    static final long serialVersionUID = 1L;


    public CreditosInsuficientesException( )   
    {
        super("Insuficiencia de creditos no plano curricular.");
    }

    public CreditosInsuficientesException( String message )
    {
        super(message);
    }

}