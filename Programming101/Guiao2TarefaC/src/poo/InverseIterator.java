package poo;

public class InverseIterator extends AbstractIterator {


	private PlayList playList;
	private int currentMusic;

	public  InverseIterator(PlayList pl){
		super(pl);
		playList=pl;
		currentMusic=playList.capacity();
	}

	public boolean hasNext(){
		return currentMusic>=0;
	}

	public Music next(){
		Music m=null;
		if(hasNext())
			m=playList.elementAt(currentMusic--);
		return m;
	}





}