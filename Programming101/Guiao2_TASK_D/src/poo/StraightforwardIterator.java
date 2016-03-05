package poo;
/**
 * 
 * @author Pedro 28289
 * 
 *
 */
public class StraightforwardIterator extends AbstractIterator {

	private PlayList playList;
	private int currentMusic;

	public StraightforwardIterator (PlayList pl){
		super(pl);
		playList=pl;
		currentMusic=0;
	}


	public boolean hasNext(){
		return currentMusic<playList.getSize();
	}

	public Music next(){
		Music m=null;
		m=playList.elementAt(currentMusic++);
		return m;
	}
}