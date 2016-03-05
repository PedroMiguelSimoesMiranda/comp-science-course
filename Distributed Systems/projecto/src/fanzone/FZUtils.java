package fanzone;

public class FZUtils
{
	public static String getString( String str, int size) {
		if( str.length() <= size)
			return str;
		else
			return str.substring(0, size - 3) + "...";
	}
}
