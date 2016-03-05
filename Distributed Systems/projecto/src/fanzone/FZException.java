package fanzone;

public class FZException extends Exception
{
	private static final long serialVersionUID = 1L;
	public FZException( String str) {
		super( str);
	}
	public FZException( Exception e) {
		super( e);
	}
	public FZException( String s, Exception e) {
		super( s, e);
	}
}
