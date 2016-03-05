package poo;
/**
 * 
 * @author Pedro 
 * 28289
 * A classe AbstractIterator é uma classe super que vai delegar os seus metodos para as classes
 * descendentes dos iteradores
 */
public abstract class AbstractIterator {

	private PlayList _playList;


	/**
	 * @param pl
	 */
	public AbstractIterator(PlayList pl) {
		_playList = pl;

	}

	public abstract boolean hasNext();
	public abstract Music next();





}

