package poo;

public abstract class AbstractIterator {

	private PlayList _playList;


	public AbstractIterator(PlayList pl) {
		_playList = pl;

	}

	public abstract boolean hasNext();
	public abstract Music next();





}

