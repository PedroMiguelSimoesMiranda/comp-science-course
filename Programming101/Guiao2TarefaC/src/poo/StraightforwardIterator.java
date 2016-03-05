
package poo;

public class StraightforwardIterator extends AbstractIterator {

	private PlayList playList;
	private int currentMusic;

	public StraightforwardIterator (PlayList pl){
		super(pl);
		playList=pl;
		currentMusic=0;
	}


	public boolean hasNext(){
		return currentMusic<playList.capacity();
	}

	public Music next(){
		Music m=null;
		if(hasNext())
			m=playList.elementAt(currentMusic++);
		return m;
	}

}
